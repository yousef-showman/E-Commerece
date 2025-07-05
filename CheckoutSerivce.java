import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CheckoutSerivce {
    private static double ShippingCostPerKG =10.0;
    public static void checkout(Customer customer) {
        Cart cart = customer.getCart();

        if (cart.isEmpty()) throw new IllegalStateException("cart is empty");

        double subtotal = cart.getSubtotal();
        double weight = cart.getTotalShippingWeight();
        double shipping = Math.ceil(weight)* ShippingCostPerKG;
        double total = subtotal + shipping;

        if (total > customer.getBalance()) throw new IllegalStateException("Insufficient balance.");

        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product p = entry.getKey();
            if (p.ExpireCheck()) throw new IllegalStateException("Product expired.");
            if (entry.getValue() > p.getQuantity()) throw new IllegalStateException("Product out of stock.");
            p.reduceQuantity(entry.getValue());
        }

        customer.balanceCheck(total);

        List<ShippableItem> shippingItems = cart.getItems().entrySet().stream()
                .filter(e -> e.getKey().ShipCheck())
                .map(e -> new ShippingItem(e.getValue() + "x " + e.getKey().getName(),
                        e.getKey().getWeight() * e.getValue()))
                .collect(Collectors.toList());

        if (!shippingItems.isEmpty()) shppingService.process(shippingItems);

        System.out.println("** Checkout receipt **");
        cart.getItems().forEach((product, qty) ->
                System.out.printf("%dx %s\t%.0f\n", qty, product.getName(), product.getPrice() * qty));
        System.out.println("-------------------------");
        System.out.printf("Subtotal\t%.0f\n", subtotal);
        System.out.printf("Shipping\t%.0f\n", shipping);
        System.out.printf("Amount\t\t%.0f\n", total);
        System.out.printf("Balance Left\t%.0f\n", customer.getBalance());

        cart.clear();
    }
}