import com.studiohartman.jamepad.ControllerState;

import java.awt.*;
import java.util.concurrent.BlockingQueue;

public class Afmcc {

    private int controllerIndex = 0;
    private BlockingQ<ControllerState> bq = new BlockingQ<ControllerState>();
    Cstate cstate;


    public Afmcc() {
        cstate = new Cstate(controllerIndex,bq);
        Thread cinput = new Thread(cstate, "cinput");
        cinput.start();

        //program aberto a receber memes do gui
        //try {
        //    Thread.sleep(1000);
        //} catch(InterruptedException e) {
        //    System.out.println("interrupt");
        //}
        while(true){
            try {
                ControllerState cs = bq.poll();
                repaintGui(cs);
            } catch (InterruptedException e) {
                System.out.println("interrupted " + e);
            }
        }

        //eventualmente ordem do gui para fechar
        //cstate.close();
    }

    private void repaintGui(ControllerState cs) {
        //whatever needs to be changed in the gui goes here

    }


    public static void main(String[] args) {
        Afmcc afmcc = new Afmcc();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    gui frame = new gui(afmcc);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
