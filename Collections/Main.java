import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Main{



public static void main(String []args){

List<String> cars = new ArrayList<>();

cars.add("BMW");
cars.add("Mercedes");
cars.add("Porsche");
cars.add("Volkswagen");

cars.remove("Porsche");
cars.remove(1);
cars.set(1, "Audi");




for(String car:cars){
    System.out.println(car);
}

System.out.println(cars.size()+"\n");
System.out.println(cars.contains("BMW"));
System.out.println(cars.indexOf("Audi"));
System.out.println(cars.isEmpty()+"\n\n");


//--------------------------------------- Set implementation --------------------------------






//--------------------------------------- Map implementation --------------------------------
Map<String,Integer> sal = new HashMap<>();
sal.put("ahmad", 600);
sal.put("ali", 700);
sal.put("ghalib", 800);
sal.put("omar", 90);

System.out.println(sal.get("ahmad"));
System.out.println(sal.size());
System.out.println(sal.containsKey("fouad"));
System.out.println(sal.containsValue(90));




}

}