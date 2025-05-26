/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registrationloginappp; 

import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author RC_Student_lab
 */
public class RegisterTest 
{
     @Test
    public void testValidUsername() {
        assertTrue(Register.isValidUsername("u_ser"));
        assertTrue(Register.isValidUsername("us_r"));       // short but valid
        assertFalse(Register.isValidUsername("user"));      // no underscore
        assertFalse(Register.isValidUsername("user_name")); // too long
    }

    @Test
    public void testValidPassword() {
        assertTrue(Register.isValidPassword("Passw0rd@"));     // valid
        assertFalse(Register.isValidPassword("password"));     // no uppercase, digit, special char
        assertFalse(Register.isValidPassword("PASSWORD1"));    // no lowercase, special char
        assertFalse(Register.isValidPassword("Pass1234"));     // no special character
        assertFalse(Register.isValidPassword("Pass@1"));       // too short
    }

    @Test
    public void testValidPhoneNumber() {
        assertTrue(Register.isValidPhoneNumber("+27812345678"));     // valid SA number
        assertFalse(Register.isValidPhoneNumber("0812345678"));      // missing country code
        assertFalse(Register.isValidPhoneNumber("+271234567"));      // too short
        assertFalse(Register.isValidPhoneNumber("+278123456789"));   // too long
        assertFalse(Register.isValidPhoneNumber("+27abcdefghi"));    // contains letters
    
    
    
}
}
