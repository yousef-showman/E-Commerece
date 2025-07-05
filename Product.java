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
        if(price < 0|| quantity < 0) throw new IllegalArgumentException("price or quantity cannot be negative");
        if(expirable && exp_date.isBefore(LocalDate.now())) throw new IllegalArgumentException("expired");
        if(shippable && weight < 0) throw new IllegalArgumentException("weight cannot be negative");
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expirable = expirable;
        this.exp_date = exp_date;
        this.shippable = shippable;
        this.weight = weight;

    }
    public boolean ExpireCheck(){
        return expirable && exp_date.isBefore(LocalDate.now());
    }
    public boolean ShipCheck(){
        return shippable;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getWeight() {
        return weight;
    }
    public void reduceQuantity(int quantity) {
        if(quantity >this.quantity) throw new IllegalArgumentException("quantity cannot be greater than current quantity");
        this.quantity -= quantity;
    }
}
