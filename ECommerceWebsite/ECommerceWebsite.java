import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class ShoppingCart {
    private List<Product> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void addItem(Product product) {
        items.add(product);
    }

    public void removeItem(Product product) {
        items.remove(product);
    }

    public List<Product> getItems() {
        return items;
    }

    public double getTotal() {
        double total = 0;
        for (Product item : items) {
            total += item.getPrice();
        }
        return total;
    }
}

public class ECommerceWebsite {
    private List<Product> products;
    private ShoppingCart cart;

    public ECommerceWebsite() {
        products = new ArrayList<>();
        cart = new ShoppingCart();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void displayProducts() {
        System.out.println("Available Products:");
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.println((i + 1) + ". " + product.getName() + " - $" + product.getPrice());
        }
    }

    public void addToCart(int index) {
        if (index >= 0 && index < products.size()) {
            Product selectedProduct = products.get(index);
            cart.addItem(selectedProduct);
            System.out.println(selectedProduct.getName() + " added to cart.");
        } else {
            System.out.println("Invalid product index.");
        }
    }

    public void displayCart() {
        List<Product> items = cart.getItems();
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Your Cart:");
            for (Product item : items) {
                System.out.println("- " + item.getName() + " - $" + item.getPrice());
            }
            System.out.println("Total: $" + cart.getTotal());
        }
    }

    public static void main(String[] args) {
        ECommerceWebsite website = new ECommerceWebsite();

        // Adding some sample products
        website.addProduct(new Product("Smartphone", 499.99));
        website.addProduct(new Product("Laptop", 899.99));
        website.addProduct(new Product("Headphones", 99.99));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. View Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    website.displayProducts();
                    break;
                case 2:
                    System.out.print("Enter the index of the product to add to cart: ");
                    int productIndex = scanner.nextInt() - 1;
                    scanner.nextLine(); // Consume newline
                    website.addToCart(productIndex);
                    break;
                case 3:
                    website.displayCart();
                    break;
                case 4:
                    System.out.println("Checkout - Total: $" + website.cart.getTotal());
                    System.out.println("Thank you for shopping with us!");
                    System.exit(0);
                case 5:
                    System.out.println("Exiting program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
        }
    }
}
