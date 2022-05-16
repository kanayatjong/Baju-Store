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

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class inventory extends JInternalFrame implements MouseListener, ActionListener{
	//judul
	JPanel judulPanel = new JPanel();
	JLabel judul = new JLabel("Invetory");
	
	//JPanel
	JPanel contentPanel = new JPanel(new GridLayout(2,1));
	
	//model, table, scrollpane kanan
	JPanel listItemPanel = new JPanel(new BorderLayout());
	String columnItemNames[] = {"Id Item", "Nama", "Harga", "Stock", "Foto"};
	DefaultTableModel dtm = new DefaultTableModel(columnItemNames, 0) {
	@Override
	public Class<?> getColumnClass(int column) {
		switch (column) {
		case 4: return Icon.class;
		default: return String.class;
		}
		}
	};
	JTable tableListItem = new JTable(dtm);
	JScrollPane scrollpaneTableListItem = new JScrollPane(tableListItem);
		
	//untuk update
	JPanel fieldPanel = new JPanel(new GridLayout(0,4));
			
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
	JPanel stockSpinnerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	SpinnerNumberModel stockSpinnerModel = new SpinnerNumberModel(1, 0, 500, 1);
	JSpinner stockSpinner = new JSpinner(stockSpinnerModel);
	
	//button
	JPanel updateButtonPanel = new JPanel();
	JButton updateButton = new JButton("Update");
	
	//content bawah
	JPanel contentBawah = new JPanel(new GridLayout(2,1));
	
	//pesan error
		JPanel pesanErrorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pesanError1 = new JLabel("Belum Ada Item Yang Dipilih!");
		JLabel pesanError2 = new JLabel("Nama Item 5-100 Karakter!");
		JLabel pesanError3 = new JLabel("Harga Item Tidak Boleh Dibawah 20000!");
	
	//connect ke database
		DatabaseConnector connector = DatabaseConnector.getConnector();
	
	public void setComponent() {
		//JInternalFrame 
		this.setResizable(false);
		this.setMaximizable(true);
		this.setClosable(true);
		this.setSize(980, 480);
		this.setVisible(true);
		this.getContentPane().setBackground(new Color(254, 251, 244));
		
		//judul
		judulPanel.add(judul);
		
		//table item di inventory
		listItemPanel.add(scrollpaneTableListItem);
		
		//field
		idItemPanel.add(idItem);
		idItemFieldPanel.add(idItemField);
		namaItemPanel.add(namaItem);
		namaItemFieldPanel.add(namaItemField);
		hargaItemPanel.add(hargaItem);
		hargaItemFieldPanel.add(hargaItemField);
		stockItemPanel.add(stockItem);
		stockSpinnerPanel.add(stockSpinner);
		
		fieldPanel.add(idItemPanel);
		fieldPanel.add(idItemFieldPanel);
		fieldPanel.add(namaItemPanel);
		fieldPanel.add(namaItemFieldPanel);
		fieldPanel.add(hargaItemPanel);
		fieldPanel.add(hargaItemFieldPanel);
		fieldPanel.add(stockItemPanel);
		fieldPanel.add(stockSpinnerPanel);
		
		contentPanel.add(listItemPanel);
		contentPanel.add(fieldPanel);
		
		//button
		updateButtonPanel.add(updateButton);
		
		//Pesan error diset tidak visible
		pesanError1.setVisible(false);
		pesanError2.setVisible(false);
		pesanError3.setVisible(false);
				
		//Pesan error diberi warna merah
		pesanError1.setForeground(Color.red);
		pesanError2.setForeground(Color.red);
		pesanError3.setForeground(Color.red);
				
		pesanErrorPanel.add(pesanError1);
		pesanErrorPanel.add(pesanError2);
		pesanErrorPanel.add(pesanError3);
		
		contentBawah.add(updateButtonPanel);
		contentBawah.add(pesanErrorPanel);
	
		//add ke frame
		add(judulPanel, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
		add(contentBawah, BorderLayout.SOUTH);
		
		//Tidak bisa di edit
		idItemField.setEditable(false);
		
		//ukuran field
		idItemField.setPreferredSize(new Dimension(200,20));
		namaItemField.setPreferredSize(new Dimension(200,20));
		hargaItemField.setPreferredSize(new Dimension(200,20));
		
		//set ukuran row table list item
		tableListItem.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		TableColumnModel colModel = tableListItem.getColumnModel();
		colModel.getColumn(4).setPreferredWidth(120);
		tableListItem.setRowHeight(120);
		
		//warna
		judulPanel.setBackground(new Color(254, 251, 244));
		listItemPanel.setBackground(new Color(254, 251, 244));
		idItemPanel.setBackground(new Color(254, 251, 244));
		idItemFieldPanel.setBackground(new Color(254, 251, 244));
		namaItemPanel.setBackground(new Color(254, 251, 244));
		namaItemFieldPanel.setBackground(new Color(254, 251, 244));
		hargaItemPanel.setBackground(new Color(254, 251, 244));
		hargaItemFieldPanel.setBackground(new Color(254, 251, 244));
		stockItemPanel.setBackground(new Color(254, 251, 244));
		stockSpinnerPanel.setBackground(new Color(254, 251, 244));
		fieldPanel.setBackground(new Color(254, 251, 244));
		contentPanel.setBackground(new Color(254, 251, 244));
		updateButtonPanel.setBackground(new Color(254, 251, 244));
		contentBawah.setBackground(new Color(254, 251, 244));
		pesanErrorPanel.setBackground(new Color(254, 251, 244));
		
		//event
		tableListItem.addMouseListener(this);
		updateButton.addActionListener(this);
		
	}
	
	public inventory() {
		//set component
		setComponent();
	
		//get data
		getDataItem();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String id = idItemField.getText();
		String nama = namaItemField.getText();
		String harga = hargaItemField.getText(); 
		int stock = (int) stockSpinner.getValue();
		
		//refresh pesan error
		pesanError1.setVisible(false);
		pesanError2.setVisible(false);
		pesanError3.setVisible(false);
		
		if(id.isEmpty()) {
			pesanError1.setVisible(true);
		}else if(nama.length() < 5 || nama.length() > 100) {
			pesanError2.setVisible(true);
		}else if(Integer.parseInt(harga) <=20000) {
			pesanError3.setVisible(true);
		}else {
			String queryUpdateItem = String.format("UPDATE `item` SET `namaItem` = '%s', `hargaItem` = %s, `stockItem` = %s WHERE `idItem` = '%s'", nama, harga, stock, id);
			connector.execute(queryUpdateItem);
			
			JOptionPane.showMessageDialog(null, "Update Item Berhasil!");
			getDataItem();
			//refresh
			idItemField.setText("");
			namaItemField.setText("");
			hargaItemField.setText(""); 
			stockSpinner.setValue(1);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int rowDiKlik = tableListItem.getSelectedRow();
		
		String id = dtm.getValueAt(rowDiKlik, 0).toString(),
				nama = dtm.getValueAt(rowDiKlik, 1).toString(),
				harga = dtm.getValueAt(rowDiKlik, 2).toString(),
				stock = dtm.getValueAt(rowDiKlik, 3).toString();
		
			idItemField.setText(id);
			namaItemField.setText(nama);
			hargaItemField.setText(harga); 
			stockSpinner.setValue(Integer.parseInt(stock));
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
		dtm.setRowCount(0);
				
		String query = "SELECT * FROM item";
		ResultSet getItem = connector.executeQuery(query);
		
		try {
			while(getItem.next()) {
				String idItem = getItem.getString("idItem");
				String namaItem = getItem.getString("namaItem");
				int hargaItem = getItem.getInt("hargaItem");
				int stockItem = getItem.getInt("stockItem");
				String imagePath = getItem.getString("imagePath");
								
				dtm.addRow(new Object[] {
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
