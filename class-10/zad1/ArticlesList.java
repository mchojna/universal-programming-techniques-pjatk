package zad1;

import java.util.ArrayList;
import java.util.List;

public class ArticlesList {

    private List<Article> articleList = new ArrayList<>();
    private int added = 0;
    private int calculated = 0;
    private double sum = 0.0;

    private boolean isAvailable = false;

    public synchronized void addArticles(String line) {
        while(isAvailable) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        this.articleList.add(new Article(line));
        this.added += 1;

        if(added % 200 == 0 && this.added != 0) {
            System.out.println("utworzono " + this.added + " obiektów");

            this.isAvailable = true;
            this.notifyAll();
        }
    }

    public synchronized void calculateWeight() {
        while(!this.isAvailable) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        while (this.calculated < this.added) {
            this.sum += this.articleList.get(this.calculated).getWeight();
            this.calculated++;

            if(this.calculated % 100 == 0 && this.calculated != 0) {
                System.out.println("policzono wage " + this.calculated + " towarów");
            }
        }


        this.isAvailable = false;
        this.notifyAll();
    }

    public int getAdded() {
        return this.added;
    }

    public int getCalculated() {
        return this.calculated;
    }

    public double getSum() {
        return this.sum;
    }

    public void setAdded(int i){
        this.added = i;
    }
}
