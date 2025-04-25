/**
 *
 *  @author Chojna Michał S29758
 *
 */

package zad2;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomersPurchaseSortFind {

    List<Purchase> list;

    public CustomersPurchaseSortFind(){
        list = new ArrayList<>();
    }

    public void readFile(String fname) {
        File file = new File(fname);
        try {
            Scanner input = new Scanner(file);
            while (input.hasNextLine()){
                list.add(new Purchase(input.nextLine().trim()));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void showSortedBy(String condition) {
        System.out.println(condition);
        List<Purchase> tmpList = new ArrayList<>(list);

        switch(condition) {
            case "Nazwiska": {

                tmpList.stream().sorted((Object o1, Object o2) -> {
                    Purchase p1 = (Purchase) o1;
                    Purchase p2 = (Purchase) o2;
                    if(p1.getName().compareTo(p2.getName()) == 0){
                        return p1.getId().compareTo(p2.getId());
                    } else {
                        return p1.getName().compareTo(p2.getName());
                    }
                }).forEach(System.out::println);
                break;
            }

            case "Koszty": {

                tmpList.stream().sorted((Object o1, Object o2) -> {
                    Purchase p1 = (Purchase) o1;
                    Purchase p2 = (Purchase) o2;
                    if(p2.getCost().compareTo(p1.getCost()) == 0){
                        return p1.getId().compareTo(p2.getId());
                    } else {
                        return p2.getCost().compareTo(p1.getCost());
                    }
                }).forEach(p -> System.out.println(p.toExtendedString()));
                break;
            }
        }
        System.out.println();
    }

    public void showPurchaseFor(String id) {
        System.out.println("Klient " + id);
        list.stream().filter(p -> p.getId().equals(id)).forEach(System.out::println);
        System.out.println();
    }
}
