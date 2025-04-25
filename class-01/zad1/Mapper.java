/**
 *
 *  @author Chojna Michał S29758
 *
 */

package zad1;


public interface Mapper<T, R> { // Uwaga: interfejs musi być sparametrtyzowany

    public R map(T t);
}
