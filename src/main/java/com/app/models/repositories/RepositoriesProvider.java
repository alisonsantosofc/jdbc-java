package com.app.models.repositories;

import com.app.models.repositories.applies.SellerRepositoryJDBC;

public class RepositoriesProvider {
  public static SellerRepository createSellerRepository() {
    return new SellerRepositoryJDBC();
  }
}
