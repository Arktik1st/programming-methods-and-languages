import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IfElseCounter {

    //if + возможные пробелы + (
    private static final Pattern IF_PATTERN = Pattern.compile("if\\s*\\(");

    //else \\b - граница слова
    private static final Pattern ELSE_PATTERN = Pattern.compile("\\belse\\b");

    public static int count(String sourceCode) {
        //удаление комментов
        String cleanCode = CommentStripper.strip(sourceCode);

        Matcher ifMatcher = IF_PATTERN.matcher(cleanCode);
        Matcher elseMatcher = ELSE_PATTERN.matcher(cleanCode);

        int ifCount = 0;
        int elseCount = 0;

        while (ifMatcher.find()) ifCount++;
        while (elseMatcher.find()) elseCount++;

        //min тк по парам
        return Math.min(ifCount, elseCount);
    }
}