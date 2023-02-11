import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Cafe extends Store{
    ArrayList<String> Cafeproducts=new ArrayList<>();
    ArrayList<Order> orderList=new ArrayList<>();

     Cafe(){
        Cafeproducts.add("chocolatecake");
        Cafeproducts.add("vanillacake");
        Cafeproducts.add("icecream");
        Cafeproducts.add("tea");
        Cafeproducts.add("coffee");
        Cafeproducts.add("hotchocolate");
        Cafeproducts.add("water");
        Cafeproducts.add("sodapop");
    }


    @Override
    public void getMenu() {

        String s = "\033[0;91m"+"Cafe  {" + "\n" + "\t Drinks{\n\t\t\tColdDrinks{\n \t\t\t\tsodapop" + available(Conv("sodapop"), storg) + "\n\t\t\t\twater" + available(Conv("water"), storg) + "\n\t\t\t}\n\t\t\tHotDrinks{\n\t\t\t\ttea"+available(Conv("tea"), storg)+"\n\t\t\t\tcoffee"+available(Conv("coffee"), storg)+"\n\t\t\t\thotchocolate"+available(Conv("hotchocolate"), storg)+"\n\t\t\t}\n\t\t\tDessert{\n\t\t\t\tvanillacake"+available(Conv("vanillacake"), storg)+"\n\t\t\t\ticecream"+available(Conv("icecream"), storg)+"\n\t\t\t}\n\t\t}\n\t}" + "\033[0;34m";
       System.out.println(s+"\033[0;34m");
    }
    @Override
    public Order AddOrder(String in, User user, Storage storg, File orderu) {
     in=in.replaceAll("\\s+"," ");
     String[] x=in.split(" ");
     boolean check=true;
     Order order=new Order();
     if(x.length>2){
         for(int i=2;i<x.length;i++){
             if(Cafeproducts.contains(x[i].toLowerCase())){
                 order.Product.add(Conv(x[i]));
             }
             else{
                 check=false;
                 break;
             }
         }
         if(check){
              if(storg.Check(order)){
                  order.user=user;
                  order.Id=LocalDateTime.now().toString();
                  storg.consume(order);
                  try {
                      FileWriter fw=new FileWriter(orderu,true);
                      BufferedWriter bw=new BufferedWriter(fw);
                      bw.write(user.name+" Ordered:\t");
                      for(Products value:order.Product) {
                       bw.write(value.get()+" || ");
                      }bw.newLine();
                      bw.close();
                  }catch (IOException e){
                      e.printStackTrace();
                  }
                  return order;
              }
              else {

                  System.out.println("Sorry,We has lack of ingredient for your Order");
                  return null;
              }
         }
         else {
             System.out.println("You Ordered something that we don't have");
             return null;
         }
     }
     else {
         System.out.println("Please Enter an Order!");
         return null;
     }
    }

    @Override
    public void RecentOrders(User user) {
         boolean check=true;
      for(Order value:orderList){
         if(value.user==user) {
             value.OrderDefine();
             check=false;
             System.out.println("\t---------------");
         }
      }
      if(check) System.out.println("No Order has been added yet!");
    }
}
