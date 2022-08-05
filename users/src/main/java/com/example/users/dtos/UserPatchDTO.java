package com.example.users.dtos;

public class UserPatchDTO {

	private String jobTitle;
	private String image;
	
	public UserPatchDTO(String jobTitle, String image) {
		this.jobTitle = jobTitle;
		this.image = image;
		
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}
