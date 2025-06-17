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
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
                case "3" -> displayLongestMessage(); 
                case"4" -> {
                    String recipient = JOptionPane.showInputDialog("Enter recipient name to search:");
                    searchByRecipient(recipient);
                }
                case "5" -> {
                    String hash = JOptionPane.showInputDialog("Enter message hash to delete");
                    deleteByHash(hash);
                }
                 case "6" -> readMessagesFromJSON();
                case "7" -> displayMessageReport();
                case "8" -> {
                    saveMessagesToJSON();
                    JOptionPane.showMessageDialog(null, "Thank you for using OuickChat.");
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
    
    sb.append("Total Messages Sent: ").append(messages.size());
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
    // Feature: Display the longest message
    public void displayLongestMessage() {
        if (messages.isEmpty()) return;
        Message longest = messages.get(0);
        for (Message m : messages) {
            if (m.content.length() > longest.content.length()) {
                longest = m;
            }
        }
        JOptionPane.showMessageDialog(null, "Longest Message:\n" + longest);
    }
    // Feature: Search by recipient name
    public void searchByRecipient(String recipient) {
        StringBuilder sb = new StringBuilder();
        for (Message msg : messages) {
            if (msg.sender.equalsIgnoreCase(recipient)) {
                sb.append(msg).append("\n------------------\n");
            }
        }
        JOptionPane.showMessageDialog(null, sb.length() > 0 ? sb.toString() : "No messages found.");
    }
     // Feature: Delete by hash
    public void deleteByHash(String hash) {
        Iterator<Message> iterator = messages.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().hash.equals(hash)) {
                iterator.remove();
                JOptionPane.showMessageDialog(null, "Message deleted.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Message not found.");
    }
    // Feature: Report statistics
    public void displayMessageReport() {
        int totalMessages = messages.size();
        int totalCharacters = 0;
        StringBuilder sb = new StringBuilder("Message Report:\n\n");
        for (Message msg : messages) {
            int length = msg.content.length();
            totalCharacters += length;
            sb.append("Message ").append(msg.number)
              .append(" - Characters: ").append(length).append("\n");
        }
        sb.append("\nTotal Messages: ").append(totalMessages)
          .append("\nTotal Characters Sent: ").append(totalCharacters);
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
    
     public void readMessagesFromJSON() 
    {
        try (FileReader reader = new FileReader("messages.json")) {
            JSONArray jsonArray = (JSONArray) new JSONParser().parse(reader);
            for (Object obj : jsonArray) {
                JSONObject jsonObj = (JSONObject) obj;
                messages.add(new Message(
                    ((Long) jsonObj.get("messageNumber")).intValue(),
                    (String) jsonObj.get("messageID"),
                    (String) jsonObj.get("messageHash"),
                    (String) jsonObj.get("sender"),
                    (String) jsonObj.get("content")
                ));
            }
            JOptionPane.showMessageDialog(null, "Messages loaded from JSON.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error reading from JSON.");
        }
    }
}
