package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;

import pojo.GistFile;
import pojo.GistLister;

public class GistListViewer extends JFrame{
	
	private JList list;
	private DefaultListModel listContent;
	
	public GistListViewer(){
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		listContent = new DefaultListModel();
		list = new JList(listContent);
		c.add(list,BorderLayout.CENTER);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("GistPal");
		setSize(800,600);
	}
	
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
