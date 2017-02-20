package mco364;

import java.util.Scanner;

/**
 * @author yrobi
 */

class Board {
    private int row;
    private int column;
    boolean b[][];

    Board(int width, int depth) {
        this.row = width;
        this.column = depth;
        b = new boolean[width][depth];
    }

    Board(Board b) {
        this(b.row, b.column);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void printBoard() {
        String horizontalLine = new String(new char[row * 4]).replace("\0", "-");
        System.out.println(horizontalLine);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(b[i][j] ? " X |" : "   |");
            }
            System.out.println();
            System.out.println(horizontalLine);
        }
    }
}

public class GameOfLife {
    private Board b;
    private int neigbors;
    private boolean alive;

    
    public int getNeighbors(){
    return neigbors;
    }
    
    public boolean getAlive(){
    return alive;
    }
    public GameOfLife() {
        play();
    }

    public final void play() {
        chooseOscillator();
        b.printBoard();
    }

    private void InitializeBoard(int row, int column) {
        this.b = new Board(row, column);
        for (int i = 0; i < b.getRow(); i++) {
            for (int j = 0; j < b.getColumn(); j++) {
                b.b[i][j] = false;
            }
        }
    }

    public int neighborCount(int row, int col) {
        int aliveNeighbors = 0;
        if (b.b[row - 1][col - 1] == true) {
            aliveNeighbors++;
        }
        if (b.b[row - 1][col] == true) {
            aliveNeighbors++;
        }
        if (b.b[row - 1][col + 1] == true) {
            aliveNeighbors++;
        }
        if (b.b[row][col - 1] == true) {
            aliveNeighbors++;
        }
        if (b.b[row][col + 1] == true) {
            aliveNeighbors++;
        }
        if (b.b[row + 1][col - 1] == true) {
            aliveNeighbors++;
        }
        if (b.b[row + 1][col] == true) {
            aliveNeighbors++;
        }
        if (b.b[row + 1][col + 1] == true) {
            aliveNeighbors++;
        }
        neigbors=aliveNeighbors;
        return aliveNeighbors;
    }

    // second piece of logic behind switching states
    public boolean AliveNextGeneration(int row, int col) {
        boolean value = b.b[row][col];
        if (b.b[row][col] == true) {
            if (neighborCount(row, col) >= 4) {
                value = false;
            }
            if (neighborCount(row, col) <= 1) {
                value = false;
            }
        } else if (b.b[row][col] == false) {
            if (neighborCount(row, col) == 3) {
                value = true;
            }
        }
        alive=value;
        return value;
    }

    public void nextBoardIteration() {
        System.out.println();
        Board b2 = new Board(b);
        for (int i = 1; i < b.getRow() - 1; i++) {
            for (int j = 1; j < b.getColumn() - 1; j++) {
                if (AliveNextGeneration(i, j)) {
                    b2.b[i][j] = true;
                }
            }
        }
        b = b2;
        b.printBoard();
    }

    private void chooseOscillator() {
        System.out.println("please pick between 5 patterns:"
                + "1- Blinker, 2-Toad, 3-Beacon,4-Pulsar,5-Pentadecathlon");
        Scanner kb = new Scanner(System.in);
        int oscillatorChoice = kb.nextInt();
        while (oscillatorChoice < 1 && oscillatorChoice > 5) {
            System.out.println("that was an invalid choice; "
                    + "please make a correct one");
        }
        //neatening console output
        for (int i = 0; i < 5; i++) {
            System.out.println( );
        }
        if (oscillatorChoice == 1) {
            InitializeBoard(5, 5);
            BlinkerFill();
            return;
        }
        if (oscillatorChoice == 2) {
            InitializeBoard(6, 6);
            ToadFill();
            return;
        }

        if (oscillatorChoice == 3) {
            InitializeBoard(6, 6);
            BeaconFill();
            return;
        }

        if (oscillatorChoice == 4) {
            InitializeBoard(17, 17);
            PulsarFill();
            return;
        }

        if (oscillatorChoice == 5) {
            InitializeBoard(18, 11);
            PentadecathlonFill();
        }
    }

    private void BlinkerFill() {
        b.b[2][1] = true;
        b.b[2][2] = true;
        b.b[2][3] = true;
    }

    private void PulsarFill() {
        b.b[1][5] = true;
        b.b[2][5] = true;
        b.b[3][5] = true;
        b.b[3][6] = true;
        b.b[1][11]= true;
        b.b[2][11]= true;
        b.b[3][11]= true;
        b.b[3][10]= true;
        b.b[5][1] = true;
        b.b[5][2] = true;
        b.b[5][3] = true;
        b.b[6][3] = true;
        b.b[5][6] = true;
        b.b[5][7] = true;
        b.b[6][5] = true;
        b.b[6][7] = true;
        b.b[7][5] = true;
        b.b[7][6] = true;
        b.b[5][9] = true;
        b.b[6][9] = true;
        b.b[5][10]= true;
        b.b[6][11]= true;
        b.b[7][10]= true;
        b.b[7][11]= true;
        b.b[5][13]= true;
        b.b[5][14]= true;
        b.b[5][15]= true;
        b.b[6][13]= true;
        b.b[9][3] = true;
        b.b[10][1]= true;
        b.b[10][2]= true;
        b.b[10][3]= true;
        b.b[9][5] = true;
        b.b[9][6] = true;
        b.b[10][5]= true;
        b.b[10][7]= true;
        b.b[11][6]= true;
        b.b[11][7]= true;
        b.b[9][10]= true;
        b.b[9][11]= true;
        b.b[10][9]= true;
        b.b[10][11]= true;
        b.b[11][9]= true;
        b.b[11][10]= true;
        b.b[10][13]= true;
        b.b[11][13]= true;
        b.b[11][14]= true;
        b.b[11][15]= true;
        b.b[10][13]= true;
        b.b[11][13]= true;
        b.b[11][14]= true;
        b.b[11][15]= true;
        b.b[13][5] = true;
        b.b[13][6] = true;
        b.b[14][5] = true;
        b.b[15][5] = true;
        b.b[13][10]= true;
        b.b[13][11]= true;
        b.b[14][11]= true;
        b.b[15][11]= true;
    }

    private void ToadFill() {
        b.b[3][1] = true;
        b.b[2][2] = true;
        b.b[3][2] = true;
        b.b[2][3] = true;
        b.b[3][3] = true;
        b.b[2][4] = true;
    }

    private void BeaconFill() {
        b.b[1][1] = true;
        b.b[1][2] = true;
        b.b[2][1] = true;
        b.b[4][3] = true;
        b.b[3][4] = true;
        b.b[4][4] = true;
    }

    private void PentadecathlonFill() {
        b.b[3][4] = true;
        b.b[3][5] = true;
        b.b[3][6] = true;
        b.b[4][5] = true;
        b.b[5][5] = true;
        b.b[6][4] = true;
        b.b[6][5] = true;
        b.b[6][6] = true;
        b.b[8][4] = true;
        b.b[8][5] = true;
        b.b[8][6] = true;
        b.b[9][4] = true;
        b.b[9][5] = true;
        b.b[9][6] = true;
        b.b[11][4]= true;
        b.b[11][5]= true;
        b.b[11][6]= true;
        b.b[12][5]= true;
        b.b[13][5]= true;
        b.b[14][4]= true;
        b.b[14][5]= true;
        b.b[14][6]= true;
    }
}