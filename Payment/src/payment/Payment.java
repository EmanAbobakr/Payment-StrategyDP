/*
 *Use strategy design pattern to implement payment methods(CreditCard and Paypal)
 */
package payment;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author EmanAbobakr
 */
public class Payment {

    /**
     * @param args the command line arguments
     */
    private static Map<String, Integer> priceOnProducts = new HashMap<>();

    private static PayStrategy strategy;

    //executed only once: the first time the class is loaded into memory.
    static {
        priceOnProducts.put("1", 2200);
        priceOnProducts.put("2", 10000);
        priceOnProducts.put("3", 15100);
        priceOnProducts.put("4", 9000);
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner reader = new Scanner(System.in);

        Order order = new Order();
        int cost;

        String continueChoice;
        String choice;
        
        //make the order
        while (!order.isClosed) {

            int count = 0;
            do {
                System.out.print("Please, select a product:" + "\n"
                        + "1 - Laptop" + "\n"
                        + "2 - Mobile" + "\n"
                        + "3 - TV" + "\n"
                        + "4 - Camera" + "\n");
                choice = reader.nextLine();
                cost = priceOnProducts.get(choice);
                System.out.print("Count: ");
                count = Integer.parseInt(reader.nextLine());
                order.setTotalCost(cost * count);
                System.out.print("Do you wish to continue selecting products? Y/N: ");
                continueChoice = reader.nextLine();
            } while (continueChoice.equalsIgnoreCase("y"));
            order.setIsClosed(true);
        }

        //select a payment method
        if (strategy == null) {
            System.out.println("Please, select a payment method:" + "\n"
                    + "1 - PalPay" + "\n"
                    + "2 - Credit Card");
            int paymentMethod = Integer.parseInt(reader.nextLine());
            
            if(paymentMethod == 1){
                strategy = new PayByPayPal();
            }else if(paymentMethod == 2){
                strategy = new PayByCreditCard();
            }else{
                System.out.println("Wrong Choice,idiot?");
            }
        }
        
        order.processOrder(strategy);

        System.out.print("pay " + order.getTotalCost() + " $ or continue shopping? p/c: ");
        String proceed = reader.nextLine();
        
        if(proceed.equalsIgnoreCase("p")){
            if(strategy.pay(order.getTotalCost())){
                System.out.println("Payment has been successful.");
            }else{
                System.out.println("FAIL! Please, check your data.");
            }
            order.setIsClosed(true);
        }

    }

}
