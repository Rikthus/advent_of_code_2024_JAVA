import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class A {

    public static void main(String[] args) {

        List<Integer> firstList = new ArrayList<>() {
        };
        List<Integer> secondList = new ArrayList<>() {
        };

        long solution = 0;

        try {
            File myFile = new File("../input.txt");
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] splittedLine = line.split(" +");

                firstList.add(Integer.parseInt(splittedLine[0]));
                secondList.add(Integer.parseInt(splittedLine[1]));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to open file");
            e.printStackTrace();
        }

        Collections.sort(firstList);
        Collections.sort(secondList);

        for (int i = 0; i < firstList.size(); ++i) {
            solution += Math.abs(firstList.get(i) - secondList.get(i));
        }

        System.out.println(solution);
    }
}
