package com.app.models.repositories;

import java.util.List;

import com.app.models.entities.Department;

public interface DepartmentRepository {
  void create(Department department);
  void update(Department department);
  void deleteById(Integer id);
  Department findById(Integer id);
  List<Department> findAll();
}