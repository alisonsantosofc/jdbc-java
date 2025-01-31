package com.app;

import java.util.List;

import com.app.models.entities.Seller;
import com.app.models.repositories.RepositoriesProvider;
import com.app.models.repositories.SellerRepository;

public class App {
  public static void main(String[] args) {
    SellerRepository sellerRepository = RepositoriesProvider.createSellerRepository();

    System.out.println("=== Test #1: Seller findById() ===");
    Seller sellerFounded = sellerRepository.findById(3);
    System.out.println(sellerFounded);
    System.out.println();

    System.out.println("=== Test #2: Seller findByDepartmentId() ===");
    List<Seller> sellers = sellerRepository.findByDepartmentId(2);
    for (Seller seller : sellers) {
      System.out.println(seller);
    }
    System.out.println();
  }
}
