package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Properties;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import core.NetWork;
import core.PropertiesIO;

import pojo.GistFile;
import pojo.GistLister;

public class GistListViewer extends JFrame implements MouseListener{
	
	private JList list;
	
	private String[] columnNames = {"Name","ID","Number of files"};  
	private	DefaultTableModel tableModel;
	private JTable table;
	private JScrollPane scrollpane;
	
	private DefaultListModel listContent;
	private PropertiesIO propIO;
	private NetWork nw;
	
	private GistLister[] glGlobal;

	/**
	 * Shows a window that list all the gist for the given user.
	 *
	 * @param fillList Set to true if you want to fill the list when created.
	 */
	public GistListViewer(boolean fillList){
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		//Create the tableModel first and override isCellEditable to make the whole
		//table not editable
		tableModel = new DefaultTableModel(columnNames,0){
			/**
			 * Autogenerated from eclipse :)
			 */
			private static final long serialVersionUID = 769778719446758565L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		//And then add it to the JTable
		table = new JTable(tableModel);
		table.setBounds(10, 36, 527, 214); 
		scrollpane = new JScrollPane(table);
		
		//listContent = new DefaultListModel();
		//list = new JList(listContent);
		//c.add(list,BorderLayout.CENTER);
		c.add(scrollpane,BorderLayout.CENTER);
		
		table.addMouseListener(this);
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("GistPal");
		setSize(800,600);
		
		//Create some extra stuff
		propIO = new PropertiesIO();
		nw = new NetWork();
		
		//If the caller wants to fill the list at once
		if(fillList){
			getAndShowGists();
		}
	}
	/**
	 * Get all the gist from the user given in the properties file
	 * and displays them.
	 */
	public void getAndShowGists(){
		//Get the user name.
		String userName = propIO.getProperty("user");
		
		//Download the gist from github.
		GistLister[] glTemp = nw.getProfile(userName);
		//And set the glTemp to glGloboal for future use.
		glGlobal = glTemp;
		
		//Display them.
		if(glTemp.length > 0){
			for(int r = 0; r < glTemp.length; r++){
				fillList(glTemp[r]);
			}
		}
		else{
			//The response for the user profile was empty 
			JOptionPane.showMessageDialog(null, "The repsonse was empty, is the username correct?");
		}
		
	}
	
	/**
	 * Fills the jframe with data from the GistLister
	 * 
	 * @param gl The GistLister containing all the data.
	 */
	public void fillList(GistLister gl){
		
		LinkedHashMap<String, GistFile> files = gl.getFiles();
//		int lastIndex = 0;
//		
//		Iterator<String> iterator = files.keySet().iterator();
//		int i = 0;
//		while (iterator.hasNext()){
//			String key = (String) iterator.next();
//			//GistFile file = (GistFile)files.get(key);
//			
//			//listContent.add(lastIndex, file.getFilename());
//			//Create temp array to hold the data
//			String[] tempArray = {file.getFilename(),gl.getId(),gl.getDescription()};
//			tableModel.addRow(tempArray);
//			//System.out.println(key + "\t\t: \t" + file.getFilename());
//		
//			//jtas.add(new JTextArea(file.getContent()));
//		}	
		
		String[] tempArray = {gl.getDescription(),gl.getId(),""+files.size()};
		tableModel.addRow(tempArray);
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("Clicked");
		//If the source was from the jtable
		if(arg0.getSource() == table){
			//and if the person double clicked on a row
			if(arg0.getClickCount() == 2){
				int row = table.getSelectedRow();
				//Get the id from for the row and use it to get the gist and send it to viewer.
				new GistViewer(nw.getGist(glGlobal[row].getId()));
			}
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
