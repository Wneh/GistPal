package core;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.io.*;

import javax.swing.JOptionPane;

import pojo.Gist;
import pojo.GistLister;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Starter {
	
	
	public Starter(){		
		
		try{
			//URL oracle = new URL("https://api.github.com?access_token=d49c44867b85fc126fce");
			
			System.out.println("=======decode=======");
            
			  String jsonText="{\"id\": 1,\"name\": \"Foo\",\"price\": 123,\"tags\": [\"Bar\",\"Eek\"],\"stock\": { \"warehouse\":300, \"retail\":20 }}";
			  String merJSON = "{\"name\" : { \"first\" : \"Joe\", \"last\" : \"Sixpack\" },\"gender\" : \"MALE\",\"verified\" : false,\"userImage\" : \"Rm9vYmFyIQ==\"}";
			  String mj = "[{\"user\": {\"gravatar_id\": \"4d52a146e276f358b8b8ff25bc3e1909\",\"avatar_url\": \"https://secure.gravatar.com\",\"login\": \"Wneh\",\"url\": \"https://api.github.com/users/Wneh\",\"id\": 722053}" +
			  				",\"git_pull_url\": \"git://gist.github.com/3042640.git\",\"updated_at\": \"2012-07-03T20:08:13Z\",\"git_push_url\": \"git@gist.github.com:3042640.git\",\"public\": true," +
			  				"\"files\": {\"2x\": {\"raw_url\": \"https://gist.github.com/raw/3042640/922efce9237835c2d7ccb742c0884e1c446541bd/2x\",\"type\": \"text/plain\",\"filename\": \"2x\",\"language\": null,\"size\": 28}," +
			  				"\"nummer 2\": {\"raw_url\": \"https://gist.github.com/raw/3042640/d64f464f51be6af919ccaf789b4e52c33fb135d7/nummer 2\",\"type\": \"text/plain\",\"filename\": \"nummer 2\",\"language\": null,\"size\": 17 }}," +
			  				"\"description\": \"MED TVÅ ST FILER\",\"comments\": 0,\"html_url\": \"https://gist.github.com/3042640\", \"id\": \"3042640\",\"url\": \"https://api.github.com/gists/3042640\",\"created_at\": \"2012-07-03T20:08:13Z\"}]";
			
			//URL oracle = new URL("https://api.github.com/users/Wneh/gists");	
			URL oracle = new URL("https://api.github.com/gists/2841832");
			//URL oracle = new URL("https://github.com/login/oauth/authorize?client_id=690ae5aa9253c0ed20cd&scope=gist");
			//URL oracle = new URL("https://api.github.com/gists");
	        URLConnection yc = oracle.openConnection();
	        
	        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
	        String inputLine;
	        String lastline = "";
	        int r = 0;
	        while ((inputLine = in.readLine()) != null){
	        	if(inputLine != null)
	        		lastline = inputLine;
	            System.out.println(inputLine);
	            r++;
	        }
	        
	        in.close();
	        
	        System.out.println("------------------------------------ R = " + r );
	        
	        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
	        //User us = mapper.readValue(merJSON, User.class);
	        //System.out.println(us.toString());
	        
	        Gist gi = mapper.readValue(lastline, Gist.class);
	        System.out.println("Done");
	        System.out.println(gi.getFiles().get("latency.txt").getContent());
	        /*System.out.println("User: "+gi[0].getUser());
	        System.out.println("git_pull_url: "+gi[0].getGit_pull_url());
	        System.out.println("updated_at: " + gi[0].getUpdated_at());
	        System.out.println("git_push_url: "+gi[0].getGit_push_url());
	        System.out.println("public: " + gi[0].is_public());
	        System.out.println("files: "+ gi[0].getFiles().get("nummer 2"));
	        System.out.println("Desc: " +gi[0].getDescription());
	        System.out.println("Comments: "+gi[0].getComments());
	        System.out.println("html_url: "+gi[0].getHtml_url());
	        System.out.println("id: "+ gi[0].getId());
	        System.out.println("url: " + gi[0].getUrl());
	        System.out.println("created_at: " + gi[0].getCreated_at());*/
	        
	        
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Starter();

	}

}
