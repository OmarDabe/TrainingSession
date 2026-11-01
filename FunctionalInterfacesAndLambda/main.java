import java.util.*;
import java.util.function.*;

public class Main {

    record Drink(String name, int price) {}

    // Step 6: your own functional interface
    @FunctionalInterface
    interface PriceRule {
        int change(int price);
    }

    static int priceAfter(Drink d, PriceRule rule) {
        return rule.change(d.price());
    }

    public static void main(String[] args) {

        List<Drink> menu = List.of(
            new Drink("Espresso", 10),
            new Drink("Latte", 16),
            new Drink("Mocha", 18),
            new Drink("Iced Tea", 12),
            new Drink("Water", 3)
        );
        Drink latte = menu.get(1);
        Drink espresso = menu.get(0);

        // Step 1: Predicate + lambda
        Predicate<Drink> isExpensive = d -> d.price() > 12;
        System.out.println(isExpensive.test(latte));       // true
        System.out.println(isExpensive.test(espresso));    // false

        // Step 2: Function + method reference, Function + lambda
        Function<Drink, String> getName = Drink::name;
        Function<Drink, String> label = d -> d.name() + " - " + d.price() + " NIS";
        System.out.println(getName.apply(latte));          // Latte
        System.out.println(label.apply(latte));            // Latte - 16 NIS

        // Step 3: Consumer + lambda
        Consumer<Drink> printer = d -> System.out.println(label.apply(d));
        menu.forEach(printer);

        // Step 4: Supplier + lambda
        Supplier<Drink> dailySpecial = () -> new Drink("Special", 14);
        System.out.println(dailySpecial.get());            // Drink[name=Special, price=14]

        // Step 5: BinaryOperator + method reference
        BinaryOperator<Integer> add = Integer::sum;
        int total = menu.stream().map(Drink::price).reduce(0, add);
        System.out.println(total);                         // 59

        // Step 6: custom interface with lambdas
        PriceRule halfPrice = p -> p / 2;
        PriceRule plusTip = p -> p + 2;
        System.out.println(priceAfter(latte, halfPrice));  // 8
        System.out.println(priceAfter(latte, plusTip));    // 18

        // Step 7: combining predicates
        Predicate<Drink> isCheap = isExpensive.negate();
        Predicate<Drink> expensiveMocha = isExpensive.and(d -> d.name().startsWith("M"));

        menu.stream().filter(isCheap).map(Drink::name).forEach(System.out::println);
        menu.stream().filter(expensiveMocha).map(Drink::name).forEach(System.out::println);

        // Step 8: everything together
        menu.stream()
            .filter(isExpensive)
            .map(label)
            .forEach(System.out::println);
    }
}