package zad1;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class Futil {
    public static void processDir(String dirName, String resultFileName) {
        List<String> paths = new ArrayList<>();
        try {
            Files.walkFileTree(Paths.get(dirName), new SimpleFileVisitor<Path>() {
               @Override
               public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if(file.getFileName().toString().endsWith(".txt")){
                        paths.add(file.toAbsolutePath().toString());
                    }

                   return  FileVisitResult.CONTINUE;
               }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try{
            for(String path: paths){
                FileInputStream fis = new FileInputStream(path);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis, "Cp1250"));

                FileOutputStream fos = new FileOutputStream(resultFileName, true); 
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));


                String line;
                while ((line = br.readLine()) != null) {
                    bw.write(line);
                    bw.newLine();
                }

                br.close();
                bw.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
