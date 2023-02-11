import java.util.ArrayList;

public class Dessert extends Products{


 static class  chocolatecake extends Products{



        ArrayList<String> build() {
            ingredients.add("Chocolate");
            ingredients.add("Water");
            return ingredients;
        }
    }
     static class vanillacake extends Products{
         @Override
         public String get() {
             return "vanillacake";
         }

         ArrayList<String> build(){
               ingredients.add("Vanilla");
               ingredients.add("Water");
             return ingredients;
          }

     }
     static class icecream extends Products{
         @Override
         public String get() {
             return "icecream";
         }

         ArrayList<String> build(){
               ingredients.add("Icecream");
               ingredients.add("Suger");
             return ingredients;
          }
     }

}
