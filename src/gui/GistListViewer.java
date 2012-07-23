package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Properties;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import core.NetWork;
import core.PropertiesIO;

import pojo.GistFile;
import pojo.GistLister;

public class GistListViewer extends JFrame{
	
	private JList list;
	private DefaultListModel listContent;
	private PropertiesIO propIO;
	private NetWork nw;

	/**
	 * Shows a window that list all the gist for the given user.
	 *
	 * @param fillList Set to true if you want to fill the list when created.
	 */
	public GistListViewer(boolean fillList){
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		listContent = new DefaultListModel();
		list = new JList(listContent);
		c.add(list,BorderLayout.CENTER);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
		
		/**
		 * Never found a casw were the response for a user profile
		 * contains more then 1.
		 * 
		 * So if it do warn the user that something might not be correct.
		 */
		if(glTemp.length >= 2){
			//Warn the user
			JOptionPane.showMessageDialog(null,"There is more data that is now showing" +
												"\nFor dev: The profile reponse contained more then 1 object");
		}
		JOptionPane.showMessageDialog(null,"GistListSize:" + glTemp.length);
		
		//Display them.
		if(glTemp.length > 0){
			fillList(glTemp[1]);
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
		int lastIndex = 0;
		
		Iterator<String> iterator = files.keySet().iterator();
		while (iterator.hasNext()){
			String key = (String) iterator.next();
			GistFile file = (GistFile)files.get(key);
			
			listContent.add(lastIndex, file.getFilename());
			//System.out.println(key + "\t\t: \t" + file.getFilename());
		
			//jtas.add(new JTextArea(file.getContent()));
		}
		
	}

}
