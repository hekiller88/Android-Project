package cosc426.assign31;

import java.text.NumberFormat;
/**
 * Created by lhe on 11/8/17.
 */

public class Calculator {

    int currPrincipal;
    int annuAddition;
    int investmentRate;
    int years;

    public Calculator(int c, int a, int r, int y)
    {
        currPrincipal = c;
        annuAddition = a;
        investmentRate = r;
        years = y;
    }

    public String[] getYearsStr()
    {
        String[] result = new String[years + 1];

        for(int i = 0; i <= years; i++)
            result[i] = i + "";

        return result;
    }

    public String[] getAmountStr()
    {
        String[] result = new String[years + 1];

        NumberFormat money = NumberFormat.getCurrencyInstance();
        money.setMaximumFractionDigits(0);

        float amount = currPrincipal + 0.0f;
        float anuu = annuAddition + 0.0f;
        float rates = investmentRate + 0.0f;
        result[0] = money.format(amount) + "";

        int i = 1;
        while( i <= years)
        {
            amount = (amount + anuu) * (1 + rates / 100);
            result[i] = money.format(Math.round(amount)) + "";
            i++;
        }

        return result;
    }

}

