import java.util.ArrayList;

public class HotDrinks extends Drinks{
    static class tea extends Products{
        @Override
        public String get() {
            return "tea";
        }

        ArrayList<String> build(){
            ingredients.clear();
            ingredients.add("Tea");
            ingredients.add("Water");
            return ingredients;
        }

    }
    static class coffee extends Products{
        @Override
        public String get() {
    return "coffee";
        }

        ArrayList<String> build(){
            ingredients.clear();
            ingredients.add("Tea");
            ingredients.add("Water");
            ingredients.add("Coffee");
            return ingredients;
        }

    }
    static class hotchocolate extends Products{
        @Override
        public String get() {
           return "hotchocolate";
        }

        ArrayList<String> build(){
            ingredients.clear();
            ingredients.add("Chocolate");
            ingredients.add("Coffee");
            ingredients.add("Water");
            return ingredients;
        }

    }
}
