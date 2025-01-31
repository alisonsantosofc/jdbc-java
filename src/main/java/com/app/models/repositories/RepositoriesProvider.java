package com.app.models.repositories;

import com.app.db.DB;
import com.app.models.repositories.applies.SellerRepositoryJDBC;

public class RepositoriesProvider {
  public static SellerRepository createSellerRepository() {
    return new SellerRepositoryJDBC(DB.getConnection());
  }
}
