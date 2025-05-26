/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package registrationloginappp;
import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 *
 * @author RC_Student_lab
 */
public class RegistrationLoginAppp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        
        Register register = new Register();
        Login login = new Login();
        MessageHandler messageHandler = new MessageHandler();
     
       
       String username = JOptionPane.showInputDialog("Enter username:");
       String password = JOptionPane.showInputDialog("Enter password:");
       String phoneNumber = JOptionPane.showInputDialog("Enter cellphone number (+27XXXXXXXXX):");
        
         // Only proceeds if registration is successful
        if (register.registerUser(username, password, phoneNumber)) 
        {
            String loginUsername = JOptionPane.showInputDialog("Login - Enter username:");
            String loginPassword = JOptionPane.showInputDialog("Login - Enter password:");

         
        
        
        if (login.loginUser(loginUsername, loginPassword, register.getUsername(), register.getPassword())) {
                JOptionPane.showMessageDialog(null, "Welcome to QuickChat!");
                
                // Show the menu
                System.out.println(" ");
                messageHandler.showMenu();
            } else {
                JOptionPane.showMessageDialog(null, "Login failed. Exiting.");
            }
        }
        
    }

}
