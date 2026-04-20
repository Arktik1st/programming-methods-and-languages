import java.util.regex.Pattern;

public class CommentStripper {
    //удаляем все типы комментов
    private static final Pattern CLEAN_PATTERN = Pattern.compile(
            "/\\*.*?\\*/|//.*?$|\".*?\"",
            Pattern.DOTALL | Pattern.MULTILINE
    );

    public static String strip(String code) {
        //заменяем всё на пробел
        return CLEAN_PATTERN.matcher(code).replaceAll(" ");
    }
}