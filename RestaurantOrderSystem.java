import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RestaurantOrderSystem {
    private static Map<String, Double> menu = new HashMap<>();
    private static Map<String, Integer> order = new HashMap<>();

    public static void main(String[] args) {
        initializeMenu();

        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\nRestaurant Menu:");
            displayMenu();
            System.out.println("3. Place Order");
            System.out.println("4. Display Order and Total");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addMenuDetails();
                    break;
                case 2:
                    displayMenu();
                    break;
                case 3:
                    placeOrder(scanner);
                    break;
                case 4:
                    displayOrder();
                   
                    calculateTotal();
                    break;
                case 5:
                    System.out.println("Exiting the restaurant. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        } while (choice != 6);
    }

    private static void initializeMenu() {
        menu.put("Burger", 25.0);
        menu.put("Pizza", 150.0);
        menu.put("Salad", 10.0);
        menu.put("Chicken Biryani", 150.0);
        menu.put("Chicken Chilli", 60.0);
        menu.put("Veg Thali", 100.0);
    }

    private static void addMenuDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter item name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter item price: ");
        double itemPrice = scanner.nextDouble();
        menu.put(itemName, itemPrice);
        System.out.println(itemName + " has been added to the menu.");
        System.out.println();
    }

    private static void displayMenu() {
        System.out.println("Menu Details:");
        for (Map.Entry<String, Double> entry : menu.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue() + " rupees");
        }
        System.out.println();
    }

    private static void placeOrder(Scanner scanner) {
        System.out.print("Please enter the item name to order (or type 'done' to finish): ");
        String itemName = scanner.next();

        while (!itemName.equals("done")) {
            if (menu.containsKey(itemName)) {
                System.out.print("Enter quantity: ");
                int quantity = scanner.nextInt();
                order.put(itemName, order.getOrDefault(itemName, 0) + quantity);
            } else {
                System.out.println("Item not found in the menu.");
            }
            System.out.print("Enter the next item name to order (or type 'done' to finish): ");
            itemName = scanner.next();
        }
         System.out.println();
    }

    private static void displayOrder() {
        System.out.println("Your Order:");
        for (Map.Entry<String, Integer> entry : order.entrySet()) {
            System.out.println(entry.getKey() + " - Quantity: " + entry.getValue());
        }
         System.out.println();
    }

    private static void calculateTotal() {
        double total = 0.0;
        for (Map.Entry<String, Integer> entry : order.entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            double itemPrice = menu.get(itemName);
            total += quantity * itemPrice;
        }
        System.out.println("Total Bill: " + total + " rupees");
        System.out.println();
    }
}