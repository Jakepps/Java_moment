package calc.operation;

public class Inverter
{
    private int invert_num;

    public Inverter()
    {
        invert_num = 0;
    }

    public Inverter(int a)
    {
        this.invert_num = a;
    }

    public void invert(int a)
    {
        invert_num = -a;
    }

    public int getInvertNum()
    {
        return invert_num;
    }
}
