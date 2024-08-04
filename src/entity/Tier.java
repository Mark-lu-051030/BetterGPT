package entity;

public abstract class Tier
{
    /*
    This is a super class for subscription. In this class we set up some basic and public properties
    of all tiers.
     */
    private double unitfee; // Unit fee of this tier. for example $1 per hour.
    private boolean isexpired; // Check if this tier is expired;
    private boolean isopen; //Check if the user starts to use the time.

}
