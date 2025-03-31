import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame{
    private JTextField UserName;
    private JPanel panel1;
    private JPasswordField Password;
    private JButton signInButton;
    private JLabel linkLabel;


    //Constructor para el login
    public Login() {

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = UserName.getText();
                String password = new String(Password.getPassword());

                if(Verify(user, password)){
                    JOptionPane.showMessageDialog(null, "Inicio Sesion Completado");
                }else{
                    JOptionPane.showMessageDialog(null, "El username o la password son incorrectas, Intente de nuevo.");
                    UserName.setText("");
                    Password.setText("");
                }
            }
        });
        linkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Register().setVisible(true);
                dispose();
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

    //Metodo para verificar usuario registrado
    private boolean Verify(String user, String password){
        try(Connection c = ConexionBD.getInstance().getConnection()){
            PreparedStatement ps = c.prepareStatement("SELECT * FROM register WHERE UserName = ? AND password = ?");
            ps.setString(1, user);
            ps.setString(2, password);

            ResultSet result = ps.executeQuery();// Ejecutando el query
            //si existe un resultado, el usuario es valido
            return result.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
