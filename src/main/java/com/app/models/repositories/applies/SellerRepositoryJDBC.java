package com.app.models.repositories.applies;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Department department = newDepartment(resultSet);
        Seller seller = newSeller(resultSet, department);

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

  @Override
  public List<Seller> findByDepartmentId(Integer departmentId ) {
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      statement = connection.prepareStatement(
        "SELECT seller.*,department.name as departmentName " +
        "FROM seller INNER JOIN department " +
        "ON seller.departmentId = department.id " +
        "WHERE departmentId = ? " +
        "ORDER BY name"
      );
      statement.setInt(1, departmentId);
      resultSet = statement.executeQuery();

      List<Seller> sellers = new ArrayList<Seller>();
      Map<Integer, Department> departmentMap = new HashMap<>();
      
      while (resultSet.next()) {
        Department sellersDepartment = departmentMap.get(resultSet.getInt("departmentId"));

        if (sellersDepartment == null) {
          sellersDepartment = newDepartment(resultSet);
          departmentMap.put(resultSet.getInt("departmentId"), sellersDepartment);
        }

        Seller seller = newSeller(resultSet, sellersDepartment);
        sellers.add(seller);
      }

      return sellers;
    } catch (SQLException e) {
      throw new DBException(e.getMessage());
    } finally {
      DB.closeStatement(statement);
      DB.closeResultSet(resultSet);
    }
  }

  private Department newDepartment(ResultSet resultSet) throws SQLException {
    Department department = new Department();
    department.setId(resultSet.getInt("departmentId"));
    department.setName(resultSet.getString("departmentName"));

    return department;
  }

  private Seller newSeller(ResultSet resultSet, Department department) throws SQLException {
    Seller seller = new Seller();
    seller.setId(resultSet.getInt("id"));
    seller.setName(resultSet.getString("name"));
    seller.setEmail(resultSet.getString("email"));
    seller.setBaseSalary(resultSet.getDouble("baseSalary"));
    seller.setBirthDate(resultSet.getDate("birthDate"));
    seller.setDepartment(department);

    return seller;
  }
}
