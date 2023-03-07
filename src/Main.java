import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        Comparator<Person> comparator= (p1, p2) -> {
            return p1.getFamily().compareTo(p2.getFamily());
        };
        while (true) {
            System.out.println("Выберите операцию над списком:" +
                    "\n1. Подсчитать количество несовершеннолетних" +
                    "\n2. Вывести список фамилий мужчин от 18 до 27 лет" +
                    "\n3. Вывести список работоспособных людей с высшим образованием");
            Scanner scanner = new Scanner(System.in);
            int num = scanner.nextInt();
            switch (num) {
                case (1): {
                    //количество несовершеннолетних:
                    long menorsNumber = persons.stream().filter(person -> person.getAge() < 18).count();
                    System.out.println("Несовершеннолетних в Лондоне: " + menorsNumber);
                    break;
                }
                case (2): {
                    //список призывников:
                    List<String> familyList = persons.stream().filter(person -> person.getAge() >= 18)
                            .filter(person -> person.getAge() < 27)
                            .filter(person -> person.getSex() == Sex.MAN)
                            .map(person -> person.getFamily().toString())
                            .collect(Collectors.toList());
                    System.out.println(Arrays.toString(familyList.toArray()));
                    break;
                }
                case (3): {
                    //список людей с высшим образованием:
                    List<String> pList = persons.stream().filter(person -> person.getAge() >= 18)
                            .filter(person -> ((person.getAge() < 65) && (person.getSex() == Sex.MAN) || ((person.getAge() < 60) && (person.getSex() == Sex.WOMAN))))
                            .filter(person -> person.getEducation() == Education.HIGHER)
                            .sorted(comparator)
                            .map(person -> person.toString())
                            .collect(Collectors.toList());
                    System.out.println(Arrays.toString(pList.toArray()));
                    break;
                }
                default: {
                    System.out.println("Такой команды не существует");
                    break;
                }
            }
        }

    }

}