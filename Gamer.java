package cosc426.assign41flingslidingboard;

/**
 * Created by lhe on 11/22/17.
 */

import java.util.ArrayList;

/**
 * Created by lhe on 10/13/17.
 */

public class Gamer {

    private static final int SIZE = 3;

    private int[][] currentMat;
    private int[][] goalMat;

    //randomly generate 2 mat
    public Gamer()
    {
        currentMat = generate3x3();

        //goal = 1,2,3,
        //       4,5,6,
        //       7,8,0
        goalMat = new int[SIZE][SIZE];
        int i = 0;
        while(i < 9)
        {
            goalMat[i / SIZE][i % SIZE] = i + 1;
            i++;
        }
        goalMat[SIZE - 1][SIZE - 1] = 0;
    }

    //accessor of currentMat
    public int[][] getCurrentMat()
    {
        return currentMat;
    }

    public void setCurrentMat(int[][] mat) {

        currentMat = new int[SIZE][SIZE];

        for(int i = 0; i < SIZE; i++)
            for(int j = 0; j < SIZE; j++)
                currentMat[i][j] = mat[i][j];
    }

    //accessor of goalMat
    public int[][] getGoalMat()
    {
        return goalMat;
    }

    //to check if the game is winning
    public boolean isWin()
    {
        for(int i = 0; i < SIZE; i++)
            for(int j = 0; j < SIZE; j++)
                if(currentMat[i][j] != goalMat[i][j])
                    return false;

        return true;
    }


    //check if (i,j) is the empty slot
    public boolean isEmpty(int i, int j)
    {
        int row = 0, col = 0;

        OUT:for(row = 0; row < SIZE; row++)
            for(col = 0; col < SIZE; col++)
                if(currentMat[row][col] == 0)
                    break OUT;

        if(i == row && j == col)
            return true;
        else
            return false;
    }

    //check if (i,j) is the neighbour of the empty slot
    public boolean isNeighbour(int i, int j)
    {
        //empty row, col
        int row = 0, col = 0;

        OUT:for(row = 0; row < SIZE; row++)
            for(col = 0; col < SIZE; col++)
                if(currentMat[row][col] == 0)
                    break OUT;

        int deltaRow = i - row >= 0 ? i - row: -(i - row);
        int deltaCol = j - col >= 0 ? j - col: -(j - col);

        if(deltaRow == 2)
            return false;

        if(deltaCol == 2)
            return false;

        if(deltaRow == 1 && deltaCol == 1)
            return false;

        if(deltaRow == 0 && deltaCol == 0)
            return false;

        return true;
    }

    public void swapBoard(int startRow, int startCol, int destRow, int destCol)
    {
        int tmp = currentMat[startRow][startCol];
        currentMat[startRow][startCol] = currentMat[destRow][destCol];
        currentMat[destRow][destCol] = tmp;
    }

    //randomly generate a 3x3 2D-arrays with 0 ~ 8, 0 represents the empty slot
    public int[][] generate3x3()
    {
        int size = 9;

        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 0; i < size; i++)
            list.add(i);

        int[] tmp = new int[size];
        int count = 0;          //for tmp array's index

        while(!list.isEmpty() && count <= 9)
        {
            int rand_index = (int)(Math.random()*list.size());
            tmp[count++] = list.get(rand_index);
            list.remove(rand_index);
        }

        int[][] result = new int[size/3][size/3];

        count = 0;

        for(int i = 0; i < size/3 ; i++)
            for(int j = 0; j < size/3 ; j++)
                result[i][j] = tmp[count++];

        return result;
    }

}
