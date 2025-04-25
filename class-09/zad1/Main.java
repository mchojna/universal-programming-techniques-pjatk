/**
 *
 *  @author Chojna Michał S29758
 *
 */

package zad1;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) throws Exception {
      Map<String, List<String>> mapA = Files.lines(Paths.get("unixdict.txt"), StandardCharsets.UTF_8)
              .collect(Collectors.groupingBy(
                      e -> e.chars()
                              .sorted()
                              .mapToObj(f -> String.valueOf((char)(f)))
                              .collect(Collectors.joining())
              ));
      int max = mapA.values().stream()
              .mapToInt(List::size)
              .max()
              .orElse(0);
      mapA
              .entrySet()
              .stream()
              .sorted((e1, e2) -> e2.getValue().size() - e1.getValue().size())
              .filter(e -> e.getValue().size() == max)
              .forEach(e -> System.out.println(String.join(" ", e.getValue())));
  }
}
