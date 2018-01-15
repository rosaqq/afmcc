import com.studiohartman.jamepad.ControllerState;

import java.util.Arrays;


/**
 * Qobj.java
 * Purpose: Object for a LinkedBlockingQueue serving as communication from the {@link Gui GUI} and {@link Cstate GPAD} threads to the MAIN thread.
 * All fields are public, but immutable.
 */
public final class Qobj {

    /**
     *  Specifies if this object comes from the {@link Gui GUI} or from the {@link Cstate GPAD}.
     */
    public final boolean fromGui;

    /**
     *  Each of these corresponds to a button on the {@link Cstate GPAD} or the {@link Gui GUI}.
     *  Their names are based on the GPAD button names.
     *  The variables starting with h correspond to the GPAD hat + the x and triangle keys. These are responsible for step commands.
     *  L1 corresponds to the GPAD L1 key and is responsible for stop commands.
     *  axvelIsZero is true if all the elements in axvel are zero.
     */
    public final boolean htop, hbot, hleft, hright, hx, htri, L1, axvelIsZero;

    /**
     * This variable holds a string representing which set of keys triggered the creating of this object.
     * Values can be "hat" (which includes the triangle and the x keys), "axis" (representing the joysticks), and "L1" (for just the L1 key).
     */
    public final String trigger;

    //ax velocity can come from both gui and controller
    //but steps and step velocity can only come from gui so they are fields in the main class
    /**
     * This array holds the values for the sweep velocities coming either from the {@link Gui GUI} scrollbars or the {@link Cstate GPAD} joysticks.
     * Step number and velocity can only come from the GUI so they are volatile fields in the main class.
     */
    public final int[] axvel = new int[3];

    /**
     * Threshold value to filter out gamepad axis analog fluctuations.
     * isConnected: 0 for no controller connected; 1 for controller connected; 2 for Qobj from GUI.
     */
    public final int thold, isConnected;

    /**
     * The constructor for the gamepad ({@link Cstate GPAD}).
     *
     * @param cs The ControllerState from the gamepad.
     */
    public Qobj(ControllerState cs, int threshold) {

        fromGui = false;
        isConnected = cs.isConnected?1:0;
        thold = threshold;

        //these values are already set with the afm position
        //no need for raw axis values so only pass normalized ones
        //sweep vel x
        int x = normalizeVel(cs.leftStickY * 1000);
        axvel[0] = x<thold?0:x;
        //sweep vel y
        int y = normalizeVel(-cs.leftStickX * 1000);
        axvel[1] = y<thold?0:y;
        //sweep vel z
        int z = normalizeVel(-cs.rightStickY * 1000);
        axvel[2] = z<thold?0:z;


        axvelIsZero = axvel[0] == 0 && axvel[1] == 0 && axvel[2] == 0;

        // x step
        htop = cs.dpadUpJustPressed;
        hbot = cs.dpadDownJustPressed;
        // y step
        hleft = cs.dpadLeftJustPressed;
        hright = cs.dpadRightJustPressed;
        // z step
        hx = cs.aJustPressed;
        htri = cs.yJustPressed;

        //todo: add steps+-=10 on circle(+) and square(-) gamepad keys with live update on gui

        // L1 stop
        L1 = cs.lbJustPressed;

        //what triggered this qobj
        trigger = (L1? "L1":((htop||hbot||hleft||hright||hx||htri)?"hat":"axis"));

    }


    /**
     * The main constructor for the  {@link Gui GUI}.
     *
     * The mappings below are definitive.
     *
     * @param x Scrollbar X value, -1000<= x <= 1000, maps to this.axvel[0].
     * @param y Scrollbar Y value, -1000<= y <= 1000, maps to this.axvel[1].
     * @param z Scrollbar Z value, -1000<= z <= 1000, maps to this.axvel[2].
     * @param bx1 -X step button, maps to this.hbot.
     * @param bx2 +X step button, maps to this.htop.
     * @param by1 -Y step button, maps to this.hright.
     * @param by2 -Y step button, maps to this.hleft.
     * @param bz1 -Z step button, maps to this.htri.
     * @param bz2 +Z step button, maps to this.hx.
     * @param stop Stop button, maps to this.L1.
     */
    public Qobj(int x, int y, int z, boolean bx1, boolean bx2, boolean by1, boolean by2, boolean bz1, boolean bz2, boolean stop) {

        fromGui = true;
        isConnected=2;
        thold = 0;

        //values already normalized from gui
        //sweep vel x
        axvel[0] = x;
        //sweep vel y
        axvel[1] = y;
        //sweep vel z
        axvel[2] = z;

        axvelIsZero = axvel[0] == 0 && axvel[1] == 0 && axvel[2] == 0;


        // x step
        htop = bx2;
        hbot = bx1;
        // y step
        hleft = by2;
        hright = by1;
        // z step
        hx = bz2;
        htri = bz1;

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
     * Qobj override of the Object toString method.
     * @return String with values for all of the Qobj fields.
     */
    @Override
    public String toString() {
        return "[QOBJ] from " + (fromGui? "GUI" : "GPAD") + ".\n" + "Contents:" +
                        "\n\thtop: "+htop+"\n\thbot: "+hbot+"\n\thleft: "+hleft+"\n\thright: "+hright+
                        "\n\thx: "+hx+"\n\thtri: "+htri+"\n\tL1: "+L1+ "\n\ttrigger: "+trigger+"\n\taxvel: "+
                        Arrays.toString(axvel);
    }

    /**
     * Qobj override of the Object equals method.
     * @param obj the object to compare with this one.
     * @return true if obj instanceof Qobj and all the values of it's fields are the same as this Qobj's fields.
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Qobj)) return false;
        Qobj a = (Qobj) obj;
        return  a.fromGui==this.fromGui && a.htop==this.htop && a.hbot==this.hbot && a.hleft==this.hleft &&
                a.hright==this.hright && a.hx==this.hx && a.htri==this.htri && a.L1==this.L1 &&
                a.trigger.equals(this.trigger) && Arrays.equals(a.axvel, this.axvel);
    }
}