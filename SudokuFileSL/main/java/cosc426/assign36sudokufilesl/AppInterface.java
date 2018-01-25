package cosc426.assign36sudokufilesl;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.Editable;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;


/**
 * Created by lhe on 11/13/17.
 */

public class AppInterface extends GridLayout {

    private int size;
    private EditText[][] editTexts;
    private TextView status;

    public AppInterface(Context context, int size, int width)
    {
        super(context);

        this.size = size;
        setRowCount(size + 1);
        setColumnCount(size);

        GridLayout.LayoutParams params;

        //create editexts grid for sudoku
        editTexts = new EditText[size][size];
        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++)
            {
                editTexts[i][j] = new EditText(context);

                //ban keyboard, avoid blocking the screen, only for computer testing
                //disableSoftInputFromAppearing(editTexts[i][j]);

                editTexts[i][j].setTextColor(Color.BLACK);
                editTexts[i][j].setTextSize((int)(width*0.12));
                editTexts[i][j].setBackgroundColor(Color.WHITE);
                editTexts[i][j].setText("");
                editTexts[i][j].setInputType(InputType.TYPE_CLASS_NUMBER);
                editTexts[i][j].setGravity(Gravity.CENTER);
                //editTexts[i][j].addTextChangedListener(textWatcher);
                params = new GridLayout.LayoutParams();
                params.width = width;
                params.height = width;
                params.rowSpec = GridLayout.spec(i,1);
                params.columnSpec = GridLayout.spec(j, 1);
                params.topMargin = params.bottomMargin = 1;
                params.leftMargin = params.rightMargin = 1;
                editTexts[i][j].setLayoutParams(params);
                addView(editTexts[i][j]);
            }

        //create status bar for promotion
        status = new TextView(context);
        status.setBackgroundColor(Color.parseColor("#87CEEB"));
        status.setTextColor(Color.BLACK);
        status.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        status.setGravity(Gravity.CENTER);
        params = new GridLayout.LayoutParams();
        params.width = width * size;
        params.height = width * 2;
        params.rowSpec = GridLayout.spec(size,1);
        params.columnSpec = GridLayout.spec(0, size);
        params.topMargin = params.bottomMargin = 1;
        params.leftMargin = params.rightMargin = 1;
        status.setLayoutParams(params);
        addView(status);

    }

    //set the sudoku grid
    //v2 updating: textWatcher paramter
    public void setSudokuGrid(int[][] board, android.text.TextWatcher textWatcher)
    {
        //error check
        if(board.length != size || board[0].length != size)
        {
            editTexts[0][0].setText("-1");
            return;
        }

        //reason for changeing here is: version 2 is not well-compatible with version 1
        //v1 only has one game, while v2 can create new games
        //in the process of v2 creating game,
        //textWachter will be trigered to make the Status shows some confusing information
        //so in the process of creating we need to ban textWatcher
        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++)
                editTexts[i][j].removeTextChangedListener(textWatcher);


        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++)
                if(board[i][j] != 0)        //if there's a value,
                //set backgroud color to gray and unEditable
                {
                    editTexts[i][j].setText(board[i][j]+"");
                    editTexts[i][j].setBackgroundColor(Color.parseColor("#D8D8D8"));
                    editTexts[i][j].setEnabled(false);        //uneditable
                }
                else
                {
                    editTexts[i][j].setText("");
                    editTexts[i][j].setBackgroundColor(Color.WHITE);
                    editTexts[i][j].setEnabled(true);
                    editTexts[i][j].addTextChangedListener(textWatcher);
                }

    }


    //overload version, seperate user input with system generate number on board
    public void setSudokuGrid(int[][] board, int[][] oriBoard, android.text.TextWatcher textWatcher)
    {
        //error check
        if(board.length != size || board[0].length != size)
        {
            editTexts[0][0].setText("-1");
            return;
        }

        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++)
                editTexts[i][j].removeTextChangedListener(textWatcher);


        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++)
                if(board[i][j] != 0)
                {
                    //case 1: board & oriBoard both have value in slot
                    //        this is a system input slot
                    if(oriBoard[i][j] != 0)
                    {
                        editTexts[i][j].setText(board[i][j]+"");
                        editTexts[i][j].setBackgroundColor(Color.parseColor("#D8D8D8"));
                        editTexts[i][j].setEnabled(false);        //uneditable
                    }
                    //case 2: board has value while oriBoard doesn't
                    //        this is a user input slot
                    else
                    {
                        editTexts[i][j].setText(board[i][j]+"");
                        editTexts[i][j].setBackgroundColor(Color.WHITE);
                        editTexts[i][j].setEnabled(true);

                    }
                }
                    //case 3: board slot doesn't has value
                    //        this is an empty slot
                else
                {
                    editTexts[i][j].setText("");
                    editTexts[i][j].setBackgroundColor(Color.WHITE);
                    editTexts[i][j].setEnabled(true);
                    editTexts[i][j].addTextChangedListener(textWatcher);
                }

    }

    //get row,col of sudoku gird
    public boolean isEditText(Editable ed, int row, int col)
    {
        return editTexts[row][col].getEditableText() == ed;
    }

    public void setGridText(int value, int row, int col)
    {
        if(value == 0)          //invalid input or wrong answer
            editTexts[row][col].setText("");
        else
            editTexts[row][col].setText(value + "");
    }

    //
    public int getGridNumber(int row, int col)
    {
        String str = editTexts[row][col].getText().toString();

        int value;

        try{
            value = Integer.parseInt(str);
        }
        catch (NumberFormatException e)
        {
            value = 0;
        }

        //boundaries check
        if(value < 0)
            value = 0;
        else if (value > 9)
            value = 0;
        else;

        return value;
    }

    public EditText getGridEditText(int row, int col)
    {
        return editTexts[row][col];
    }

    //set Status Text
    public void setStatusText(String text)
    {
        status.setText(text);
    }

    /**
     * Disable soft keyboard from appearing, use in conjunction with android:windowSoftInputMode="stateAlwaysHidden|adjustNothing"
     * @param editText
     * refer: https://stackoverflow.com/questions/10636635/disable-keyboard-on-edittext
     */
    public static void disableSoftInputFromAppearing(EditText editText) {
        if (Build.VERSION.SDK_INT >= 11) {
            editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
            editText.setTextIsSelectable(true);
        } else {
            editText.setRawInputType(InputType.TYPE_NULL);
            editText.setFocusable(true);
        }
    }
}
