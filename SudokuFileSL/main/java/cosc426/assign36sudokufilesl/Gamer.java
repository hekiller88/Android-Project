package cosc426.assign36sudokufilesl;


/**
 * Created by lhe on 10/22/17.
 */

public class Gamer {

    public static boolean check(int[][] board, int x, int y)
    {
        int a, b, i, j;

        for (j = 0; j < 9; j++)
            if (j != y && board[x][j] == board[x][y])
                return false;

        for (i = 0; i < 9; i++)
            if (i != x && board[i][y] == board[x][y])
                return false;

        a = (x/3)*3; b = (y/3)*3;
        for (i = 0; i < 3; i++)
            for (j = 0; j < 3; j++)
                if ((a + i != x) && (b + j != y) && board[a+i][b+j] == board[x][y])
                    return false;

        return true;
    }

    public static boolean isWin(int[][] board)
    {
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                if(board[i][j] == 0)
                    return false;

        return true;
    }

}
