package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListCreator<T> {
    private List<T> list;

    private ListCreator(List<T> list) {
        this.list = list;
    }

    public static <T> ListCreator<T> collectFrom(List<T> list) {
        return new ListCreator<>(list);
    }

    public ListCreator<T> when(Predicate<T> predicate) {
        List<T> listT = new ArrayList<>();

        for(T element: this.list){
            if(predicate.test(element)){
                listT.add(element);
            }
        }

        return ListCreator.collectFrom(listT);
    }

    public <R> List<R> mapEvery(Function<T, R> mapper) {
        List<R> listR = new ArrayList<>();

        for(T element: this.list){
            listR.add(mapper.apply(element));
        }

        return listR;
    }
}
