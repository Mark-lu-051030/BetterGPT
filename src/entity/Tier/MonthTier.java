package entity.Tier;

import entity.Tier.Tier;
import java.util.Date;
import java.util.Timer;

public class MonthTier extends Tier
{
    /*

        This is a monthly bill method.
     */
    private Date start_date;
    private int number_of_months;

    public MonthTier(double inputunitfee,Date inputstart, int inputmonth)
    {
        this.unitfee = inputunitfee;
        this.start_date = inputstart;
        this.number_of_months = inputmonth;
        this.isopen = false;

    }

    private void checkExpired()
    {

    }


}
