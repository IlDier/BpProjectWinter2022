import java.util.ArrayList;

public class ColdDrinks extends  Drinks{
    static class  sodapop extends Products{

        @Override
        public String get() {
            return "sodapop";
        }

        ArrayList<String> build(){
            ingredients.clear();
            ingredients.add("Soda");
            ingredients.add("Water");
            return ingredients;
        }

    }
    static class water extends Products{

        @Override
        public String get() {
            return "water";
        }

        ArrayList<String> build(){
            ingredients.clear();
            ingredients.add("Water");
            return ingredients;
        }

    }
}
