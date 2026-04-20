import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        double change;

        Triangle tri = new Triangle();
        System.out.println("Вв. треугольник");
        tri.read(scanner);
        System.out.println(tri);

        do {
            System.out.print("Поменять сторону? 0-нет 1-да: ");
            choice = scanner.nextInt();
            if (choice == 0) break;
            System.out.print("1-A, 2-B, 3-C, 0-выход: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    change = scanner.nextDouble();
                    tri.changeA(change);
                    break;
                case 2:
                    change = scanner.nextDouble();
                    tri.changeB(change);
                    break;
                case 3:
                    change = scanner.nextDouble();
                    tri.changeC(change);
                    break;
            }
        } while (choice != 0);

        double[] angles = new double[3];
        tri.calcAngles(angles);

        System.out.println("Углы треуглольника:");
        System.out.println(angles[0]);
        System.out.println(angles[1]);
        System.out.println(angles[2]);

        System.out.println("Периметр = " + tri.calcPerim());

        RightAngle rig = new RightAngle();
        System.out.println("Вв. прямоугольный треугольник");
        rig.read(scanner);
        System.out.println(rig);

        do {
            System.out.print("Поменять сторону? 0-нет 1-да: ");
            choice = scanner.nextInt();
            if (choice == 0) break;
            System.out.print("1-A, 2-B, 3-C, 0-выход: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    change = scanner.nextDouble();
                    rig.changeA(change);
                    if (rig.validRightAngle() == 0) {
                        System.out.println("Ошибка (Не яв. прямоугольным треугольником). Выход из программы");
                        System.exit(0);
                    }
                    break;
                case 2:
                    change = scanner.nextDouble();
                    rig.changeB(change);
                    if (rig.validRightAngle() == 0) {
                        System.out.println("Ошибка (Не яв. прямоугольным треугольником). Выход из программы");
                        System.exit(0);
                    }
                    break;
                case 3:
                    change = scanner.nextDouble();
                    rig.changeC(change);
                    if (rig.validRightAngle() == 0) {
                        System.out.println("Ошибка (Не яв. прямоугольным треугольником). Выход из программы");
                        System.exit(0);
                    }
                    break;
            }
        } while (choice != 0);

        rig.calcAngles(angles);

        System.out.println("Углы треуглольника:");
        System.out.println(angles[0]);
        System.out.println(angles[1]);
        System.out.println(angles[2]);

        System.out.println("Периметр = " + rig.calcPerim());

        scanner.close();
    }
}