import javax.swing.*;
public class Register extends JFrame {
    private JTextField Name;
    private JPanel panel1;
    private JTextField LastName;
    private JTextField UserName;
    private JTextField Password;
    private JButton signUpButton;

    public static void main(String[] args) {
        Register r = new Register();
        r.setContentPane(new Register().panel1);
        r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        r.setVisible(true);
        r.pack();
    }
}

