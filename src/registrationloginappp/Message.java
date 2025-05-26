/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registrationloginappp;

/**
 *
 * @author RC_Student_lab
 */
public class Message {
    int number;
    String id;
    String hash;
    String sender;
    String content;

    public Message(int number, String id, String hash, String sender, String content) {
        this.number = number;
        this.id = id;
        this.hash = hash;
        this.sender = sender;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message Number: " + number + "\nID: " + id + "\nHash: " + hash + "\nSender: " + sender + "\nContent: " + content;
    }
}
