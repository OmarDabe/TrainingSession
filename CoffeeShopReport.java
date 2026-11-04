import java.util.*;
import java.util.stream.*;

public class CoffeeShopReport {

    record Order(String customer, String drink, String category, int price, int quantity) {
        int total() { return price * quantity; }
    }

    public static void main(String[] args) {

        List<Order> orders = List.of(
            new Order("Layla",  "Latte",      "HOT",  16, 2),
            new Order("Sam",    "Espresso",   "HOT",  10, 1),
            new Order("Hassan", "Iced Tea",   "COLD", 12, 3),
            new Order("Layla",  "Mocha",      "HOT",  18, 1),
            new Order("Ali",    "Iced Latte", "COLD", 17, 2),
            new Order("Sam",    "Latte",      "HOT",  16, 1)
        );

        // 1. filter + map: orders worth more than 20 NIS
        System.out.println("--- 1. Big orders ---");
        List<String> big = orders.stream()
            .filter(o -> o.total() > 20)
            .map(o -> o.drink() + " (" + o.total() + " NIS)")
            .toList();
        System.out.println(big);

        // 2. sorting: top 3 orders, highest total first
        System.out.println("\n--- 2. Top 3 orders ---");
        orders.stream()
            .sorted(Comparator.comparingInt(Order::total).reversed())
            .limit(3)
            .forEach(o -> System.out.println(o.customer() + " - " + o.total() + " NIS"));

        // 3. grouping + counting: orders per category
        System.out.println("\n--- 3. Orders per category ---");
        Map<String, Long> perCategory = orders.stream()
            .collect(Collectors.groupingBy(Order::category, TreeMap::new, Collectors.counting()));
        System.out.println(perCategory);

        // 4. grouping + summing: money spent per customer
        System.out.println("\n--- 4. Money spent per customer ---");
        Map<String, Integer> spent = orders.stream()
            .collect(Collectors.groupingBy(Order::customer, TreeMap::new, Collectors.summingInt(Order::total)));
        System.out.println(spent);

        // 5. grouping + mapping: drinks per customer
        System.out.println("\n--- 5. Drinks per customer ---");
        Map<String, List<String>> drinks = orders.stream()
            .collect(Collectors.groupingBy(Order::customer, TreeMap::new,
                     Collectors.mapping(Order::drink, Collectors.toList())));
        drinks.forEach((name, list) -> System.out.println(name + " -> " + list));

        // 6. total revenue
        System.out.println("\n--- 6. Total revenue ---");
        int revenue = orders.stream().mapToInt(Order::total).sum();
        System.out.println(revenue + " NIS");
    }
}