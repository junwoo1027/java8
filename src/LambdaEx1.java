import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class LambdaEx1 {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        list.forEach(i -> System.out.print(i + ","));
        System.out.println();

        list.removeIf(x -> x%2==0 || x%3==0);
        System.out.println(list);

        list.replaceAll(i->i*10);
        System.out.println(list);

        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");

        map.forEach((k,v) -> System.out.print("{"+k+", "+v+"},"));
        System.out.println();

        String[] str1 = {"123", "456", "789"};
        String[] str2 = {"abc", "def", "ghi"};

        Stream<String> strs1 = Stream.of(str1);
        Stream<String> strs2 = Stream.of(str2);
        Stream<String> strs3 = Stream.concat(strs1, strs2);

//        strs3.forEach(s -> System.out.print(s + ","));
        strs3.forEach(System.out::print);
    }
}
