import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register extends JFrame {
    private JTextField Name;
    private JPanel panel1;
    private JTextField LastName;
    private JTextField UserName;
    private JTextField Password;
    private JButton signUpButton;
    PreparedStatement ps;

    public Register() {
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Insert();
            }
        });
    }


    public static void main(String[] args) {
        Register r = new Register();
        r.setContentPane(r.panel1);
        r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        r.setVisible(true);
        r.pack();
    }

    private void Insert(){
        try(Connection c = ConexionBD.getInstance().getConnection()){
            ps = c.prepareStatement("INSERT INTO register VALUES (?,?,?,?,?)");
            ps.setInt(1,1);
            ps.setString(2, Name.getText());
            ps.setString(3, LastName.getText());
            ps.setString(4, UserName.getText());
            ps.setString(5, Password.getText());
            int rowsAffected = ps.executeUpdate();

            if(rowsAffected > 0){
                JOptionPane.showMessageDialog(null, "Nuevo usuario registrado");
            }else{
                JOptionPane.showMessageDialog(null, "Error al registrar nuevo usuario ");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        panel1 = new JPanel();
    }
}

