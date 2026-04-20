import java.util.Scanner;

public class Triangle {
    protected double a, b, c;
    protected static final double PI = 3.14159;

    public Triangle() {
        this(1, 1, 1);
    }

    public Triangle(double aa, double bb, double cc) {
        changeA(aa);
        changeB(bb);
        changeC(cc);
        if (!validTriangle()) {
            System.out.println("Ошибка (Одна из сторон больше суммы двух других). Выход из программы");
            System.exit(0);
        }
    }

    public void changeA(double aa) {
        if (aa <= 0) {
            System.out.println("Ошибка (А<0). Выход из программы");
            System.exit(0);
        }
        a = aa;
    }

    public void changeB(double bb) {
        if (bb <= 0) {
            System.out.println("Ошибка (B<0). Выход из программы");
            System.exit(0);
        }
        b = bb;
    }

    public void changeC(double cc) {
        if (cc <= 0) {
            System.out.println("Ошибка (C<0). Выход из программы");
            System.exit(0);
        }
        c = cc;
    }

    public boolean validTriangle() {
        boolean abMorec = (a + b) > c;
        boolean acMoreb = (a + c) > b;
        boolean bcMorea = (b + c) > a;
        boolean allSidesValid = a > 0 && b > 0 && c > 0;
        return abMorec && acMoreb && bcMorea && allSidesValid;
    }

    public void calcAngles(double[] angles) {
        // angles[0] - угол между сторонами a и c (alpha)
        // angles[1] - угол между сторонами a и b (beta)
        // angles[2] - угол между сторонами c и b (gamma)
        angles[0] = Math.acos((a * a + c * c - b * b) / (2 * a * c)) * 180 / PI;
        angles[1] = Math.acos((a * a + b * b - c * c) / (2 * a * b)) * 180 / PI;
        angles[2] = Math.acos((c * c + b * b - a * a) / (2 * c * b)) * 180 / PI;
    }

    public double calcPerim() {
        return a + b + c;
    }

    public void read(Scanner scanner) {
        a = scanner.nextDouble();
        b = scanner.nextDouble();
        c = scanner.nextDouble();
        if (!validTriangle()) {
            System.out.println("Ошибка. Выход из программы");
            System.exit(0);
        }
    }

    public String toString() {
        return "Сторона A = " + a + "\n" +
                "Сторона B = " + b + "\n" +
                "Сторона C = " + c;
    }
}