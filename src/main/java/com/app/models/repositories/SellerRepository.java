package com.app.models.repositories;

import java.util.List;

import com.app.models.entities.Seller;

public interface SellerRepository {
  void create(Seller seller);
  void update(Seller seller);
  void deleteById(Integer id);
  Seller findById(Integer id);
  List<Seller> findAll();
}
