package exercise2_2_6;
import javax.swing.JOptionPane;

public class EquaSolve {

    public static void main(String[] args) {
        String menu = "What do you want to solve \n Type 1 for Linear equation\n Type 2 for Linear system\n Type 3 for Second-degree equation\n";

        while (true) {
            String command = JOptionPane.showInputDialog(null, menu, "Choose what to do", JOptionPane.QUESTION_MESSAGE);
            double icom = Double.parseDouble(command);
            if(icom == 1)
            {
            	LiEqua();
            	break;
            }
            if(icom == 2)
            {
                    LiSys();
                    break;
            }
            if(icom == 3)
            {
                    QuaEqua();
                    break;
            }
            if(icom != 1 && icom != 2 && icom !=3) 
            	JOptionPane.showMessageDialog(null, "Please enter 1 or 2 or 3!");
           }
       }
    

    private static void LiEqua() {
        double a = Double.parseDouble(JOptionPane.showInputDialog("Enter a:"));
        double b = Double.parseDouble(JOptionPane.showInputDialog("Enter b:"));

        if (a == 0) {
            if (b == 0) {
                JOptionPane.showMessageDialog(null, "Infinite Solutions");
            } else {
                JOptionPane.showMessageDialog(null, "No Solution");
            }
        } else {
            double x = -b / a;
            JOptionPane.showMessageDialog(null, "Root x = " + x);
        }
    }

    private static void LiSys() {
        double a11 = Double.parseDouble(JOptionPane.showInputDialog("Enter a11:"));
        double a12 = Double.parseDouble(JOptionPane.showInputDialog("Enter a12:"));
        double b1  = Double.parseDouble(JOptionPane.showInputDialog("Enter b1:"));
        double a21 = Double.parseDouble(JOptionPane.showInputDialog("Enter a21:"));
        double a22 = Double.parseDouble(JOptionPane.showInputDialog("Enter a22:"));
        double b2  = Double.parseDouble(JOptionPane.showInputDialog("Enter b2:"));

        double D  = a11*a22-a21*a12;
        double D1 = b1*a22-b2 *a12;
        double D2 = a11*b2-a21*b1;

        if (D != 0) {
            double x1 = D1 / D;
            double x2 = D2 / D;
            JOptionPane.showMessageDialog(null, "The only root:\nx1 = " + x1 + "\nx2 = " + x2);
        } else {
            if (D1 == 0 && D2 == 0) {
                JOptionPane.showMessageDialog(null, "Infinite Solutions");
            } else {
                JOptionPane.showMessageDialog(null, "No Solution");
            }
        }
    }

    private static void QuaEqua() {
        double a = Double.parseDouble(JOptionPane.showInputDialog("Enter a:"));
        double b = Double.parseDouble(JOptionPane.showInputDialog("Enter b:"));
        double c = Double.parseDouble(JOptionPane.showInputDialog("Enter c:"));

        if (a == 0) {
            if (b == 0) {
                JOptionPane.showMessageDialog(null, c == 0 ? "Infinte Solutions" : "No Solution");
            } else {
                JOptionPane.showMessageDialog(null, "Root of the Linear Equation x = " + (-c / b));
            }
        } else {
            double delta = b * b - 4 * a * c;
            if (delta > 0) {
                double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                JOptionPane.showMessageDialog(null, "Two different roots:\nx1 = " + x1 + "\nx2 = " + x2);
            } else if (delta == 0) {
                double x = -b / (2 * a);
                JOptionPane.showMessageDialog(null, "Double root: x = " + x);
            } else {
                JOptionPane.showMessageDialog(null, "No real solution");
            }
        }
    }
}