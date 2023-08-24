import java.util.ArrayList;
import java.util.List;

public class RabinKarp {
    public static void main(String[] args) {
        String source = "Alibaba or Alibubab? I do not know!";
        String pattern = "b*b";
        System.out.println(search(source, pattern));
    }

    public static List<Integer> search(String source, String pattern) {
        final String notFoundMessage = "Подстрока \"" + pattern + "\" не найдена";

        if (source.length() < pattern.length()) {
            System.out.println(notFoundMessage);
            return null;
        }

        List<Integer> result = new ArrayList<>();
        int asterikPosition = pattern.indexOf('*');
        int patternHash = simpleHash(pattern);
        patternHash -= pattern.charAt(asterikPosition);
        int windowHash = simpleHash(source.substring(0, pattern.length()));
        windowHash -= source.charAt(asterikPosition);

        for (int start = 0; start <= source.length() - pattern.length(); start++) {
            if (start > 0) {
                windowHash -= source.charAt(start - 1);
                windowHash += source.charAt(start + pattern.length() - 1);
                windowHash -= source.charAt(start + asterikPosition);
            }
            if (windowHash == patternHash) {
                for (int i = 0; i < pattern.length(); i++) {
                    if (pattern.charAt(i) != '*' && source.charAt(start + i) != pattern.charAt(i)) {
                        break;
                    }
                }
                result.add(start);
            }
            windowHash += source.charAt(start + asterikPosition);
        }

        return result;
    }

    // Функция подсчёта хэша строки без учёта символа *
    public static int simpleHash(String string) {
        int result = 0;
        for (int c : string.toCharArray()) {
            result += c;
        }
        return result;
    }
}
