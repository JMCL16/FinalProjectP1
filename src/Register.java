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
    private JPasswordField Password;
    private JButton signUpButton;
    PreparedStatement ps;

    //Constructor de la clase register
    public Register() {
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validateFields()){
                    Insert();
                }
            }
        });
        setLocationRelativeTo(null);
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Para solo cerrar esta ventana
        setVisible(true);
        pack();
    }

    //Metodo para insertar nuevos usuarios en la base de datos
    private void Insert(){
        //Usando singleton para utilizar la misma instancia
        try(Connection c = ConexionBD.getInstance().getConnection()){
            //Sentencia para enviar a la base de datos
            ps = c.prepareStatement("INSERT INTO register (name, lastname, username, password) VALUES (?,?,?,?)");
            //Capturando parametros
            ps.setString(1, Name.getText());
            ps.setString(2, LastName.getText());
            ps.setString(3, UserName.getText());
            ps.setString(4, new String(Password.getPassword()));

            int rowsAffected = ps.executeUpdate();
            //Verificacion de filas afectadas en la base de datos
            if(rowsAffected > 0){
                JOptionPane.showMessageDialog(null, "Nuevo usuario registrado");
            }else{
                JOptionPane.showMessageDialog(null, "Error al registrar nuevo usuario ");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    //Metodo para validar campos
    private boolean validateFields() {
        String vUser = UserName.getText().trim();
        String vPassword = new String(Password.getPassword()).trim();
        String vName = Name.getText().trim();
        String vLastName = LastName.getText().trim();

        if (vName.isEmpty()) {
            showMessage("El campo Name es obligatorio");
            return false;
        }

        if (vLastName.isEmpty()) {
            showMessage("El campo Lastname es obligatorio");
            return false;
        }

        if (vUser.isEmpty()) {
            showMessage("El campo Username es obligatorio");
            return false;
        }

        if (vPassword.isEmpty()) {
            showMessage("El campo Password es obligatorio");
            return false;
        }
        return true; //Campos llenos
    }

    private void showMessage(String message){
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    //Metodo para invocar componentes de la UI personalizados
    private void createUIComponents() {
        // TODO: place custom component creation code here
        panel1 = new JPanel();
    }
}

