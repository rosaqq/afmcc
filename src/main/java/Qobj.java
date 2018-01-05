import com.studiohartman.jamepad.ControllerState;

public class Qobj {

    public boolean fromGui;

    public float lx, ly, ry;
    public boolean htop, hbot, hleft, hright, hx, htri, L1;

    public int i;

    public Qobj(ControllerState cs) {

        //constructor for gamepad
        fromGui = false;

        // x sweep
        lx = cs.leftStickX;
        // y sweep
        ly = cs.leftStickY;
        // z sweep
        ry = cs.rightStickY;

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
    }


    public Qobj(int i) {

        //constructor for gui
        fromGui = true;
        this.i = i;
    }

    @Override
    public String toString() {
        return "Queue object #" + i + " from " + (fromGui? "GUI" : "Controller");
    }
}