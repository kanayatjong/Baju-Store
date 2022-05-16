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
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class salesHistory extends JInternalFrame implements ActionListener, MouseListener{
	//judul
	JPanel judulPanel = new JPanel();
	JLabel judul = new JLabel("Sales History");
	
	//search
	JPanel gabunganSearch = new JPanel(new GridLayout(0,2));
	
	JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel searchLabel = new JLabel("Search by Date (yyyy-mm-dd)");
	
	JPanel searchFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JTextField searchField = new JTextField();
	
	JPanel searchButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JButton searchButton = new JButton("Search");
	JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JButton backButton = new JButton("Back");
	
	//kumpulan table
	JPanel kumpulanTable = new JPanel(new GridLayout(4,1));
	
	//model, table, scrollpane
	JPanel judulTable1Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel judulTable1 = new JLabel("Table Order");
	
	JPanel listSales = new JPanel(new BorderLayout());
	String columnOrder[] = {"Id Order", "Date", "Member", "Operator", "Approved By", "Grand Total", "Status"};
	DefaultTableModel dtm = new DefaultTableModel(columnOrder, 0);
	JTable tableListOrder = new JTable(dtm);
	JScrollPane scrollpaneTableListOrder = new JScrollPane(tableListOrder);

	//model, table, scrollpane
	JPanel judulTable2Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel judulTable2 = new JLabel("Table Order Detail");
	
	JPanel listSalesDetail = new JPanel(new BorderLayout());
	String columnOrderDetail[] = {"Id Order", "Id Item", "Quantity"};
	DefaultTableModel dtm2 = new DefaultTableModel(columnOrderDetail, 0);
	JTable tableListOrderDetail = new JTable(dtm2);
	JScrollPane scrollpaneTableListOrderDetail = new JScrollPane(tableListOrderDetail);
	
	//pesan error
	JPanel pesanErrorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel pesanError1 = new JLabel("Belum ada tanggal yang dimasukkan!");
	
	//connect ke database
	DatabaseConnector connector = DatabaseConnector.getConnector();
	
	public void setComponent() {
		//JInternalFrame 
		this.setResizable(false);
		this.setMaximizable(true);
		this.setClosable(true);
		this.setSize(980, 390);
		this.setVisible(true);
		this.getContentPane().setBackground(new Color(254, 251, 244));	
		
		//judul
		judulPanel.add(judul);
		
		//table
		judulTable1Panel.add(judulTable1);
		listSales.add(scrollpaneTableListOrder);
		judulTable2Panel.add(judulTable2);
		listSalesDetail.add(scrollpaneTableListOrderDetail);
		
		kumpulanTable.add(judulTable1Panel);
		kumpulanTable.add(listSales);
		kumpulanTable.add(judulTable2Panel);
		kumpulanTable.add(listSalesDetail);
		
		searchPanel.add(searchLabel);
		searchFieldPanel.add(searchField);
		searchButtonPanel.add(searchButton);
		backButtonPanel.add(backButton);
		
		gabunganSearch.add(searchPanel);
		gabunganSearch.add(searchFieldPanel);
		gabunganSearch.add(backButtonPanel);
		gabunganSearch.add(searchButtonPanel);
		
		//Pesan error diset tidak visible
		pesanError1.setVisible(false);
				
		//Pesan error diberi warna merah
		pesanError1.setForeground(Color.red);
		pesanErrorPanel.add(pesanError1);
		gabunganSearch.add(pesanErrorPanel);
		
		add(judulPanel, BorderLayout.NORTH);
		add(kumpulanTable, BorderLayout.CENTER);
		add(gabunganSearch, BorderLayout.SOUTH);

		//ukuran
		searchField.setPreferredSize(new Dimension(200,20));
		backButton.setEnabled(false);
		
		//warna background
		judulPanel.setBackground(new Color(254, 251, 244));
		judulTable1Panel.setBackground(new Color(254, 251, 244));
		listSales.setBackground(new Color(254, 251, 244));
		judulTable2Panel.setBackground(new Color(254, 251, 244));
		listSalesDetail.setBackground(new Color(254, 251, 244));
		kumpulanTable.setBackground(new Color(254, 251, 244));
		gabunganSearch.setBackground(new Color(254, 251, 244));
		searchPanel.setBackground(new Color(254, 251, 244));
		searchFieldPanel.setBackground(new Color(254, 251, 244));
		searchButtonPanel.setBackground(new Color(254, 251, 244));
		backButtonPanel.setBackground(new Color(254, 251, 244));
		pesanErrorPanel.setBackground(new Color(254, 251, 244));
		
		//event
		searchButton.addActionListener(this);
		backButton.addActionListener(this);
		tableListOrder.addMouseListener(this);
	
	}
		
	public salesHistory() {
		//set component
		setComponent();
	
		//get data
		getDataOrder();
	}
	
	public void getDataOrder() {
		//refresh data
		dtm.setRowCount(0); 
		
		String query = String.format("SELECT * FROM `order`");
		ResultSet getData = connector.executeQuery(query);
		
		try {
			while(getData.next()) {
				String idOrder = getData.getString("idOrder");
				String date = getData.getString("dateOrder");
				String member = getData.getString("idMember");
				String operator = getData.getString("idOperator");
				String approved = getData.getString("approvedBy");
				String status = getData.getString("statusOrder");
				int grandTotal = getData.getInt("grandTotal");
								
				dtm.addRow(new String[] {idOrder, date, member, operator, approved, grandTotal+"", status});
				}
			} catch (SQLException e) {
				e.printStackTrace();
				}	
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
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
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String date = searchField.getText();
		
		//refresh
		pesanError1.setVisible(false);
		
		if(arg0.getSource() == searchButton) {
			if(date.isEmpty()) {
				pesanError1.setVisible(true);
			}else {
				
				int rowIdSama = -1;
				 for(int i = 0; i < dtm.getRowCount(); i++) {
					 if(date.equals(dtm.getValueAt(i, 1))) {
							rowIdSama = i;
							break;
						} 
				 }
				 
				 if(rowIdSama != -1) {
					    String idOrder = (String) dtm.getValueAt(rowIdSama, 0);
						String member = (String) dtm.getValueAt(rowIdSama, 2);
						String operator = (String) dtm.getValueAt(rowIdSama, 3);
						String approved = (String) dtm.getValueAt(rowIdSama, 4);
						String status = (String) dtm.getValueAt(rowIdSama,6);
						String grandTotal = (String) dtm.getValueAt(rowIdSama,5); 
						 
						dtm.setRowCount(0);
						
						dtm.addRow(new String[] {idOrder, date, member, operator, approved, grandTotal, status});
						backButton.setEnabled(true);
				 }else {
					 JOptionPane.showMessageDialog(null, "Tidak Ditemukan Transaksi Pada Tanggal yang Diinput!", "Error", JOptionPane.ERROR_MESSAGE);
				 }
				
			}
		}if(arg0.getSource() == backButton) {
			getDataOrder();
			backButton.setEnabled(false);
			
			//refresh
			searchField.setText("");
		}
	}
		
}
