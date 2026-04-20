import java.util.Scanner;

public class Rational {
    private int a, b;

    public Rational() {
        this(1, 1);
    }

    public Rational(int aa, int bb) {
        a = aa;
        b = bb;
    }

    public void init(int aa, int bb) {
        a = aa;
        b = bb;
    }

    public void read(Scanner scanner) {
        System.out.print("Вв. числитель дроби: ");
        int aa = scanner.nextInt();
        System.out.print("Вв. знаменатель дроби: ");
        int bb = scanner.nextInt();
        a = aa;
        b = bb;
    }

    public void display() {
        int maxLen = Math.max(numLen(a), numLen(b));

        System.out.println("Дробь:");
        System.out.println(a);
        System.out.println("-".repeat(maxLen));
        System.out.println(b);
    }

    public void reduce() {
        int c = a, d = b, del;
        // общий делитель по алгоритму евклида
        while (c > 0 && d > 0) {
            if (c >= d) c %= d;
            else d %= c;
        }
        del = Math.max(c, d);
        a /= del;
        b /= del;
    }

    public void add(Rational c) {
        a = (a * c.b) + (c.a * b);
        b *= c.b;
        this.reduce();
    }

    public void sub(Rational c) {
        a = (a * c.b) - (c.a * b);
        b *= c.b;
        this.reduce();
    }

    public void mul(Rational c) {
        a *= c.a;
        b *= c.b;
        this.reduce();
    }

    public void div(Rational c) {
        a *= c.b;
        b *= c.a;
        this.reduce();
    }

    public boolean equal(Rational c) {
        float a1 = a, b1 = b;
        float a2 = c.a, b2 = c.b;
        return (a1 / b1) == (a2 / b2);
    }

    public boolean greate(Rational c) {
        float a1 = a, b1 = b;
        float a2 = c.a, b2 = c.b;
        return (a1 / b1) > (a2 / b2);
    }

    public boolean less(Rational c) {
        float a1 = a, b1 = b;
        float a2 = c.a, b2 = c.b;
        return (a1 / b1) < (a2 / b2);
    }

    private int numLen(int n) {
        int len = 0;
        while (n != 0) {
            n /= 10;
            len++;
        }
        return len;
    }
}