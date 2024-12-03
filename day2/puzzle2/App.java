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

        boolean isJokerUsed = false;
        boolean isDescending = false;

        // MISSING LOGIC FOR FIRST 2 ELEMENTS CURRENTLY BAD ??
        if (levels[0] < levels[1])
            isDescending = false;
        else if (levels[0] > levels[1])
            isDescending = true;
        else
            return false;
        // if (levels[0] == levels[1] || Math.abs(levels[0] - levels[1]) > 3) {
        // if (levels.length < 3)
        // return false;

        // if (levels[1] < levels[2])
        // isDescending = false;
        // else if (levels[1] > levels[2])
        // isDescending = true;
        // if (levels[1] == levels[2] || Math.abs(levels[1] - levels[2]) > 3)
        // return false;

        // isJokerUsed = true;
        // }

        for (int i = 1; i < levels.length - 1; ++i) {
            int diff = levels[i] - levels[i + 1];

            if (diff == 0 || diff < 0 && isDescending || diff > 0 && !isDescending || Math.abs(diff) > 3) {
                if (isJokerUsed)
                    return false;

                int newDiff = levels[i - 1] - levels[i + 1];
                if (newDiff == 0 || newDiff < 0 && isDescending || newDiff > 0 && !isDescending
                        || Math.abs(newDiff) > 3) {
                    if (i + 2 == levels.length)
                        return false;

                    newDiff = levels[i] - levels[i + 2];
                    if (newDiff == 0 || newDiff < 0 && isDescending || newDiff > 0 && !isDescending
                            || Math.abs(newDiff) > 3)
                        return false;

                    i++;
                }
                isJokerUsed = true;
            }
        }
        return true;
    }
}