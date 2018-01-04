import com.sun.jna.ptr.IntByReference;

public class Afmcc {

    private int controllerIndex = 0;

    public Afmcc() {
        /*
        Cstate cstate = new Cstate(controllerIndex);
        Thread cinput = new Thread(cstate, "cinput");
        cinput.start();

        //program aberto a receber memes do gui
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            System.out.println("interrupt");
        }

        //eventualmente ordem do gui para fechar
        cstate.close();
        */

        CU30Wrap culib = CU30Wrap.INSTANCE;

        culib.CU30WrapperInit();
        String ret = culib.CU30WOpen(new IntByReference(1), new IntByReference(1), new IntByReference(1), new IntByReference(1));
        System.out.println(ret);
        culib.CU30WrapperDispose();

    }

    public static void main(String[] args) {
        new Afmcc();
    }
}
