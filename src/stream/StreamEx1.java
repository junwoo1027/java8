package stream;

import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamEx1 {
    public static void main(String[] args) {
        Stream<Student> studentStream = Stream.of(
                new Student("lee", 3, 300),
                new Student("kim", 1, 200),
                new Student("ann", 2, 100),
                new Student("park", 2, 150),
                new Student("so", 1, 200),
                new Student("na", 3, 290),
                new Student("gam", 3, 180)
        );
//        studentStream.sorted(Comparator.comparing(stream.Student::getBan)
//                .thenComparing(Comparator.naturalOrder()))
//                .forEach(System.out::println);

        IntStream scoreStream = studentStream.mapToInt(Student::getTotalScore);

//        long totalScore = scoreStream.sum();
//        System.out.println("totalScore = " + totalScore);

        IntSummaryStatistics stat = scoreStream.summaryStatistics();
        long totalCount = stat.getCount();
        long totalScore = stat.getSum();

    }
}

//class stream.Student implements Comparable<stream.Student> {
//    String name;
//    int ban;
//    int totalScore;
//
//    stream.Student(String name, int ban, int totalScore) {
//        this.name = name;
//        this.ban = ban;
//        this.totalScore = totalScore;
//    }
//
//    public String toString() {
//        return String.format("[%s, %d, %d]", name, ban, totalScore);
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getBan() {
//        return ban;
//    }
//
//    public int getTotalScore() {
//        return totalScore;
//    }
//
//    public int compareTo(stream.Student s) {
//        return s.totalScore - this.totalScore;
//    }
//}