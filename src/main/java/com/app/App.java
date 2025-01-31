package com.app;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.app.models.entities.Department;
import com.app.models.entities.Seller;
import com.app.models.repositories.DepartmentRepository;
import com.app.models.repositories.RepositoriesProvider;
import com.app.models.repositories.SellerRepository;

public class App {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Seller tests
    SellerRepository sellerRepository = RepositoriesProvider.createSellerRepository();

    System.out.println("=== TEST #1: SellerRepository findById() ===");
    Seller sellerFounded = sellerRepository.findById(3);
    System.out.println(sellerFounded);
    System.out.println();

    System.out.println("=== TEST #2: SellerRepository findByDepartmentId() ===");
    List<Seller> sellers = sellerRepository.findByDepartmentId(2);
    for (Seller seller : sellers) {
      System.out.println(seller);
    }
    System.out.println();

    System.out.println("=== TEST #3: SellerRepository findAll() ===");
    sellers = sellerRepository.findAll();
    for (Seller seller : sellers) {
      System.out.println(seller);
    }
    System.out.println();

    System.out.println("=== TEST #4: SellerRepository create() ===");
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
    System.out.println("Created successfully! New id: " + newSeller.getId());
    System.out.println();

    System.out.println("=== TEST #5: SellerRepository update() ===");
    sellerFounded = sellerRepository.findById(1);
    sellerFounded.setName("Bob Brown");
    sellerRepository.update(sellerFounded);
    System.out.println("Updated successfully!");
    System.out.println();

    System.out.println("=== TEST #6: SellerRepository delete() ===");
    System.out.print("Enter seller id for delete test: ");
    int sellerDeleteId = scanner.nextInt();
    sellerRepository.deleteById(sellerDeleteId);
    System.out.println("Deleted successfully!");
    System.out.println();

    // Department tests
    DepartmentRepository departmentRepository = RepositoriesProvider.createDepartmentRepository();

		System.out.println("=== TEST #1: DepartmentRepository findById() =======");
		Department dep = departmentRepository.findById(1);
		System.out.println(dep);
		
		System.out.println("\n=== TEST #2: DepartmentRepository findAll() =======");
		List<Department> list = departmentRepository.findAll();
		for (Department d : list) {
			System.out.println(d);
		}

		System.out.println("\n=== TEST #3: DepartmentRepository create() =======");
		Department newDepartment = new Department(null, "Music");
		departmentRepository.create(newDepartment);
		System.out.println("Created successfully! New id: " + newDepartment.getId());

		System.out.println("\n=== TEST #4: DepartmentRepository update() =======");
		Department dep2 = departmentRepository.findById(1);
		dep2.setName("Food");
		departmentRepository.update(dep2);
		System.out.println("Updated successfully");
		
		System.out.println("\n=== TEST #5: DepartmentRepository delete() =======");
		System.out.print("Enter id for delete test: ");
		int id = scanner.nextInt();
		departmentRepository.deleteById(id);
		System.out.println("Deleted successfully");

    scanner.close();
  }
}
