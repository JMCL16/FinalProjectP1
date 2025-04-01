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
        setTitle("Login");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = UserName.getText();
                String password = new String(Password.getPassword());

                //Validando si los campos estan vacios
                if (!validateFields()){
                    return;
                }

                //Verificando si el user y password son correctos
                if(verify(user, password)){
                    JOptionPane.showMessageDialog(null, "Inicio Sesion Completado");
                    new Main().setVisible(true);
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "El username o la password son incorrectas," +
                            " Intente de nuevo.");
                    UserName.setText("");
                    Password.setText("");
                }
            }
        });

        //Accion para acceder a registro
        linkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Register().setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Login::new);
    }

    //Metodo para verificar usuario registrado
    private boolean verify(String user, String password){
        try(Connection c = ConexionBD.getInstance()){
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

    //Metodo para validar campos
    private boolean validateFields(){
        String vUser = UserName.getText().trim();
        String vPassword = new String(Password.getPassword()).trim();

        if (vUser.isEmpty() && vPassword.isEmpty()){
            JOptionPane.showMessageDialog(null, "Debe ingresar su usuario y contraseña, " +
                            "si no está registrado debe registrarse"
                    , "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            return false;
        }
        return true;
    }
}
