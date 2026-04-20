public class Octal extends Array {

    public Octal(int n, byte initVal) {
        super(n, initVal);
        // проверка, что все цифры в диапазоне 0-7
        for (int i = 0; i < n; i++) {
            if ((data[i] & 0xFF) > 7) data[i] = 7;
        }
    }

    public Octal(int n) {
        this(n, (byte)0);
    }

    // переопределение метода сложения для восьмеричных чисел
    @Override
    public Octal addArrays(Array other) {
        if (!(other instanceof Octal)) {
            System.out.println("Ошибка: попытка сложить Octal с не-Octal объектом");
            return null;
        }

        Octal otherOctal = (Octal) other;
        int newCount = (count > otherOctal.count) ? count : otherOctal.count;
        Octal result = new Octal(newCount);

        int carry = 0;
        for (int i = 0; i < newCount || carry != 0; i++) {
            if (i >= MAX_SIZE) {
                System.out.println("Переполнение максимального размера!");
                break;
            }

            int val1 = (i < count) ? (data[i] & 0xFF) : 0;
            int val2 = (i < otherOctal.count) ? (otherOctal.data[i] & 0xFF) : 0;
            int sum = val1 + val2 + carry;

            result.data[i] = (byte)(sum % 8);
            carry = sum / 8;

            if (i >= newCount) {
                result.count = i + 1;
            }
        }

        return result;
    }

    // метод для преобразования в строку
    public String toString() {
        StringBuilder result = new StringBuilder();
        boolean leadingZero = true;

        for (int i = count - 1; i >= 0; i--) {
            int value = data[i] & 0xFF;
            if (value == 0 && leadingZero && i != 0) continue;
            leadingZero = false;
            result.append((char)('0' + value));
        }

        return result.length() == 0 ? "0" : result.toString();
    }

    @Override
    public void print() {
        System.out.print("Octal число: " + toString() + " (в массиве: ");
        for (int i = 0; i < count; i++) {
            System.out.print((data[i] & 0xFF) + " ");
        }
        System.out.println(")");
    }
}