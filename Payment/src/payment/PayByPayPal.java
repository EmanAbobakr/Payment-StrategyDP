/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payment;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author EmanAbobakr
 */
public class PayByPayPal implements PayStrategy {

    private static final Map<String, String> DATA_BASE = new HashMap<>();

    static {
        DATA_BASE.put("12345", "abobakremy@gmail.com");
        DATA_BASE.put("123", "nermatarek@gmail.com");
    }

    Scanner reader = new Scanner(System.in);

    private String email;
    private String password;
    private boolean signedIn;

    @Override
    public boolean pay(int paymentAmount) {
        if (signedIn) {
            System.out.println("Paying " + paymentAmount + " using PayPal.");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void collectPaymentDetails() {
        //System.out.println("Hello from paypal");
        while (!signedIn) {
            System.out.print("Enter the user's email: ");
            email = reader.nextLine();
            System.out.print("Enter the user password: ");
            password = reader.nextLine();
            if (verify()) {
                System.out.println("Data verification has been successful.");
            } else {
                System.out.println("Wrong email or password!");
            }
        }
    }

    private boolean verify() {
        signedIn = email.equals(DATA_BASE.get(password));
        return  signedIn;
    }

}
