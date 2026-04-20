import java.util.Scanner;

public class Main {
    public static void menu() {
        System.out.println("Меню:");
        System.out.println("(1)Вв. дробь d1");
        System.out.println("(2)Вв. дробь d2");
        System.out.println("(3)Выв. дробь d1");
        System.out.println("(4)Выв. дробь d2");
        System.out.println("(5)d1 += d2");
        System.out.println("(6)d1 -= d2");
        System.out.println("(7)d1 *= d2");
        System.out.println("(8)d1 /= d2");
        System.out.println("(9)Выв. d1 == d2");
        System.out.println("(10)Выв. d1 > d2");
        System.out.println("(11)Выв. d1 < d2");
        System.out.println("(0)Выход");
        System.out.print("Ввод: ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Rational d1 = new Rational();
        Rational d2 = new Rational();
        int choice = 0;

        do {
            menu();
            choice = scanner.nextInt();

            switch (choice) {
                case 1: d1.read(scanner); break;
                case 2: d2.read(scanner); break;
                case 3: d1.display(); break;
                case 4: d2.display(); break;
                case 5: d1.add(d2); break;
                case 6: d1.sub(d2); break;
                case 7: d1.mul(d2); break;
                case 8: d1.div(d2); break;
                case 9: System.out.println(d1.equal(d2) ? "d1 == d2" : "d1 != d2"); break;
                case 10: System.out.println(d1.greate(d2) ? "d1 > d2" : "d1 <= d2"); break;
                case 11: System.out.println(d1.less(d2) ? "d1 < d2" : "d1 >= d2"); break;
            }
        } while (choice != 0);

        scanner.close();
    }
}