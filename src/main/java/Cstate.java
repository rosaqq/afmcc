import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;

public class Cstate implements Runnable{

    private boolean inloop = true;
    private ControllerManager controllers;
    private BlockingQ<ControllerState> bq;
    private int cindex;
    private ControllerState lastState;

    public Cstate(int x, BlockingQ bq) {
        this.bq = bq;
        controllers = new ControllerManager();
        controllers.initSDLGamepad();
        cindex = x;
    }


    @Override
    public void run() {
        ControllerState currState = controllers.getState(cindex);
        ControllerState lastState = currState;
        while(inloop){
            lastState = currState;
            currState = controllers.getState(cindex);
            if(!currState.isConnected) {
                System.out.println("controller not connected");
                close();
            }else{
                if(!currState.equals(lastState)) bq.offer(currState);
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
