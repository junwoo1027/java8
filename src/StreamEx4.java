import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class StreamEx4 {
    public static void main(String[] args) {
        Stream<String[]> strArrStream = Stream.of(
                new String[] {"abc", "def", "ghi"},
                new String[] {"ABC", "DEF", "GHI"}
        );

        Stream<String> strStream = strArrStream.flatMap(Arrays::stream);

        strStream.map(String::toLowerCase)
                .distinct()
                .sorted()
                .forEach(System.out::println);
        System.out.println();

        String[] lineArr = {
                "Believe or not It is true",
                "Do or do not There is no try"
        };

        Stream<String> lineStream = Arrays.stream(lineArr);
        lineStream.flatMap(line -> Stream.of(line.split(" ")))
                .map(String::toLowerCase)
                .distinct()
//                .sorted()
                .forEach(System.out::println);
    }
}
