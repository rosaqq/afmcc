import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;

import java.util.concurrent.LinkedBlockingQueue;

public class Cstate implements Runnable{

    private boolean inloop = true;
    private ControllerManager controllers;
    private LinkedBlockingQueue<Qobj> bq;
    private int cindex;
    //keeping this here just in case
    private float thold;
    private boolean axreset = false;

    public Cstate(int x, LinkedBlockingQueue<Qobj> queue) {
        bq = queue;
        cindex = x;
        thold = 0.08f;
        controllers = new ControllerManager();
        controllers.initSDLGamepad();
    }


    @Override
    public void run() {

        ControllerState currState = controllers.getState(cindex);
        ControllerState lastState;

        while(inloop){

            lastState = currState;
            currState = controllers.getState(cindex);

            if(!currState.isConnected) {

                System.out.println("[GPAD] controller not connected");
                close();


            }
            else {
                Qobj curr = new Qobj(currState);
                Qobj last = new Qobj(lastState);

                if(!curr.equals(last)) {
                    System.out.println(curr);
                    axreset = (Math.abs(last.axvel[0]) > 0 && curr.axvel[0] == 0) || (Math.abs(last.axvel[1]) > 0 && curr.axvel[1] == 0) || (Math.abs(last.axvel[2]) > 0 && curr.axvel[2] == 0);
                    System.out.println(axreset);
                    if (!(curr.trigger.equals("axis") && !axreset && curr.axvelIsZero)) {
                        bq.add(curr);
                        System.out.println("sent");
                    } else System.out.println("not sent");
                }
            }

        }
    }

    public void close() {

        //se ja esta fechado n fecha outra vez
        if(inloop) {
            inloop = false;
            controllers.quitSDLGamepad();
            System.out.println("[GPAD] close gamepad drivers");
        }
        else {
            System.out.println("[GPAD] gamepad already closed");
        }
    }
}
