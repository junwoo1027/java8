package stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamEx8 {
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

                new Students("na", true, 2, 1, 300),
                new Students("kim", false, 2, 1, 250),
                new Students("kim", true, 2, 1, 200),
                new Students("lee", false, 2, 2, 150),
                new Students("nam", true, 2, 2, 100),
                new Students("ann", false, 2, 2, 50),
                new Students("hwang", false, 2, 3, 100),
                new Students("kang", false, 2, 3, 150),
                new Students("lee", true, 2, 3, 200)
        };

        System.out.println("1. 반 별로 그룹화");
        Map<Integer, List<Students>> stuByBan = Stream.of(stuArr)
                .collect(Collectors.groupingBy(Students::getBan));

        for (List<Students> ban : stuByBan.values()) {
            System.out.println("ban = " + ban);
        }

        System.out.println("2. 성적 별로 그룹화");
        Map<Students.Level, List<Students>> stuByLevel = Stream.of(stuArr)
                .collect(Collectors.groupingBy(s->{
                    if (s.getScore() >= 200) return Students.Level.HIGH;
                    else if (s.getScore() >= 100) return Students.Level.MID;
                    else return Students.Level.LOW;
                }));

        TreeSet<Students.Level> keySet = new TreeSet<>(stuByLevel.keySet());

        for (Students.Level key : keySet) {
            System.out.println("["+key+"]");

            for (Students s : stuByLevel.get(key)) {
                System.out.println(s);
            }
        }

        System.out.println("3. 성적별 학생 수");
        Map<Students.Level, Long> stuCntByLevel = Stream.of(stuArr)
                .collect(Collectors.groupingBy(s->{
                    if (s.getScore() >= 200) return Students.Level.HIGH;
                    else if (s.getScore() >= 100) return Students.Level.MID;
                    else return Students.Level.LOW;
                },Collectors.counting()));

        for (Students.Level key : stuCntByLevel.keySet()) {
            System.out.println(key + "," + stuCntByLevel.get(key));
        }

        System.out.println("4. 다중 그룹화(학년별, 반별)");
        Map<Integer, Map<Integer, List<Students>>> stuByHackAndBan =
                Stream.of(stuArr).
                        collect(Collectors.groupingBy(Students::getHak,
                        Collectors.groupingBy(Students::getBan)));

        for (Map<Integer, List<Students>> hak : stuByHackAndBan.values()) {
            for (List<Students> ban : hak.values()) {
                System.out.println();
                for (Students students : ban) {
                    System.out.println(students);
                }
            }
        }


        System.out.println("5. 통계(학년별, 반별 1등)");
        Map<Integer, Map<Integer, Students>> topStuByHakAndBan =
                Stream.of(stuArr)
                .collect(Collectors.groupingBy(Students::getHak,
                        Collectors.groupingBy(Students::getBan,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(Comparator.comparingInt(Students::getScore)), Optional::get
                                ))));

        for (Map<Integer, Students> ban : topStuByHakAndBan.values()) {
            for (Students s : ban.values()) {
                System.out.println(s);
            }
        }
    }
}
