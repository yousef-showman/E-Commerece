
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product,Integer> items= new HashMap<>();
    private Customer owner;


    public Cart( Customer owner) {
        this.owner = owner;

    }

    public void addProduct(Product product, int quantity) {
        if (product.ExpireCheck()) throw new IllegalArgumentException("Product is expired");
        if (quantity <= 0 || quantity > product.getQuantity()) throw new IllegalArgumentException("Invalid quantity.");
        items.merge(product, quantity, Integer::sum);
    }
    public double getSubtotal() {
        return items.entrySet().stream().mapToDouble(e -> e.getKey().getPrice() * e.getValue()).sum();
    }

    public double getTotalShippingWeight() {
        return items.entrySet().stream()
                .filter(e -> e.getKey().ShipCheck())
                .mapToDouble(e -> e.getKey().getWeight() * e.getValue())
                .sum();
    }

    public boolean isEmpty() { return items.isEmpty(); }
    public Map<Product, Integer> getItems() { return items; }
    public void clear() { items.clear(); }
}



