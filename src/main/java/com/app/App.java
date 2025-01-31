package com.app;

import java.util.Date;

import com.app.models.entities.Department;
import com.app.models.entities.Seller;

public class App {
  public static void main(String[] args) {
    Department department = new Department(1, "Books");
    Seller seller = new Seller(
      1, 
      "John Doe", 
      "john@email.com", 
      new Date(), 
      3000.0, 
      department
    );

    System.out.println(seller);
  }
}
