package zad2;

import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Function;

public class Maybe<T> {
    T input;

    private Maybe(T t){
        this.input = t;
    }

    @Override
    public String toString() {
        if(input != null){
            return "Maybe has value " + this.input.toString();
        }else{
            return "Maybe is empty";
        }
    }

    // ta metoda statyczna zwraca obiekt Maybe, „opakowujący” wartość x, dowolnego typu referencyjnego
    public static <T> Maybe<T> of(T t) {
        return new Maybe<>(t);
    }

    // jeżeli w obiekcie Maybe znajduje się wartość, wykonywana jest operacja cons z tą wartością jako argumentem, w przeciwnym razie - gdy obiekt Maybe jest pusty - nic się nie dzieje
    public void ifPresent(Consumer<T> cons){
        if(input != null) cons.accept(input);
    }

    // jeżeli w obiekcie  jest wartość, wykonywana jest funkcja func z tą wartością jako argumentem i zwracany jest jej wynik „zapakowany” w nowy obiekt klasy Maybe (to opakowanie jest niezbędne, bo wynik mógłby być null, a tego chcemy uniknąć w ewentualnym dalszym przetwarzaniu; jeśli wynikiem funkcji jest null, zwracany jest pusty obiekt klasy Maybe)
    public <R> Maybe<R> map(Function<T, R> func){
        if(this.input != null && func.apply(this.input) != null){
            return new Maybe<R>(func.apply(this.input));
        }

        return new Maybe<R>(null);
    }

    // zwraca zawartość obiektu Maybe, ale jeśli jest on pusty, powinna zgłosić wyjątek NoSuchElementException
    public T get(){
        if(this.input != null){
            return this.input;
        }else{
            throw new NoSuchElementException("maybe is empty");
        }
    }

    // zwraca true jeśli w obiekcie Maybe zawarta jest wartośc, a false - gdy jest on pusty
    public boolean isPresent(){
        return this.input == null;
    }

    // zwraca zawartość obiektu Maybe lub domyślną wartosć defVal, jeśli obiekt Maybe jest pusty
    public T orElse(T defVal){
        if(this.input != null){
            return this.input;
        }else{
            return defVal;
        }
    }

    // zwraca  to Maybe, jeśli spełniony jest warunek pred lub to Maybe jest puste; zwraca puste Maybe, jeśli warunek pred jest niespełniony
    public Maybe<T> filter(Predicate<T> pred){
        if (!pred.test(this.input)) {
            this.input = null;
        }
        return this;
    }
}
