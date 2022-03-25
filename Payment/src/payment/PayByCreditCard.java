/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payment;

import java.util.Scanner;

/**
 *
 * @author EmanAbobakr
 */
public class PayByCreditCard implements PayStrategy{
    
    Scanner reader = new Scanner(System.in);
    
    private CreditCard card; 

    @Override
    public boolean pay(int paymentAmount) {
        if(cardIsPresent()){
            System.out.println("Your current amount before payment = " + card.getAmount());
            System.out.println("Paying " + paymentAmount + " using credit card");
            card.setAmount(card.getAmount() - paymentAmount);
            System.out.println("Your current amount after payment = " + card.getAmount());
            return true;
        }else{
            return false;
        }      
    }

    @Override
    public void collectPaymentDetails() {
        //System.out.println("Hello from credit card");
        System.out.println("Enter the card Num: ");
        String Num = reader.nextLine();
        System.out.println("Enter the card expiration dat 'mm/yy': ");
        String date = reader.nextLine();
        System.out.println("Enter the CVV code: ");
        String cvv = reader.nextLine();
        card = new CreditCard(Num, date, cvv);
        
    }
    
    private boolean cardIsPresent() {
        return card != null;
    }
}
