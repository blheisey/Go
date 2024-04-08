
import java.util.Scanner;


public class Go {

    static boolean[][] beenChecked = new boolean[9][9];
    static boolean[][] alwaysFalse = new boolean[9][9];

    static String[][] goBoard = new String[9][9];

    public static void printBoard(){
        System.out.println("  0 1 2 3 4 5 6 7 8");
            for (int i = 0; i < goBoard.length; i++) {
                System.out.print(i + " ");
                for (int j = 0; j < goBoard[i].length; j++) {
                    if (goBoard[i][j] == null) {
                        if (j == 0)
                            System.out.print("|");
                        else
                            System.out.print("-|");

                    } else {
                        System.out.print(goBoard[i][j]);
                    }

                }
                System.out.println();
            }
    }
    

    public static boolean isAlive(int row, int col, String piece){ //if piece is out of bounds (no neighbor there) or already been checked
        if (row <= -1 || row >= 9 || col <= -1 || col >= 9 || beenChecked[row][col]) {
            return false;
            }
            if (goBoard[row][col] == null) { 
            return true;
            }
            // if (goBoard[row][col] != piece) {
            // return false;
            //}
            beenChecked[row][col] = true;
            return isAlive(row-1, col, piece) || isAlive(row+1, col, piece) || 
            isAlive(row, col-1, piece) || isAlive(row, col+1, piece);
    }

    
    
    public static void main(String[] args) {

        int capturedWhite = 0;
        int capturedBlack = 0;

        Boolean cont = true, turn = true;
        int moveX, moveY;

        Scanner scn = new Scanner(System.in);

        int dimension = goBoard.length - 1;

        while (cont) {

            printBoard();

            System.out.println("\n" + ((turn) ? "Black" : "White") + "'s turn to move!\n\n");

            System.out.println("Please enter the x coordinate where you'd like to place a "
                    + ((turn) ? "Black" : "White") + " piece");

            moveX = scn.nextInt();

            System.out.println("Please enter the y coordinate where you'd like to place a "
                    + ((turn) ? "Black" : "White") + " piece");

            moveY = scn.nextInt();

            if ((moveY > dimension || moveY < 0) || (moveX > dimension || moveX < 0)) {
                System.out.println(
                        "\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\nOut of Bounds!\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n");
                continue;
            } else {

                if (goBoard[moveY][moveX] == null) {
                        goBoard[moveY][moveX] = ((turn) ? "X" : "O");
                        printBoard();
                        System.out.println(isAlive(moveY, moveX, (turn ? "X" : "O"))); 
                } else {
                    System.out.println(
                            "\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\nThere is already a piece there!\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n");
                    continue;
                }

                for (int i = 0; i < goBoard.length; i++){ //iterating over Go board
                    for (int j = 0; j < goBoard[i].length; j++){
                        if(goBoard[i][j] != null){ //if there's a piece there
                            if(!isAlive(i, j, ((turn) ? "X" : "O"))){ //currentPiece is dead
                                if(goBoard[i][j].equals("x")){ //piece is black
                                    capturedBlack += 1;
                                }else{
                                    capturedWhite += 1;
                                }
                                goBoard[i][j] = null; //remove captured piece from board
                            }
                        }
                    }
                }
                beenChecked = alwaysFalse.clone();

            }

            turn = !turn;
        }
        scn.close();

    

}
}




