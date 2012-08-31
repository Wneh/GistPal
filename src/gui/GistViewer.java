package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import pojo.Gist;
import pojo.GistFile;

public class GistViewer extends JFrame {
	

	private static final long serialVersionUID = -7319639111951226534L;
	private static final int JLIST_CELL_WIDTH = 200;
	
	private Gist gist;
	private ArrayList<JTextArea> jtas;
	
	private JList fileList;
	private DefaultListModel fileListModel;
	private JScrollPane fileListScroll;
	
	public GistViewer(Gist gistIn){		
		this.gist = gistIn;
		//First of all reverse the fileinput
		LinkedHashMap<String, GistFile> tempLHP = this.reverseLinkedHashMap(gist.getFiles());
		gist.setFiles(tempLHP);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		//JList 
		fileListModel = new DefaultListModel();
	    fileList = new JList(fileListModel);
	    	fileList.setFixedCellWidth(JLIST_CELL_WIDTH);
	    fileListScroll = new JScrollPane(fileList);
	    fillJList();
	        
		//JTextArea
		jtas = new ArrayList<JTextArea>();
		createAllJTextArea();
		
		c.add(fileListScroll,BorderLayout.WEST);
		c.add(jtas.get(0),BorderLayout.CENTER);
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("GistPal - " + gist.getDescription());
		setSize(800,600);
		
	}
	
	/**
	 * Small help functions that fills the ArrayList holding the jtextarea's with the content from the gist
	 */
	private void createAllJTextArea(){
		
		//Fetch hashmap with all the files
		LinkedHashMap<String, GistFile> files = gist.getFiles();
		
		Iterator<String> iterator = files.keySet().iterator();
		while (iterator.hasNext()){
			String key = (String) iterator.next();
			GistFile file = (GistFile)files.get(key);
			//System.out.println(key + "\t\t: \t" + file.getFilename());
		
			jtas.add(new JTextArea(file.getContent()));
		}
	}
	private void fillJList(){
		LinkedHashMap<String, GistFile> files = gist.getFiles();
		Iterator<String> iterator = files.keySet().iterator();
		
		while(iterator.hasNext()){
			String key = (String) iterator.next();
			GistFile file = (GistFile)files.get(key);	
			fileListModel.addElement(file.getFilename());
		}	
	}
	/**
	 * Reverse the order of the content in the LinkedHashMap that is given as input
	 * 
 	 * @param in The LinkedHashMap that shall be reversed
	 * @return The reversed order of the content from in.
	 */
	private LinkedHashMap<String, GistFile> reverseLinkedHashMap(LinkedHashMap<String, GistFile> in){
		LinkedHashMap<String, GistFile> out = new LinkedHashMap<String, GistFile>();
		ArrayList<GistFile> tempHolder = new ArrayList<GistFile>();
		Iterator<String> iterator = in.keySet().iterator();
		
		while(iterator.hasNext()){
			String key = (String) iterator.next();
			GistFile file = (GistFile)in.get(key);
			tempHolder.add(file);
		}	
		for(int i = tempHolder.size()-1; i >= 0; i--){
			GistFile tempGist = tempHolder.get(i);
			out.put(tempGist.getFilename(), tempGist);
		}
		
		return out;
		
	}
}
