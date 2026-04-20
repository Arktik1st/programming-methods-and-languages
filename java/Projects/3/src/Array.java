public class Array {
    protected static final int MAX_SIZE = 100;
    protected byte[] data;
    protected int count;

    // конструктор инициализации
    public Array(int n, byte initVal) {
        if (n < 0) n = 0;
        if (n > MAX_SIZE) n = MAX_SIZE;

        count = n;
        data = new byte[MAX_SIZE];

        for (int i = 0; i < n; i++) {
            data[i] = initVal;
        }
        for (int i = n; i < MAX_SIZE; i++) {
            data[i] = 0;
        }
    }

    public Array(int n) {
        this(n, (byte)0);
    }

    // конструктор копирования
    public Array(Array other) {
        this.count = other.count;
        this.data = new byte[MAX_SIZE];

        for (int i = 0; i < MAX_SIZE; i++) {
            this.data[i] = other.data[i];
        }
    }

    // оператор индексирования с проверкой
    public byte get(int index) {
        if (index < 0 || index >= count) {
            System.out.println("Индекс " + index + " выходит за границы [0, " + (count-1) + "]");
            return 0;
        }
        return data[index];
    }

    public void set(int index, byte value) {
        if (index < 0 || index >= count) {
            System.err.println("Индекс " + index + " выходит за границы [0, " + (count-1) + "]");
            return;
        }
        data[index] = value;
    }

    // метод сложения (не виртуальный, но может быть переопределен)
    public Array addArrays(Array other) {
        int newCount = (count > other.count) ? count : other.count;
        Array result = new Array(newCount);

        for (int i = 0; i < newCount; i++) {
            int val1 = (i < count) ? (data[i] & 0xFF) : 0;
            int val2 = (i < other.count) ? (other.data[i] & 0xFF) : 0;
            result.data[i] = (byte)(val1 + val2);
        }

        return result;
    }

    public int getCount() { return count; }
    public int getMaxSize() { return MAX_SIZE; }

    // метод для вывода массива
    public void print() {
        System.out.print("Массив [" + count + "]: ");
        for (int i = 0; i < count; i++) {
            System.out.print((data[i] & 0xFF) + " ");
        }
        System.out.println();
    }
}