package use_case;

import data_access.GptApiClient;
import interface_adapter.*;

public class ChatService
{


    private ChatController chatcontroller;

    public ChatService(ChatController inputchatcontroller)
    {
        this.chatcontroller = inputchatcontroller;
    }

    public ChatController getChatController()
    {
        return this.chatcontroller;
    }
}