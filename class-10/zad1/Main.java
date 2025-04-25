/**
 *
 *  @author Chojna Michał S29758
 *
 */

package zad1;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ArticlesList articlesList = new ArticlesList();
        Adder adder = new Adder(articlesList, "Towary.txt");
        Calculator calculator = new Calculator(articlesList);

        Thread taskA = new Thread(adder);
        Thread taskB = new Thread(calculator);

        taskA.start();
        taskB.start();

        taskA.join();
        taskB.join();
  }
}
