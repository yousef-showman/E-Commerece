
import java.util.Map;

public class Cart {
    private Map<Product,Integer> items;
    private Customer customer;


    public Cart(Map<Product,Integer> items, Customer customer) {
        this.items = items;
        this.customer = customer;

    }

}
