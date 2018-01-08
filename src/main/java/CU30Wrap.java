import com.sun.jna.FunctionMapper;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallFunctionMapper;
import com.sun.jna.win32.StdCallLibrary;

import java.util.HashMap;

public interface CU30Wrap extends StdCallLibrary {


    //requires StdCallFunctionMapper on the AFMLAB windows 7 PC, native function names are suffixed with @bytes as seen on depends.exe
    CU30Wrap INSTANCE = (CU30Wrap) Native.loadLibrary("CU30Wrap", CU30Wrap.class, new HashMap<String, FunctionMapper>() {{put(Library.OPTION_FUNCTION_MAPPER, new StdCallFunctionMapper()); }});
    CU30Wrap SYNC_INSTANCE = (CU30Wrap)Native.synchronizedLibrary(INSTANCE);

    void CU30WrapperInit();
    void CU30WrapperDispose();

    String CU30WOpen(IntByReference USBInstance, IntByReference USBVersion, IntByReference DevID, IntByReference EEID);

    int CU30WGetEEpromInfo(int USBInstance, int USBVersion, int DevID, int EEID,
                           IntByReference pUSBVendorID, IntByReference pUSBProductID, IntByReference pUSBDeviceID,
                           IntByReference pDeviceID, IntByReference pEEPromID, IntByReference pVersion,
                           IntByReference pSerialNumber, IntByReference pCustomerID,
                           String pCompany, String pDate, String pProductStr, String pCustomer, String pCustomerStr);

    void CU30WClose(int USBInstance, int USBVersion, int DevID, int EEID);

    void CU30WStop(int USBInstance, int USBVersion, int DevID, int EEID);
    void CU30WStep(int USBInstance, int USBVersion, int DevID, int EEID, int Axis, int n, int Vel);
    void CU30WSweep(int USBInstance, int USBVersion, int DevID, int EEID, int Vel, int Axis, int Timeout);

}
