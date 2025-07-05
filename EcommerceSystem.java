import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EcommerceSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        List<Product> products = new ArrayList<>();
        Customer customer = new Customer("Yousef", 1000);
        Producer producer = new Producer("Fawry");

        while (true) {
            System.out.println("Main Menu:");
            System.out.println("1. Login as Customer");
            System.out.println("2. Login as Producer");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();

            if (choice == 1) {
                customerMenu(scanner, customer, products);
            } else if (choice == 2) {
                producerMenu(scanner, producer, products);
            } else {
                break;
            }
        }
    }

    static void customerMenu(Scanner scanner, Customer customer, List<Product> products) {
        while (true) {
            System.out.println("\nCustomer Menu:");
            System.out.println("1. View Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. Checkout");
            System.out.println("4. Exit");
            int c = scanner.nextInt();
            scanner.nextLine();

            if (c == 1) {
                for (int i = 0; i < products.size(); i++) {
                    Product p = products.get(i);
                    System.out.printf("%d. %s - %.2f LE - %d in stock\n", i + 1, p.getName(), p.getPrice(), p.getQuantity());
                }
            } else if (c == 2) {
                System.out.print("Enter product number: ");
                int index = scanner.nextInt() - 1;
                System.out.print("Enter quantity: ");
                int qty = scanner.nextInt();
                scanner.nextLine();
                if (index >= 0 && index < products.size()) {
                    try {
                        customer.getCart().addProduct(products.get(index), qty);
                        System.out.println("Added successfully.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
            } else if (c == 3) {
                try {
                    CheckoutSerivce.checkout(customer);
                } catch (Exception e) {
                    System.out.println("Checkout failed: " + e.getMessage());
                }
            } else break;
        }
    }

    static void producerMenu(Scanner scanner, Producer producer, List<Product> products) {
        while (true) {
            System.out.println("\nProducer Menu:");
            System.out.println("1. Add Product");
            System.out.println("2. View Owned Products");
            System.out.println("3. Exit");
            int c = scanner.nextInt();
            scanner.nextLine();

            if (c == 1) {
                try {
                    System.out.print("Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Price: ");
                    double price = Double.parseDouble(scanner.nextLine());

                    System.out.print("Quantity: ");
                    int qty = Integer.parseInt(scanner.nextLine());

                    System.out.print("Has Expiry? (true/false): ");
                    boolean hasExpiry = Boolean.parseBoolean(scanner.nextLine());

                    LocalDate expiry = null;
                    if (hasExpiry) {
                        System.out.print("Enter expiry date (YYYY-MM-DD): ");
                        String dateStr = scanner.nextLine();
                        expiry = LocalDate.parse(dateStr);  // throws exception if invalid
                    }

                    System.out.print("Shippable? (true/false): ");
                    boolean shippable = Boolean.parseBoolean(scanner.nextLine());

                    double weight = 0;
                    if (shippable) {
                        System.out.print("Enter weight (in kg): ");
                        weight = Double.parseDouble(scanner.nextLine());
                    }

                    Product p = new Product(name, price, qty, hasExpiry, expiry, shippable, weight);
                    producer.getProducts().add(p);
                    products.add(p);
                    System.out.println("✅ Product added.");

                } catch (Exception e) {
                    System.out.println("❌ Error: " + e.getMessage());
                    scanner.nextLine();
                }

            } else if (c == 2) {
                for (Product p : producer.getProducts()) {
                    System.out.println(p.getName() + " - Qty: " + p.getQuantity());
                }
            } else break;
        }
    }

}
