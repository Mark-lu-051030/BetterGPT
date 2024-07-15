package data_access;

import jdk.nashorn.api.scripting.JSObject;


import java.util.ArrayList;
import java.util.HashMap;

public class MessageData
{
    private String id;
    private String object;
    private int created;
    private String model;
    private HashMap<String, Integer> usage;
    private ArrayList<Enum> choices;

    public MessageData(String inputid, String inputobject,int inputcreated,
                       String inputmodel,HashMap<String, Integer> inputusage,ArrayList inputchoices)
    {
        this.id = inputid;
        this.object = inputobject;
        this.created = inputcreated;

        this.model = inputmodel;
        this.usage = inputusage;
        this.choices = inputchoices;
    }

    public String getId(){return this.id;}
    public String getObject(){return this.object;}
    public int getCreated(){return this.created;}
    public String getModel(){return this.model;}
    public HashMap getUsage(){return this.usage;}
    public ArrayList getChoices(){return this.choices;}


}
