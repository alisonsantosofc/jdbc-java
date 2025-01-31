package com.app;

import java.util.Date;
import java.util.List;

import com.app.models.entities.Department;
import com.app.models.entities.Seller;
import com.app.models.repositories.RepositoriesProvider;
import com.app.models.repositories.SellerRepository;

public class App {
  public static void main(String[] args) {
    SellerRepository sellerRepository = RepositoriesProvider.createSellerRepository();

    System.out.println("=== Test #1: SellerRepository findById() ===");
    Seller sellerFounded = sellerRepository.findById(3);
    System.out.println(sellerFounded);
    System.out.println();

    System.out.println("=== Test #2: SellerRepository findByDepartmentId() ===");
    List<Seller> sellers = sellerRepository.findByDepartmentId(2);
    for (Seller seller : sellers) {
      System.out.println(seller);
    }
    System.out.println();

    System.out.println("=== Test #3: SellerRepository findAll() ===");
    sellers = sellerRepository.findAll();
    for (Seller seller : sellers) {
      System.out.println(seller);
    }
    System.out.println();

    System.out.println("=== Test #4: SellerRepository create() ===");
    Department department = new Department(2, null);
    Seller newSeller = new Seller(
      null, 
      "John Doe", 
      "john@email.com", 
      new Date(), 
      4200.0, 
      department
    );
    sellerRepository.create(newSeller);
    System.out.println("Created! New seller id: " + newSeller.getId());
    System.out.println();
  }
}
