package pojo;

import java.util.Date;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.annotation.JsonProperty;


public class GistLister {
	
	
	private User user;
	private String git_pull_url;
	private Date updated_at;
	private String git_push_url;
	@JsonProperty("public")
	private boolean _public;
	private LinkedHashMap<String,GistFile> files; 
	private String description;
	private int comments;
	private String html_url;
	private String id;
	private String url;
	private Date created_at;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getGit_pull_url() {
		return git_pull_url;
	}

	public void setGit_pull_url(String git_pull_url) {
		this.git_pull_url = git_pull_url;
	}
	
	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public String getGit_push_url() {
		return git_push_url;
	}

	public void setGit_push_url(String git_push_ur) {
		this.git_push_url = git_push_ur;
	}
	
	public boolean is_public() {
		return _public;
	}
	
	
	public void set_public(boolean _public) {
		this._public = _public;
	}

	public LinkedHashMap<String,GistFile> getFiles() {
		return files;
	}

	public void setFiles(LinkedHashMap<String,GistFile> files) {
		this.files = files;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	public String getHtml_url() {
		return html_url;
	}

	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	
}
