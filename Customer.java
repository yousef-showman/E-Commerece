public class Customer {
    private String name;
    private Double balance;
    private Cart cart;
    public Customer(String name, Double balance, Cart cart) {
        this.name = name;
        this.balance = balance;
        this.cart = cart;
    }
}
