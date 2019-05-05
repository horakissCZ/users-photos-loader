package com.fb.app.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "photos")
public class Photo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="fb_link")
	private String fbLink;
	
	@Column(name="image_file_url")
	private String imageFileUrl;
	
	@Column(name="album_name")
	private String albumName;
	
	@OneToMany(
			mappedBy = "photo",
			cascade = CascadeType.ALL,
	        orphanRemoval = true
	)
	private Set<Reaction> reactions;
	
	@ManyToMany(
			mappedBy = "photos",
			cascade = CascadeType.ALL
	)
	private Set<User> users = new HashSet<>();

	public Photo() {
		super();
	}

	public Photo(String fbLink, String imageFileUrl, String albumName, Set<User> users, Set<Reaction> reactions) {
		super();
		this.fbLink = fbLink;
		this.imageFileUrl = imageFileUrl;
		this.albumName = albumName;
		this.users = users;
		this.reactions = reactions;
		this.reactions.stream().forEach(x -> x.setPhoto(this));
	}
	
	public String getFbLink() {
		return fbLink;
	}

	public void setFbLink(String fbLink) {
		this.fbLink = fbLink;
	}

	public String getImageFileUrl() {
		return imageFileUrl;
	}

	public void setImageFileUrl(String imageFileUrl) {
		this.imageFileUrl = imageFileUrl;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Reaction> getReactions() {
		return reactions;
	}

	public void setReactions(Set<Reaction> reactions) {
		this.reactions = reactions;
		this.reactions.stream().forEach(x -> x.setPhoto(this));
	}

	@Override
	public String toString() {
		return "Photo [imageFileUrl=" + imageFileUrl + ", albumName=" + albumName + ", users=" + users + ", reactions="
				+ reactions + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((albumName == null) ? 0 : albumName.hashCode());
		result = prime * result + ((fbLink == null) ? 0 : fbLink.hashCode());
		result = prime * result + ((imageFileUrl == null) ? 0 : imageFileUrl.hashCode());
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
		Photo other = (Photo) obj;
		if (albumName == null) {
			if (other.albumName != null)
				return false;
		} else if (!albumName.equals(other.albumName))
			return false;
		if (fbLink == null) {
			if (other.fbLink != null)
				return false;
		} else if (!fbLink.equals(other.fbLink))
			return false;
		if (imageFileUrl == null) {
			if (other.imageFileUrl != null)
				return false;
		} else if (!imageFileUrl.equals(other.imageFileUrl))
			return false;
		return true;
	}

}