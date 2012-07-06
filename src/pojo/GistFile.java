package pojo;

import java.net.URL;

public class GistFile {
	
	private URL raw_url;
	private String type;
	private String filename;
	private String language;
	private int size;
	private String content;
	
	public String toString(){
		return  raw_url + ", " + type + ", " + filename + ", " + language + ", " + size;
	}
	
	public URL getRaw_url() {
		return raw_url;
	}
	public void setRaw_url(URL raw_url) {
		this.raw_url = raw_url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
