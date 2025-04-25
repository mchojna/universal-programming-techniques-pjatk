package zad1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Predicate;

public class ProgLang {
    List<String> lang;
    List<List<String>> prog;

    Map<String, Set<String>> langsMap;
    Map<String, Set<String>> progsMap;

    public ProgLang(String fname) throws FileNotFoundException {
        Scanner input = new Scanner(new File(fname));

        lang = new ArrayList<>();
        prog = new ArrayList<>(new ArrayList<>());

        while(input.hasNextLine()){
            String[] outputs = input.nextLine().split("\t");
            List<String> programmers = new ArrayList<>();

            for(int i = 0; i < outputs.length; i++){
                if(i == 0){
                    lang.add(outputs[i]);
                } else {
                    programmers.add(outputs[i]);
                }
            }
            prog.add(programmers);
        }
    }

    public static <K, V> Map<K, V> sorted(Map<K, V> map, Comparator<Map.Entry<K, V>> comparator) {
        List<Map.Entry<K, V>> entries = new ArrayList<>(map.entrySet());
        entries.sort(comparator);

        LinkedHashMap<K, V> result = new LinkedHashMap<>();
        entries.forEach(e -> result.put(e.getKey(), e.getValue()));

        return result;
    }

    public static <K, V> Map<K, V> filtered(Map<K, V> map, Predicate<V> predicate) {
        Set<Map.Entry<K, V>> entries = map.entrySet();
        entries.removeIf(e -> !predicate.test(e.getValue()));

        LinkedHashMap<K, V> result = new LinkedHashMap<>();
        entries.forEach(e -> result.put(e.getKey(), e.getValue()));

        return result;
    }

    public Map<String, Set<String>> getLangsMap() {
        langsMap = new LinkedHashMap<>();
        for (int i = 0; i < lang.size(); i++) {
            langsMap.put(lang.get(i), new LinkedHashSet<>(prog.get(i)));
        }

        return langsMap;
    }

    public Map<String, Set<String>> getProgsMap() {
        progsMap = new LinkedHashMap<>();

        for(int i = 0; i < prog.size(); i++){
            for(int j = 0; j < prog.get(i).size(); j++){
                String key = prog.get(i).get(j);
                String val = lang.get(i);

                if(!progsMap.containsKey(key)){
                    Set<String> vals = new LinkedHashSet<>();
                    vals.add(val);
                    progsMap.put(key, vals);
                } else {
                    Set<String> vals = progsMap.get(key);
                    vals.add(val);
                    progsMap.put(key, vals);
                }
            }
        }

        return progsMap;
    }

    public Map<String, Set<String>> getLangsMapSortedByNumOfProgs() {
        List<Map.Entry<String, Set<String>>> entries = new ArrayList<>(langsMap.entrySet());
        entries.sort((o1, o2) -> (o2.getValue().size() - o1.getValue().size()));

        LinkedHashMap<String, Set<String>> map = new LinkedHashMap<>();
        entries.forEach(e -> map.put(e.getKey(), e.getValue()));
        return map;
    }

    public Map<String, Set<String>> getProgsMapSortedByNumOfLangs() {
//        List<Map.Entry<String, Set<String>>> entries = new ArrayList<>(progsMap.entrySet());
//        entries.sort((o1, o2) -> {
//                if(o2.getValue().size() != o1.getValue().size()){
//                    return o2.getValue().size() - o1.getValue().size();
//                } else {
//                    return o1.getKey().compareTo(o2.getKey());
//                }
//            }
//        );
//
//        LinkedHashMap<String, Set<String>> map = new LinkedHashMap<>();
//        entries.forEach(e -> map.put(e.getKey(), e.getValue()));
//
//        return map;
//        return ProgLang.sorted((o1, o2) -> {
//            if(o2.getValue().size() != o1.getValue().size()){
//                return o2.getValue().size() - o1.getValue().size();
//            } else {
//                return o1.getKey().compareTo(o2.getKey());
//            }
//        })

        return ProgLang.sorted(this.progsMap, (e1, e2) -> {
            if(e2.getValue().size() != e1.getValue().size()){
                return e2.getValue().size() - e1.getValue().size();
            } else {
                return e1.getKey().compareTo(e2.getKey());
            }
        });
    }

    public Map<String, Set<String>> getProgsMapForNumOfLangsGreaterThan(int i) {
//        Set<Map.Entry<String, Set<String>>> entries = progsMap.entrySet();
//        entries.removeIf(e -> e.getValue().size() <= i);
//
//        LinkedHashMap<String, Set<String>> map = new LinkedHashMap<>();
//        entries.forEach(e -> map.put(e.getKey(), e.getValue()));
//
//        return map;
        return ProgLang.filtered(this.progsMap, e -> e.size() > i);
    }
}
