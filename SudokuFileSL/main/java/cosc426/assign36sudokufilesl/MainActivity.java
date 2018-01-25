package cosc426.assign36sudokufilesl;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final int SIZE = 9;
    private final String FILE_NAME = "DataSudoku";

    private Sudoku sudoku;
    private int[][] board;              //current board with user input
    private int[][] oriBoard;           //original board
    private AppInterface appInterface;

    TextChangeHandler textChangeHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sudoku = new Sudoku();
        board = sudoku.generate();
        oriBoard = copyBoard(board);

        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);
        int width = screenSize.x / SIZE;

        textChangeHandler = new TextChangeHandler();

        appInterface = new AppInterface(this, SIZE, width);
        appInterface.setSudokuGrid(board, textChangeHandler);


        RelativeLayout outputRL = (RelativeLayout)findViewById(R.id.sudoku);
        outputRL.addView(appInterface);


    }

    private class TextChangeHandler implements TextWatcher
    {
        @Override
        public void afterTextChanged(Editable editable) {

            //test
            Log.w("Log", "\nTextWatcher Call!!\n");

            int row = 0, col = 0;

            //find the current editbox
            OUT:
            for( row = 0; row < SIZE; row++ )
                for( col = 0; col < SIZE; col++)
                    if(appInterface.isEditText(editable, row, col))
                        break OUT;

            int value = appInterface.getGridNumber(row, col);

            //check if the current gird obeys the Sudoku rule
            board[row][col] = value;
            if(!Gamer.check(board, row, col)) {
                board[row][col] = 0;
                value = 0;
            }

            if(value == 0)
                appInterface.setStatusText("Wrong!");
            else {
                appInterface.setStatusText("Keep Going!");
            }

            //remove listener and then add it back to avoid the stack over flow error,
            // which means once you change the text and it's wrong
            // it will triger the empty slot operation
            // and infinitely call the afterTextChanged() method
            appInterface.getGridEditText(row,col).removeTextChangedListener(textChangeHandler);
            appInterface.setGridText(value, row, col);
            appInterface.getGridEditText(row,col).addTextChangedListener(textChangeHandler);

            if(Gamer.isWin(board))
                appInterface.setStatusText("You Win!!");
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
    }

    public void newSudoku(View v)
    {
        board = sudoku.generate();
        oriBoard = copyBoard(board);
        appInterface.setStatusText("New Game");
        appInterface.setSudokuGrid(board, textChangeHandler);
    }

    public void saveSudoku(View v)
    {
        appInterface.setStatusText("Saved!");
        try
        {
            FileOutputStream fout = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);

            for(int i = 0; i < SIZE; i++)
                for(int j = 0; j < SIZE; j++)
                    fout.write(board[i][j]);

            for(int i = 0; i < SIZE; i++)
                for(int j = 0; j < SIZE; j++)
                    fout.write(oriBoard[i][j]);

//            Log.w("Log", "-------------Save---------------");
//            for(int j = 0; j < SIZE; j++)
//                Log.w("Log", Arrays.toString(board[j]) );
//            Log.w("Log", "--------------------------------");

            fout.close();
        }
        catch(Exception e)
        {
            Toast.makeText(MainActivity.this, "Error: Save Button", Toast.LENGTH_LONG).show();
            return;
        }
    }

    public void retrieveSudoku(View v)
    {
        appInterface.setStatusText("Retrieved!");
        try
        {
            FileInputStream fin = openFileInput(FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fin);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            int i = 0;
            int boardCnt = 0;
            int oriBoardCnt = 0;
            while( (i = bufferedReader.read()) != -1)
            {
                if(boardCnt < SIZE * SIZE)
                {
                    board[boardCnt / SIZE][boardCnt % SIZE] = i;
                    boardCnt++;
                }
                else
                {
                    oriBoard[oriBoardCnt / SIZE][oriBoardCnt % SIZE] = i;
                    oriBoardCnt++;
                }
            }
            appInterface.setSudokuGrid(board, oriBoard, textChangeHandler);

//            Log.w("Log", "-------------Retr---------------");
//            for(int j = 0; j < SIZE; j++)
//                Log.w("Log", Arrays.toString(board[j]) );
//            Log.w("Log", "--------------------------------");

            fin.close();

        }
        catch(Exception e)
        {
            Toast.makeText(MainActivity.this, "Error: Retrieve Button", Toast.LENGTH_LONG).show();
            return;
        }
    }

    public int[][] copyBoard(int[][] board)
    {
        int len = board[0].length;

        if(len != board.length)
            throw new IllegalArgumentException("Error! Matrix should be square!");

        int[][] result = new int[len][len];

        for(int i = 0; i < len; i++)
            for(int j = 0; j < len; j++)
            {
                result[i][j] = board[i][j];
            }

        return result;
    }
}
