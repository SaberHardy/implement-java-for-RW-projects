package main.java;

import main.java.repository.FileInventoryRepository;
import main.java.repository.InventoryRepository;
import main.java.service.InventoryService;
import main.java.models.Product;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InventoryRepository repository = new FileInventoryRepository();
        InventoryService service = new InventoryService(repository);
        Scanner scanner = new Scanner(System.in);

        try {
            repository.loadFromFile("inventory.dat");
        } catch (Exception e) {
            System.out.println("No existing Inventory found!");
        }

        while (true) {
            System.out.println("Please enter your choice:\n1. Add Product\n2. Find Product\n3. Remove Product\n4. Generate Report\n5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // To consume new line.

            switch (choice) {
                case 1 -> addProduct(service, scanner);
                case 2 -> findProductItem(service, scanner);
                case 3 -> removeProductItem(service, scanner);
                case 4 -> generateReportItem(service);
            }
        }
    }

    private static void addProduct(InventoryService service, Scanner scanner) {
        System.out.println("Enter ID: ");
        String id = scanner.nextLine();

        System.out.println("Enter name: ");
        String name = scanner.nextLine();

        System.out.println("Enter price: ");
        double price = scanner.nextDouble();

        System.out.println("Enter quantity: ");
        int quantity = scanner.nextInt();

        service.addProduct(new Product(id, name, price, quantity));
    }

    private static void findProductItem(InventoryService service, Scanner scanner) {
        System.out.print("Enter product ID: ");
        String id = scanner.nextLine();

        service.findProduct(id).ifPresentOrElse(
                p -> System.out.printf("Found: %s - $%.2f (%d in stock)%n",
                        p.getName(), p.getPrice(), p.getQuantity()),
                () -> System.out.println("Product not found")
        );
    }

    private static void removeProductItem(InventoryService service, Scanner scanner) {
        System.out.print("Enter product ID to remove: ");
        String id = scanner.nextLine();
        service.removeProduct(id);
    }

    private static void generateReportItem(InventoryService service) {
        var report = service.generateReport();
        System.out.printf("""
                Inventory Report:
                Total Products: %d
                Out of Stock: %d
                Value by Product: %s
                """, report.totalProducts(), report.outOfStockItems(), report.valueByCategory());
    }
}