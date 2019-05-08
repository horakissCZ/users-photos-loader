package com.fb.app.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_fb_id")
	private String userFbId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="profile_picture_url")
	private String profilePictureUrl;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_photos",
	           joinColumns = @JoinColumn(name = "user_fb_id", referencedColumnName = "user_fb_id"),
	           inverseJoinColumns = @JoinColumn(name = "fb_link", referencedColumnName = "fb_link"))
	private Set<Photo> photos;

	public User() {
		super();
	}

	public User(String userFbId, String name, String gender, String profilePictureUrl, Set<Photo> photos) {
		super();
		this.userFbId = userFbId;
		this.name = name;
		this.gender = gender;
		this.profilePictureUrl = profilePictureUrl;
		this.photos = photos;
		this.photos.forEach(x -> x.getUsers().add(this));
	}
	
	public String getUserFbId() {
		return userFbId;
	}

	public void setUserFbId(String userFbId) {
		this.userFbId = userFbId;
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

	public Set<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}

	@Override
	public String toString() {
		return "User [userFbId=" + userFbId + ", name=" + name + ", gender=" + gender + ", profilePictureUrl="
				+ profilePictureUrl + ", photos=" + photos + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((profilePictureUrl == null) ? 0 : profilePictureUrl.hashCode());
		result = prime * result + ((userFbId == null) ? 0 : userFbId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (profilePictureUrl == null) {
			if (other.profilePictureUrl != null)
				return false;
		} else if (!profilePictureUrl.equals(other.profilePictureUrl))
			return false;
		if (userFbId == null) {
			if (other.userFbId != null)
				return false;
		} else if (!userFbId.equals(other.userFbId))
			return false;
		return true;
	}

}
