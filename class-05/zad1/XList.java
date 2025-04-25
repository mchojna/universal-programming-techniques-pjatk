package zad1;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class XList<T> extends ArrayList<T> {

    public XList(T... ts) {
        super.addAll(Arrays.asList(ts));
    }

    public XList(Collection<? extends T> ts) {
        super(ts);
    }

    public static <T> XList<T> of(T... ts) {
        return new XList<>(ts);
    }

    public static <T> XList<T> of(Collection<? extends T> t) {
        return new XList<>(t);
    }

    public static XList<String> tokensOf(String text, String separator) {
        return new XList<>(text.split(separator));
    }

    public static XList<String> tokensOf(String text) {
        return new XList<>(text.split(" "));
    }

    public static XList<String> charsOf(String text) {
        return new XList<>(text.split(""));
    }

    public XList<T> union(T... ts) {
        XList<T> tmpList = new XList<>(this);
        tmpList.addAll(Arrays.asList(ts));
        return tmpList;
    }

    public XList<T> union(Collection<? extends T> ts) {
        XList<T> tmpList = new XList<>(this);
        tmpList.addAll(ts);
        return tmpList;
    }

    public XList<T> diff(T... ts) {
        XList<T> tmpList = new XList<>(this);
        tmpList.removeAll(Arrays.asList(ts));
        return tmpList;
    }

    public XList<T> diff(Collection<? extends T> ts) {
        XList<T> tmpList = new XList<>(this);
        tmpList.removeAll(ts);
        return tmpList;
    }

    public XList<T> unique() {
        XList<T> tmpList = new XList<>();
        for(T t: this){
            if(!tmpList.contains(t)){
                tmpList.add(t);
            }
        }
        return tmpList;
    }

    public XList<XList<T>> combine() {
        XList<XList<T>> result = new XList<>();
        combineHelper(this, 0, new XList<>(), result);
        return result;
    }

    private void combineHelper(XList<?> list, int position, XList<T> currentList, XList<XList<T>> resultList) {
        if (position != list.size()) {
            Collection<?> collection = (Collection<?>) list.get(position);
            for (Object item : collection) {
                currentList.add((T) item);
                combineHelper(list, position + 1, currentList, resultList);
                currentList.remove(currentList.size() - 1);
            }
        }
        resultList.add(new XList<>(currentList));
    }

    public <R> XList<R> collect(Function<T, R> function) {
        XList<R> tmpList = new XList<>();
        for(T t: this){
             tmpList.add(function.apply(t));
        }
        return tmpList;
    }

    public String join(String separator) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < this.size(); i++){
            sb.append(this.get(i).toString());
            if(i + 1 < this.size()){
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    public String join() {
        return this.join("");
    }

    public void forEachWithIndex(BiConsumer<T, Integer> biConsumer) {
        for(int i = 0; i < this.size(); i++){
            biConsumer.accept(this.get(i), i);
        }
    }
}
