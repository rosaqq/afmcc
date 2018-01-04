import com.sun.jna.ptr.IntByReference;
import com.studiohartman.jamepad.ControllerState;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

public class Afmcc {

    private int controllerIndex = 0;
    private BlockingQ<ControllerState> bq = new BlockingQ<ControllerState>();
    private CU30Wrap culib;
    Cstate cstate;


    private Afmcc() {

        EventQueue.invokeLater(() -> {
            try {
                gui frame = new gui(this);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        cstate = new Cstate(controllerIndex, bq);

        Thread cinput = new Thread(cstate, "cinput");
        cinput.start();

        //initialize java native access interface
        culib = CU30Wrap.INSTANCE;
        culib.CU30WrapperInit();

        while(true){
            try {
                ControllerState cs = bq.poll();
                repaintGui(cs);
            } catch (InterruptedException e) {
                System.out.println("interrupted " + e);
            }
        }

    }

    private void repaintGui(ControllerState cs) {
        //whatever needs to be changed in the gui goes here
    }

    private void guiReaction() {
        //acabei de inventar isto pa servir de placeholder para as cenas que devem ser chamadas pelos eventos do gui

        String ret = culib.CU30WOpen(new IntByReference(1), new IntByReference(1), new IntByReference(1), new IntByReference(1));
        System.out.println(ret);
        culib.CU30WrapperDispose();
        cstate.close();
    }


    public static void main(String[] args) {
        Afmcc afmcc = new Afmcc();
    }
}
