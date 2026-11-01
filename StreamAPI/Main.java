import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toMap;

public class Main{
    record Car(String type, String manufacture, String model, Integer engineCapacity){}
    public static void main (String []args){
        List <Car> cars = List.of(
            new Car("sedan", "BMW", "E39", 4900),
            new Car("sedan", "BMW", "E60", 5000),
            new Car("sedan", "BMW", "E63", 5000),
            new Car("hatchback", "BMW", "z1", 2000),
            new Car("crossover", "Hyundai", "kona", 2000),
            new Car("hatchback", "Volkswagen", "mk7.5", 2000)
        
        );

        List<Car> hatchbackCars = cars.stream().filter(car -> car.type.equals("hatchback")).toList();
        List<String> belong = cars.stream().map(car -> car.manufacture).toList();
        List<String> typeAndModel = cars.stream().flatMap(car -> Stream.of(car.type, car.model)).toList();
        Map<Boolean, List<Car>> partitionedCars = cars.stream().collect(partitioningBy(car -> car.type.equals("sedan")));
        Map<String,Map<String,Integer>> carsByManufactureAndEngineCapacity =cars.stream().collect(
                    groupingBy(Car:: type ,toMap(Car::manufacture,Car:: engineCapacity) ));
        System.out.println("\nHatchback cars: " + hatchbackCars + "\nManufacturers: " +
         belong + "\nTypes and Models: " + typeAndModel + "\n partitioned cars(sedans):" + partitionedCars);
       
        
    }
}