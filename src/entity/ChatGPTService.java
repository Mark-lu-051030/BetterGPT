package entity;

import com.theokanning.openai.service.OpenAiService;

public class ChatGPTService
{
    private final String APIKEY = "sk-proj-R28ol8hXTZvbitrM96dfT3BlbkFJ1vvGN44cRIZHmhV3ZnEh";
    public static OpenAiService service;

    public ChatGPTService()
    {
        service = new OpenAiService(APIKEY);
    }

    public OpenAiService getService()
    {
        return service;
    }
}
