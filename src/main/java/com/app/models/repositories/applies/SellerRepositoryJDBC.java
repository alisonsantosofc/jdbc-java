package com.app.models.repositories.applies;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.app.db.DB;
import com.app.db.DBException;
import com.app.models.entities.Department;
import com.app.models.entities.Seller;
import com.app.models.repositories.SellerRepository;

public class SellerRepositoryJDBC implements SellerRepository{
  private Connection connection;

  public SellerRepositoryJDBC(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void create(Seller seller) {
    // TODO Auto-generated method stub
  }

  @Override
  public void update(Seller seller) {
    // TODO Auto-generated method stub
  }

  @Override
  public void deleteById(Integer id) {
    // TODO Auto-generated method stub
  }

  @Override
  public Seller findById(Integer id) {
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      statement = connection.prepareStatement(
        "SELECT seller.*,department.name as departmentName " +
        "FROM seller INNER JOIN department " +
        "ON seller.departmentId = department.id " +
        "WHERE seller.id = ?"
      );
      statement.setInt(1, id);
      resultSet = statement.executeQuery();

      if (resultSet.next()) {
        Department department = new Department();
        department.setId(resultSet.getInt("departmentId"));
        department.setName(resultSet.getString("departmentName"));

        Seller seller = new Seller();
        seller.setId(resultSet.getInt("id"));
        seller.setName(resultSet.getString("name"));
        seller.setEmail(resultSet.getString("email"));
        seller.setBaseSalary(resultSet.getDouble("baseSalary"));
        seller.setBirthDate(resultSet.getDate("birthDate"));
        seller.setDepartment(department);

        return seller;
      }

      return null;
    } catch (SQLException e) {
      throw new DBException(e.getMessage());
    } finally {
      DB.closeStatement(statement);
      DB.closeResultSet(resultSet);
    }
  }

  @Override
  public List<Seller> findAll() {
    // TODO Auto-generated method stub
    return null;
  }
}
