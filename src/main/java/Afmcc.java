import java.awt.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Afmcc {


    public LinkedBlockingQueue<Qobj> bq;
    public volatile int[] stepVel =  new int[3];
    public volatile int[] steps =  new int[3];

    private int controllerIndex = 0;
    private CU30Wrap culib;
    Cstate cstate;


    private Afmcc() {

        EventQueue.invokeLater(() -> {
            try {
                Gui frame = new Gui(this);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        bq = new LinkedBlockingQueue<>();
        cstate = new Cstate(controllerIndex, bq);

        Thread cinput = new Thread(cstate, "cinput");
        cinput.start();

        //initialize java native access interface
        culib = CU30Wrap.SYNC_INSTANCE;
        culib.CU30WrapperInit();

        //todo: rework to support multiple hardware interfaces

        Cu30 cu30 = new Cu30(culib, 1,1,1,1);
        System.out.println("[MAIN][CU30] - " + cu30.open());

        while(true){
            try {
                Qobj qobj = bq.take();

                switch(qobj.trigger) {
                    case "L1":
                        cu30.stop();
                        break;
                    case "hat":
                        dohat(cu30, qobj);
                        break;
                    case "axis":
                        doax(cu30, qobj);
                        break;
                }

                repaintGui();
            } catch (InterruptedException e) {
                System.out.println("[MAIN] loop interrupted: " + e);
            }
        }

    }

    private void repaintGui() {
        //whatever needs to be changed in the Gui goes here, not sure if needed {standby}
    }

    private void dohat(Cu30 cu30, Qobj qobj) {
        int axis = (qobj.htop||qobj.hbot)?1:((qobj.hleft||qobj.hright)?2:3);
        //if order came from negative axis button then use -stepVel
        int velo = (qobj.hright || qobj.hbot || qobj.htri) ? -stepVel[axis - 1] : stepVel[axis - 1];
        cu30.step(axis, velo, steps[axis - 1]);
        System.out.printf("[MAIN] %s stepping from %s %d steps at %d vel\n", axis == 1 ? "X" : (axis == 2 ? "Y" : "Z"), qobj.fromGui ? "GUI" : "GPAD", steps[axis - 1], velo);
    }

    private void doax(Cu30 cu30, Qobj qobj) {
        int x = Math.abs(qobj.axvel[0]);
        int y = Math.abs(qobj.axvel[1]);
        int z = Math.abs(qobj.axvel[2]);
        int axis = x>y ? (x>z ? 1 : 3) : (y>z ? 2 : 3);
        cu30.sweep(axis, qobj.axvel[axis-1], 0);
        System.out.printf("[MAIN] %s sweeping from %s at %d vel\n", axis==1?"X":(axis==2?"Y":"Z"), qobj.fromGui?"GUI":"GPAD", qobj.axvel[axis-1]);
    }

    public static void main(String[] args) {
        new Afmcc();
    }
}
