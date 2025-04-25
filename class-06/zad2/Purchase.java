/**
 *
 *  @author Chojna Michał S29758
 *
 */

package zad2;


public class Purchase {

    private String id;
    private String name;
    private String product;
    private String price;
    private String quantity;
    private Double cost;

    public Purchase(String data){
        String[] dataArr = data.split(";");

        id = dataArr[0];
        name = dataArr[1];
        product = dataArr[2];
        price = dataArr[3];
        quantity = dataArr[4];
        cost = Double.parseDouble(price) * Double.parseDouble(quantity);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getCost() {
        return cost;
    }

    @Override
    public String toString(){
        return id + ";" + name + ";" + product + ";" + price + ";" + quantity;
    }

    public String toExtendedString(){
        return id + ";" + name + ";" + product + ";" + price + ";" + quantity +  " (koszt: " + cost + ")" ;
    }
}
