import com.yourusername.anotherlib.bidimap.BidiMapImpl;
import com.yourusername.superlib.treemap.MyTreeMap;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== TreeMap ===");
        MyTreeMap<String, Integer> treeMap = new MyTreeMap<>();
        treeMap.put("apple", 1);
        treeMap.put("banana", 2);
        treeMap.put("pear", 3);
        System.out.println("Value for key 'banana': " + treeMap.get("banana"));
        System.out.println("Size: " + treeMap.size());

        System.out.println("\n=== BidiMap ===");
        BidiMapImpl<String, Integer> bidiMap = new BidiMapImpl<>();
        bidiMap.put("one", 1);
        bidiMap.put("two", 2);
        System.out.println("Get value for 'one': " + bidiMap.getValue("one"));
        System.out.println("Get key for value 2: " + bidiMap.getKey(2));
        bidiMap.removeByKey("one");
        System.out.println("After remove, get value for 'one': " + bidiMap.getValue("one"));
    }
}