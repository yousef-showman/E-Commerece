import java.sql.Time;
import java.time.LocalDate;

public class Product {
    private String name;
    private double price;
    private int quantity;
    private boolean expirable;
    private LocalDate exp_date;
    private boolean shippable;
    private double weight;
    Product(String name, double price, int quantity, boolean expirable, LocalDate exp_date, boolean shippable, double weight) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expirable = expirable;
        this.exp_date = exp_date;
        this.shippable = shippable;
        this.weight = weight;

    }
}
