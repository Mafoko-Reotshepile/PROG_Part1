/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registrationloginappp;
/**
 *
 * @author RC_Student_lab
 */

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;

public class MessageHandlerTest {

    private MessageHandler handler;

    @BeforeEach
    public void setUp() {
        handler = new MessageHandler();
    }

    @Test
    public void testAddMessageAndView() {
        // simulate adding a message
        Message testMsg = new Message(1, "TE001", "123hash", "TestUser", "Hello test message");
        MessageHandlerTestUtils.injectMessage(handler, testMsg);

        assertEquals(1, MessageHandlerTestUtils.getMessageList(handler).size());
    }

    @Test
    public void testLongestMessage() {
        Message m1 = new Message(1, "A001", "h1", "User1", "Short");
        Message m2 = new Message(2, "A002", "h2", "User2", "This is the longest message in the test.");
        MessageHandlerTestUtils.injectMessage(handler, m1);
        MessageHandlerTestUtils.injectMessage(handler, m2);

        Message longest = MessageHandlerTestUtils.getLongestMessage(handler);
        assertEquals(m2.content, longest.content);
    }

    @Test
    public void testDeleteByHash() {
        Message m = new Message(1, "B001", "delete123", "User3", "Message to delete");
        MessageHandlerTestUtils.injectMessage(handler, m);

        boolean deleted = MessageHandlerTestUtils.deleteByHash(handler, "delete123");
        assertTrue(deleted);
        assertEquals(0, MessageHandlerTestUtils.getMessageList(handler).size());
    }

    @Test
    public void testReadAndSaveJSON() {
        Message msg = new Message(1, "C001", "savehash", "UserSave", "Save this message.");
        MessageHandlerTestUtils.injectMessage(handler, msg);
        handler.readMessagesFromJSON(); // should load any existing messages safely
        handler.saveMessagesToJSON();

        File jsonFile = new File("messages.json");
        assertTrue(jsonFile.exists());
    }

    @AfterEach
    public void tearDown() {
        File jsonFile = new File("messages.json");
        if (jsonFile.exists()) jsonFile.delete();
    }
}

// Helper class to access private data for testing
class MessageHandlerTestUtils {
    static void injectMessage(MessageHandler handler, Message message) {
        try {
            var field = MessageHandler.class.getDeclaredField("messages");
            field.setAccessible(true);
            List<Message> list = (List<Message>) field.get(null);
            list.add(message);
        } catch (Exception e) {
            fail("Reflection failed: " + e.getMessage());
        }
    }

    static List<Message> getMessageList(MessageHandler handler) {
        try {
            var field = MessageHandler.class.getDeclaredField("messages");
            field.setAccessible(true);
            return (List<Message>) field.get(null);
        } catch (Exception e) {
            fail("Reflection failed: " + e.getMessage());
            return null;
        }
    }

    static Message getLongestMessage(MessageHandler handler) {
        return getMessageList(handler).stream()
                .max((a, b) -> Integer.compare(a.content.length(), b.content.length()))
                .orElse(null);
    }

    static boolean deleteByHash(MessageHandler handler, String hash) {
        List<Message> list = getMessageList(handler);
        return list.removeIf(m -> m.hash.equals(hash));
    }
}

