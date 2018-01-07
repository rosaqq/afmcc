import com.studiohartman.jamepad.ControllerState;

public class Qobj {

    public boolean fromGui;

    public float lx, ly, ry;
    public boolean htop, hbot, hleft, hright, hx, htri, L1;

    public int i;

    private String function;
    private int var1,var2,var3;

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




    public Qobj(String func, int v1, int v2, int v3) {

        fromGui = true;

        function=func;
        var1 = v1;
        var2 = v2;
        var3 = v3;
    }

    public Qobj(String func) {
        this(func, 0, 0, 0);
    }

    public String getFunction() {
        return function;
    }

    public int getVar1() {
        return var1;
    }

    public int getVar2() {
        return var2;
    }
    public int getVar3() {
        return var3;
    }

    @Override
    public String toString() {
        return "Recieved queue object from " + (fromGui? "GUI" : "Controller");
    }
}