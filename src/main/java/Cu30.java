import com.sun.jna.ptr.IntByReference;

public class Cu30 {

    private CU30Wrap lib;
    private int usbver, usbinst, devid, eeid;

    public Cu30(CU30Wrap culib, int USBInstance, int USBVersion, int DevID, int EEID) {

        lib = culib;
        usbinst = USBInstance;
        usbver = USBVersion;
        devid = DevID;
        eeid = EEID;
    }

    public String open() {
        IntByReference a = new IntByReference(usbinst);
        IntByReference b = new IntByReference(usbver);
        IntByReference c = new IntByReference(devid);
        IntByReference d = new IntByReference(eeid);

        String ret = lib.CU30WOpen(a, b, c, d);

        usbinst = a.getValue();
        usbver = b.getValue();
        devid = c.getValue();
        eeid = d.getValue();

        return ret;
    }

    void close() {
        lib.CU30WClose(usbinst, usbver, devid, eeid);
    }

    void step(int axis, int vel, int steps) {
        lib.CU30WStep(usbinst, usbver, devid, eeid, axis, steps, vel);
    }

    void sweep(int axis, int vel, int timeout) {
        lib.CU30WSweep(usbinst, usbver, devid, eeid, vel, axis, timeout);
    }

    void stop() {
        lib.CU30WStop(usbinst, usbver, devid, eeid);
    }
}