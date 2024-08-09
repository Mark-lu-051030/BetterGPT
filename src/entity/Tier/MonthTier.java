package entity.Tier;

import entity.Tier.Tier;

import java.util.Calendar;
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
        this.isexpired = checkExpired();

    }

    public boolean checkExpired()
    {
        // expired return true, not expired return false
        Date today = new Date();
        Calendar temp = Calendar.getInstance();
        temp.setTime(this.start_date);
        temp.add(Calendar.MONTH,this.number_of_months);
        Date expireday = temp.getTime();

        if(!today.equals(expireday) && expireday.before(today)) return true;
        else return false;
    }

    public void renewal(long inputtime)
    {
        // this method is for existing user!
        if (getExpired())
        {
            this.start_date = new Date();
            this.number_of_months = (int)inputtime;
        }
        else
        {
            this.number_of_months += (int)inputtime;
        }

    }



}
