import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main extends JFrame {

    private JPanel panel1;
    private JButton newCli;
    private JButton updateCli;
    private JButton deleteCli;
    private JButton closeSession;
    private DefaultTableModel model;
    private JTable table1;
    private JLabel titleLabel;

    public Main(){
        setTitle("Clientes Registrados");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Para solo cerrar esta ventana
        setLocationRelativeTo(null);
        panel1 = new JPanel(new BorderLayout(10,10));

        //Creando tabla
        String[] columnas = {"Nombre", "Apellido", "Correo electrónico", "Teléfono", "Usuario"};
        model = new DefaultTableModel(columnas, 0);
        table1 = new JTable(model);
        table1.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table1);
        table1.setBorder(new EmptyBorder(10, 10, 10, 10));
        //Creando los botones
        JPanel buttonPanel = new JPanel(new GridLayout(1,4,10, 10));
        buttonPanel.setBorder(new EmptyBorder(5,10,10,10));
        buttonPanel.add(newCli);
        buttonPanel.add(updateCli);
        buttonPanel.add(deleteCli);
        buttonPanel.add(closeSession);

        panel1.add(scrollPane); //Agregando tabla al panel
        panel1.add(buttonPanel, BorderLayout.SOUTH); //Agregando botones al panel
        panel1.add(titleLabel, BorderLayout.NORTH); //Agregando titulo al panel
        setContentPane(panel1);
        setVisible(true);

        //Funcion Boton cerrar sesion
        closeSession.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login().setVisible(true);
                dispose();
            }
        });

        //Funcion Boton nuevo
        newCli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Register().setVisible(true);
            }
        });
        updateCli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        loadUser();
    }

    //Metodo para cargar Usuario
    private void loadUser(){
        model.setRowCount(0);
        try(Connection c = ConexionBD.getInstance()){
            PreparedStatement ps = c.prepareStatement("SELECT * FROM register");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                model.addRow(new Object[]{
                        rs.getString("name"),
                        rs.getString("lastname"),
                        rs.getString("email"),
                        rs.getString("numCel"),
                        rs.getString("UserName"),
                });
            }
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}