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
        String bin_string = Integer.toBinaryString(a);
        StringBuilder invert_bin = new StringBuilder();

        for (int i = 0; i < bin_string.length(); i++) {
            char bit = bin_string.charAt(i);
            invert_bin.append(bit == '0' ? '1' : '0');
        }

        invert_num = Integer.parseInt(invert_bin.toString(), 2);
    }

    public int getInvertNum()
    {
        return invert_num;
    }
}
