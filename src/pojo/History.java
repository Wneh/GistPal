package pojo;

import java.util.Date;

public class History {

	private User user;
	private String version;
	
	public static class Change_status{
		private int deletions;
		private int additions;
		private int total;
		public int getDeletions() {
			return deletions;
		}
		public void setDeletions(int deletions) {
			this.deletions = deletions;
		}
		public int getAdditions() {
			return additions;
		}
		public void setAdditions(int additions) {
			this.additions = additions;
		}
		public int getTotal() {
			return total;
		}
		public void setTotal(int total) {
			this.total = total;
		}
	}
	
	private Change_status change_status;
	private Date committed_at;
	private String url;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Change_status getChange_status() {
		return change_status;
	}
	public void setChange_status(Change_status change_status) {
		this.change_status = change_status;
	}
	public Date getCommitted_at() {
		return committed_at;
	}
	public void setCommitted_at(Date committed_at) {
		this.committed_at = committed_at;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
