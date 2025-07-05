import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Producer {
    private String producerName;
    private List<Product> products= new ArrayList<>();
    public Producer(String producerName) {
        this.producerName = producerName;
    }

    public List<Product> getProducts() {
        return products;
    }
}
