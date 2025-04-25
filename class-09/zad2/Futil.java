package zad2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Futil {
    public static void processDir(String dirName, String resultFileName) {
        List<String> lines = new ArrayList<>();
        try(Stream<Path> paths = Files.walk(Paths.get(dirName))) {
            lines = paths
                    .filter(f -> f.getFileName().toString().endsWith(".txt"))
                    .map(f -> f.toAbsolutePath())
                    .flatMap(e -> {
                        try {
                            return Files.newBufferedReader(e, Charset.forName("windows-1250")).lines();
                        } catch (IOException ignored) {
                            throw new RuntimeException();
                        }
                    })
                    .collect(Collectors.toList());
        } catch (Exception ignored) { }

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(resultFileName), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            lines.forEach(line -> {
                try {
                    writer.write(line);
                    writer.newLine();
                } catch (IOException ignored) { }
            });
        } catch (IOException ignored) { }
    }
}