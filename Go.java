package Go;
import java.util.Scanner;

public class Go {

    static String[][] goBoard = new String[9][9];
    static String[][] otherBoard = {
        {null, null, "X", "X", null, null, null, null, null},
        {null, "X", "O", "O", "X", null, null, null, null},
        {null, "X", "O", null, "O", "X", null, null, null},
        {null, "X", "O", "O", "O", "X", null, null, null},
        {null, null, "X", "O", "O", "X", null, null, null},
        {null, null, null, "X", "X", null, null, null, null},
        {null, null, null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null, null, null},
    };
    static boolean[][] lives = new boolean[9][9];
    static boolean[][] territory = new boolean[9][9];
    static boolean[][] beenChecked = new boolean[9][9];


    public static boolean isAlive(int row, int col){
        //corner piece
        if(row == 0 && col == 0 || row == 8 && col == 8 || row == 0 && col == 8 || row == 8 && col == 0){

        }
    }

    public static boolean didCapture(int moveX, int moveY, boolean turn){

        //for a piece placed in the corner
        if(moveX == 0 && moveY == 0 || moveX == 8 && moveY == 0 || moveX == 0 && moveY == 8 || moveX == 8 && moveY == 8){
            
        }
        //check if a piece not in the corner or edge has been captured
        if(turn == true){ //black's piece               //check black piece's neighbor's to avoid illegal suicide move
            if(goBoard[moveY + 1][moveX].equals("O") && goBoard[moveY][moveX + 1].equals("O") && goBoard[moveY - 1][moveX].equals("O") && goBoard[moveY][moveX - 1].equals("O")){
                return true;
            }
            else{
                return true;
            }
            //so
        }
    
        else{
            return false;
        }
    }//
    public static void main(String[] args) {

        Boolean cont = true, turn = true;
        int moveX, moveY;

        Scanner scn = new Scanner(System.in);

        int dimension = otherBoard.length - 1;

        while (cont) {

            System.out.println("  0 1 2 3 4 5 6 7 8");
            for (int i = 0; i < otherBoard.length; i++) {
                System.out.print(i + " ");
                for (int j = 0; j < otherBoard[i].length; j++) {
                    if (otherBoard[i][j] == null) {
                        if (j == 0)
                            System.out.print("|");
                        else
                            System.out.print("-|");

                    } else {
                        System.out.print(otherBoard[i][j]);
                    }

                }
                System.out.println();
            }

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

                if (otherBoard[moveY][moveX] == null) {
                    otherBoard[moveY][moveX] = ((turn) ? "-●" : "-◯");
                } else {
                    System.out.println(
                            "\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\nThere is already a piece there!\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n");
                    continue;
                }

            }

            turn = !turn;
        }
        scn.close();

    }

}




