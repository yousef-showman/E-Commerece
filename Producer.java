import java.util.List;

public class Producer {
    private String producerName;
    private List<Product> products;
    public Producer(String producerName, List<Product> products) {
        this.producerName = producerName;
        this.products = products;
    }
}
