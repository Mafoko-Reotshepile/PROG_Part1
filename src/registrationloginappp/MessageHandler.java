/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registrationloginappp;
/**
 *
 * @author RC_Student_lab
 */

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MessageHandler 
{
    private static final List<Message> messages = new ArrayList<>();
    private static int messageCounter = 0;

    public void showMenu() 
    {
        while (true) {
            String option = JOptionPane.showInputDialog("""
                Main Menu:
                1. Add Messages
                2. View All Messages
                3. Display Longest Message
                4. Search Message by Recipient
                5. Delete Message by Hash
                6. Load Message from JSON
                7. Message Report
                8. Exit
                Enter your choice (1–8):""");

            switch (option) 
            {
                case "1" -> addMessages();
                case "2" -> viewMessages();
                case "3" -> {
                    saveMessagesToJSON();
                    JOptionPane.showMessageDialog(null, "Thank you. Program exiting.");
                    //System.exit(0);
                    return;
                }
                default -> JOptionPane.showMessageDialog(null, "Invalid choice. Try again.");
            }
        }
    }
    
   private void sendMessages() {
    if (messages.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No messages to send.");
        return;
    }

    StringBuilder sb = new StringBuilder("Messages Sent Successfully:\n\n");
    for (Message msg : messages) {
        sb.append("Message ID: ").append(msg.id).append("\n")
          .append("Hash: ").append(msg.hash).append("\n")
          .append("Sender: ").append(msg.sender).append("\n")
          .append("Message: ").append(msg.content).append("\n")
          .append("-------------------------------\n");
    }

    JOptionPane.showMessageDialog(null, sb.toString());

}
            
    private void addMessages() {
    String input = JOptionPane.showInputDialog("How many messages would you like to enter?");
    
    if (input == null || input.isEmpty()) return;
    
    int count = Integer.parseInt(input);

    for (int i = 0; i < count; i++) {
        String sender = JOptionPane.showInputDialog("Enter sender name:");
        String content = JOptionPane.showInputDialog("Enter message content:");

        messageCounter++;
        String messageID = generateMessageID(sender, messageCounter);
        String hash = generateMessageHash(sender, content, messageCounter);

        Message msg = new Message(messageCounter, messageID, hash, sender, content);
        messages.add(msg);
    }

    // Ask user what to do after entering messages
    int choice = JOptionPane.showOptionDialog(null,
        "Messages captured. What would you like to do?",
        "Next Step",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        new String[]{"Send Now", "Disregard & Save for Later"},
        "Send Now");

    if (choice == JOptionPane.YES_OPTION) {
        sendMessages();
    } else {
        saveMessagesToJSON();
        JOptionPane.showMessageDialog(null, "Messages saved for later sending.");
    }
}


    private void viewMessages() 
    {
        if (messages.isEmpty()) 
        {
            JOptionPane.showMessageDialog(null, "No messages to display.");
            return;
        }

        StringBuilder sb = new StringBuilder("All Messages:\n\n");
        for (Message msg : messages) 
        {
            sb.append(msg).append("\n---------------------\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private String generateMessageID(String sender, int counter) 
    {
        return sender.substring(0, 2).toUpperCase() + String.format("%03d", counter);
    }

    private String generateMessageHash(String sender, String content, int counter) {
        return (sender + content + counter).hashCode() + "";
    }

    private void saveMessagesToJSON() 
    {
        JSONArray jsonMessages = new JSONArray();
        for (Message msg : messages) {
            JSONObject obj = new JSONObject();
            obj.put("messageNumber", msg.number);
            obj.put("messageID", msg.id);
            obj.put("messageHash", msg.hash);
            obj.put("sender", msg.sender);
            obj.put("content", msg.content);
            jsonMessages.add(obj);
        }

        try (FileWriter file = new FileWriter("messages.json")) 
        {
            file.write(jsonMessages.toJSONString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving messages to JSON.");
        }
    }
}
