import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Cstate implements Runnable{

    private boolean inloop = true;
    private ControllerManager controllers;
    private LinkedBlockingQueue<Qobj> bq;
    private int cindex;
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


            }else{
                if(isSignificant(currState, lastState)) {
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
            System.out.println("[GPAD] close gamepad drivers");
        }
        else {
            System.out.println("[GPAD] gamepad already closed");
        }
    }

    private boolean isSignificant(ControllerState state, ControllerState lastState) {
        if(state.equals(lastState)) return false;

        boolean ret = true;
        if(Math.abs(state.leftStickX)<thold && Math.abs(state.leftStickY)<thold && Math.abs(state.rightStickY)<thold) {
            ret = axreset;
            axreset = false;
        }
        else {
            axreset = true;
        }

        return ret;
    }
}
