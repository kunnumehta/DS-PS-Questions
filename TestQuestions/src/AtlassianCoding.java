package Router;
//Imagine we have a system that stores files, and these files can be grouped into collections. We are interested in knowing where our resources are being taken up.
//
//        For this system we would like to generate a report that lists:
//
//        The total size of all files stored; and
//
//        The top N collections (by file size) where N can be a user-defined value

import java.util.*;

public class AtlassianCoding {

    public class Pair{
        String filename;
        Integer fileSize;

        public Pair(String filename, Integer fileSize){
            this.filename = filename;
            this.fileSize = fileSize;
        }
    }
    public class MapEntryObject {
        List<Pair> filesList;
        Integer sizeOfCollection;

        public MapEntryObject(List<Pair> filesList, Integer sizeOfCollection){
            this.filesList = filesList;
            this.sizeOfCollection = sizeOfCollection;
        }

    }
    private Integer totalSize;
    private Map<String, MapEntryObject> collectionToFilesMap;

    public AtlassianCoding() {
        totalSize = 0;
        collectionToFilesMap = new HashMap<>();
    }

    public void insertFile(String fileName, Integer fileSize, String collection){
        Pair newPair = new Pair(fileName, fileSize);
        if(collection == null) return;
        totalSize+=fileSize;
        if(collectionToFilesMap.containsKey(collection)){
            List<Pair> files = collectionToFilesMap.get(collection).filesList;
            Integer sizeOfCollection = collectionToFilesMap.get(collection).sizeOfCollection;
            sizeOfCollection+=fileSize;
            files.add(newPair);
            collectionToFilesMap.put(collection, new MapEntryObject(files,sizeOfCollection));
        } else {
            List<Pair> files = new ArrayList<>();
            files.add(new Pair(fileName, fileSize));
            MapEntryObject mapEntryObject = new MapEntryObject(files, fileSize);
            collectionToFilesMap.put(collection, mapEntryObject);
        }
    }

    public Integer getTotalSize(){
        return totalSize;
    }

    public void printTopNCollections(Integer N){
        List<Map.Entry<String, MapEntryObject> > list =
                new LinkedList<>(collectionToFilesMap.entrySet());

        // Sort the list
        list.sort((o1, o2) -> {
            if (o2.getValue().sizeOfCollection.equals(o1.getValue().sizeOfCollection)) {
                return (o2.getValue().filesList.size() - o1.getValue().filesList.size());
            }
            return (o2.getValue().sizeOfCollection).compareTo(o1.getValue().sizeOfCollection);
        });

        // put data from sorted list to hashmap
        HashMap<String, MapEntryObject> temp = new LinkedHashMap<>();
        for (Map.Entry<String, MapEntryObject> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        int counter = 0;
        for(Map.Entry<String, MapEntryObject> mapEntry : temp.entrySet()){
            if(counter < N) {
                System.out.println("collection: " + mapEntry.getKey());
                System.out.println("files in collections: ");
                for (Pair pair : mapEntry.getValue().filesList) {
                    System.out.println("name: " + pair.filename + " size: " + pair.fileSize);
                }
                counter++;
            }
        }
    }

    public static void main(String[] args){
        AtlassianCoding atlassianCoding = new AtlassianCoding();
        atlassianCoding.insertFile("file1", 10, null);
        atlassianCoding.insertFile("file2", 15, null);
        atlassianCoding.insertFile("file3", 25, null);
        atlassianCoding.insertFile("file4", 20, null);
        System.out.println(atlassianCoding.totalSize);
        atlassianCoding.printTopNCollections(2);
    }
}
