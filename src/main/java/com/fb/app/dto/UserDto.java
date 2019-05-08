package com.fb.app.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class UserDto {
	
	@NotBlank(message = "The parameter userFbId can't be null or empty.")
    private String userFbId;
	
    @NotBlank(message = "The parameter accessToken can't be null or empty.")
    @JsonProperty(access = Access.WRITE_ONLY)
	private String accessToken;
	
    @JsonProperty(access = Access.READ_ONLY)
	private String name;
	
    @JsonProperty(access = Access.READ_ONLY)
	private String gender;
	
    @JsonProperty(access = Access.READ_ONLY)
	private String profilePictureUrl;
	
	@JsonIgnore
	private Set<PhotoDto> photos;
	
	public UserDto() {
		super();
	}

	public UserDto(String userFbId, String accessToken, String name, String gender, String profilePictureUrl,
			Set<PhotoDto> photos) {
		super();
		this.userFbId = userFbId;
		this.accessToken = accessToken;
		this.name = name;
		this.gender = gender;
		this.profilePictureUrl = profilePictureUrl;
		this.photos = photos;
	}

	public String getUserFbId() {
		return userFbId;
	}

	public void setUserFbId(String userFbId) {
		this.userFbId = userFbId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProfilePictureUrl() {
		return profilePictureUrl;
	}

	public void setProfilePictureUrl(String profilePictureUrl) {
		this.profilePictureUrl = profilePictureUrl;
	}

	public Set<PhotoDto> getPhotos() {
		return photos;
	}

	public void setPhotos(Set<PhotoDto> photos) {
		this.photos = photos;
	}

	@Override
	public String toString() {
		return "UserDto [userFbId=" + userFbId + ", accessToken=" + accessToken + ", name=" + name + ", gender="
				+ gender + ", profilePictureUrl=" + profilePictureUrl + ", photos=" + photos + "]";
	}
}
