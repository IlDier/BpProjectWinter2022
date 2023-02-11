import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

abstract class Store {

    Storage storg;
    void getStorage(Storage storg){
        this.storg=storg;
    }
   public String available(Products s,Storage storg) {
        if(!storg.ProductCheck(s)) return " (Not available)";
        else return "";
   }
   public void getMenu(){ }


    public  Order AddOrder(String in, User user, Storage storg, File orderu){ return null;}

    public void RecentOrders(User user){}
   public  Products Conv(String input){
        input=input.toLowerCase();
        if(input.equals("pizza")){
            return new Food.pizza();
        }
        else if(input.equals("burger")){
            return new Food.burger();
        }
        else if(input.equals("steak")){
            return new Food.steak();
        }
        else if(input.equals("friedchicken")){
            return new Food.friedchicken();
        }
        else if(input.equals("coffee")){
            return new HotDrinks.coffee();
        }
        else if(input.equals("tea")){
            return new HotDrinks.tea();
        }
        else if(input.equals("hotchocolate")){
            return new HotDrinks.hotchocolate();
        }
        else if(input.equals("sodapop")){
            return new ColdDrinks.sodapop();
        }
        else if(input.equals("water")){
            return new ColdDrinks.water();
        }
        else if(input.equals("chocolatecake")){
            return new Dessert.chocolatecake();
        }
        else if(input.equals("vanillacake")){
            return new Dessert.vanillacake();
        }
        else if(input.equals("icecream")){
            return new Dessert.icecream();
        }
        else if(input.equals("salad")){
            return new Appetizer.salad();
        }
        else if(input.equals("frenchfries")){
            return new Appetizer.frenchfries();
        }
        else return null;
    }
}
