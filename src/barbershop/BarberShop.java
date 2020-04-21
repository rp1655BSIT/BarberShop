/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbershop;

/**
 *
 * @author Riccardo
 */
public class BarberShop{

    public static Semaphore customers = new Semaphore(0);
    public static Semaphore barber = new Semaphore(0);

  /* we denote that the number of chairs in this barbershop is 5. */

    public static final int CHAIRS = 3;
    private int numberOfFreeSeats = CHAIRS;
    
    synchronized int getNumSeats()
    {
        return numberOfFreeSeats;
    }
    synchronized int decNumSeats()
    {
        if(numberOfFreeSeats>0) 
        {
            numberOfFreeSeats--;
            return numberOfFreeSeats;
        }
        else
        {
            return -1;
        }
    }
    synchronized void incNumSeats()
    {
        numberOfFreeSeats++;
    }

  /* we create the integer numberOfFreeSeats so that the customers
   can either sit on a free seat or leave the barbershop if there
   are no seats available */

    @SuppressWarnings("empty-statement")
  public static void main(String args[]) 
  {
      
    Customer []aCustomer=new Customer[8];
    int numCustomer=8;
    BarberShop barberShop = new BarberShop();  //Creates a new barbershop
    
   Barber giovanni = new Barber(customers,barber,barberShop);  //Giovanni is the best barber ever 
   giovanni.start();  //Ready for another day of work

   /* This method will create new customers for a while */
    
   for (int i=0; i<8; i++) 
   {
     aCustomer[i] = new Customer(i,customers,barber,barberShop);
     aCustomer[i].start();
     try {
       Thread.sleep(2000);
     } catch(InterruptedException ex) {};
   }
   
   while(numCustomer>0) 
   {
       numCustomer=0;
   
    try {
     for (int i=0; i<8; i++) 
     {
        if(aCustomer[i].notCut==true) numCustomer++;
        Thread.sleep(100);
     }
    Thread.sleep(1000);
    } catch(InterruptedException ex) {};
   }
   
   System.exit(0);
}
}
