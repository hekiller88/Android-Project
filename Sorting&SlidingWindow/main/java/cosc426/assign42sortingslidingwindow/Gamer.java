package cosc426.assign42sortingslidingwindow;

import android.util.Log;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by lhe on 11/23/17.
 */

public class Gamer {

    private final int SIZE = 10;
    private int[] numbers;
    private int[] sorted;
    private int swapLeft;

    public Gamer(){

        setNumbers();
        swapLeft = 45;

        sorted = numbers.clone();
        Arrays.sort(sorted);

        Log.w("Log", Arrays.toString(numbers));
    }

    public void setNumbers()
    {
        Random random = new Random();

        numbers = new int[SIZE];
        for(int i = 0; i < SIZE; i++)
        {
            numbers[i] = 1 + random.nextInt(100);
        }
    }

    public int getSwapLeft()
    {
        return swapLeft;
    }

    public int[] getNumbers()
    {
        return numbers;
    }

    //i is the upper slot index in sliding window
    public void swapInWindows(int i)
    {


        int tmp = numbers[i];
        numbers[i] = numbers[i + 1];
        numbers[i + 1] = tmp;

        swapLeft--;
    }

    public boolean isWin()
    {
        if(swapLeft < 0)
            return false;

        for(int i = 0; i < SIZE; i++)
            if( numbers[i] != sorted[i])
                return false;

        return true;
    }

    public boolean isLoose()
    {
        return !isWin() && swapLeft <= 0;
    }



}
