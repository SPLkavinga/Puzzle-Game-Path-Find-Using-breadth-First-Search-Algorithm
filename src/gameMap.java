//Name: S.P.Lahiru Kavinga
//Student ID: 20211349
//UOW ID: w1954049

class GameMap {
    private char[][] map;
    private int startRow, startColumn; // set Starting row and column
    private int finishRow, finishColumn; // set Finishing row and column

    public GameMap(char[][] map) {
        this.map = map;
        findPositions();
    }

    private void findPositions() {
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[row].length; column++) {
                if (map[row][column] == 'S') { //find the start position
                    startRow = row;
                    startColumn = column;
                } else if (map[row][column] == 'F') {  //find the end position
                    finishRow = row;
                    finishColumn = column;
                }
            }
        }
    }

    // creating getter for the encapsulation variables to access in other classes
    public char[][] getMap() {
        return map;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public int getFinishRow() {
        return finishRow;
    }

    public int getFinishColumn() {
        return finishColumn;
    }
}
