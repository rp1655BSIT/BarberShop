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
class Customer extends Thread 
{
  
  /* we create the integer iD which is a unique ID number for every customer
     and a boolean notCut which is used in the Customer waiting loop */
  
  int iD;
  boolean notCut=true;
  Semaphore customers,barber;
  BarberShop bs;
  /* Constructor for the Customer */
    
  public Customer(int i,Semaphore customers,Semaphore barber,BarberShop bs) {
        iD = i;
        this.customers=customers;
        this.barber=barber;
        this.bs=bs;
  }

  public void run() {   
    while (notCut) 
    {  // as long as the customer is not cut 
        try 
        {
            if (bs.decNumSeats() >= 0) 
            {  //if there are any free seats
                System.out.println("Customer " + this.iD + " just sat down.");
		
                customers.release();  //notify the barber that there is a customer		
                barber.acquire();  // now it's this customers turn but we have to wait if the barber is busy
                this.get_haircut();  //cutting...
                notCut = false;  // this customer will now leave after the procedure
            }   
            else  
            {  // there are no free seats
                System.out.println("There are no free seats. Customer " + this.iD + " has left the barbershop.");
                notCut=false; // the customer will leave since there are no spots in the queue left.
            }
        }catch (InterruptedException ex) {}
    }
  }

  /* this method will simulate getting a hair-cut */
  
  public void get_haircut(){
    System.out.println("Customer " + this.iD + " is getting his hair cut");
    try {
    sleep(5050);
    } catch (InterruptedException ex) {}
  }

}

