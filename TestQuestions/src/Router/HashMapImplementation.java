package Router;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HashMapImplementation implements Router{

    class Pair {
        String result;
        Integer count;

        public Pair(String result, Integer count){
            this.result = result;
            this.count = count;
        }
    }
    private Map<String, PriorityQueue<Pair>> routerMap;

    private static HashMapImplementation hashMapImplementation;

    public static HashMapImplementation getInstance(){
        if(hashMapImplementation == null){
            hashMapImplementation = new HashMapImplementation();
        }
        return hashMapImplementation;
    }

    private HashMapImplementation(){
        routerMap = new HashMap<>();
    }

    @Override
    public void withRoute(String path, String result) {
        String key;
        if(path.contains("*")){
            key = path.substring(0, path.indexOf("*"));
        } else {
            key = path;
        }
        System.out.println(key);
        Integer countOfSplits = path.split("/").length;
        PriorityQueue<Pair> pairPriorityQueue = routerMap.getOrDefault(path, new PriorityQueue<>());
        pairPriorityQueue.add(new Pair(result, countOfSplits));
        routerMap.put(key, pairPriorityQueue);
        for(Map.Entry<String, PriorityQueue<Pair>> entry : routerMap.entrySet()){
            System.out.println("key: " + entry.getKey() + " value: " + entry.getValue().peek().result);
        }
    }

    @Override
    public String route(String path) {
        // "/bar/xyz"  && key stored is "/bar/*"

        // "/bar/xyz/*" , "/bar/*"
        // split out input key while adding the paths based on delimiter and then store count of sections;
        String[] splits = path.split("/");
        for (String s : splits){
            System.out.println(s);
        }
        String key = "";
        String finalKey = key;
        for(String s: splits){
            key += s + "/";
            if(routerMap.containsKey(key)){
                finalKey = key;
            }
        }
        if(routerMap.containsKey(finalKey)){
            return routerMap.get(finalKey).peek().result;
        }
        return "";
    }
}
