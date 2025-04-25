package zad1;

public class Calculator implements Runnable {

    ArticlesList articlesList;

    public Calculator(ArticlesList articlesList) {
        this.articlesList = articlesList;
    }

    @Override
    public void run() {
        do {
            this.articlesList.calculateWeight();
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while(articlesList.getAdded() != articlesList.getCalculated());
        System.out.println(this.articlesList.getSum());
    }
}
