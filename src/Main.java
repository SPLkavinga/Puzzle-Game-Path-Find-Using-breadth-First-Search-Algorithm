//Name: S.P.Lahiru Kavinga
//Student ID: 20211349
//UOW ID: w1954049

import java.io.IOException;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        String red= "\u001B[31m";
        String yellow= "\u001B[33m";
        String cyan = "\033[1;94m";
        String green= "\u001B[1;32m";
        String defaultColor = "\u001B[0m";

        try {
            long startTime = System.currentTimeMillis();// start the timer
            GameMap gameMap = Puzzle.readDataFromFile("C:\\Users\\asus\\OneDrive\\Desktop\\W1954049(20211349)_Lahiru_Kavinga_AlgorithmCourseworkCode\\src\\benchmark_series\\puzzle_20.txt");


            List<String> path =Puzzle.PathFinder(gameMap);

            if (path != null) {
                int stepNumber = 1;
                System.out.println(yellow+"\n\t  path found."+defaultColor);
                System.out.println(stepNumber +cyan+ ". Start at (" + (gameMap.getStartColumn() + 1) + "," + (gameMap.getStartRow() + 1) + ")"+defaultColor);
                for (String step : path) {
                    System.out.println(++stepNumber + ". " + step);
                }
                System.out.println(++stepNumber + yellow+". Done!"+defaultColor);

                long EndTime = System.currentTimeMillis(); //stop the timer
                double elapsed = (EndTime - startTime); //Calculate runtime time in milliseconds
                System.out.println(green+"\nRuntime is = " + elapsed + " milliseconds"+defaultColor);
            } else {
                System.out.println(red+"No path found,Try Again!."+defaultColor);
            }
        } catch (IOException e) {
            System.out.println(red+"Error reading map file,Try Again!: " +defaultColor+ e.getMessage());
        }
    }
}