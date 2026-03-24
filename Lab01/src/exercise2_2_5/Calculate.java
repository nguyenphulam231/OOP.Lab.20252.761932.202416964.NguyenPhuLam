package exercise2_2_5;
import javax.swing.JOptionPane;
public class Calculate {
	public static void main(String args[])
	{
		String snum1;
		String snum2;
		String command = JOptionPane.showInputDialog(null, "What do you want to do? \n Type 1 to calculate sum \n Type 2 to calculate difference \n Type 3 to calculate product \n Type 4 to calculate quotient");
		double icom=Double.parseDouble(command);
		if(icom !=1 && icom!=2 && icom!=3) 
		{
			JOptionPane.showMessageDialog(null, "Error: Invalid Selection");
			System.exit(0);
		}
		snum1 = JOptionPane.showInputDialog(null, "Enter the first number:", "First number", JOptionPane.INFORMATION_MESSAGE);
		snum2 = JOptionPane.showInputDialog(null, "Enter the second number:", "Second number", JOptionPane.INFORMATION_MESSAGE);
		double num1 = Double.parseDouble(snum1);
		double num2 = Double.parseDouble(snum2);
		double sum = num1+num2;
		double diff= num1-num2;
		double prod= num1*num2;
	
		if(icom == 1)
		{
			JOptionPane.showMessageDialog(null, sum);
			System.exit(0);
		}
		if(icom == 2)
		{
			JOptionPane.showMessageDialog(null, diff);
			System.exit(0);
		}
		if(icom == 3)
		{
			JOptionPane.showMessageDialog(null, prod);
			System.exit(0);
		}
		if(icom == 4)
		{
			if(num2==0)
			{
				JOptionPane.showMessageDialog(null, "Error: Divisor can't be 0");
				System.exit(0);
			}
			else
			{
				double quot= num1/num2;;
				JOptionPane.showMessageDialog(null, quot);
				System.exit(0);
		
			}
	
		}

	}
}