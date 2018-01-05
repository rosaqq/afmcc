import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Cstate implements Runnable{

    private boolean inloop = true;
    private ControllerManager controllers;
    private LinkedBlockingQueue<Qobj> bq;
    private int cindex;

    public Cstate(int x, LinkedBlockingQueue<Qobj> bq) {
        this.bq = bq;
        controllers = new ControllerManager();
        controllers.initSDLGamepad();
        cindex = x;
    }


    @Override
    public void run() {

        ControllerState currState = controllers.getState(cindex);
        ControllerState lastState;
        int i =0;
        while(inloop){

            lastState = currState;
            currState = controllers.getState(cindex);

            if(!currState.isConnected) {

                bq.add(new Qobj(i++));
                try {
                    Thread.sleep(1000);
                }
                catch(InterruptedException e) {
                    System.out.println("interrupted");
                }
                /*
                System.out.println("controller not connected");
                close();
                */

            }else{
                if(!currState.equals(lastState)) {
                    bq.add(new Qobj(currState));
                }
            }

        }
    }

    public void close() {

        //se ja esta fechado n fecha outra vez
        if(inloop) {
            inloop = false;
            controllers.quitSDLGamepad();
            System.out.println("close drivers");
        }
        else {
            System.out.println("already closed");
        }
    }
}
