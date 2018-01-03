
public class Afmcc {

    private int controllerIndex = 0;

    public Afmcc() {
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
    }

    public static void main(String[] args) {
        new Afmcc();
    }
}
