package hbase;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.*;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

public class Main {

	private JFrame frmEmi;
	private JTable table;
	//private JTextField columnf;
	private JComboBox<String> columnf;
	private JTextField row;
	private JTextField key;
	private JTextField value;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmEmi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public Main() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		Configuration config = HBaseConfiguration.create();
	    final HTable hTable = new HTable(config, "student");
	    
		frmEmi = new JFrame();
		frmEmi.setTitle("EMI");
		frmEmi.setBounds(100, 100, 707, 594);
		frmEmi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEmi.getContentPane().setLayout(null);
		
		JLabel lblStudents = new JLabel("Students");
		lblStudents.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblStudents.setBounds(281, 57, 114, 16);
		frmEmi.getContentPane().add(lblStudents);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Ajout
				Configuration config = HBaseConfiguration.create();
			    // instantiate HTable class
			    try {
					HTable hTable = new HTable(config, "student");
					String ling = row.getText();
					String column = key.getText();
					String valeur = value.getText();
					//String cfamily = columnf.getText();
					String cfamily = (String) columnf.getSelectedItem();
					Put p = new Put(Bytes.toBytes(ling));
					p.add(Bytes.toBytes(cfamily),Bytes.toBytes(column),Bytes.toBytes(valeur));
					hTable.put(p);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    // instantiate Put class
			}
		});
		btnAjouter.setBounds(498, 147, 97, 25);
		frmEmi.getContentPane().add(btnAjouter);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Modification
				Configuration config = HBaseConfiguration.create();
			    // instantiate HTable class
			    try {
					HTable hTable = new HTable(config, "student");
					String ling = row.getText();
					String column = key.getText();
					String valeur = value.getText();
					//String cfamily = columnf.getText();
					String cfamily = (String) columnf.getSelectedItem();
					Put p = new Put(Bytes.toBytes(ling));
					p.add(Bytes.toBytes(cfamily),Bytes.toBytes(column),Bytes.toBytes(valeur));
					hTable.put(p);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    // instantiate Put class
			}
		});
		btnModifier.setBounds(345, 147, 97, 25);
		frmEmi.getContentPane().add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//suppression
				String ling = row.getText();
				String column = key.getText();
				//String cfamily = columnf.getText();
				String cfamily = (String) columnf.getSelectedItem();
				// instantiate the Scan class
			    Scan scan = new Scan();
			    // scan the columns or familly
			    scan.addFamily(Bytes.toBytes(cfamily));
				if(!ling.equals("")){
					if(!column.equals("")){
						// get the ResultScanner
					    try {
					    	ResultScanner scanner = hTable.getScanner(scan);
					    	Delete delete = new Delete(Bytes.toBytes(ling));
					        delete.deleteColumn(Bytes.toBytes(cfamily), Bytes.toBytes(column));
					        hTable.delete(delete);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					else{
						try {
					    	ResultScanner scanner = hTable.getScanner(scan);
					        Delete delete = new Delete(Bytes.toBytes(ling));
					        delete.deleteFamily(Bytes.toBytes(cfamily));
					        hTable.delete(delete);
					        // delete the data
					    	
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
			}
		});
		btnSupprimer.setBounds(186, 147, 97, 25);
		frmEmi.getContentPane().add(btnSupprimer);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id","semestre" ,"matier", "note"
			}
		));
		table.setBounds(12, 210, 653, 230);
		frmEmi.getContentPane().add(table);
		
		row = new JTextField();
		row.setBounds(30, 112, 50, 22);
		frmEmi.getContentPane().add(row);
		row.setColumns(10);
		
		//columnf = new JTextField();
		String[] bookTitles = new String[] {"1","2","3","4","5"};

		columnf = new JComboBox<>(bookTitles);
		columnf.setBounds(175, 112, 116, 22);
		frmEmi.getContentPane().add(columnf);
		//columnf.setColumns(10);
		
		key = new JTextField();
		key.setBounds(350, 112, 116, 22);
		frmEmi.getContentPane().add(key);
		key.setColumns(10);
		
		value = new JTextField();
		value.setBounds(550, 112, 116, 22);
		frmEmi.getContentPane().add(value);
		value.setColumns(10);
		
		JLabel lblRow = new JLabel("id");
		lblRow.setBounds(10, 115, 56, 16);
		frmEmi.getContentPane().add(lblRow);
		
		JLabel lblcolumnf = new JLabel("semestre");
		lblcolumnf.setBounds(110, 115, 60, 16);
		frmEmi.getContentPane().add(lblcolumnf);
		
		JLabel lblKey = new JLabel("matier");
		lblKey.setBounds(300, 115, 56, 16);
		frmEmi.getContentPane().add(lblKey);
		
		JLabel lblValue = new JLabel("notes");
		lblValue.setBounds(500, 115, 56, 16);
		frmEmi.getContentPane().add(lblValue);
		
		JButton btnChoisir = new JButton("Visualiser");
		btnChoisir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// code pour visualisation des donnees (scan) sachant que le tableau possede 3 valeurs : row key et value
				String ling = row.getText();
				String column = key.getText();
				//String cfamily = columnf.getText();
				String cfamily = (String) columnf.getSelectedItem();
				// instantiate the Scan class
			    Scan scan = new Scan();
			    // scan the columns or familly
			    scan.addFamily(Bytes.toBytes(cfamily));
				if(!ling.equals("")){
					if(!column.equals("")){
						// get the ResultScanner
					    try {
					    	ResultScanner scanner = hTable.getScanner(scan);
					    	List<String> rows = new ArrayList<String>();
					    	List<String> columns = new ArrayList<String>();
					    	List<String> values = new ArrayList<String>();
					    	List<String> family = new ArrayList<String>();
					    	
					    	for (Result result = scanner.next(); result != null; result=scanner.next())
						      { 
					    		for (Cell cell : result.rawCells()) {
					    			if(ling.equals(new String((result.getRow())))){
					    				if(column.equals(new String(CellUtil.cloneQualifier(cell)))){
					    				rows.add(new String(result.getRow()));
						                columns.add(new String(CellUtil.cloneQualifier(cell)));
						                values.add(new String(CellUtil.cloneValue(cell)));
						                family.add(new String(CellUtil.cloneFamily(cell)));}
						                
					    			}
					                //byte[] family = CellUtil.cloneFamily(cell);
					    			
					    		}
						      }
					    	int n = values.size();
					    	String [][] myTable = new String[n][4];
					    	for(int i = 0; i < values.size();i++){
						    		  myTable[i][0] =  rows.get(i);
						    		  myTable[i][1] = family.get(i);
						    		  myTable[i][2] = columns.get(i);
						    		  myTable[i][3] = values.get(i);
					    	}
					    	String [] cols = {"id","semestre" ,"matier", "note"};
					    	DefaultTableModel dts = new DefaultTableModel(myTable,cols) ;
					    	table.setModel(dts);
					    	
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					else{
						try {
					    	ResultScanner scanner = hTable.getScanner(scan);
					    	List<String> rows = new ArrayList<String>();
					    	List<String> columns = new ArrayList<String>();
					    	List<String> values = new ArrayList<String>();
					    	List<String> family = new ArrayList<String>();
					    	
					    	for (Result result = scanner.next(); result != null; result=scanner.next())
						      { 
					    		for (Cell cell : result.rawCells()) {
					    			if(ling.equals(new String((result.getRow())))){
					    				rows.add(new String(result.getRow()));
						                columns.add(new String(CellUtil.cloneQualifier(cell)));
						                values.add(new String(CellUtil.cloneValue(cell)));
						                family.add(new String(CellUtil.cloneFamily(cell)));
					    			}
					                //byte[] family = CellUtil.cloneFamily(cell);
					    			
					    		}
						      }
					    	int n = rows.size();
					    	String [][] myTable = new String[n][4];
					    	for(int i = 0; i < values.size();i++){
						    		  myTable[i][0] =  rows.get(i);
						    		  myTable[i][1] = family.get(i);
						    		  myTable[i][2] = columns.get(i);
						    		  myTable[i][3] = values.get(i);
					    	}
					    	String [] cols = {"id","semestre" ,"matier", "note"};
					    	DefaultTableModel dts = new DefaultTableModel(myTable,cols) ;
					    	table.setModel(dts);
					    	
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
				else{
					try {
				    	ResultScanner scanner = hTable.getScanner(scan);
				    	List<String> rows = new ArrayList<String>();
				    	List<String> columns = new ArrayList<String>();
				    	List<String> values = new ArrayList<String>();
				    	List<String> family = new ArrayList<String>();
				    	
				    	for (Result result = scanner.next(); result != null; result=scanner.next())
					      { 
				    		for (Cell cell : result.rawCells()) {
				    				rows.add(new String(result.getRow()));
					                columns.add(new String(CellUtil.cloneQualifier(cell)));
					                values.add(new String(CellUtil.cloneValue(cell)));
					                family.add(new String(CellUtil.cloneFamily(cell)));
				                //byte[] family = CellUtil.cloneFamily(cell);
				    		}
					      }
				    	int n = rows.size();
				    	String [][] myTable = new String[n][4];
				    	for(int i = 0; i < values.size();i++){
					    		  myTable[i][0] =  rows.get(i);
					    		  myTable[i][1] = family.get(i);
					    		  myTable[i][2] = columns.get(i);
					    		  myTable[i][3] = values.get(i);
				    	}
				    	String [] cols = {"id","semestre" ,"matier", "note"};
				    	DefaultTableModel dts = new DefaultTableModel(myTable,cols) ;
				    	table.setModel(dts);
				    	
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		btnChoisir.setBounds(35, 147, 97, 25);
		frmEmi.getContentPane().add(btnChoisir);
		
		JLabel label = new JLabel("*");
		label.setBounds(277, 140, 56, 16);
		frmEmi.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("**");
		label_1.setBounds(437, 140, 56, 16);
		frmEmi.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("***");
		label_2.setBounds(593, 140, 56, 16);
		frmEmi.getContentPane().add(label_2);
		
		JLabel lblChoisirLe = new JLabel("*    Choisir le Row \u00E0 supprimer");
		lblChoisirLe.setBounds(35, 453, 449, 16);
		frmEmi.getContentPane().add(lblChoisirLe);
		
		JLabel lblRemplirTousLes = new JLabel("**  Remplir tous les champs pour modifier");
		lblRemplirTousLes.setBounds(35, 468, 449, 16);
		frmEmi.getContentPane().add(lblRemplirTousLes);
		
		JLabel lblsiLeRow = new JLabel("***Si le Row et la colonne existe deja, alors il s'agira de modification");
		lblsiLeRow.setBounds(35, 482, 449, 16);
		frmEmi.getContentPane().add(lblsiLeRow);
		
		JMenuBar menuBar = new JMenuBar();
		frmEmi.setJMenuBar(menuBar);
		
		JMenu mnAccueil = new JMenu("Accueil");
		menuBar.add(mnAccueil);
		
		JMenu mnEmi = new JMenu("EMI");
		menuBar.add(mnEmi);
		
		JMenu mnStudents = new JMenu("Students");
		menuBar.add(mnStudents);
		
		JMenu mnAPropos = new JMenu("A propos");
		menuBar.add(mnAPropos);
	}
}

