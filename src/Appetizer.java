import java.util.ArrayList;

public class Appetizer {
    static class salad extends Products{
        @Override
        public String get() {
            return "salad";
        }

        ArrayList<String> build(){
            ingredients.clear();
            ingredients.add("Vegetable");
            ingredients.add("Potato");
            return ingredients;
        }

    }
    static class frenchfries extends Products{
        @Override
        public String get() {
            return "frenchfries";
        }

        ArrayList<String> build(){
            ingredients.clear();
            ingredients.add("Flour");
            ingredients.add("Potato");
            return ingredients;
        }

    }
}
