package entity.Tier;

import entity.Tier.Tier;

public class SubmitTier extends Tier
{
    /*

        This tier counts users' submit time to calculate money. User pay of each hour.
     */

    private int remain_submit;

    public SubmitTier(int inputsubmit)
    {
        this.remain_submit = inputsubmit;
        this.isexpired = checkExpired();
    }

    public boolean checkExpired()
    {
        return this.remain_submit <= 0;
    }

    public void countSubmit()
    {
        this.remain_submit--;
        setExpired(checkExpired());

    }

    public void renewal(long inputsubmit)
    {
        this.remain_submit += (int)inputsubmit;
    }


}
