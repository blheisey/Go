
import java.util.Scanner;

class GoPiece{
    //attributes
    private String color;
    private int row;
    private int col;
    public boolean Alive;


    //constructor
    public GoPiece(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int GetRow(){
        return row;
    }

    public int GetCol(){
        return col;
    }

    public String SetPiece(){
        if(color.equals("Black")){
            return("X");
        }else{
            return("O");
        }
    }
       
        
}


public class Go {

    static boolean[][] beenChecked = new boolean[9][9];

    static String[][] goBoard = new String[9][9];
    

    public static boolean isAlive(GoPiece piece){ //if piece is out of bounds (no neighbor there) or already been checked
            if (piece.GetCol() <= -1 || piece.GetCol() >= 9 || piece.GetRow() <= -1 || piece.GetRow() >= 9 || beenChecked[piece.GetCol()][piece.GetRow()]) {
            return false;
            }
            if (goBoard[piece.GetRow()][piece.GetCol()] == null) { //there is an empty space (piece can breathe)
            return true;
            }
            if (!goBoard[piece.GetRow()][piece.GetCol()].equals(piece)){ //there is an enemy
            return false;
            }
            beenChecked[piece.GetRow()][piece.GetCol()] = true;
            return isAlive(piece.GetRow()-1, piece.GetCol(), piece) || checkLiberties(x+1, y, piece) || 
            checkLiberties(x, y-1, piece) || checkLiberties(x, y+1, piece);
            
        

    }
    static boolean[][] lives = new boolean[9][9];
    static boolean[][] territory = new boolean[9][9];
    


    
    public static void main(String[] args) {

        int capturedWhite = 0;
        int capturedBlack = 0;

        Boolean cont = true, turn = true;
        int moveX, moveY;

        Scanner scn = new Scanner(System.in);

        int dimension = goBoard.length - 1;

        while (cont) {

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

            System.out.println("\n" + ((turn) ? "Black" : "White") + "'s turn to move!\n\n");

            System.out.println("Please enter the x coordinate where you'd like to place a "
                    + ((turn) ? "Black" : "White") + " piece");

            moveX = scn.nextInt();

            System.out.println("Please enter the y coordinate where you'd like to place a "
                    + ((turn) ? "Black" : "White") + " piece");

            moveY = scn.nextInt();

            GoPiece piece = new GoPiece(moveY, moveX);

            if ((moveY > dimension || moveY < 0) || (moveX > dimension || moveX < 0)) {
                System.out.println(
                        "\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\nOut of Bounds!\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n");
                continue;
            } else {

                if (goBoard[moveY][moveX].equals("|") || goBoard[moveY][moveX].equals("-|")) {
                    goBoard[moveY][moveX] = piece.SetPiece();
                    System.out.println(isAlive(piece));
                } else {
                    System.out.println(
                            "\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\nThere is already a piece there!\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n");
                    continue;
                }

                for (int i = 0; i < goBoard.length; i++){ //iterating over Go board
                    for (int j = 0; j < goBoard[i].length; j++){
                        if(goBoard[i][j] != null){ //if there's a piece there
                            GoPiece currentPiece = new GoPiece(i, j); //create GoPiece object
                            if(isAlive(currentPiece) == false){ //currentPiece is dead
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

            }

            turn = !turn;
        }
        scn.close();

    

}
}




