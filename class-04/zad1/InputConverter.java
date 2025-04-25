package zad1;

import java.util.function.Function;

public class InputConverter<T> {
    T fname;

    public InputConverter(T fname){
        this.fname = fname;
    }

    public <R> R convertBy(Function<?, ?>... functions){
        Object result = fname;
        for (Function function : functions) {
            result = function.apply(result);
        }
        return (R) result;
    }
}
