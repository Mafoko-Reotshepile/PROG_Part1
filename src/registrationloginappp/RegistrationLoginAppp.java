/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package registrationloginappp;
import java.util.Scanner;
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
        Scanner scanner = new Scanner(System.in);
        Register register = new Register();
        Login login = new Login();
        
        //Prompts to register a user 
        System.out.println("=== User Registration ===");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter cellphone number (+27XXXXXXXXX): ");
        String phoneNumber = scanner.nextLine();

    }

}
