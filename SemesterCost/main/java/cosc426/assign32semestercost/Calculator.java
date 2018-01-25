package cosc426.assign32semestercost;

import android.util.Log;

import java.text.NumberFormat;

/**
 * Created by lhe on 11/10/17.
 */

public class Calculator {

    public enum Degree {UNDERGRADUATE, GRADUATE};

    private Degree degree;
    private int numberOfCredits;
    private boolean livingDorm;
    private boolean livingDinning;

    public Calculator()
    {
        degree = Degree.UNDERGRADUATE;
        numberOfCredits = 0;
        livingDorm = false;
        livingDinning = false;
    }

    public void setCredits(int n)
    {
        numberOfCredits = n;
    }
    public int getCredits() {return numberOfCredits;}

    public void setLivingDorm(boolean flag)
    {
        livingDorm = flag;
    }
    public boolean getLivingDorm() {return livingDorm;}

    public void setLivingDinning(boolean flag) {livingDinning = flag;}
    public boolean getLivingDinning()   {return livingDinning;}

    public void setDegree(Degree d)
    {
        degree = d;
    }
    public Degree getDegree()       {return degree;}

    public String getSummary() {
        String result = "";
        int total = 0;

        result += "Number of Credits: " + numberOfCredits + "\n";

        if (degree == Degree.UNDERGRADUATE) {
            result += "Degree: Undergraduate\n";
            total += numberOfCredits * 300;
        } else {
            result += "Degree: Graduate\n";
            total += numberOfCredits * 400;
        }

        result += "Living Expense: ";


        Log.w("Log", "dorm " + (livingDorm?"checked":"unchecked") + "\n" +
                     "dinning " + (livingDinning?"checked":"unchecked"));
        if (livingDorm) {
            result += "Dorm ";
            total += 1000;
        }

        if (livingDinning) {
            result += "Dinning ";
            total += 500;
        }

        if(!livingDorm && !livingDinning)
            result += "None";

        NumberFormat money = NumberFormat.getCurrencyInstance();
        money.setMaximumFractionDigits(0);

        result += "\n" + "Total: " + money.format(total);

        return result;
    }
}
