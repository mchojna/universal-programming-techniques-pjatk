package zad1;

public class Article {
    private final int id;
    private final double weight;

    public Article(String line) {
        this.id = Integer.parseInt(line.split(" ")[0]);
        this.weight = Double.parseDouble(line.split(" ")[1]);
    }

    public int getId() {
        return this.id;
    }

    public double getWeight() {
        return this.weight;
    }
}
