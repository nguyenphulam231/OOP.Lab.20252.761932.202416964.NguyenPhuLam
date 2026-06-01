package exercise2_2_4;
import javax.swing.JOptionPane;
public class ShowTwoNumbers
{
	public static void main(String arg[])
	{
		String strnum1, strnum2;
		String strnotification= "You've just entered: ";
		strnum1 = JOptionPane.showInputDialog(null, "Please input the first number: ", "Input the first number", JOptionPane.INFORMATION_MESSAGE);
		strnotification+=strnum1+" and ";
		strnum2 = JOptionPane.showInputDialog(null, "Please input the second number: ", "Input the second number", JOptionPane.INFORMATION_MESSAGE);
		strnotification+=strnum2;
		JOptionPane.showMessageDialog(null, strnotification, "Show 2 numbers", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}
}