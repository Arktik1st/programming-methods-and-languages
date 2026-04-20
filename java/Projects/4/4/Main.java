import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();

        //работа с числами
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i <= 10; i++) {
            list.add(rand.nextInt(100));
        }

        System.out.println("Исходный список: " + list);
        list.shellSort();
        System.out.println("После сортировки: " + list);


        //работа со строками
        LinkedList<String> strList = new LinkedList<>();
        strList.add("java");
        strList.add("cplusplus");
        strList.add("python");
        strList.add("lua");
        strList.add("csharp");

        System.out.println("\nИсходный список: " + strList);
        strList.shellSort();
        System.out.println("После сортировки: " + strList);
    }
}