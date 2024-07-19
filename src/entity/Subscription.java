package entity;

public class Subscription
{
    private String level;
    private long remain_time_sec;

    public Subscription(String input_level, long input_time)
    {
        this.level = input_level;
        this.remain_time_sec = input_time;
    }

    public long getTime() {return this.remain_time_sec;}

    public String getLevel() {return this.level;}

    public void setLevel(String input_level)
    {
        //this method need more discussion, this is related to database info change
        //but now it is only local change.
        this.level = input_level;
    }

    public void setTime(long input_time)
    {
        //this method need more discussion, this is related to database info change
        //but now it is only local change.
        this.remain_time_sec = input_time;
    }

    public void onOpen()
    {
        //this is a listener.
        //Once a user login sucessfully, his or her remain time should decrease
        //If there is no remain time, do something.

    }

}
