package com.app;

import com.app.models.entities.Department;

public class App {
  public static void main(String[] args) {
    Department department = new Department(1, "Books");
    System.out.println(department);
  }
}
