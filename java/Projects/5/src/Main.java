import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        String testCode = """
            public class Test {
                public void method() {
                    int a = 10;
                    //коммент с if()
                    if (a > 5) {
                        System.out.println("строка с if() else внутри");
                        if (a < 20) { // вложенный if
                            a = 15;
                        } else {
                            a = 25;
                        }
                    } else if (a == 10) { /* коммент с if() */
                        a = 30;
                    } else return;
                    
                    /* блок без else */
                    if (a != 0) a = 0;
                }
            }
            """;

        int count = IfElseCounter.count(testCode);
        System.out.println("Найдено if() else: " + count);
}