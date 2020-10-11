package stream;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

class Students {
    String name;
    boolean isMale;
    int hak;
    int ban;
    int score;

    public Students(String name, boolean isMale, int hak, int ban, int score) {
        this.name = name;
        this.isMale = isMale;
        this.hak = hak;
        this.ban = ban;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public boolean isMale() {
        return isMale;
    }

    public int getHak() {
        return hak;
    }

    public int getBan() {
        return ban;
    }

    public int getScore() {
        return score;
    }

    public String toString() {
        return String.format("[%s, %s, %d학년 %d반, %3d점]", name, isMale ? "남" :"여", hak, ban, score);
    }

    enum Level{ HIGH, MID, LOW}
}

public class StreamEx7 {
    public static void main(String[] args) {
        Students[] stuArr = {
                new Students("na", true, 1, 1, 300),
                new Students("kim", false, 1, 1, 250),
                new Students("kim", true, 1, 1, 200),
                new Students("lee", false, 1, 2, 150),
                new Students("nam", true, 1, 2, 100),
                new Students("ann", false, 1, 2, 50),
                new Students("hwang", false, 1, 3, 100),
                new Students("kang", false, 1, 3, 150),
                new Students("lee", true, 1, 3, 200),

                new Students("na", true, 1, 1, 300),
                new Students("kim", false, 1, 1, 250),
                new Students("kim", true, 1, 1, 200),
                new Students("lee", false, 1, 2, 150),
                new Students("nam", true, 1, 2, 100),
                new Students("ann", false, 1, 2, 50),
                new Students("hwang", false, 1, 3, 100),
                new Students("kang", false, 1, 3, 150),
                new Students("lee", true, 1, 3, 200)
        };

        System.out.println("1. 단순분할(성별로 분할)");
        Map<Boolean, List<Students>> stuBySex = Stream.of(stuArr)
                .collect(partitioningBy(Students::isMale));

        List<Students> maleStu = stuBySex.get(true);
        List<Students> femaleStu = stuBySex.get(false);

        for (Students students : maleStu) {
            System.out.println("students = " + students);
        }

        for (Students students : femaleStu) {
            System.out.println("students = " + students);
        }

        System.out.println("2. 단순분할 + 통계(성별 학생수");
        Map<Boolean, Long> stuNumsBySex = Stream.of(stuArr)
                .collect(partitioningBy(Students::isMale, counting()));

        System.out.println("남학생 수" + stuNumsBySex.get(true));
        System.out.println("여학생 수" + stuNumsBySex.get(false));

        System.out.println("3. 단순분할 + 통계(성별 1등)");
        Map<Boolean, Optional<Students>> stuTopBySex =  Stream.of(stuArr)
                .collect(partitioningBy(Students::isMale, maxBy(comparing(Students::getScore))));

        System.out.println(stuTopBySex.get(true));
        System.out.println(stuTopBySex.get(false));

        Map<Boolean, Students> topScoreBySex = Stream.of(stuArr)
                .collect(partitioningBy(Students::isMale,
                        collectingAndThen(
                                maxBy(comparingInt(Students::getScore)), Optional::get)
                        ));

        System.out.println("topScoreBySex = " + topScoreBySex.get(true));
        System.out.println("topScoreBySex = " + topScoreBySex.get(false));

        System.out.println("4. 다중분할(성별 불합격자, 100점이하)");
        Map<Boolean, Map<Boolean, List<Students>>> failedStu =
                Stream.of(stuArr).collect(partitioningBy(Students::isMale,
                        partitioningBy(s->s.getScore() <= 100)));

       List<Students> failedMale = failedStu.get(true).get(true);
       List<Students> failedFeMale = failedStu.get(false).get(true);

        for (Students students : failedMale) {
            System.out.println("students = " + students);
        }

        for (Students students : failedFeMale) {
            System.out.println("students = " + students);
        }
    }
}
