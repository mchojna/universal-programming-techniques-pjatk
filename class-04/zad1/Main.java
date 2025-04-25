/**
 *
 *  @author Chojna Michał S29758
 *
 */

package zad1;

/*<--
 *  niezbędne importy
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) {
    /*<--
     *  definicja operacji w postaci lambda-wyrażeń:
     *  - flines - zwraca listę wierszy z pliku tekstowego
     *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
     *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
     *  - sum - zwraca sumę elmentów listy liczb całkowitych
     */
    Function<String, List<String>> flines = (String fname) -> {
      List<String> lines = new ArrayList<>();
      try{
        Scanner scanner = new Scanner(new File(String.valueOf(fname)));
        while (scanner.hasNextLine()) {
          lines.add(scanner.nextLine());
        }
        return lines;
      } catch (FileNotFoundException e) {
        return null;
      }
    };

    Function<List<String>, String> join = (List<String> lines) -> String.join("", lines);

    Function<String, List<Integer>> collectInts = (String lines) -> Arrays.stream(lines.split("\\D+"))
            .filter(s -> !s.isEmpty())
            .map(Integer::valueOf)
            .collect(Collectors.toList());

    Function<List<Integer>, Integer> sum = (List<Integer> numbers) -> numbers.stream().reduce(0, Integer::sum);

    String fname = System.getProperty("user.home") + "/LamComFile.txt";
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);
  }
}
