
import java.util.Scanner;

//changes
public class Go {

    static String[][] goBoard = new String[9][9];
    static String[][] otherBoard = {
        {null, null, "X", "X", null, null, null, null, null},
        {null, "X", "O", "O", "X", null, null, null, null},
        {null, "X", "O", null, "O", "X", null, null, null},
        {null, "X", "O", "O", "O", "X", null, null, null},
        {null, "x", "O", null, "O", "X", null, null, null},
        {null, null, "X", "O", "O", "X", null, null, null},
        {null, null, null, "X", "X", null, null, null, null},
        {null, null, null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null, null, null},
    };
    static boolean[][] lives = new boolean[9][9];
    static boolean[][] territory = new boolean[9][9];
    static boolean[][] beenChecked = new boolean[9][9];


    public static boolean isAlive(int row, int col){
        int enemyNeighbors = 0;
        int neighborCount = 0;
        //if piece is breathing
        if(row - 1 >= 0){
            neighborCount += 1;
            if(!otherBoard[row - 1][col].equals(otherBoard[row][col]) && !otherBoard[row - 1][col].equals("-|")){
                enemyNeighbors += 1;
            }
        }

        if(row + 1 <= 8){
            neighborCount += 1;
            if(!otherBoard[row + 1][col].equals(otherBoard[row][col]) && !otherBoard[row + 1][col].equals("-|")){
                    enemyNeighbors += 1;
            }

        }

        if(col - 1 >= 0){
            neighborCount += 1;
            if(!otherBoard[row][col - 1 ].equals(otherBoard[row][col]) && !otherBoard[row][col -1].equals("-|")){
                enemyNeighbors += 1;
            }
        }

        if(col + 1 <= 8){
            neighborCount += 1;
            if(!otherBoard[row][col + 1].equals(otherBoard[row][col]) && !otherBoard[row][col + 1].equals("-|")){
                enemyNeighbors += 1;
            }
        
        }

        if(enemyNeighbors != neighborCount){ //piece can breathe and is alive
            return true;
        }
        else{                             
            return false; //piece is dead   
        }
    }

    
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




