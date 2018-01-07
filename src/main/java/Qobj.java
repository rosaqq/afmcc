import com.studiohartman.jamepad.ControllerState;


/**
 * Qobj.java
 * Purpose: Object for a LinkedBlockingQueue serving as communication from the {@link Gui GUI} and {@link Cstate GPAD} threads to the MAIN thread.
 */
public class Qobj {

    /**
     *  Specifies if this object comes from the {@link Gui GUI} or from the {@link Cstate GPAD}.
     */
    public boolean fromGui;

    /**
     *  Each of these corresponds to a button on the {@link Cstate GPAD} or the {@link Gui GUI}.
     *  Their names are based on the GPAD button names.
     *  The variables starting with h correspond to the GPAD hat + the x and triangle keys. These are responsible for step commands.
     *  L1 corresponds to the GPAD L1 key and is responsible for stop commands.
     */
    public boolean htop, hbot, hleft, hright, hx, htri, L1;

    /**
     * This variable holds a string representing which set of keys triggered the creating of this object.
     * Values can be "hat" (which includes the triangle and the x keys), "axis" (representing the joysticks), and "L1" (for just the L1 key).
     */
    public String trigger;

    //ax velocity can come from both gui and controller
    //but steps and step velocity can only come from gui so they are fields in the main class
    /**
     * This array holds the values for the sweep velocities coming either from the {@link Gui GUI} scrollbars or the {@link Cstate GPAD} joysticks.
     * Step number and velocity can only come from the GUI so they are volatile fields in the main class.
     */
    public int[] axvel = new int[3];

    /**
     * The constructor for the gamepad ({@link Cstate GPAD}).
     *
     * @param cs The ControllerState from the gamepad.
     */
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


    /**
     * The main constructor for the  {@link Gui GUI}.
     *
     * Note: the mappings described below are subject to change.
     *
     * @param x Scrollbar X value, -1000<= x <= 1000, maps to this.axvel[0].
     * @param y Scrollbar Y value, -1000<= y <= 1000, maps to this.axvel[1].
     * @param z Scrollbar Z value, -1000<= z <= 1000, maps to this.axvel[2].
     * @param bx1 -X step button, maps to this.htop.
     * @param bx2 +X step button, maps to this.hbot.
     * @param by1 -Y step button, maps to this.hleft.
     * @param by2 -Y step button, maps to this.hright.
     * @param bz1 -Z step button, maps to this.hx.
     * @param bz2 +Z step button, maps to this.htri.
     * @param stop Stop button, maps to this.L1.
     */
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

    /**
     * Utility function that maps the {@link Cstate GPAD} analog axis values to a velocity int.
     *
     * @param vel the GPAD analog axis values
     * @return normalized int velocity values
     */
    private int normalizeVel(float vel) {
        int value = Math.round(vel);
        if(value>1000) return 1000;
        if(value<-1000) return -1000;
        return value;
    }

    /**
     * Qobj override of the Java toString method.
     * @return String saying whether this Qobj was constructed from the {@link Gui GUI} or from the {@link Cstate GPAD}.
     */
    @Override
    public String toString() {
        return "[QOBJ] Recieved queue object from " + (fromGui? "GUI" : "GPAD");
    }
}