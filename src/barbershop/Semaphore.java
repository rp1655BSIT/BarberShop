/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbershop;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Riccardo
 */
public class Semaphore {
    
    int v;
    Semaphore(int v)
    {
        this.v=v;
    }
    synchronized void acquire() throws InterruptedException
    {
        while(v==0)
        {
            wait();
        }
        v--;
    }
    synchronized void release()
    {
        v++;
        notifyAll();
    }
}
