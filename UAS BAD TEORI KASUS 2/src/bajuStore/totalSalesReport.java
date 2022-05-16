package bajuStore;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class totalSalesReport extends JInternalFrame implements MouseListener, ActionListener{
	//menyimpan data operator yang login
	String currentID;
	
	//judul
	JPanel judulPanel = new JPanel();
	JLabel judul = new JLabel("Total Sales Report");
	
	//JPanel Content tengah
	JPanel contentPanel = new JPanel(new GridLayout(0,1));
	JPanel tampungSemuaTable = new JPanel(new GridLayout(0,1)); 
	
	//model, table, scrollpane
	JPanel judulTable1Panel = new JPanel();
	JLabel judulTable1 = new JLabel("Table Order");
	
	JPanel listSales = new JPanel(new BorderLayout());
	String columnOrder[] = {"Id Order", "Date", "Member", "ID Operator", "Grand Total", "Status"};
	DefaultTableModel dtm = new DefaultTableModel(columnOrder, 0);
	JTable tableListOrder = new JTable(dtm);
	JScrollPane scrollpaneTableListOrder = new JScrollPane(tableListOrder);
	
	//model, table, scrollpane
	JPanel judulTable2Panel = new JPanel();
	JLabel judulTable2 = new JLabel("Table Order Detail");
	
	JPanel listSalesDetail = new JPanel(new BorderLayout());
	String columnOrderDetail[] = {"Id Order", "Id Item", "Quantity"};
	DefaultTableModel dtm2 = new DefaultTableModel(columnOrderDetail, 0);
	JTable tableListOrderDetail = new JTable(dtm2);
	JScrollPane scrollpaneTableListOrderDetail = new JScrollPane(tableListOrderDetail);
	
	//content bawah
	JPanel bawahPanel = new JPanel(new GridLayout(3,1));
	
	JPanel contentBawahPanel = new JPanel(new GridLayout(0,4));
	JPanel totalPenjualanPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel totalPenjualan = new JLabel("Total Penjualan : ");
	JPanel totalPenjualanFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JTextField totalPenjualanField = new JTextField();
	
	JPanel totalTransaksiPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel totalTransaksi = new JLabel("Total Transaksi : ");
	JPanel totalTransaksiFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JTextField totalTransaksiField = new JTextField();
	
	JPanel approvedPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel approved = new JLabel("Approved By (ID Supervisor) : ");
	JPanel approvedFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JTextField appprovedField = new JTextField();
	
	JPanel passPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel pass = new JLabel("Password (Password Supervisor) : ");
	JPanel passFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPasswordField passField = new JPasswordField();
	
	JPanel buttonPanel = new JPanel(new GridLayout(1,2));
	JPanel approveButtonPanel = new JPanel();
	JButton approveButton = new JButton("Approve");
	
	JPanel cancelButtonPanel = new JPanel();
	JButton cancelButton = new JButton("Cancel");
	
	//pesan error
	JPanel pesanErrorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel pesanError1 = new JLabel("Id Supervisor Harus Diisi!");
	JLabel pesanError2 = new JLabel("Password Supervisor Harus Diisi!");
	JLabel pesanError3 = new JLabel("Id dan Password Supervisor Harus Diisi!");
	JLabel pesanError4 = new JLabel("Id atau Password Supervisor Tidak Valid!");
	
	//connect ke database
	DatabaseConnector connector = DatabaseConnector.getConnector();
	
	public void setComponent() {
		//JInternalFrame 
		this.setResizable(false);
		this.setMaximizable(true);
		this.setClosable(true);
		this.setSize(980, 530);
		this.setVisible(true);
		this.getContentPane().setBackground(new Color(254, 251, 244));
		
		//judul
		judulPanel.add(judul);

		//table order
		judulTable1Panel.add(judulTable1);
		listSales.add(scrollpaneTableListOrder);
		
		//table order detail
		judulTable2Panel.add(judulTable2);
		listSalesDetail.add(scrollpaneTableListOrderDetail);
		
		tampungSemuaTable.add(judulTable1);
		tampungSemuaTable.add(listSales);
		tampungSemuaTable.add(judulTable2);
		tampungSemuaTable.add(listSalesDetail);
		
		//content bawah
		totalPenjualanPanel.add(totalPenjualan);
		totalPenjualanFieldPanel.add(totalPenjualanField);
		totalTransaksiPanel.add(totalTransaksi);
		totalTransaksiFieldPanel.add(totalTransaksiField);
		approvedPanel.add(approved);
		approvedFieldPanel.add(appprovedField);
		passPanel.add(pass);
		passFieldPanel.add(passField);
		
		contentBawahPanel.add(totalPenjualanPanel);
		contentBawahPanel.add(totalPenjualanFieldPanel);
		contentBawahPanel.add(totalTransaksiPanel);
		contentBawahPanel.add(totalTransaksiFieldPanel);
		contentBawahPanel.add(approvedPanel);
		contentBawahPanel.add(approvedFieldPanel);
		contentBawahPanel.add(passPanel);
		contentBawahPanel.add(passFieldPanel);
		
		contentPanel.add(tampungSemuaTable);
		
		//button
		approveButtonPanel.add(approveButton);
		cancelButtonPanel.add(cancelButton);
		buttonPanel.add(approveButtonPanel);
		buttonPanel.add(cancelButtonPanel);
		
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
		
		//add ke panel bawah
		bawahPanel.add(contentBawahPanel);
		bawahPanel.add(buttonPanel);
		bawahPanel.add(pesanErrorPanel);
		
		//add ke frame
		add(judul, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
		add(bawahPanel, BorderLayout.SOUTH);
		
		//Ukuran
		totalPenjualanField.setPreferredSize(new Dimension(180,20));
		totalTransaksiField.setPreferredSize(new Dimension(180,20));
		appprovedField.setPreferredSize(new Dimension(180,20));
		passField.setPreferredSize(new Dimension(180,20));
		
		//tidak dapat diedit
		totalTransaksiField.setEditable(false);
		totalPenjualanField.setEditable(false);
		
		//Warna background
		judulPanel.setBackground(new Color(254, 251, 244));
		judulTable1Panel.setBackground(new Color(254, 251, 244));
		listSales.setBackground(new Color(254, 251, 244));
		judulTable2Panel.setBackground(new Color(254, 251, 244));
		listSalesDetail.setBackground(new Color(254, 251, 244));
		tampungSemuaTable.setBackground(new Color(254, 251, 244));
		totalPenjualanPanel.setBackground(new Color(254, 251, 244));
		totalPenjualanFieldPanel.setBackground(new Color(254, 251, 244));
		totalTransaksiPanel.setBackground(new Color(254, 251, 244));
		totalTransaksiFieldPanel.setBackground(new Color(254, 251, 244));
		approvedPanel.setBackground(new Color(254, 251, 244));
		approvedFieldPanel.setBackground(new Color(254, 251, 244));
		passPanel.setBackground(new Color(254, 251, 244));
		passFieldPanel.setBackground(new Color(254, 251, 244));
		buttonPanel.setBackground(new Color(254, 251, 244));
		approveButtonPanel.setBackground(new Color(254, 251, 244));
		cancelButtonPanel.setBackground(new Color(254, 251, 244));
		bawahPanel.setBackground(new Color(254, 251, 244));
		contentBawahPanel.setBackground(new Color(254, 251, 244));
		pesanErrorPanel.setBackground(new Color(254, 251, 244));
		
		//event
		tableListOrder.addMouseListener(this);
		approveButton.addActionListener(this);
		cancelButton.addActionListener(this);
	}
	
	public totalSalesReport(String currentID) {
		//set component
		setComponent();
		
		//Current ID
		this.currentID = currentID;	
		
		//get data
		getDataOrder();
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//refresh data
		dtm2.setRowCount(0); 
		
		int rowDiklik = tableListOrder.getSelectedRow();
		String idOrderKlik = (String) dtm.getValueAt(rowDiklik, 0);
		
		String queryOrderDetail = String.format("SELECT * FROM `orderDetail` WHERE `idOrder` = '%s'", idOrderKlik);
		ResultSet getDataOrderDetail = connector.executeQuery(queryOrderDetail);
		
		try {
			while(getDataOrderDetail.next()) {
				String idOrder = getDataOrderDetail.getString("idOrder");
				String idItem = getDataOrderDetail.getString("idItem");
				int qty = getDataOrderDetail.getInt("qty");
				
				dtm2.addRow(new String[] {idOrder, idItem, qty+""});
				}
			} catch (SQLException e) {
				e.printStackTrace();
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
	
	public String validasiSupervisor() {
		String passSupervisor = passField.getText();
		String idSupervisor = appprovedField.getText();
		
		String valid = "";
		
		String queryPassSupervisor = String.format("SELECT * FROM employee WHERE passwordEmployee = '%s' AND roleEmployee = '%s' AND idEmployee = '%s' ", passSupervisor, "Supervisor", idSupervisor);
		ResultSet resultSet =  connector.executeQuery(queryPassSupervisor);
		
		try {
			if(resultSet.next()) {
				valid = "ya";
				} 
			} catch (SQLException e1) {
				e1.printStackTrace();
				}
		return valid;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String passSupervisor = passField.getText();
		String idSupervisor = appprovedField.getText();
		
		//refresh pesan error
		pesanError1.setVisible(false);
		pesanError2.setVisible(false);
		pesanError3.setVisible(false);
		pesanError4.setVisible(false);
		
		if(arg0.getSource() == approveButton) {
			if(idSupervisor.isEmpty() && !(passSupervisor.isEmpty())) {
				pesanError1.setVisible(true);
				} else if(!idSupervisor.isEmpty() && (passSupervisor.isEmpty())) {
					pesanError2.setVisible(true);
				} else if(idSupervisor.isEmpty() || (passSupervisor.isEmpty())) {
					pesanError3.setVisible(true); 
				} else {
					if(validasiSupervisor().equals("ya")) {
						String message = "Yakin Ingin Approve Total Transaksi dan Penjualan?";
						String option[] = {"Ya", "Tidak"};
						int mauCheckout = JOptionPane.showOptionDialog(
								null, 
								message, 
								"Confirmation Message", 
								0, 
								JOptionPane.QUESTION_MESSAGE, 
								null, 
								option, 
								option[0]);
						
						switch(mauCheckout) {
						case 0:
							String queryUpdateOrder = String.format("UPDATE `order` SET `approvedBy` = '%s', `statusOrder` = '%s' WHERE `idOperator` = '%s' AND `statusOrder` = '%s'", idSupervisor,  "Approved", currentID, "Not Approved");
							connector.execute(queryUpdateOrder);
							
							JOptionPane.showMessageDialog(null, "Approval Total Transaksi dan Belanja Berhasil!");
							this.dispose();
							break;
						case 1:
							break;
						}
						
					}else {
						pesanError4.setVisible(true); 
					}
				}	
		} if(arg0.getSource() == cancelButton) {
			String message = "Batal Log Out?";
			String option[] = {"Ya", "Tidak"};
			int mauCheckout = JOptionPane.showOptionDialog(
					null, 
					message, 
					"Confirmation Message", 
					0, 
					JOptionPane.QUESTION_MESSAGE, 
					null, 
					option, 
					option[0]);
			
			switch(mauCheckout) {
			case 0:
				this.dispose();
				break;
			case 1:
				break;
			}
		}
	}
	
	public void getDataOrder() {
		//refresh data
		dtm.setRowCount(0); 
		
		String query = String.format("SELECT * FROM `order` WHERE `idOperator` = '%s' AND `statusOrder` = '%s'", currentID, "Not Approved");
		ResultSet getData = connector.executeQuery(query);
		
		try {
			while(getData.next()) {
				String idOrder = getData.getString("idOrder");
				String date = getData.getString("dateOrder");
				String member = getData.getString("idMember");
				String operator = getData.getString("idOperator");
				String status = getData.getString("statusOrder");
				int grandTotal = getData.getInt("grandTotal");
								
				dtm.addRow(new String[] {idOrder, date, member, operator, grandTotal+"", status});
				}
			} catch (SQLException e) {
				e.printStackTrace();
				}	
		
		int totalTransaksi = dtm.getRowCount();
		totalTransaksiField.setText(totalTransaksi +"");
		
		int totalPenjualan = 0;
		
		for(int i = 0; i < dtm.getRowCount(); i++) {
			String grandTotal = (String) dtm.getValueAt(i, 4);
			totalPenjualan = totalPenjualan + Integer.parseInt(grandTotal);
		}
		
		totalPenjualanField.setText(totalPenjualan + "");
		
	}
		
}
