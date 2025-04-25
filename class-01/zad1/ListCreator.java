/**
 *
 *  @author Chojna Michał S29758
 *
 */

package zad1;


import java.util.List;
import java.util.stream.Collectors;

public class ListCreator<T> { // Uwaga: klasa musi być sparametrtyzowana

    private List<T> list;

    private ListCreator(List<T> list){
        this.list = list;
    }

    public static <T> ListCreator<T> collectFrom(List<T> list){
        return new ListCreator<>(list);
    }

    public ListCreator<T> when(Selector<T> sel){
        this.list = this.list.stream().filter(sel::select).collect(Collectors.toList());
        return this;
    }

    public <R> List<R> mapEvery(Mapper<T, R> map){
        return this.list.stream().map(map::map).collect(Collectors.toList());
    }
}  
