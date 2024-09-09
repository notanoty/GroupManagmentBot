package org.notanoty;

import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMemberUpdated;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.List;

public class GroupManager implements LongPollingSingleThreadUpdateConsumer
{
    private final TelegramClient telegramClient;

    public GroupManager(String token)
    {
        this.telegramClient = new OkHttpTelegramClient(token);
    }

    @Override
    public void consume(Update update)
    {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText())
        {
            // Set variables
            String message_text = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            switch (message_text)
            {
                case "/start" ->
                {
                    // User send /start
                    SendMessage message = SendMessage // Create a message object object
                            .builder()
                            .chatId(chatId)
                            .text(message_text)
                            .build();
                    try
                    {
                        telegramClient.execute(message); // Sending our message object to user
                    } catch (TelegramApiException e)
                    {
                        e.printStackTrace();
                    }
                }
                case "/test" ->
                {
                    Message message = update.getMessage();

                    System.out.println(message.getFrom().getUserName());
                    System.out.println(message.getChat());

                }
                case "/pic" ->
                {
                    // User sent /pic
                    SendPhoto msg = SendPhoto
                            .builder()
                            .chatId(chatId)
                            .photo(new InputFile("https://png.pngtree.com/background/20230519/original/pngtree-this-is-a-picture-of-a-tiger-cub-that-looks-straight-picture-image_2660243.jpg"))
                            .caption("This is a little cat :)")
                            .build();
                    try
                    {
                        telegramClient.execute(msg); // Call method to send the photo
                    } catch (TelegramApiException e)
                    {
                        e.printStackTrace();
                    }
                }
                case "/markup" ->
                {
                    SendMessage message = SendMessage // Create a message object object
                            .builder()
                            .chatId(chatId)
                            .text("Here is your keyboard")
                            .build();

                    // Add the keyboard to the message
                    message.setReplyMarkup(ReplyKeyboardMarkup
                            .builder()
                            // Add first row of 3 buttons
                            .keyboardRow(new KeyboardRow("Row 1 Button 1", "Row 1 Button 2", "Row 1 Button 3"))
                            // Add second row of 3 buttons
                            .keyboardRow(new KeyboardRow("Row 2 Button 1", "Row 2 Button 2", "Row 2 Button 3"))
                            .build());
                    try
                    {
                        telegramClient.execute(message); // Sending our message object to user
                    } catch (TelegramApiException e)
                    {
                        e.printStackTrace();
                    }
                }
                default ->
                {
                    // Unknown command
                    SendMessage message = SendMessage // Create a message object object
                            .builder()
                            .chatId(chatId)
                            .text("Unknown command")
                            .build();
                    try
                    {
                        telegramClient.execute(message); // Sending our message object to user
                    } catch (TelegramApiException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}