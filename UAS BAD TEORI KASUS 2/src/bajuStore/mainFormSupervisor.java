package bajuStore;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.SwingConstants;

public class mainFormSupervisor extends JFrame implements MouseListener{
	//menyimpan data supervisor yang login
	String namaEmp, currentID;
	
	//Menu
	JMenuBar menu = new JMenuBar();
	JMenu logOut = new JMenu("Log Out");
	JMenu inventory = new JMenu("Inventory");
	JMenu salesHistory = new JMenu("Sales History");
	
	//JOptionPane
		JDesktopPane mainFormSupervisorPane = new JDesktopPane();
		private boolean paneSupervisorSudahDiBuat = false;
		
		JLabel welcome;
	
	public void setComponent(){
		//Add menu
		this.setJMenuBar(menu);
		menu.add(logOut);
		menu.add(inventory);
		menu.add(salesHistory);
		
		//event
		logOut.addMouseListener(this);
		inventory.addMouseListener(this);
		salesHistory.addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(paneSupervisorSudahDiBuat == false) {
			add(mainFormSupervisorPane);
			paneSupervisorSudahDiBuat = true;
			}
		
		if(arg0.getSource() == logOut) {
			this.dispose(); 
			new formLogin();
		}else if(arg0.getSource() == inventory) {
			welcome.setVisible(false);
			mainFormSupervisorPane.add(new inventory());
			//set warna background pane
			mainFormSupervisorPane.setBackground(new Color(254, 251, 244));
		}else if(arg0.getSource() == salesHistory) {
			welcome.setVisible(false);
			mainFormSupervisorPane.add(new salesHistory());
			//set warna background pane
			mainFormSupervisorPane.setBackground(new Color(254, 251, 244));
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public mainFormSupervisor(String nama, String currentID) {
		this.setTitle("Main Form Supervisor");
		this.setSize(1150, 680);
		namaEmp = nama;
		this.currentID = currentID;
		saySelamatDatang();
		setComponent();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		//set warna background frame
		this.getContentPane().setBackground(new Color(254, 251, 244));
	}
	
	public void saySelamatDatang() {
		welcome = new JLabel("Welcome Supervisor, " + namaEmp, SwingConstants.CENTER);
		add(welcome, BorderLayout.CENTER);
		welcome.setBackground(new Color(254, 251, 244));
		welcome.setOpaque(true);
	}

}
