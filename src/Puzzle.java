//Name: S.P.Lahiru Kavinga
//Student ID: 20211349
//UOW ID: w1954049


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class Puzzle {
    //creating a method to access the data from the file
    public static GameMap readDataFromFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        ArrayList<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();

        char[][] map = new char[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            map[i] = lines.get(i).toCharArray();
        }

        return new GameMap(map);
    }


    public static List<String> PathFinder(GameMap gameMap) {
        int rows = gameMap.getMap().length;
        int columns = gameMap.getMap()[0].length;
        boolean[][] visited = new boolean[rows][columns];
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        Map<String, String> parentMap = new HashMap<>();
        Map<String, Integer[]> parentPosMap = new HashMap<>();

        int[] start = {gameMap.getStartRow(), gameMap.getStartColumn(), 0}; // Include step count as third element for priority queue
        queue.add(start);
        visited[start[0]][start[1]] = true;
        String startKey = Arrays.toString(new int[] {start[0], start[1]});
        parentMap.put(startKey, null);
        parentPosMap.put(startKey, new Integer[]{start[0], start[1]});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int column = current[1];
            int stepCount = current[2];

            if (row == gameMap.getFinishRow() && column == gameMap.getFinishColumn()) {
                return reconstructPath(parentMap, parentPosMap, new int[]{row, column});
            }

            int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // set the moves as Right, Down, Left, Up
            for (int[] dir : directions) {
                int nextRow = row, nextColumn = column;
                while (true) {
                    int checkRow = nextRow + dir[0];
                    int checkColumn = nextColumn + dir[1];
                    if (checkRow < 0 || checkRow >= rows || checkColumn < 0 || checkColumn >= columns || gameMap.getMap()[checkRow][checkColumn] == '0') {
                        break;
                    }
                    nextRow = checkRow;
                    nextColumn = checkColumn;

                    if (gameMap.getMap()[checkRow][checkColumn] == 'F') {
                        // If it is find 'F', add that point as final destination and stop moving
                        nextRow = checkRow;
                        nextColumn = checkColumn;
                        break;
                    }

                }
                if (!visited[nextRow][nextColumn]) {
                    visited[nextRow][nextColumn] = true;
                    int[] nextPosition = new int[]{nextRow, nextColumn, stepCount + 1};
                    queue.add(nextPosition);
                    String key = Arrays.toString(new int[] {nextRow, nextColumn});
                    String parentKey = Arrays.toString(new int[] {row, column});
                    parentMap.put(key, parentKey);
                    parentPosMap.put(key, new Integer[]{nextRow, nextColumn});
                }
            }
        }

        return null; // If there is no path

    }

    private static List<String> reconstructPath(Map<String, String> parentMap, Map<String, Integer[]> parentPosMap, int[] target) {
        List<String> path = new ArrayList<>();
        String currentKey = Arrays.toString(new int[]{target[0], target[1]});

        while (currentKey != null && parentMap.get(currentKey) != null) {
            String parentKey = parentMap.get(currentKey);
            if (parentKey != null) {
                Integer[] currentPosition = parentPosMap.get(currentKey);
                Integer[] parentPosition = parentPosMap.get(parentKey);
                path.add(MovingPath(parentPosition, currentPosition));
            }
            currentKey = parentKey;
        }

        Collections.reverse(path);
        return path;
    }

    private static String MovingPath(Integer[] from, Integer[] to) {
        String cyan = "\033[1;94m";
        String defaultColor = "\u001B[0m";

        if (from == null) {
            // Print column first then row
            return cyan + "Start at (" + (to[1] + 1) + "," + (to[0] + 1) + ")" + defaultColor;
        }
        if (from[0].equals(to[0])) {
            if (from[1] < to[1]) {
                return cyan + "Move right to (" + (to[1] + 1) + "," + (to[0] + 1) + ")" + defaultColor;
            } else {
                return cyan + "Move left to (" + (to[1] + 1) + "," + (to[0] + 1) + ")" + defaultColor;
            }
        } else {
            if (from[0] < to[0]) {
                return cyan + "Move down to (" + (to[1] + 1) + "," + (to[0] + 1) + ")" + defaultColor;
            } else {
                return cyan + "Move up to (" + (to[1] + 1) + "," + (to[0] + 1) + ")" + defaultColor;
            }
  }
}
}