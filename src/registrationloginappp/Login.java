/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registrationloginappp;

/**
 *
 * @author RC_Student_lab
 */
public class Login 
{
    
    public boolean loginUser(String inputUsername, String inputPassword, String registeredUsername, String registeredPassword) 
    {
        if (!inputUsername.equals(registeredUsername)) {
            System.out.println("Invalid username.");
            return false;
        }

        if (!inputPassword.equals(registeredPassword)) {
            System.out.println("Invalid password.");
            return false;
        }

        System.out.println("Login successful!");
        return true;
        
    }
    
    
    
}
