/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registrationloginappp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author RC_Student_lab
 */
public class Register 
{
    //Private variables
    private String username;
    private String password;
    private String phoneNumber;

    public boolean registerUser(String username, String password, String phoneNumber) {
        if (!isValidUsername(username)) {
            System.out.println("Invalid username. Must contain an underscore and be max 5 characters.");
            return false;
        }

        if (!isValidPassword(password)) {
            System.out.println("Invalid password. Must contain uppercase, lowercase, digit, and special character.");
            return false;
        }

        if (!isValidPhoneNumber(phoneNumber)) {
            System.out.println("Invalid phone number. Format must be: +27XXXXXXXXX");
            return false;
        }

        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        System.out.println("Registration successful!");
        return true;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    //Validation methods
    public static boolean isValidUsername(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    //This code was provided by OpenAI
    public static boolean isValidPhoneNumber(String phoneNumber) {
        String phoneNumberRegex = "^\\+27\\d{9}$";
        Pattern pattern = Pattern.compile(phoneNumberRegex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
