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
class Barber extends Thread 
{
  Semaphore customers,barber;
  BarberShop bs;
  public Barber(Semaphore customers,Semaphore barber,BarberShop bs) 
  {
      this.customers=customers;
      this.barber=barber;
      this.bs=bs;
  }
  
  @Override
  public void run() {
    while(true) 
    {  // runs in an infinite loop
      try 
      {
		customers.acquire(); // tries to acquire a customer - if none is available he goes to sleep
		
                bs.incNumSeats(); // one chair gets free		
		this.cutHair();  //cutting...
       
		barber.release();  // the barber is ready to cut
	  } catch (InterruptedException ex) {}
    }
  }

    /* this method will simulate cutting hair */
   
  public void cutHair(){
    System.out.println("The barber is cutting hair");
    try {
      sleep(5000);
    } catch (InterruptedException ex){ }
  }
}       
