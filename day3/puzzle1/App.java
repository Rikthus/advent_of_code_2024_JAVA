import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

    public static void main(String[] args) {
        int result = 0;
        try {
            File file = new File("../input.txt");
            Scanner scanner = new Scanner(file);
            Pattern mulPattern = Pattern.compile("mul\\(([1-9]\\d{0,2}),([1-9]\\d{0,2})\\)");
            Pattern valuePattern = Pattern.compile("[1-9]\\d{0,2}");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Matcher mulMatcher = mulPattern.matcher(line);

                while (mulMatcher.find()) {
                    String substr = mulMatcher.group();
                    result += makeMul(valuePattern, substr);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println(result);
    }

    private static int makeMul(Pattern pattern, String expr) {
        Matcher valueMatcher = pattern.matcher(expr);
        valueMatcher.find();
        int a = Integer.parseInt(valueMatcher.group());

        valueMatcher.find();
        int b = Integer.parseInt(valueMatcher.group());

        return a * b;
    }
}