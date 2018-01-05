import com.sun.jna.ptr.IntByReference;

import java.awt.*;

public class Afmcc {

    private int controllerIndex = 0;
    public BlockingQ<Qobj> bq = new BlockingQ<Qobj>();
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

        cstate = new Cstate(controllerIndex, bq);

        Thread cinput = new Thread(cstate, "cinput");
        cinput.start();

        //initialize java native access interface
        culib = CU30Wrap.INSTANCE;
        culib.CU30WrapperInit();

        while(true){
            try {
                Qobj o = bq.poll();
                switch (o.getFunction()){
                    case "sweep":   culib.CU30WSweep(0,0,0,0,o.getVar1(),o.getVar2(),o.getVar3());
                                    break;
                    case "stop":    culib.CU30WStop(0,0,0,0);
                                    break;
                    case "step":    culib.CU30WStep(0,0,0,0,o.getVar1(),o.getVar2(),o.getVar3());
                                    break;
                }
                repaintGui();
            } catch (InterruptedException e) {
                System.out.println("interrupted " + e);
            }
        }

    }

    private void repaintGui() {
        //whatever needs to be changed in the Gui goes here, not sure if needed {standby}
    }

    private void guiReaction() {
        //acabei de inventar isto pa servir de placeholder para as cenas que devem ser chamadas pelos eventos do Gui
        String ret = culib.CU30WOpen(new IntByReference(1), new IntByReference(1), new IntByReference(1), new IntByReference(1));
        System.out.println(ret);
        culib.CU30WrapperDispose();
        cstate.close();
    }


    public static void main(String[] args) {
        Afmcc afmcc = new Afmcc();
    }
}
