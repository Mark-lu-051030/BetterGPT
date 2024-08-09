package entity.Tier;

public abstract class Tier
{
    /*
    This is a super class for subscription. In this class we set up some basic and public properties
    of all tiers.
     */
    protected double unitfee; // Unit fee of this tier. for example $1 per hour.
    protected boolean isexpired; // Check if this tier is expired; True means expired, false means not.
    protected boolean isopen; //Check if the user starts to use the time. True means open.

    public abstract boolean checkExpired();
    public abstract void renewal(long inputvalue);

    public double getUnitfee()
    {
        return this.unitfee;
    }

    public void setUnitfee(double inputfee)
    {
        this.unitfee = inputfee;
    }

    public boolean getExpired()
    {
        return this.isexpired;
    }
    public void setExpired(boolean inputstatus)
    {
        this.isexpired = inputstatus;
    }
    public boolean getOpen()
    {
        return this.isopen;
    }
    public void setOpen(boolean inputopen)
    {
        this.isopen = inputopen;
    }





}
