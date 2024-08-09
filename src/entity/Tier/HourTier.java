package entity.Tier;
import java.util.Timer;
import java.util.TimerTask;

import entity.Tier.Tier;

public class HourTier extends Tier
{
    /*

        This is an hour billing method. User pay of each hour.
     */
    private long remain_time_per_second;
    private Timer timer;
    public HourTier(double inputunitfee, long inputremaintime)
    {
        this.unitfee = inputunitfee;
        this.remain_time_per_second = inputremaintime;
        this.isexpired = checkExpired();
        this.isopen = false;
        this.timer = new Timer();
    }


    private void countTime()
    {
        // timer counter, if user start to use, remain time -1 per second;
        this.remain_time_per_second--;
        setExpired(checkExpired());
    }

    public void open()
    {
        setOpen(true);
        timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                countTime();
                if(getExpired())
                {
                    timer.cancel();
                    System.out.println("Expired!");
                    setOpen(false);
                }
            }
        }, 0, 1000);
    }
    public void close()
    {
        this.timer.cancel();
        setOpen(false);
    }

    public void renewal(long inputtime)
    {
        // CAREFUL!! this input is hour!!! we need to exchange it to second!!!
        this.remain_time_per_second += inputtime * 3600;
    }

    public boolean checkExpired()
    {
        return (this.remain_time_per_second <= 0);
    }

}
