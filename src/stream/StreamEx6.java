package stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamEx6 {
    public static void main(String[] args) {
        Student[] stuArr = {
                new Student("lee", 3, 300),
                new Student("kim", 1, 200),
                new Student("ann", 2, 100),
                new Student("park", 2, 150),
                new Student("so", 1, 200),
                new Student("na", 3, 290),
                new Student("gam", 3, 180)
        };

        List<String> names = Stream.of(stuArr).map(Student::getName).collect(Collectors.toList());
        System.out.println(names);

        Student[] stuArr2 = Stream.of(stuArr).toArray(Student[]::new);

        for (Student student : stuArr2) {
            System.out.println("student = " + student);
        }

        Map<String, Student> stuMap = Stream.of(stuArr).collect(Collectors.toMap(s->s.getName(), p->p));

        for (String s : stuMap.keySet()) {
            System.out.println(s + "-" + stuMap.get(s));
        }

        long count = Stream.of(stuArr).collect(Collectors.counting());
        long totalScore = Stream.of(stuArr).collect(Collectors.summingInt(Student::getTotalScore));

        System.out.println("count = " + count);
        System.out.println("totalScore = " + totalScore);

        totalScore = Stream.of(stuArr).collect(Collectors.reducing(0, Student::getTotalScore, Integer::sum));
        System.out.println("totalScore = " + totalScore);

        Optional<Student> topStudent = Stream.of(stuArr).collect(Collectors.maxBy(Comparator.comparingInt(Student::getTotalScore)));
        System.out.println("topStudent = " + topStudent);

        IntSummaryStatistics stat = Stream.of(stuArr).collect(Collectors.summarizingInt(Student::getTotalScore));
        System.out.println("stat = " + stat);

        String stuNames = Stream.of(stuArr).map(Student::getName).collect(Collectors.joining(",", "{", "}"));
        System.out.println("stuNames = " + stuNames);
    }
}
