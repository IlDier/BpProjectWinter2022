import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Restaurant extends Store{
    ArrayList<String> Restaurantproducts=new ArrayList<>();
    ArrayList<Order> orderList=new ArrayList<>();
    Restaurant(){
        Restaurantproducts.add("pizza");
        Restaurantproducts.add("burger");
        Restaurantproducts.add("steak");
        Restaurantproducts.add("friedchicken");
        Restaurantproducts.add("salad");
        Restaurantproducts.add("frenchfries");
    }
    @Override
    public void getMenu() {
        String s="Restaurant  {"+"\n\t Foods{\n\t\t\t\tpizza"+available(Conv("pizza"),storg)+"\n \t\t\t\tburger"+available(Conv("burger"),storg)+"\n\t\t\t\tsteak"+available(Conv("steak"),storg)+"\n\t\t\t\tfriedchicken"+available(Conv("friedchicken"),storg)+"\n\t\t\t}\n\tAppetizer{\n\t\t\t\tsalad"+available(Conv("tea"),storg)+"\n\t\t\t\tfrenchfries"+available(Conv("frenchfries"),storg)+"\n\t\t}\n\t}"+"\033[0;34m";
        System.out.println(s);
    }

    @Override
    public Order AddOrder(String in, User user, Storage storg, File orderu) {
        in=in.replaceAll("\\s+"," ");
        String[] x=in.split(" ");
        boolean check=true;
        Order order=new Order();
        if(x.length>2){
            for(int i=2;i<x.length;i++){
                if(Restaurantproducts.contains(x[i].toLowerCase())){
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
                check=false;
                value.OrderDefine();
                System.out.println("\t---------------");
            }
        }
        if(check) System.out.println("No Order has been added yet!");
    }
}
