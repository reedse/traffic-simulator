//Sean Reed
//7033251
//COSC3P91 Assignment 4

package Traffic_Simulation.Game;

import java.util.List;

import Traffic_Simulation.Core.Lane;
import Traffic_Simulation.Core.Vehicle;

public class VehicleThread implements Runnable {

    Vehicle v = null;
    Simulation sim = null;

    public VehicleThread(Vehicle pVehicle, Simulation sim) {
        this.v = pVehicle;
        this.sim = sim;
    }

    //use locks to handle pausing and resuming thread
    private final Object lock = new Object();

    //use wait to pause thread
    public void pauseThread() {
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    //use notify to resume thread
    @Override
    public void resume() {
        synchronized (lock) {
            lock.notify();
        }
    }

    //update vehicle state
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {

            //use vehicles class decision to make next move
            v.makeDecision();

            //pause thread after move, will be awoken each simulation step
            pauseThread();

        }
    }

}
