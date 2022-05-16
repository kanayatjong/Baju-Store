package bajuStore;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class formSalesEntry extends JInternalFrame implements MouseListener, ActionListener, ItemListener {
	//menyimpan data operator yang login
	String currentID;
	
	//judul
	JPanel judulPanel = new JPanel();
	JLabel judulLabel = new JLabel("Sales Entry");
	
	//panel penampung content sisi kiri dan kanan
	JPanel contentKeseluruhan = new JPanel(new GridLayout(1,2));
	
	//panel sisi kiri
	JPanel contentKiri = new JPanel(new GridLayout(0,1));
	
	//Pertanyaan member atau bukan
	JPanel pertanyaan = new JPanel(new GridLayout(0,2));
	JPanel memberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel member = new JLabel ("Member ?");
	JPanel radioButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JRadioButton Ya = new JRadioButton("Ya");
	JRadioButton tidak = new JRadioButton("Tidak");
	ButtonGroup jawabanGroup = new ButtonGroup();
	//Memberid
	JPanel idMemberPanelPanel = new JPanel(new GridLayout(0,2));
	JPanel idMemberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel idMember = new JLabel("Id Member : ");
	JPanel idMemberFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JTextField idMemberField = new JTextField();
	
	//Content item dipilih
	JPanel contentItemDipilih = new JPanel(new GridLayout(1,2));
	JPanel itemDipilihKiri = new JPanel(new GridLayout(0,2));
	JPanel itemDipilihKanan = new JPanel(new GridLayout(0,2));
	
	JPanel idItemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel idItem = new JLabel("  Id Item : ");
	JPanel idItemFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JTextField idItemField = new JTextField();
	
	JPanel namaItemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel namaItem = new JLabel("Nama Item : ");
	JPanel namaItemFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JTextField namaItemField = new JTextField();
	
	JPanel hargaItemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel hargaItem = new JLabel("Harga Item : ");
	JPanel hargaItemFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JTextField hargaItemField = new JTextField();
	
	JPanel stockItemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel stockItem = new JLabel("Stock Item : ");
	JPanel stockItemFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JTextField stockItemField = new JTextField();
	
	JPanel qtyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel qtyItem = new JLabel("Qty : ");
	JPanel qtySpinnerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	SpinnerNumberModel qtySpinnerModel = new SpinnerNumberModel(1, 1, 500, 1);
	JSpinner qtySpinner = new JSpinner(qtySpinnerModel);
	
	JPanel insertPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JButton insertButton = new JButton("Insert");
	
	//model, table, scrollpane
	JPanel listItemPanelBeli = new JPanel(new BorderLayout());
	String columnItemBeliNames[] = {"Id Item", "Nama", "Harga", "Qty"};
	DefaultTableModel dtm = new DefaultTableModel(columnItemBeliNames, 0);
	JTable tableListItemBeli = new JTable(dtm);
	JScrollPane scrollpaneTableListItemBeli = new JScrollPane(tableListItemBeli);
	
	//panel sisi kanan
	JPanel contentKanan = new JPanel(new GridLayout(0,1));
	
	//model, table, scrollpane kanan
	JPanel listItemPanel = new JPanel(new BorderLayout());
	String columnItemNames[] = {"Id Item", "Nama", "Harga", "Stock", "Foto"};
	DefaultTableModel dtm2 = new DefaultTableModel(columnItemNames, 0) {
		@Override
		public Class<?> getColumnClass(int column) {
			switch (column) {
			case 4: return Icon.class;
			default: return String.class;
			}
		}
	};
	JTable tableListItem = new JTable(dtm2);
	JScrollPane scrollpaneTableListItem = new JScrollPane(tableListItem);
	
	//content bawah
	JPanel contentBawah = new JPanel(new GridLayout(0,2));
	JPanel contentBawahKiri = new JPanel(new GridLayout(0,2));
	JPanel contentBawahKanan = new JPanel(new GridLayout(0,2));
	
	JPanel diskonPanel= new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel diskon = new JLabel("Diskon : ");
	JPanel diskonFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JTextField diskonField = new JTextField();
	
	JPanel grandTotalPanel= new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel grandTotal = new JLabel("Grand Total : ");
	JPanel grandTotalFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JTextField grandTotalField = new JTextField();
	
	JPanel pembayaranPanel= new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel pembayaran = new JLabel("Pembayaran : ");
	JPanel pembayaranFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JTextField pembayaranField = new JTextField();

	JPanel insertPaymentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JButton insertPayment = new JButton("Insert");
	
	//JPanel gabungan content bawah dan table
	JPanel gabunganContentTabel = new JPanel(new GridLayout(3,1));
	
	//Content paling bawah
	JPanel contentPalingBawah = new JPanel(new GridLayout(1,4));
	
	JPanel kembalianPanel= new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel kembalian = new JLabel("Kembalian : ");
	JPanel kembalianFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JTextField kembalianField = new JTextField();
	
	JPanel callSupervisorPanel = new JPanel();
	JButton callSupervisorButton = new JButton("Call Supervisor");

	JPanel savePanel = new JPanel();
	JButton saveButton = new JButton("Save");
	
	//Content Supervisor
	JPanel contentSupervisor = new JPanel(new GridLayout(1,2));
	JPanel contentSupervisorKiri = new JPanel(new GridLayout(0,2));
	JPanel contentSupervisorKanan = new JPanel(new GridLayout(0,2));
	
	JPanel passSupervisorLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel passSupervisorLabel = new JLabel("Password Supervisor : ");
	JPanel passSupervisorFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPasswordField passSupervisorField = new JPasswordField();

	JPanel idItemUpdatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel idItemUpdate = new JLabel("ID Item Update : ");
	JPanel idItemUpdateFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JTextField idItemUpdateField = new JTextField();
	
	JPanel idQtyUpdatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel idQtyUpdate = new JLabel("Qty Item Update : ");
	JPanel qtyUpSpinnerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	SpinnerNumberModel qtyUpSpinnerModel = new SpinnerNumberModel(1, 1, 500, 1);
	JSpinner qtyUpSpinner = new JSpinner(qtySpinnerModel);

	JPanel qtyUpPanel = new JPanel();
	JButton qtyUpButton = new JButton("Update Quantity");
	
	//3 tombol tambahan saat supervisor update atau melakukan revisi
	JPanel tigaTombol = new JPanel(new GridLayout(1,3));
	
	JPanel removePanel = new JPanel();
	JButton removeButton = new JButton("Remove Item From List");
	
	JPanel finishPanel = new JPanel();
	JButton finishButton = new JButton("Finish Update");
	
	JPanel cancelPanel = new JPanel();
	JButton cancelButton = new JButton("Cancel");
	
	//JPanel seluruh content call supervisor
	JPanel contentCallSupervisor = new JPanel(new GridLayout(2,0));
	
	//connect ke database
	DatabaseConnector connector = DatabaseConnector.getConnector();
	
	
	public void setComponent() {
		//JInternalFrame 
		this.setResizable(false);
		this.setMaximizable(true);
		this.setClosable(true);
		this.setSize(1090, 605);
		this.setVisible(true);
		this.getContentPane().setBackground(new Color(254, 251, 244));
		
		//add Judul
		judulPanel.add(judulLabel);
		add(judulPanel, BorderLayout.NORTH);
		
		//add Button Group
		jawabanGroup.add(Ya);
		jawabanGroup.add(tidak);
		
		//add content kiri atas
		memberPanel.add(member);
		radioButtonPanel.add(Ya);
		radioButtonPanel.add(tidak);
		
		idMemberPanel.add(idMember);
		idMemberFieldPanel.add(idMemberField);
		
		//content item dipilih kiri
		idItemPanel.add(idItem);
		idItemFieldPanel.add(idItemField);
		namaItemPanel.add(namaItem);
		namaItemFieldPanel.add(namaItemField);
		hargaItemPanel.add(hargaItem);
		hargaItemFieldPanel.add(hargaItemField);
		
		itemDipilihKiri.add(memberPanel);
		itemDipilihKiri.add(radioButtonPanel);
		
		itemDipilihKiri.add(idItem);
		itemDipilihKiri.add(idItemFieldPanel);
		itemDipilihKiri.add(namaItemPanel);
		itemDipilihKiri.add(namaItemFieldPanel);
		itemDipilihKiri.add(hargaItemPanel);
		itemDipilihKiri.add(hargaItemFieldPanel);
		
		//content item dipilih kanan
		stockItemPanel.add(stockItem);
		stockItemFieldPanel.add(stockItemField);
		qtyPanel.add(qtyItem);
		qtySpinnerPanel.add(qtySpinner);
		insertPanel.add(insertButton);
		
		itemDipilihKanan.add(idMemberPanel);
		itemDipilihKanan.add(idMemberFieldPanel);
		
		itemDipilihKanan.add(stockItemPanel);
		itemDipilihKanan.add(stockItemFieldPanel);
		itemDipilihKanan.add(qtyPanel);
		itemDipilihKanan.add(qtySpinnerPanel);
		itemDipilihKanan.add(insertPanel);
	
		
		contentItemDipilih.add(itemDipilihKiri);
		contentItemDipilih.add(itemDipilihKanan);
		
		//table list item beli
		listItemPanelBeli.add(scrollpaneTableListItemBeli);
		
		//content kiri bawah
		diskonPanel.add(diskon);
		diskonFieldPanel.add(diskonField);
		grandTotalPanel.add(grandTotal);
		grandTotalFieldPanel.add(grandTotalField);
		pembayaranPanel.add(pembayaran);
		pembayaranFieldPanel.add(pembayaranField);
		insertPaymentPanel.add(insertPayment);
		
		contentBawahKiri.add(diskonPanel);
		contentBawahKiri.add(diskonFieldPanel);
		contentBawahKiri.add(grandTotalPanel);
		contentBawahKiri.add(grandTotalFieldPanel);
		
		contentBawahKanan.add(pembayaranPanel);
		contentBawahKanan.add(pembayaranFieldPanel);
		contentBawahKanan.add(insertPaymentPanel);
		
		//Content paling bawah
		kembalianPanel.add(kembalian);
		kembalianFieldPanel.add(kembalianField);
		
		callSupervisorPanel.add(callSupervisorButton);
		savePanel.add(saveButton);
		
		contentPalingBawah.add(kembalianPanel);
		contentPalingBawah.add(kembalianFieldPanel);
		contentPalingBawah.add(callSupervisorPanel);
		contentPalingBawah.add(savePanel);
		
		contentBawah.add(contentBawahKiri);
		contentBawah.add(contentBawahKanan);
		
		gabunganContentTabel.add(listItemPanelBeli);
		gabunganContentTabel.add(contentBawah);
		gabunganContentTabel.add(contentPalingBawah);
		
		//semua content kiri
		contentKiri.add(contentItemDipilih, BorderLayout.NORTH);
		contentKiri.add(gabunganContentTabel, BorderLayout.CENTER);
		
		//table list item 
		listItemPanel.add(scrollpaneTableListItem);
		contentKanan.add(listItemPanel, BorderLayout.CENTER);
		
		//Content update atau koreksi saat memanggil supervisor
		passSupervisorLabelPanel.add(passSupervisorLabel);
		passSupervisorFieldPanel.add(passSupervisorField);
	
		idItemUpdatePanel.add(idItemUpdate);
		idItemUpdateFieldPanel.add(idItemUpdateField);
		
		contentSupervisorKiri.add(passSupervisorLabelPanel);
		contentSupervisorKiri.add(passSupervisorFieldPanel);
		contentSupervisorKiri.add(idItemUpdatePanel);
		contentSupervisorKiri.add(idItemUpdateFieldPanel);
		
		idQtyUpdatePanel.add(idQtyUpdate);
		qtyUpSpinnerPanel.add(qtyUpSpinner);
		contentSupervisorKanan.add(idQtyUpdatePanel);
		contentSupervisorKanan.add(qtyUpSpinnerPanel);
		
		qtyUpPanel.add(qtyUpButton);
		contentSupervisorKanan.add(qtyUpPanel);
		
		contentSupervisor.add(contentSupervisorKiri);
		contentSupervisor.add(contentSupervisorKanan);
		
		removePanel.add(removeButton);
		finishPanel.add(finishButton);
		cancelPanel.add(cancelButton);
		
		tigaTombol.add(removePanel);
		tigaTombol.add(finishPanel);
		tigaTombol.add(cancelPanel);
		
		//supervisor invisible sampai tombol call supervisor ditekan
		contentSupervisor.setVisible(false);
		tigaTombol.setVisible(false);
		
		contentCallSupervisor.add(contentSupervisor);
		contentCallSupervisor.add(tigaTombol);
		
		contentKiri.add(contentCallSupervisor, BorderLayout.SOUTH);
		
		//add semua ke frame
		contentKeseluruhan.add(contentKiri);
		contentKeseluruhan.add(contentKanan);
		add(contentKeseluruhan);
		
		//field yang tidak dapat diedit
		idItemField.setEditable(false);
		namaItemField.setEditable(false);
		hargaItemField.setEditable(false);
		stockItemField.setEditable(false);
		diskonField.setEditable(false); 
		grandTotalField.setEditable(false);  
		kembalianField.setEditable(false);
		idMemberField.setEditable(false);
		saveButton.setEnabled(false);
		idItemUpdateField.setEditable(false);
		
		//set ulang ukuran field
		idMemberField.setPreferredSize(new Dimension(120,20));
		idItemField.setPreferredSize(new Dimension(120,20));
		namaItemField.setPreferredSize(new Dimension(120,20));
		hargaItemField.setPreferredSize(new Dimension(120,20));
		stockItemField.setPreferredSize(new Dimension(120,20));
		diskonField.setPreferredSize(new Dimension(120,20)); 
		grandTotalField.setPreferredSize(new Dimension(120,20));
		pembayaranField.setPreferredSize(new Dimension(120,20)); 
		kembalianField.setPreferredSize(new Dimension(120,20));
		passSupervisorField.setPreferredSize(new Dimension(120,20));
		idItemUpdateField.setPreferredSize(new Dimension(120,20));
		
		//set ukuran row table list item
		tableListItem.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		TableColumnModel colModel = tableListItem.getColumnModel();
		colModel.getColumn(4).setPreferredWidth(120);
		tableListItem.setRowHeight(120);
		
		//event
		tableListItem.addMouseListener(this);
		insertButton.addActionListener(this);
		Ya.addItemListener(this);
		tidak.addItemListener(this);
		saveButton.addActionListener(this);
		insertPayment.addActionListener(this);
		callSupervisorButton.addActionListener(this);
		tableListItemBeli.addMouseListener(this);
		qtyUpButton.addActionListener(this);
		removeButton.addActionListener(this);
		finishButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		//warna background
		judulPanel.setBackground(new Color(254, 251, 244));
		memberPanel.setBackground(new Color(254, 251, 244));
		contentKeseluruhan.setBackground(new Color(254, 251, 244));
		contentKanan.setBackground(new Color(254, 251, 244));
		listItemPanel.setBackground(new Color(254, 251, 244));
		diskonPanel.setBackground(new Color(254, 251, 244));
		diskonFieldPanel.setBackground(new Color(254, 251, 244));
		grandTotalPanel.setBackground(new Color(254, 251, 244));
		grandTotalFieldPanel.setBackground(new Color(254, 251, 244));
		pembayaranPanel.setBackground(new Color(254, 251, 244));
		pembayaranFieldPanel.setBackground(new Color(254, 251, 244));
		insertPaymentPanel.setBackground(new Color(254, 251, 244));
		kembalianPanel.setBackground(new Color(254, 251, 244));
		kembalianFieldPanel.setBackground(new Color(254, 251, 244));
		callSupervisorPanel.setBackground(new Color(254, 251, 244));
		savePanel.setBackground(new Color(254, 251, 244));
		memberPanel.setBackground(new Color(254, 251, 244));
		radioButtonPanel.setBackground(new Color(254, 251, 244));
		idMemberPanel.setBackground(new Color(254, 251, 244));
		idMemberFieldPanel.setBackground(new Color(254, 251, 244));
		Ya.setBackground(new Color(254, 251, 244));
		tidak.setBackground(new Color(254, 251, 244));
		idItemPanel.setBackground(new Color(254, 251, 244));
		idItemFieldPanel.setBackground(new Color(254, 251, 244));
		namaItemPanel.setBackground(new Color(254, 251, 244));
		namaItemFieldPanel.setBackground(new Color(254, 251, 244));
		hargaItemPanel.setBackground(new Color(254, 251, 244));
		hargaItemFieldPanel.setBackground(new Color(254, 251, 244));
		itemDipilihKiri.setBackground(new Color(254, 251, 244));
		stockItemPanel.setBackground(new Color(254, 251, 244));
		stockItemFieldPanel.setBackground(new Color(254, 251, 244));
		qtyPanel.setBackground(new Color(254, 251, 244));
		qtySpinnerPanel.setBackground(new Color(254, 251, 244));
		insertPanel.setBackground(new Color(254, 251, 244));
		itemDipilihKanan.setBackground(new Color(254, 251, 244));
		contentItemDipilih.setBackground(new Color(254, 251, 244));
		insertPaymentPanel.setBackground(new Color(254, 251, 244));
		contentBawahKanan.setBackground(new Color(254, 251, 244));
		contentSupervisor.setBackground(new Color(254, 251, 244));
		contentSupervisorKiri.setBackground(new Color(254, 251, 244));
		contentSupervisorKanan.setBackground(new Color(254, 251, 244));
		passSupervisorLabelPanel.setBackground(new Color(254, 251, 244));
		passSupervisorLabel.setBackground(new Color(254, 251, 244));
		passSupervisorFieldPanel.setBackground(new Color(254, 251, 244));
		idItemUpdatePanel.setBackground(new Color(254, 251, 244));
		idItemUpdate.setBackground(new Color(254, 251, 244));
		idItemUpdateFieldPanel.setBackground(new Color(254, 251, 244));
		idQtyUpdatePanel.setBackground(new Color(254, 251, 244));
		idQtyUpdate.setBackground(new Color(254, 251, 244));
		qtyUpSpinnerPanel.setBackground(new Color(254, 251, 244));
		qtyUpPanel.setBackground(new Color(254, 251, 244));
		tigaTombol.setBackground(new Color(254, 251, 244));
		removePanel.setBackground(new Color(254, 251, 244));
		finishPanel.setBackground(new Color(254, 251, 244));
		cancelPanel.setBackground(new Color(254, 251, 244));
		contentBawahKiri.setBackground(new Color(254, 251, 244));
		contentPalingBawah.setBackground(new Color(254, 251, 244));
		contentKiri.setBackground(new Color(254, 251, 244));
		contentCallSupervisor.setBackground(new Color(254, 251, 244));
	}
	
	public formSalesEntry(String currentID) {
		//set component
		setComponent();
		
		//Current ID
		this.currentID = currentID;	
		
		//get data
		getDataItem();
	}
	
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		 if(Ya.isSelected()) {
			 idMemberField.setEditable(true);
			 diskonField.setText("5%");
			 hitungGrandTotal();
		 }else if(tidak.isSelected()) {
			 idMemberField.setEditable(false);
			 diskonField.setText("0%");
			 hitungGrandTotal();
		 }
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String id = idItemField.getText();
		String nama = namaItemField.getText();
		String harga = hargaItemField.getText(); 
		String stock = stockItemField.getText();
		Integer quantity = (Integer) qtySpinner.getValue();
		String payment = pembayaranField.getText();
		String grandTotal = grandTotalField.getText();
		String idMember = idMemberField.getText();
		boolean adaIdSama = false;
		Integer rowIdSama = -1;
		
		if(arg0.getSource() == insertButton) {
			 if(id.isEmpty()) {
				 JOptionPane.showMessageDialog(null, "Belum ada Item yang Dipilih!", "Error", JOptionPane.ERROR_MESSAGE); 
			 } else if(quantity > Integer.parseInt(stock)) {
				 JOptionPane.showMessageDialog(null, "Quantity Beli Melebihi Stock!", "Error", JOptionPane.ERROR_MESSAGE);
			 }else{
				 
				 for(int i = 0; i < dtm.getRowCount(); i++) {
					 if(id.equals(dtm.getValueAt(i, 0))) {
							rowIdSama = i;
							adaIdSama = true;
							break;
						} 
				 }
				 
				 if(adaIdSama == true) {
					 String quantityDiList = dtm.getValueAt(rowIdSama, 3).toString();
					 int quantitySekarang = Integer.parseInt(quantityDiList) + quantity;
					 
					 if(Integer.parseInt(stock) < quantitySekarang) {
						 JOptionPane.showMessageDialog(null, "Quantity Beli Melebihi Stock!", "Error", JOptionPane.ERROR_MESSAGE);
						}else {
							dtm.setValueAt(quantitySekarang, rowIdSama, 3);
						}
				 }else {
					 dtm.addRow(new String[] {id, nama, harga, quantity + ""});
				 }
				 
				 //refresh field
				 idItemField.setText("");
				 namaItemField.setText("");
				 hargaItemField.setText("");
				 stockItemField.setText("");
				 qtySpinner.setValue(1);
				 
				 hitungGrandTotal();
			 }
		}else if(arg0.getSource() == insertPayment) {
			if(payment.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Belum ada Nominal Pembayaran yang Dimasukkan!", "Error", JOptionPane.ERROR_MESSAGE); 
				saveButton.setEnabled(false);
			}else if (Integer.parseInt(payment) <  Integer.parseInt(grandTotal)) {
				JOptionPane.showMessageDialog(null, "Nominal Pembayaran Kurang!", "Error", JOptionPane.ERROR_MESSAGE); 
				saveButton.setEnabled(false);
			}else {
				int kembaliannya = Integer.parseInt(payment) - Integer.parseInt(grandTotal);
				kembalianField.setText(kembaliannya + "");
				saveButton.setEnabled(true);
			}

		}else if(arg0.getSource() == saveButton) {
			if(Integer.parseInt(payment) <  Integer.parseInt(grandTotal)) {
				JOptionPane.showMessageDialog(null, "Nominal Pembayaran Kurang!", "Error", JOptionPane.ERROR_MESSAGE); 
				saveButton.setEnabled(false);
			}else if(!(Ya.isSelected()) && !(tidak.isSelected())) {
				JOptionPane.showMessageDialog(null, "Jenis Member harus Dipilih!", "Error", JOptionPane.ERROR_MESSAGE); 
				saveButton.setEnabled(false);
			}else if(Ya.isSelected() && idMember.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Id Member Harus Diisi!", "Error", JOptionPane.ERROR_MESSAGE); 
				saveButton.setEnabled(false);
			}else if(Ya.isSelected() && !(idMember.isEmpty())) {
				String query = String.format("SELECT * FROM member WHERE idMember = '%s'", idMember);
				ResultSet resultSet =  connector.executeQuery(query);
				
				try {
					if(resultSet.next()) {
						String message = "Yakin Ingin Menyimpan Data Sales Entry?";
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
							String idOrder = generateID();
							Date date = new Date(System.currentTimeMillis());
							date.toString();
							
							String queryInsertOrder = String.format("INSERT INTO `order` VALUES ('%s', '%s', '%s', '%s', null, '%s', %s)", idOrder, date, idMember, currentID, "Not Approved", grandTotal);
							connector.execute(queryInsertOrder);
						
							for(int i = 0; i < dtm.getRowCount(); i++) {
								String idItem = dtm.getValueAt(i, 0).toString();
								String qty = dtm.getValueAt(i, 3).toString();
								int stockItem = 0;
								
								String queryInsertOrderDetail = String.format("INSERT INTO `orderdetail` VALUES ('%s', '%s', %s)", idOrder, idItem, qty);
								connector.execute(queryInsertOrderDetail);
								
								String queryGetStock = String.format("SELECT * FROM `item` WHERE `idItem` = '%s'", idItem);
								ResultSet getStockItem = connector.executeQuery(queryGetStock);
								
								try {
									while(getStockItem.next()) {
										stockItem = getStockItem.getInt("stockItem");
									}
								}catch (SQLException e) {
									e.printStackTrace();
								}
								
								int stockItemSekarang = stockItem - Integer.parseInt(qty);
								
								String queryUpdateStock = String.format("UPDATE `item` SET `stockItem` = %d WHERE `idItem` =  '%s'", stockItemSekarang,  idItem);
								connector.execute(queryUpdateStock);
							}
							
							JOptionPane.showMessageDialog(null, "Sales Entry Berhasil!");
							
							//clear list item beli
							dtm.setRowCount(0);
							
							//refresh
							getDataItem();
							
							//refresh field
							idItemField.setText("");
							namaItemField.setText("");
							hargaItemField.setText("");
							stockItemField.setText("");
							qtySpinner.setValue(1);
							pembayaranField.setText("");
							grandTotalField.setText("");
							idMemberField.setText("");
							diskonField.setText("");
							kembalianField.setText("");
							break;
							
						case 1:
							break;
							}
						} else {
							JOptionPane.showMessageDialog(null, "Id Member Tidak Valid!", "Error", JOptionPane.ERROR_MESSAGE);
							saveButton.setEnabled(false);
							}
					} catch (SQLException e1) {
						e1.printStackTrace();
						}
			}else if(tidak.isSelected()) {
					String message = "Yakin Ingin Menyimpan Data?";
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
						String idOrder = generateID();
						Date date = new Date(System.currentTimeMillis());
						date.toString();
						
						String queryInsertOrder = String.format("INSERT INTO `order` VALUES ('%s', '%s', null, '%s', null, '%s', %s)", idOrder, date, currentID, "Not Approved", grandTotal);
						connector.execute(queryInsertOrder);
						
					
						for(int i = 0; i < dtm.getRowCount(); i++) {
							String idItem = dtm.getValueAt(i, 0).toString();
							String qty = dtm.getValueAt(i, 3).toString();
							int stockItem = 0;
							
							String queryInsertOrderDetail = String.format("INSERT INTO `orderdetail` VALUES ('%s', '%s', %s)", idOrder, idItem, qty);
							connector.execute(queryInsertOrderDetail);
							
							String queryGetStock = String.format("SELECT * FROM `item` WHERE `idItem` = '%s'", idItem);
							ResultSet getStockItem = connector.executeQuery(queryGetStock);
							
							try {
								while(getStockItem.next()) {
									stockItem = getStockItem.getInt("stockItem");
								}
							}catch (SQLException e) {
								e.printStackTrace();
							}
							
							int stockItemSekarang = stockItem - Integer.parseInt(qty);
							
							String queryUpdateStock = String.format("UPDATE `item` SET `stockItem` = %d WHERE `idItem` =  '%s'", stockItemSekarang,  idItem);
							connector.execute(queryUpdateStock);
						}
						
						JOptionPane.showMessageDialog(null, "Sales Entry Berhasil!");
						
						//clear list item beli
						dtm.setRowCount(0);
						
						//refresh
						getDataItem();
						
						//refresh field
						idItemField.setText("");
						namaItemField.setText("");
						hargaItemField.setText("");
						stockItemField.setText("");
						qtySpinner.setValue(1);
						pembayaranField.setText("");
						grandTotalField.setText("");
						idMemberField.setText("");
						diskonField.setText("");
						kembalianField.setText("");
						break;
						
					case 1:
						break;
					}
			}
		}else if (arg0.getSource() == callSupervisorButton) {
			contentSupervisor.setVisible(true);
			tigaTombol.setVisible(true);
			insertButton.setEnabled(false);
			
		}else if(arg0.getSource() == qtyUpButton) {
			String passSupervisor = passSupervisorField.getText();
			Integer stockItem = 0;
			String idItemUp = idItemUpdateField.getText();
			Integer stockItemUp = (Integer) qtyUpSpinner.getValue();
			
			//untuk ambil stock item yang mau di update
			String queryGetStock = String.format("SELECT * FROM `item` WHERE `idItem` = '%s'", idItemUp);
			ResultSet getStockItem = connector.executeQuery(queryGetStock);
			
			try {
				while(getStockItem.next()) {
					stockItem = getStockItem.getInt("stockItem");
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(passSupervisor.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Password Supervisor Belum Diisi!", "Error", JOptionPane.ERROR_MESSAGE);
			}else if(idItemUp.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Item Untuk di Update Belum Dipilih!", "Error", JOptionPane.ERROR_MESSAGE);
			}else if(stockItem < stockItemUp) {
				JOptionPane.showMessageDialog(null, "Quantity Beli Melebihi Stock!", "Error", JOptionPane.ERROR_MESSAGE);
			}else {
				if(validasiSupervisor().equals("ya")) {
					 for(int i = 0; i < dtm.getRowCount(); i++) {
						 if(idItemUp.equals(dtm.getValueAt(i, 0))) {
								rowIdSama = i;
								break;
							} 
					 }
					 dtm.setValueAt(stockItemUp, rowIdSama, 3);
					 hitungGrandTotal();
					 JOptionPane.showMessageDialog(null, "Update Quantity Beli Berhasil!");
				}else {
					JOptionPane.showMessageDialog(null, "Password Supervisor Tidak Valid!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}else if(arg0.getSource() == removeButton) {
			String passSupervisor = passSupervisorField.getText();
			String idItemUp = idItemUpdateField.getText();
			
			if(passSupervisor.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Password Supervisor Belum Diisi!", "Error", JOptionPane.ERROR_MESSAGE);
			}else if(idItemUp.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Item Untuk di Hapus Belum Dipilih!", "Error", JOptionPane.ERROR_MESSAGE);
			}else {
				if(validasiSupervisor().equals("ya")) {
					for(int i = 0; i < dtm.getRowCount(); i++) {
						if(idItemUp.equals(dtm.getValueAt(i, 0))) {
							rowIdSama = i;
							break;
							} 
						}
					dtm.removeRow(rowIdSama);
					hitungGrandTotal();
					JOptionPane.showMessageDialog(null, "Remove Item Beli Berhasil!");
					}else {
						JOptionPane.showMessageDialog(null, "Password Supervisor Tidak Valid!", "Error", JOptionPane.ERROR_MESSAGE);
						}
				}
			}else if(arg0.getSource() == finishButton) {
				String message = "Sudah Selesai Mengupdate Data Pembelian?";
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
					contentSupervisor.setVisible(false);
					tigaTombol.setVisible(false);
					insertButton.setEnabled(true);
					break;
				case 1:
					break;
					}
			}else if(arg0.getSource() == cancelButton ) {
				String message = "Batal Meminta Bantuan Supervisor?";
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
					contentSupervisor.setVisible(false);
					tigaTombol.setVisible(false);
					insertButton.setEnabled(true);
					break;
				case 1:
					break;
					}
			}
		}
	
	
	public String validasiSupervisor() {
		String passSupervisor = passSupervisorField.getText();
		String valid = "";
		
		String queryPassSupervisor = String.format("SELECT * FROM employee WHERE passwordEmployee = '%s' AND roleEmployee = '%s' ", passSupervisor, "Supervisor");
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
	
	public String generateID() {
		String query = "SELECT COUNT(*) AS rowcount FROM `order`";
		String id = "O";
		ResultSet jumlahRow = connector.executeQuery(query);
		try {
			while(jumlahRow.next()) {
				int count = jumlahRow.getInt("rowcount");
				id = id + String.format("%04d", count + 1);
				}
			}catch (SQLException e) {
				e.printStackTrace();
				}
		
		return id;
	}
	
	public void hitungGrandTotal() {
		 int grandTotal = 0;
		 for(int i = 0; i < dtm.getRowCount(); i++) {
			 String hargaItem = dtm.getValueAt(i, 2).toString();
			 String qty = dtm.getValueAt(i, 3).toString();
			 grandTotal = grandTotal + (Integer.parseInt(hargaItem) * Integer.parseInt(qty));
		 }
		 
		 if(Ya.isSelected()) {
			 int diskon = (int) (grandTotal * 0.05);
			 grandTotal = grandTotal - diskon;
			 grandTotalField.setText(grandTotal+"");
		 }else {
			 grandTotalField.setText(grandTotal+""); 
		 }
		 
		 String payment = pembayaranField.getText();
		 if(!(payment.isEmpty())) {
			 if(Integer.parseInt(payment) < grandTotal) {
				 JOptionPane.showMessageDialog(null, "Nominal Pembayaran Kurang!", "Error", JOptionPane.ERROR_MESSAGE); 
				 saveButton.setEnabled(false);
			 }else {
				 int kembaliannya = Integer.parseInt(payment) - grandTotal;
				 kembalianField.setText(kembaliannya + "");
				 saveButton.setEnabled(true);
			 }
		 }
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == tableListItem) {
			int rowDiKlik = tableListItem.getSelectedRow();
			
			String id = dtm2.getValueAt(rowDiKlik, 0).toString(),
					nama = dtm2.getValueAt(rowDiKlik, 1).toString(),
					harga = dtm2.getValueAt(rowDiKlik, 2).toString(),
					stock = dtm2.getValueAt(rowDiKlik, 3).toString();
			
			if(Integer.parseInt(stock) == 0) {
				JOptionPane.showMessageDialog(null, "Stock Item Kosong!", "Error", JOptionPane.ERROR_MESSAGE);
			}else {
				idItemField.setText(id);
				namaItemField.setText(nama);
				hargaItemField.setText(harga); 
				stockItemField.setText(stock); 
			}
		}else if(arg0.getSource() == tableListItemBeli) {
			int rowDiKlik = tableListItemBeli.getSelectedRow();
			
			String id = dtm.getValueAt(rowDiKlik, 0).toString(),
					qty = dtm.getValueAt(rowDiKlik, 3).toString();
				
			idItemUpdateField.setText(id);
			qtyUpSpinner.setValue(Integer.parseInt(qty));	
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
	
	public void getDataItem() {
		//refresh data tabel 
		dtm2.setRowCount(0);
				
		String query = "SELECT * FROM item";
		ResultSet getItem = connector.executeQuery(query);
		
		try {
			while(getItem.next()) {
				String idItem = getItem.getString("idItem");
				String namaItem = getItem.getString("namaItem");
				int hargaItem = getItem.getInt("hargaItem");
				int stockItem = getItem.getInt("stockItem");
				String imagePath = getItem.getString("imagePath");
								
				dtm2.addRow(new Object[] {
					idItem,
					namaItem,
					hargaItem,
					stockItem,
					new ImageIcon(imagePath)
				});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
