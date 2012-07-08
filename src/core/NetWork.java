package core;

import gui.GistViewer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pojo.Gist;
import pojo.GistLister;

public class NetWork {
	
	private ObjectMapper mapper;
	
	public NetWork(){
		mapper = new ObjectMapper();
	}
	/**
	 * Gets the gist with the given ID from the github api
	 * 
	 * @param id The id for the gist
	 * @return The gist with the given id in form of the pojo.Gist.java class
	 */
	public Gist getGist(String id) {
		return getGist(id,"EMPTY");
	}
	/**
	 * Gets the gist with the given ID from the github api
	 * 
	 * @param id The id for the gist
	 * @param access_token The token to gaining access to private gist
	 * @return The gist with the given id in form of the pojo.Gist.java class
	 */
	public Gist getGist(String id,String access_token){
		String urlcreater = "https://api.github.com/gists/"+id;
		
		if(access_token != "EMPTY"){
			urlcreater = urlcreater + "?access_token=" + access_token;
		}
		//Now call the api
		String response = apiCall(urlcreater);
		Gist gist = this.jsonToPojo(response, Gist.class);
		return gist;
	}
	
	/**
	 * Fetch the the list of all gist's from the user giving in name.
	 * (Only public gist's) 
	 * 
	 * @param name The name of the user
	 * @param access_token The access token the get private gist's as well
	 * @return List of the gist's for the user
	 */
	public GistLister[] getProfile(String name){
		return getProfile(name,"EMPTY");
	}
	/**
	 * Fetch the the list of all gist's from the user giving in name.
	 * (Both public and private gist's) 
	 * 
	 * @param name The name of the user
	 * @param access_token The access token the get private gist's as well
	 * @return List of the gist's for the user
	 */
	public GistLister[] getProfile(String name,String access_token){
		String urlcreater = "https://api.github.com/users/"+name+"/gists";
		
		if(access_token != "EMPTY"){
			urlcreater = urlcreater + "?access_token=" + access_token;
		}
		//Now call the api
		String response = apiCall(urlcreater);
		//Convert to the json to java pojo
		GistLister[] gists = this.jsonToPojo(response, GistLister[].class);
		
		return gists;
		
	}
	/**
	 * Deserialize the incoming json string to the giving java class  
	 * 
	 * @param input Data in json format
	 * @param inputType The class to extract the information from input too.
	 * @return Returns in the same format as in inputType
	 */
	private <T> T jsonToPojo(String input,Class<T> inputType){
		try {
			return inputType.cast(mapper.readValue(input, inputType));
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Makes the url request to the url that is given as input and return the response+
	 * The return from gist api calls in only one line the method will only return the
	 * first line from the response
	 * 
	 * @param inputURL The address to make the request
	 * @return The answer or if something went wrong 
	 */
	private String apiCall(String inputURL){
		String response = "EMPTY";
		try {
			URL url = new URL(inputURL);
			URLConnection con  = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			response = in.readLine();		
		} catch (MalformedURLException e) {
			response = "{\"error\": \"Something went wrong with malformedurl\"}";
			e.printStackTrace();
		} catch (IOException e) {
			response = "{\"error\": \"IOExceptions happend\"}";
			e.printStackTrace();
		}
		return response; 
	}
	
	public static void main(String[] args){
		NetWork nw = new NetWork();
		
		//new GistViewer(nw.getGist("2841832"));
		GistLister[] temp = nw.getProfile("Wneh");
		System.out.println(temp[0].getDescription());
	}
}
