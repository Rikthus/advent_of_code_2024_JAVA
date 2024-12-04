import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class App {

    public static void main(String[] args) {
        int nbWords = 0;

        try {
            File file = new java.io.File("../input.txt");
            Scanner scanner = new Scanner(file);

            char[][] matrix = fileTo2DArray(scanner);

            nbWords = countMatrixMAS(matrix);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("There is " + nbWords + " XMAS words in this file.");
    }

    private static char[][] fileTo2DArray(Scanner scanner) {
        ArrayList<char[]> charMatrix = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            charMatrix.add(line.toCharArray());
        }

        return charMatrix.toArray(new char[charMatrix.size()][]);
    }

    private static int countMatrixMAS(char[][] matrix) {
        int nbMAS = 0;

        int xLen = matrix[0].length;
        int yLen = matrix.length;

        for (int i = 1; i < yLen - 1; ++i) {
            for (int j = 1; j < xLen - 1; ++j) {
                if (matrix[i][j] == 'A') {
                    if (findMatrixMAS(matrix, i, j))
                        ++nbMAS;
                }
            }
        }
        return nbMAS;
    }

    private static boolean findMatrixMAS(char[][] matrix, int y, int x) {
        if (findLeftRightDiagonalMAS(matrix, y, x) && findRightLeftDiagonalMAS(matrix, y, x))
            return true;
        else
            return false;
    }

    private static boolean findLeftRightDiagonalMAS(char[][] matrix, int y, int x) {
        if ((matrix[y - 1][x - 1] == 'M' && matrix[y + 1][x + 1] == 'S')
                || (matrix[y - 1][x - 1] == 'S' && matrix[y + 1][x + 1] == 'M'))
            return true;
        else
            return false;
    }

    private static boolean findRightLeftDiagonalMAS(char[][] matrix, int y, int x) {
        if ((matrix[y - 1][x + 1] == 'M' && matrix[y + 1][x - 1] == 'S')
                || (matrix[y - 1][x + 1] == 'S' && matrix[y + 1][x - 1] == 'M'))
            return true;
        else
            return false;
    }
}