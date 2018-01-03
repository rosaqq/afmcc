import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;

public class Cstate implements Runnable{

    private boolean inloop = true;
    private ControllerManager controllers;
    private int cindex;

    public Cstate(int x) {
        controllers = new ControllerManager();
        controllers.initSDLGamepad();
        cindex = x;
    }


    @Override
    public void run() {
        while(inloop){
            ControllerState currState = controllers.getState(cindex);

            if(!currState.isConnected) {
                System.out.println("controller not connected");
                close();
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
