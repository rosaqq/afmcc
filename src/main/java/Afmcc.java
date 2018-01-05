import com.sun.jna.ptr.IntByReference;

import java.awt.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Afmcc {

    private int controllerIndex = 0;
    public LinkedBlockingQueue<Qobj> bq;
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

        while(true){
            try {
                Qobj qobj = bq.take();
                System.out.println(qobj);

                if(qobj.fromGui) {
                    switch (qobj.getFunction()){
                        case "sweep":
                            System.out.printf("sweep(%d, %d, %d)\n", qobj.getVar1(), qobj.getVar2(), qobj.getVar3());
                            //culib.CU30WSweep(tbd,tbd,tbd,tbd,qobj.getVar1(),qobj.getVar2(),qobj.getVar3());
                            break;
                        case "stop":
                            System.out.println("stop call");
                            //culib.CU30WStop(tbd,tbd,tbd,tbd);
                            break;
                        case "step":
                            System.out.printf("step(%d, %d, %d)\n", qobj.getVar1(), qobj.getVar2(), qobj.getVar3());
                            //culib.CU30WStep(tbd,tbd,tbd,tbd,qobj.getVar1(), qobj.getVar2(),qobj.getVar2());
                            break;
                    }
                } else {
                    //processing trash do secknv goes below \/

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
        culib.CU30WClose(0,0,0,0);
        culib.CU30WrapperDispose();
        cstate.close();
    }


    public static void main(String[] args) {
        new Afmcc();
    }
}
