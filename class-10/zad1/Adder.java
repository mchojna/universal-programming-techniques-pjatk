package zad1;

import java.io.*;

public class Adder implements Runnable {

    private final ArticlesList articlesList;
    private final String fileName;

    public Adder(ArticlesList articlesList, String fileName) {
        this.fileName = fileName;
        this.articlesList = articlesList;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.fileName));
            String line;

            while((line = br.readLine()) != null) {
                this.articlesList.addArticles(line);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
