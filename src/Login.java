import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class Login extends JFrame{
    private JTextField UserName;
    private JPanel panel1;
    private JPasswordField Password;
    private JButton Enter;

    public Login() {
        Enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(Connection c = ConexionBD.getInstance().getConnection()){
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static void main(String[] args) {
        Login l = new Login();
        l.setContentPane(new Login().panel1);
        l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l.setVisible(true);
        l.pack();
    }


}
