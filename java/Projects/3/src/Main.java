public class Main {
    public static void main(String[] args) {
        // работа с классом Array
        System.out.println("1. Array:");
        Array arr1 = new Array(5, (byte)3);  // массив из 5 элементов, каждый равен 3
        Array arr2 = new Array(3, (byte)4);

        // Для демонстрации Array нужно создать конкретную реализацию
        // Используем Hex как базовый массив для демонстрации
        System.out.println("arr1: ");
        arr1.print();
        System.out.println("arr2: ");
        arr2.print();

        System.out.println("\n2. Hex:");
        // создаем шестнадцатеричное число 0x1A5
        Hex hex1 = new Hex(3);
        hex1.set(0, (byte)5);  // младший разряд
        hex1.set(1, (byte)10); // A
        hex1.set(2, (byte)1);  // старший разряд

        // создаем шестнадцатеричное число 0x2F
        Hex hex2 = new Hex(2);
        hex2.set(0, (byte)15); // F
        hex2.set(1, (byte)2);

        System.out.print("hex1 = ");
        hex1.print();
        System.out.print("hex2 = ");
        hex2.print();

        System.out.println("\n3. Octal:");
        // создаем восьмеричное число 075 (61 в десятичной)
        Octal oct1 = new Octal(2);
        oct1.set(0, (byte)5);
        oct1.set(1, (byte)7);

        // создаем восьмеричное число 014
        Octal oct2 = new Octal(2);
        oct2.set(0, (byte)4);
        oct2.set(1, (byte)1);

        System.out.print("oct1 = ");
        oct1.print();
        System.out.print("oct2 = ");
        oct2.print();

        System.out.println("\n4. Демонстрация полиморфизма:");
        // массив указателей на базовый класс
        Array[] objects = new Array[4];
        objects[0] = new Hex(2);
        objects[0].set(0, (byte)12); // C
        objects[0].set(1, (byte)1);

        objects[1] = new Hex(3);
        objects[1].set(0, (byte)8);
        objects[1].set(1, (byte)3);
        objects[1].set(2, (byte)2);

        objects[2] = new Octal(2);
        objects[2].set(0, (byte)7);
        objects[2].set(1, (byte)5);

        objects[3] = new Octal(3);
        objects[3].set(0, (byte)4);
        objects[3].set(1, (byte)3);
        objects[3].set(2, (byte)1);

        for (int i = 0; i < 4; i++) {
            System.out.print("Объект " + i + ": ");
            objects[i].print();
        }

        // сложение объектов одного типа через виртуальный метод
        System.out.println("\nСложение объектов 0 и 1 (оба Hex):");
        Array sum1 = objects[0].addArrays(objects[1]);
        if (sum1 != null) {
            sum1.print();
        }

        System.out.println("\nСложение объектов 2 и 3 (оба Octal):");
        Array sum2 = objects[2].addArrays(objects[3]);
        if (sum2 != null) {
            sum2.print();
        }

        // попытка сложить объекты разных типов
        System.out.println("\nПопытка сложить объекты 0 и 2 (Hex и Octal):");
        Array sum3 = objects[0].addArrays(objects[2]);
        if (sum3 != null) {
            sum3.print();
        }

        System.out.println("\n5. Проверка оператора индексирования:");
        Array test = new Hex(3, (byte)7);
        System.out.println("test[0] = " + (test.get(0) & 0xFF));
        System.out.println("test[1] = " + (test.get(1) & 0xFF));
        System.out.println("test[2] = " + (test.get(2) & 0xFF));
        System.out.print("Попытка доступа к test[10]: ");
        byte val = test.get(10);
    }
}