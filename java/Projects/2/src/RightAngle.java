import java.util.Scanner;

public class RightAngle extends Triangle {
    private double area;

    public RightAngle() {
        this(3, 4, 5);
    }

    public RightAngle(double aa, double bb, double cc) {
        super(aa, bb, cc);
        int validity = validRightAngle();
        if (validity == 0) {
            System.out.println("Ошибка (Не яв. прямоугольным треугольником). Выход из программы");
            System.exit(0);
        }
        area = calcArea(validity);
    }

    // определяет прямоугольный-ли треугольник и какая у него гипотенуза
    // 0 - не прям. треуг, 1 - гипотенуза A, 2 - гипотенуза B, 3 - гипотенуза C
    public int validRightAngle() {
        boolean abEqualc = (a * a + b * b) == (c * c);
        boolean acEqualb = (a * a + c * c) == (b * b);
        boolean bcEquala = (b * b + c * c) == (a * a);

        if (bcEquala) return 1;
        if (acEqualb) return 2;
        if (abEqualc) return 3;
        else return 0;
    }

    public double calcArea(int type) {
        switch (type) {
            case 1: return b * c * 0.5;
            case 2: return a * c * 0.5;
            case 3: return a * b * 0.5;
        }
        System.out.println("Ошибка (Не яв. прямоугольным треугольником). Выход из программы");
        System.exit(0);
        return 0;
    }

    @Override
    public void read(Scanner scanner) {
        a = scanner.nextDouble();
        b = scanner.nextDouble();
        c = scanner.nextDouble();
        area = calcArea(validRightAngle());
    }

    @Override
    public String toString() {
        return "Сторона A = " + a + "\n" +
                "Сторона B = " + b + "\n" +
                "Сторона C = " + c + "\n" +
                "Площадь = " + area;
    }

    public double getArea() {
        return area;
    }
}