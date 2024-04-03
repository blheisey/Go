import java.util.Scanner;

public class GoBoard {
    static String[][] goBoard = new String[9][9];
        

        public static void main(String[] args){
        System.out.println(" 0 1 2 3 4 5 6 7 8 ");
         
                for (int i = 0; i <= goBoard.length; i++) {
                        System.out.print(i + " "); 
                        for (int j = 0; j <= goBoard.length; j++) {
                                if (goBoard[i][j] == null) {
                                        if (j == 0) {
                                                System.out.println("|");
                                        }
                                        else {
                                                System.out.print("-|");
                                        }
          
                                } else {
                                        System.out.print(goBoard[i][j]);
                                }
                }               
                System.out.println();
                }

        Scanner myObj = new Scanner(System.in);
        System.out.println("Please enter an x-coordinate");
        moveX = myObj.nextInt();
        System.out.println("Please enter a y-coordinate");
        moveY = myObj.nextInt();
        myObj.close();
}
}

