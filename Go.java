
import java.util.Scanner;

class GoPiece{
    //attributes
    private String color;
    private int row;
    private int col;
    public boolean Alive;


    //constructor
    public GoPiece(String color, int row, int col){
        this.color = color;
        this.row = row;
        this.col = col;
    }

    //methods
    public String GetColor(){
        return color;
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

    

    static String[][] goBoard = new String[9][9];
    static String[][] otherBoard = {
        {null, null, "X", null, null, null, null, null, null},
        {null, "X", "O", "X", "X", null, null, null, null},
        {null, "X", null, null, "O", "X", null, null, null},
        {null, "X", "O", "O", "O", "X", null, null, null},
        {null, "x", "O", null, "O", "X", null, null, null},
        {null, null, "X", "O", "O", "X", null, null, null},
        {null, null, null, "X", "X", null, null, null, null},
        {null, null, null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null, null, null},
    };


    public static boolean isAlive(GoPiece piece){
        int enemyNeighbors = 0;
        int neighborCount = 0;
        //if piece is breathing
        if(piece.GetRow() - 1 >= 0){ //is neighbor in bounds
            neighborCount += 1;//there is a neighbor or empty space here
            if(goBoard[piece.GetRow() - 1][piece.GetCol()] != null){
                if(!piece.GetColor().equals(goBoard[piece.GetRow() - 1][piece.GetCol()])){//piece is opposing color
                    enemyNeighbors += 1;
                }
            }
        }

        if(piece.GetRow() + 1 <= 8){
            neighborCount += 1;
            if(goBoard[piece.GetRow() + 1][piece.GetCol()] != null){
                if(!piece.GetColor().equals(goBoard[piece.GetRow() + 1][piece.GetCol()])){
                    enemyNeighbors += 1;
                }
            }
        }

        if(piece.GetCol() - 1 >= 0){
            neighborCount += 1;
            if(goBoard[piece.GetRow()][piece.GetCol() - 1] != null){
                if(!piece.GetColor().equals(goBoard[piece.GetRow()][piece.GetCol() - 1])){
                    enemyNeighbors += 1;
                }
            }
           
        }

        if(piece.GetCol() + 1 <= 8){
            neighborCount += 1;
            if(goBoard[piece.GetRow()][piece.GetCol() + 1] != null){
                if(!piece.GetColor().equals(goBoard[piece.GetRow()][piece.GetCol() + 1])){
                    enemyNeighbors += 1;
                }
            }
        
        }

        if(enemyNeighbors != neighborCount){ //piece can breathe and is alive
            return true;
        }
        else{                             
            return false; //piece is dead   
        }
        }
    static boolean[][] lives = new boolean[9][9];
    static boolean[][] territory = new boolean[9][9];
    static boolean[][] beenChecked = new boolean[9][9];


    
    public static void main(String[] args) {

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

            GoPiece piece = new GoPiece(((turn) ? "Black" : "White"), moveY, moveX);

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

            }

            turn = !turn;
        }
        scn.close();

    

}
}




