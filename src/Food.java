import java.util.ArrayList;

public class Food extends Products {
    static class pizza extends Products{

        @Override
        public String get() {
            return "pizza";
        }

        ArrayList<String> build(){
            ingredients.clear();
            ingredients.add("Potato");
            ingredients.add("Vegetable");
            ingredients.add("Bread");
            ingredients.add("Meat");
            ingredients.add("Cheese");
            return ingredients;
        }

    }
    static class burger extends Products{
        @Override
        public String get() {
           return "burger";
        }

        ArrayList<String> build(){
            ingredients.clear();
            ingredients.add("Meat");
            ingredients.add("Bread");
            ingredients.add("Potato");
            return ingredients;
        }

    }
    static class steak extends Products{
        @Override
        public String get() {
        return "steak";
        }

        ArrayList<String> build(){
            ingredients.clear();
            ingredients.add("Flour");
            ingredients.add("Meat");
            return ingredients;
        }

    }
    static class friedchicken extends Products{
        @Override
        public String get() {
           return "friedchicken";
        }

        ArrayList<String> build(){
            ingredients.clear();
            ingredients.add("Chicken");
            ingredients.add("Potato");
            ingredients.add("Flour");
            ingredients.add("Egg");
            return ingredients;
        }

    }
}
