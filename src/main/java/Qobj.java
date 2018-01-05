public class Qobj {

    private String function;
    private int var1,var2,var3;

    public Qobj(String func, int v1, int v2, int v3) {
        function=func;
        var1 = v1;
        var2 = v2;
        var3 = v3;
    }

    public Qobj(String func){
        function = func;
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
}
