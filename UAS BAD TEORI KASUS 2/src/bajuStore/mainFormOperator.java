package bajuStore;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class mainFormOperator extends JFrame implements MouseListener, ActionListener{
	//menyimpan data operator yang login
	String namaEmp, currentID;
	
	//Menu
	JMenuBar menu = new JMenuBar();
	JMenu logOut = new JMenu("Log Out");
	JMenuItem logOutItem = new 	JMenuItem("Log Out");
	JMenuItem salesReport = new 	JMenuItem("Sales Repot");
	JMenu salesEntry = new JMenu("Sales Entry");
	
	//JOptionPane
	JDesktopPane mainFormOperatorPane = new JDesktopPane();
	private boolean paneOperatorSudahDiBuat = false;
	
	JLabel welcome;
	
	//connect ke database
	DatabaseConnector connector = DatabaseConnector.getConnector();
	
	public void setComponent(){
		//Add menu
		this.setJMenuBar(menu);
		menu.add(logOut);
		logOut.add(salesReport);
		logOut.add(logOutItem);
		menu.add(salesEntry);
		
		//Event
		salesReport.addActionListener(this);
		logOutItem.addActionListener(this);
		salesEntry.addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		if(paneOperatorSudahDiBuat == false) {
			add(mainFormOperatorPane);
			paneOperatorSudahDiBuat = true;
			}
			// TODO Auto-generated method stub
			if(arg0.getSource() == salesEntry) {
				welcome.setVisible(false);
				mainFormOperatorPane.add(new formSalesEntry(this.currentID));
				//set warna background pane
				mainFormOperatorPane.setBackground(new Color(254, 251, 244));
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
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(paneOperatorSudahDiBuat == false) {
			add(mainFormOperatorPane);
			paneOperatorSudahDiBuat = true;
			}
			
		if(arg0.getSource() == salesReport) {
			welcome.setVisible(false);
			mainFormOperatorPane.add(new totalSalesReport(this.currentID));
			//set warna background pane
			mainFormOperatorPane.setBackground(new Color(254, 251, 244));
		}if(arg0.getSource() == logOutItem) {
			
			String query = String.format("SELECT * FROM `order` WHERE `idOperator` = '%s' AND `statusOrder` = '%s'", currentID, "Not Approved");
			ResultSet getData = connector.executeQuery(query);
			
			try {
				if(getData.next()) {
					welcome.setVisible(false);
					mainFormOperatorPane.setBackground(new Color(254, 251, 244));
					JOptionPane.showMessageDialog(null, "Total Transaksi dan Penjualan Belum di Approve!", "Error", JOptionPane.ERROR_MESSAGE);
					}else {
						this.dispose();
						new formLogin();
					}
				} catch (SQLException e) {
					e.printStackTrace();
					}	
		}
	}



	public mainFormOperator(String nama, String currentID) {
		this.setTitle("Main Form Operator");
		this.setSize(1150, 680);
		namaEmp = nama;
		this.currentID = currentID;
		setComponent();
		saySelamatDatang();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		//set warna background frame
		this.getContentPane().setBackground(new Color(254, 251, 244));
	}
	
	public void saySelamatDatang() {
		welcome = new JLabel("Welcome Operator, " + namaEmp, SwingConstants.CENTER);
		add(welcome, BorderLayout.CENTER);
		//set warna background label
		welcome.setBackground(new Color(254, 251, 244));
		welcome.setOpaque(true);
	}

}
