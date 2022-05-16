package bajuStore;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class formLogin extends JFrame implements ActionListener {
	//contentPanel
	JPanel contentPanel = new JPanel(new GridLayout(0,2));
	JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel idFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel passFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	
	JLabel idEmployee = new JLabel("Id Employee :");
	JTextField idField = new JTextField();
	
	JLabel passEmployee = new JLabel("Password :");
	JPasswordField passField = new JPasswordField();
	
	//bagianBawah
	JPanel bagianBawahPanel = new JPanel(new GridLayout(2,1));
	
	//button
	JPanel buttonPanel = new JPanel();
	JButton login = new JButton("Login");
	
	//label pesan error
	JPanel pesanErrorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel pesanError1 = new JLabel("Id Harus Diisi!");
	JLabel pesanError2 = new JLabel("Password Harus Diisi!");
	JLabel pesanError3 = new JLabel("Id dan Password Harus Diisi!");
	JLabel pesanError4 = new JLabel("Id atau Password Tidak Valid!");
	
	//connect ke database
	DatabaseConnector connector = DatabaseConnector.getConnector();
	
	public void setComponent() {
		//add content
		idField.setPreferredSize(new Dimension(200,20));
		idPanel.add(idEmployee);
		idFieldPanel.add(idField);
		
		passField.setPreferredSize(new Dimension(200,20));
		passwordPanel.add(passEmployee);
		passFieldPanel.add(passField);
		
		contentPanel.add(idPanel);
		contentPanel.add(idFieldPanel);
		contentPanel.add(passwordPanel);
		contentPanel.add(passFieldPanel);
		
		add(contentPanel, BorderLayout.CENTER);
		
		//bagian Bawah
		
		//Pesan error diset tidak visible
		pesanError1.setVisible(false);
		pesanError2.setVisible(false);
		pesanError3.setVisible(false);
		pesanError4.setVisible(false);
		
		//Pesan error diberi warna merah
		pesanError1.setForeground(Color.red);
		pesanError2.setForeground(Color.red);
		pesanError3.setForeground(Color.red);
		pesanError4.setForeground(Color.red);
		
		pesanErrorPanel.add(pesanError1);
		pesanErrorPanel.add(pesanError2);
		pesanErrorPanel.add(pesanError3);
		pesanErrorPanel.add(pesanError4);
		
		buttonPanel.add(login);
		
		bagianBawahPanel.add(buttonPanel);
		bagianBawahPanel.add(pesanErrorPanel);
		add(bagianBawahPanel, BorderLayout.SOUTH);
		
		//ganti warna background
		contentPanel.setBackground(new Color(254, 251, 244));
		idPanel.setBackground(new Color(254, 251, 244));
		idFieldPanel.setBackground(new Color(254, 251, 244));
		passwordPanel.setBackground(new Color(254, 251, 244));
		passFieldPanel.setBackground(new Color(254, 251, 244));
		buttonPanel.setBackground(new Color(254, 251, 244));
		pesanErrorPanel.setBackground(new Color(254, 251, 244));
		bagianBawahPanel.setBackground(new Color(254, 251, 244));
		
		//event
		login.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String id, password;
		id = idField.getText();
		password = passField.getText();
		
		//refresh pesan error
		pesanError1.setVisible(false);
		pesanError2.setVisible(false);
		pesanError3.setVisible(false);
		pesanError4.setVisible(false);
		
		if(id.isEmpty() && !(password.isEmpty())) {
			pesanError1.setVisible(true);
			} else if(!id.isEmpty() && (password.isEmpty())) {
				pesanError2.setVisible(true);
			} else if(id.isEmpty() || (password.isEmpty())) {
				pesanError3.setVisible(true); 
			} else {
				String query = String.format("SELECT * FROM employee WHERE idEmployee = '%s' AND passwordEmployee = '%s'", id, password);
				ResultSet resultSet =  connector.executeQuery(query);
				
				try {
					if(resultSet.next()) {
						String role = resultSet.getString("roleEmployee");
						String currentID = resultSet.getString("idEmployee");
						String nama = resultSet.getString("namaEmployee");
						if(role.equals("Operator")) {
							this.dispose(); 
							new mainFormOperator(nama, currentID);
							}else {
								this.dispose(); 
								new mainFormSupervisor(nama, currentID);
								}
						} else {
							pesanError4.setVisible(true);
							}
					} catch (SQLException e1) {
						e1.printStackTrace();
						}
			}
	}
	
	public void logo() {
		JPanel logoPanel = new JPanel();
		ImageIcon logo = new ImageIcon("logo.png");
		
		Image gantiLogo = logo.getImage().getScaledInstance(300, 150, Image.SCALE_DEFAULT);
		logo.setImage(gantiLogo);
		JLabel logoToko = new JLabel(logo);
		
		logoPanel.add(logoToko);
		logoPanel.setBackground(new Color(254, 251, 244));
		add(logoPanel, BorderLayout.NORTH);
	}
	
	
	public formLogin() {
		this.setTitle("Form Login");
		this.setSize(480, 340);
		logo();
		setComponent();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
}
