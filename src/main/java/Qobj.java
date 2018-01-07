import com.studiohartman.jamepad.ControllerState;

public class Qobj {

    public boolean fromGui;

    public boolean htop, hbot, hleft, hright, hx, htri, L1;
    public String trigger;

    //ax velocity can come from both gui and controller
    //but steps and step velocity can only come from gui so they are fields in the main class
    public int[] axvel = new int[3];

    //constructor for gamepad
    public Qobj(ControllerState cs) {

        fromGui = false;

        //no need for raw axis values so only pass normalized ones
        //sweep vel x
        axvel[0] = normalizeVel(cs.leftStickX*1000);
        //sweep vel y
        axvel[1] = normalizeVel(cs.leftStickY*1000);
        //sweep vel z
        axvel[2] = normalizeVel(cs.rightStickY*1000);

        // x step
        htop = cs.dpadUpJustPressed;
        hbot = cs.dpadDownJustPressed;
        // y step
        hleft = cs.dpadLeftJustPressed;
        hright = cs.dpadRightJustPressed;
        // z step
        hx = cs.aJustPressed;
        htri = cs.yJustPressed;

        // L1 stop
        L1 = cs.lbJustPressed;

        //what triggered this qobj
        trigger = (L1? "L1":((htop||hbot||hleft||hright||hx||htri)?"hat":"axis"));

    }



    //main constructor for gui
    public Qobj(int x, int y, int z, boolean bx1, boolean bx2, boolean by1, boolean by2, boolean bz1, boolean bz2, boolean stop) {

        fromGui = true;

        //values already normalized from gui
        //sweep vel x
        axvel[0] = x;
        //sweep vel y
        axvel[1] = y;
        //sweep vel z
        axvel[2] = z;

        // x step
        htop = bx1;
        hbot = bx2;
        // y step
        hleft = by1;
        hright = by2;
        // z step
        hx = bz1;
        htri = bz2;

        // L1 stop
        L1 = stop;

        //what triggered this qobj
        trigger = (L1? "L1":((htop||hbot||hleft||hright||hx||htri)?"hat":"axis"));

    }


    private int normalizeVel(float vel) {
        int value = Math.round(vel);
        if(value>1000) return 1000;
        if(value<-1000) return -1000;
        return value;
    }

    @Override
    public String toString() {
        return "[QOBJ] Recieved queue object from " + (fromGui? "GUI" : "GPAD");
    }
}