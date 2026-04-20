public class Test {
    public void method() {
        int a = 10;
        // Это комментарий с if() в нем
        if (a > 5) {
            System.out.println("Это строка с if() else внутри");
            if (a < 20) { // вложенный if
                a = 15;
            } else {
                a = 25;
            }
        } else if (a == 10) { /* Многострочный if() */
            a = 30;
        } else return;

        /* Блок без else */
        if (a != 0) a = 0;
    }
}