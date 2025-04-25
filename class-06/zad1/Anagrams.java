/**
 *
 *  @author Chojna Michał S29758
 *
 */

package zad1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Anagrams {

    private Map<List<String>, List<String>> map;

    public Anagrams(String fname){
        map = new HashMap<>();

        List<String> list = new ArrayList<String>();
        File file = new File(fname);
        try {
            Scanner input = new Scanner(file);
            while(input.hasNextLine()){
                String[] outputs = input.nextLine().split(" ");

                for(String output: outputs){
                    List<String> words = new ArrayList<>(Arrays.asList(output.split("")));
                    Collections.sort(words);

                    if(map.containsKey(words)){
                        List<String> value = map.get(words);
                        value.add(output);
                        map.put(words, value);
                    } else {
                        List<String> value = new ArrayList<>(Arrays.asList(output));
                        map.put(words, value);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<List<String>> getSortedByAnQty() {
        Collection<List<String>> collection = map.values();
        List<List<String>> list = new ArrayList<>(collection);
        list.sort((List<String> o1, List<String> o2) -> o2.size() - o1.size());

        return list;
    }

    public String getAnagramsFor(String text) {
        String result = "[]";
        List<String> key = Arrays.asList(text.split(""));
        Collections.sort(key);

        if(map.containsKey(key)){
            List<String> list = map.get(key);
            list.remove(text);
            result = list.toString();
        }

        return text + ": " + result;
    }
}
