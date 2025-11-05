package app;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Programmer> programmerList = List.of(
                new Programmer("Jack", "Berlin", List.of(
                        new Task(1, "Java project", Status.OPEN, 2),
                        new Task(2, "Python project", Status.IN_PROGESS, 3),
                        new Task(3, "JS project", Status.COMPLETED, 7))),
                new Programmer("John", "Berlin", List.of(
                        new Task(4, "Python project", Status.CANCELED, 1),
                        new Task(5, "Java project", Status.OPEN, 2))),
                new Programmer("Ann", "Bremen", List.of(
                        new Task(6, "C++ project", Status.OPEN, 1),
                        new Task(7, "JS project", Status.IN_PROGESS, 3),
                        new Task(8, "Python project", Status.COMPLETED, 5),
                        new Task(9, "Java project", Status.OPEN, 2))),
                new Programmer("Lena", "Dresden", List.of(
                        new Task(10, "С# project", Status.COMPLETED, 10)))
        );

        System.out.println("Map<String, Integer> ключ -> имя программиста, значение -> количество задач в списке у данного программиста\n");
        Map<String, Integer> collect1 = programmerList
                .stream()
                .collect(Collectors.toMap(Programmer::getName, p -> p.getTasks().size()));

        print(collect1);

        // ToDo Не совсем то - значение List<Task>, а надо только Task - в противном случае возникает конфликт, т.к. номера не уникальны
        System.out.println("\nMap<Integer, Task> где ключ, это номер задачи, а значение сама задача\n");
        /*Map<Integer, List<Task>> collect2 = programmerList
                .stream()
                .flatMap(p -> p.getTasks().stream())
                .collect(Collectors.groupingBy(Task::getNumber));

        print(collect2);
         */
        Map<Integer, Task> collect2 = programmerList
                .stream()
                .flatMap(p -> p.getTasks().stream())
                .collect(Collectors.toMap(
                        Task::getNumber,
                        task -> task,
                        (t1, t2) -> t1
                ));

        print(collect2);

        System.out.println("\nMap<String, List<Task>> где ключ, это статус задачи, а значение список задач в этом статусе\n");
        Map<String, List<Task>> collect3 = programmerList
                .stream()
                .flatMap(p -> p.getTasks().stream())
                .collect(Collectors.groupingBy(task -> task.getStatus().name(),
                        Collectors.toList()));

        print(collect3);
    }

    public static <K,V>void print (Map<K,V> map){
        if (map == null || map.isEmpty()) {
            System.out.println("Map is empty or null");
            return;
        }

        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " -> Value: " + entry.getValue());
        }
    }

}