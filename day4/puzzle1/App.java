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

            nbWords = countMatrixWord("XMAS", matrix);
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

    private static int countMatrixWord(String word, char[][] matrix) {
        int nbWords = 0;

        int firstLetter = word.charAt(0);
        int xLen = matrix[0].length;
        int yLen = matrix.length;

        for (int i = 0; i < yLen; ++i) {
            for (int j = 0; j < xLen; ++j) {
                if (matrix[i][j] == firstLetter) {
                    nbWords += findMatrixWord(word, matrix, i, j);
                }
            }
        }
        return nbWords;
    }

    private static int findMatrixWord(String word, char[][] matrix, int y, int x) {
        int nbwords = 0;

        nbwords += findHorizontalWords(word, matrix, y, x);
        nbwords += findVerticalWords(word, matrix, y, x);
        nbwords += findLeftRightDiagonalWords(word, matrix, y, x);
        nbwords += findRightLeftDiagonalWords(word, matrix, y, x);

        return nbwords;
    }

    private static int findHorizontalWords(String word, char[][] matrix, int y, int x) {
        int nbWords = 2;
        int wordLen = word.length();

        if (x + wordLen <= matrix[y].length) {
            for (int i = 1; i < wordLen; ++i) {
                if (matrix[y][x + i] != word.charAt(i)) {
                    --nbWords;
                    break;
                }
            }
        } else {
            --nbWords;
        }

        if (x >= wordLen - 1) {
            for (int i = 1; i < wordLen; ++i) {
                if (matrix[y][x - i] != word.charAt(i)) {
                    --nbWords;
                    break;
                }
            }
        } else {
            --nbWords;
        }
        return nbWords;
    }

    private static int findVerticalWords(String word, char[][] matrix, int y, int x) {
        int nbWords = 2;
        int wordLen = word.length();

        if (y + wordLen <= matrix.length) {
            for (int i = 1; i < wordLen; ++i) {
                if (matrix[y + i][x] != word.charAt(i)) {
                    --nbWords;
                    break;
                }
            }
        } else {
            --nbWords;
        }

        if (y >= wordLen - 1) {
            for (int i = 1; i < wordLen; ++i) {
                if (matrix[y - i][x] != word.charAt(i)) {
                    --nbWords;
                    break;
                }
            }
        } else {
            --nbWords;
        }

        return nbWords;
    }

    private static int findLeftRightDiagonalWords(String word, char[][] matrix, int y, int x) {
        int nbWords = 2;
        int wordLen = word.length();

        if (y + wordLen <= matrix.length && x + wordLen <= matrix[y].length) {
            for (int i = 1; i < wordLen; ++i) {
                if (matrix[y + i][x + i] != word.charAt(i)) {
                    --nbWords;
                    break;
                }
            }
        } else {
            --nbWords;
        }

        if (y >= wordLen - 1 && x >= wordLen - 1) {
            for (int i = 1; i < wordLen; ++i) {
                if (matrix[y - i][x - i] != word.charAt(i)) {
                    --nbWords;
                    break;
                }
            }
        } else {
            --nbWords;
        }

        return nbWords;
    }

    private static int findRightLeftDiagonalWords(String word, char[][] matrix, int y, int x) {
        int nbWords = 2;
        int wordLen = word.length();

        if (y >= wordLen - 1 && x + wordLen <= matrix[y].length) {
            for (int i = 1; i < wordLen; ++i) {
                if (matrix[y - i][x + i] != word.charAt(i)) {
                    --nbWords;
                    break;
                }
            }
        } else {
            --nbWords;
        }

        if (y + wordLen <= matrix.length && x >= wordLen - 1) {
            for (int i = 1; i < wordLen; ++i) {
                if (matrix[y + i][x - i] != word.charAt(i)) {
                    --nbWords;
                    break;
                }
            }
        } else {
            --nbWords;
        }

        return nbWords;
    }
}