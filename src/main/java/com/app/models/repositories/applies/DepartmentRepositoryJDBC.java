package com.app.models.repositories.applies;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.db.DB;
import com.app.db.DBException;
import com.app.models.entities.Department;
import com.app.models.entities.Seller;
import com.app.models.repositories.DepartmentRepository;
import com.app.models.repositories.SellerRepository;

public class DepartmentRepositoryJDBC implements DepartmentRepository{
  private Connection connection;

  public DepartmentRepositoryJDBC(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void create(Department department) {
    PreparedStatement statement = null;

    try {
      statement = connection.prepareStatement(
        "INSERT INTO department " +
        "(name) VALUES (?)",
        Statement.RETURN_GENERATED_KEYS
      );
      statement.setString(1, department.getName());

      int rowsAffected = statement.executeUpdate();

      if (rowsAffected > 0) {
        ResultSet resultSet = statement.getGeneratedKeys();

        if (resultSet.next()) {
          int id = resultSet.getInt(1);
          department.setId(id);
        }

        DB.closeResultSet(resultSet);
      } else {
        throw new DBException("Unexpected error! No rows affected.");
      }
    } catch (SQLException e) {
      throw new DBException(e.getMessage());
    } finally {
      DB.closeStatement(statement);
    }
  }

  @Override
  public void update(Department department) {
    PreparedStatement statement = null;

    try {
      statement = connection.prepareStatement(
        "UPDATE department " +
        "SET name = ? WHERE id = ?"
      );
      statement.setString(1, department.getName());
      statement.setInt(2, department.getId());

      statement.executeUpdate();
    } catch (SQLException e) {
      throw new DBException(e.getMessage());
    } finally {
      DB.closeStatement(statement);
    }
  }

  @Override
  public void deleteById(Integer id) {
    PreparedStatement statement = null;

    try {
      statement = connection.prepareStatement(
        "DELETE FROM department WHERE id = ?"
      );
      statement.setInt(1, id);

      int rowsAffected = statement.executeUpdate();

      if (rowsAffected == 0) {
        throw new DBException("Invalid department id.");
      }
    } catch (SQLException e) {
      throw new DBException(e.getMessage());
    } finally {
      DB.closeStatement(statement);
    }
  }

  @Override
  public Department findById(Integer id) {
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      statement = connection.prepareStatement(
        "SELECT department.* " +
        "FROM department " +
        "WHERE department.id = ?"
      );
      statement.setInt(1, id);
      resultSet = statement.executeQuery();

      if (resultSet.next()) {
        Department department = newDepartment(resultSet);

        return department;
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
  public List<Department> findAll() {
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      statement = connection.prepareStatement(
        "SELECT department.* " +
        "FROM department " +
        "ORDER BY name"
      );
      resultSet = statement.executeQuery();

      List<Department> departments = new ArrayList<Department>();
      
      while (resultSet.next()) {
        Department department = newDepartment(resultSet);
        departments.add(department);
      }

      return departments;
    } catch (SQLException e) {
      throw new DBException(e.getMessage());
    } finally {
      DB.closeStatement(statement);
      DB.closeResultSet(resultSet);
    }
  }

  private Department newDepartment(ResultSet resultSet) throws SQLException {
    Department department = new Department();
    department.setId(resultSet.getInt("id"));
    department.setName(resultSet.getString("name"));

    return department;
  }
}
