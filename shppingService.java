import java.util.List;

public class shppingService {
    public static void process(List<ShippableItem> items) {
        double totalWeight = 0;
        System.out.println("** Shipment notice **" );
        for (ShippableItem item : items) {
            System.out.printf("%s\t%.0fg\n", item.getName(), item.getWeight() * 1000);
            totalWeight += item.getWeight();
        }

        System.out.printf("Total package weight: %.1fkg\n", totalWeight);

    }
}
