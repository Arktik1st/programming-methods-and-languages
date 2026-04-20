public class Hex extends Array {

    public Hex(int n, byte initVal) {
        super(n, initVal);
        // проверка, что все цифры в диапазоне 0-15
        for (int i = 0; i < n; i++) {
            if ((data[i] & 0xFF) > 15) data[i] = 15;
        }
    }

    public Hex(int n) {
        this(n, (byte)0);
    }

    // переопределение метода сложения для шестнадцатеричных чисел
    @Override
    public Hex addArrays(Array other) {
        if (!(other instanceof Hex)) {
            System.out.println("Ошибка: попытка сложить Hex с другим типом объекта");
            return null;
        }

        Hex otherHex = (Hex) other;
        int newCount = (count > otherHex.count) ? count : otherHex.count;
        Hex result = new Hex(newCount);

        int carry = 0;
        for (int i = 0; i < newCount || carry != 0; i++) {
            if (i >= MAX_SIZE) {
                System.out.println("Переполнение максимального размера");
                break;
            }

            int val1 = (i < count) ? (data[i] & 0xFF) : 0;
            int val2 = (i < otherHex.count) ? (otherHex.data[i] & 0xFF) : 0;
            int sum = val1 + val2 + carry;

            result.data[i] = (byte)(sum % 16);
            carry = sum / 16;

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

            if (value < 10) {
                result.append((char)('0' + value));
            } else {
                result.append((char)('A' + (value - 10)));
            }
        }

        return result.length() == 0 ? "0" : result.toString();
    }

    @Override
    public void print() {
        System.out.print("Hex число: " + toString() + " (в массиве: ");
        for (int i = 0; i < count; i++) {
            System.out.print((data[i] & 0xFF) + " ");
        }
        System.out.println(")");
    }
}