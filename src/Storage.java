import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Storage {
   String[] main = {"Flour", "Cheese", "Meat", "Bread", "Vegetable", "Chicken", "Potato", "Coffee", "Beans", "Tea", "Chocolate", "Soda", "Icecream", "Egg", "Vanilla", "Water", "Suger"};
   HashMap<String, Integer> Storage = new HashMap<>();

   File usage=new File("C:/Users/iran/IdeaProjects/BpProjectWinter2022/Usage.txt");
    Storage() {
        try {
            Scanner input= new Scanner(usage);
            while(input.hasNextLine()){
                String usge= input.nextLine();
                String[] us=usge.split(" ");
                Storage.put(us[0],Integer.parseInt(us[1]));
            }
input.close();
        }catch (
                IOException e){
            e.printStackTrace();
        }
   }
   boolean ProductCheck(Products x){
        boolean result=true;
        x.build();
          for(String value:x.ingredients){
              if(Storage.get(value)<1){
                  result=false;
                  break;
              }
          }
          return result;
   }

   void consume(int x) {
      boolean result = true;
      String lack = null;
      for (String value : main) {
         if ((Storage.get(value)+x)<0) {
            lack = value;
            result = false;
            break;
         }
      }
      if (result) {
         for (String value : main) Storage.put(value, Storage.get(value) + x);
         System.out.println("Done!");
      } else System.out.println(lack + " was not enough for your request!");
   }
   void Update(Products serv){
        serv.build();
      for(String value:serv.ingredients) Storage.put(value,Storage.get(value)+1);
   }
   void Update(Order order){
        for(Products products:order.Product) Update(products);
   }

   boolean consmue(ArrayList<String> y, ArrayList<Integer> x) {
      boolean result = true;
      for (int i = 0; i < y.size(); i++) {
         if(Storage.get(y.get(i))!=null) {
            if (Storage.get(y.get(i)) < x.get(i)) {
               result = false;
               break;
            }
         }
         else {
             result=false;
             break;
         }
      }
      if (result) {
         for (int i = 0; i < y.size()&&y.get(i)!=null; i++) Storage.put(y.get(i),Storage.get(y.get(i))-x.get(i));
      }
return result;
   }

boolean Check(Order order){
     boolean result=true;
     HashMap<String,Integer> cute=new HashMap<>();
     for(String value:main) cute.put(value,Storage.get(value));
      for(Products value:order.Product){
          value.build();
         for(String ing:value.ingredients){
            if(cute.get(ing)<1){
               result=false;
               break;
            }
            else{
                cute.put(ing,cute.get(ing)-1);
            }
         }
         if(!result) break;
      }

      return result;
}
void consume(Order order){
      for(Products value:order.Product){
         ArrayList<String> y=value.build();
         ArrayList<Integer> x=new ArrayList<>();
         for(int i=0;i<y.size();i++) x.add(1);
         consmue(y,x);
      }
}
public String available(int x){
       if(x==0) return "Not Avalaible";
       else return Integer.toString(x);
}
void report(){
      for(String value:main) System.out.println(value+" : "+available(Storage.get(value)));
}
public void CancelOrder(String Id,ArrayList<Order> orderlist){
        Id=Id.replaceAll("\\s+"," ");
        String[] x=Id.split(" ");
        if(x.length>2) {
            Id = x[2];
            boolean result = false;
            for (Order order : orderlist) {
                if (order!=null&&order.Id.equals(Id)) {
                    order.Status = "Has Been Canceled";
                    Update(order);
                    System.out.println("Order Canceled!");
                    result = true;
                    break;
                }
            }
            if (!result) System.out.println("Please Enter correct Id!");
        }
        else System.out.println("Enter Nothing?!");
}


}
