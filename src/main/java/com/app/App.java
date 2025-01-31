package com.app;

import java.util.Date;

import com.app.models.entities.Department;
import com.app.models.entities.Seller;
import com.app.models.repositories.RepositoriesProvider;
import com.app.models.repositories.SellerRepository;

public class App {
  public static void main(String[] args) {
    SellerRepository sellerRepository = RepositoriesProvider.createSellerRepository();

    Seller seller = sellerRepository.findById(3);

    System.out.println(seller);
  }
}
