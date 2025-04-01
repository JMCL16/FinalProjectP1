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
    private JTextField Email;
    private JPasswordField confirmPass;
    private JTextField numCel;
    PreparedStatement ps;

    //Constructor de la clase register
    public Register() {
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validateFields()){
                    Insert();
                    dispose();
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
        try(Connection c = ConexionBD.getInstance()){
            //Sentencia para enviar a la base de datos
            ps = c.prepareStatement("INSERT INTO register (name, lastname, email, numCel, username, password) VALUES (?,?,?,?,?,?)");
            //Capturando parametros
            ps.setString(1, Name.getText());
            ps.setString(2, LastName.getText());
            ps.setString(3, Email.getText());
            ps.setString(4, numCel.getText());
            ps.setString(5, UserName.getText());
            ps.setString(6, new String(Password.getPassword()));

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
        String vName = Name.getText().trim();
        String vLastName = LastName.getText().trim();
        String vEmail = Email.getText().trim();
        String vNumCel = numCel.getText().trim();
        String vUser = UserName.getText().trim();
        String vPassword = new String(Password.getPassword()).trim();
        String vConPass =  new String(confirmPass.getPassword()).trim();

        //Enviando mensajes por campos vacios
        if (vName.isEmpty()) {
            showMessage("El campo Name es obligatorio");
            return false;
        }
        if (vLastName.isEmpty()) {
            showMessage("El campo Lastname es obligatorio");
            return false;
        }
        if (vEmail.isEmpty()) {
            showMessage("El campo Email es obligatorio");
            return false;
        }
        if (vNumCel.isEmpty()) {
            showMessage("El campo Telefono es obligatorio");
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
        if (vConPass.isEmpty()) {
            showMessage("El campo Confirmar Password es obligatorio");
            return false;
        }
        //Verificando si password y confirmar password son iguales
        if(!vPassword.equals(vConPass)){
            showMessage("El campo Confirmar Password debe ser igual al de Password");
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

