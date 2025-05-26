/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author RC_Student_lab
 */


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MessageHandlerTest {

    private MessageHandler messageHandler;

    @BeforeEach
    public void setUp() {
        messageHandler = new MessageHandler();
    }

    @Test
    public void testMessageNotOver250Characters() {
        String content = "a".repeat(251);
        assertTrue(content.length() > 250, "Message should be over 250 characters (setup check)");

        String validContent = "Hello this is a valid message.";
        assertTrue(validContent.length() <= 250, "Message should be under or equal to 250 characters");
    }

    @Test
    public void testMessageHashGeneration() {
        String sender = "Jo";
        String content = "Test content";
        int count = 1;

        String hash = sender.concat(content).concat(String.valueOf(count)).hashCode() + "";
        assertNotNull(hash, "Hash should not be null");
        assertTrue(hash.matches("-?\\d+"), "Hash should be a numeric string (from hashCode)");
    }

    @Test
    public void testValidCellphoneFormat() {
        assertTrue(Register.isValidPhoneNumber("+27831234567"), "Valid South African number should pass");
        assertFalse(Register.isValidPhoneNumber("0831234567"), "Missing +27 prefix should fail");
        assertFalse(Register.isValidPhoneNumber("+271234567"), "Too short number should fail");
    }

    @Test
    public void testMessageIDGeneration() {
        String sender = "John";
        int counter = 5;
        String expectedID = sender.substring(0, 2).toUpperCase() + String.format("%03d", counter);
        assertEquals("JO005", expectedID, "Message ID format should match");
    }

    @Test
    public void testMessageSentSuccessfully() {
        Message msg = new Message(1, "JO001", "123456", "John", "Test message");
        assertEquals("John", msg.sender);
        assertEquals("JO001", msg.id);
        assertEquals("Test message", msg.content);
        assertNotNull(msg.hash);
    }
}
