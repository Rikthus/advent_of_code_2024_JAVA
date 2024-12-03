import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        int nbSafeReports = 0;
        try {
            File file = new File("../input.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] levelsString = scanner.nextLine().trim().split(" +");

                Integer[] levels = Arrays.stream(levelsString).map(Integer::valueOf).toArray(Integer[]::new);
                if (isReportSafe(levels))
                    nbSafeReports++;
            }

            System.out.println(nbSafeReports);
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found");
            System.exit(1);
        }
    }

    public static boolean isReportSafe(Integer[] levels) {
        boolean isDescending = false;
        if (levels[0] < levels[1])
            isDescending = false;
        else if (levels[0] > levels[1])
            isDescending = true;
        else
            return false; // if 2 values are equal the report is considered UNSAFE

        for (int i = 0; i < levels.length - 1; ++i) {
            int diff = levels[i] - levels[i + 1];

            if (diff == 0)
                return false;
            else if (diff < 0 && isDescending)
                return false;
            else if (diff > 0 && !isDescending)
                return false;
            else if (Math.abs(diff) > 3)
                return false;
        }

        return true;
    }
}