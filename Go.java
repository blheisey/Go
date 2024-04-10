
import java.util.Scanner;


public class Go {

    static boolean[][] beenChecked = new boolean[9][9];
    static boolean[][] alwaysFalse = new boolean[9][9];
    static boolean[][] lives = new boolean[9][9];

    //static String[][] goBoard = new String[9][9];
    static String[][] goBoard =     {
                                        {null,null,null,null,"X",null,null,null,null},
                                        {null,null,null,"X","O","O","X",null,null},
                                        {null,null,null,"X","O","X",null,null,null},
                                        {null,null,null,null,"X",null,null,null,null},
                                        {null,null,null,"X","X",null,null,null,null},
                                        {null,null,"X","O","O","X",null,null,null},
                                        {null,null,null,null,null,null,null,null,null},
                                        {null,null,null,null,null,null,null,null,null},
                                        {null,null,null,null,null,null,null,null,null}
                                    };
    public static void printBoard(){
        System.out.println();
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
    
    public static boolean canBreathe(int row, int col){
        if(row - 1 >= 0){
            if(goBoard[row - 1][col] == null){
                return true; //add if statements
            }
        }

        if(row + 1 <= 8){
            if(goBoard[row + 1][col] == null){
                return true;
            }
        }

        if(col - 1 >= 0){
            if(goBoard[row][col -1] == null){
                return true;
            }
        }

        if(col + 1 <= 8){
            if(goBoard[row][col + 1] == null){
                return true;
            }
        }

        return false;
    }

    public static boolean isAlive(int row, int col){ 
        beenChecked[row][col] = true;
        

        if(canBreathe(row, col)){
            return true;
        }

        if(goBoard[row - 1][col] == goBoard[row][col] && !beenChecked[row - 1][col]){
            return isAlive(row - 1, col);    
        }

        if(goBoard[row][col + 1] == goBoard[row][col] && !beenChecked[row][col + 1]){
            return isAlive(row, col + 1);
        }

        if(goBoard[row + 1][col] == goBoard[row][col] && !beenChecked[row + 1][col]){
            return isAlive(row + 1, col);
        }

        if(goBoard[row][col - 1] == goBoard[row][col] && !beenChecked[row][col - 1]){
            return isAlive(row, col - 1);
        }

        return false;
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
                        
                } else {
                    System.out.println(
                            "\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\nThere is already a piece there!\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n");
                    continue;
                }
                lives = new boolean[9][9];
                for (int i = 0; i < goBoard.length; i++){ //iterating over Go board
                    for (int j = 0; j < goBoard[i].length; j++){
                        if(goBoard[i][j] != null){ //if there's a piece there
                            beenChecked = new boolean[9][9];
                            boolean live = isAlive(i, j);
                            if(!live){ //current piece is dead
                                lives[i][j] = true;
                                if(goBoard[i][j].equals("X")){ //piece is black
                                    capturedBlack += 1;
                                }else{
                                    capturedWhite += 1;
                                }
                            }
                        }
                    }
                }
                
                for (int i = 0; i < lives.length; i++){ //iterating over Go board
                    for (int j = 0; j < lives[i].length; j++){
                        if(lives[i][j] == true){
                            goBoard[i][j] = null;
                        }

                    }
                }
                printBoard();

            }

            turn = !turn;
            System.out.println("Please enter y to keep playing or n to stop");
            String choice = scn.next();
            if(choice.equals("n")){
                cont = false;
            }
        }
        scn.close();

    System.out.println(capturedWhite + " white pieces were captured.");
    System.out.println(capturedBlack + " black pieces were captured.");
}
}




