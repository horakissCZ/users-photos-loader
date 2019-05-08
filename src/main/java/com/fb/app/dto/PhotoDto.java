package com.fb.app.dto;

import java.util.Set;

public class PhotoDto {
	private String fbLink;
	private String imageFileUrl;
	private String albumName;
	private Set<ReactionDto> reactions;
	
	public PhotoDto() {
		super();
	}

	public PhotoDto(String fbLink, String imageFileUrl, String albumName, Set<ReactionDto> reactions) {
		super();
		this.fbLink = fbLink;
		this.imageFileUrl = imageFileUrl;
		this.albumName = albumName;
		this.reactions = reactions;
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

	public Set<ReactionDto> getReactions() {
		return reactions;
	}

	public void setReactions(Set<ReactionDto> reactions) {
		this.reactions = reactions;
	}

	@Override
	public String toString() {
		return "PhotoDto [fbLink=" + fbLink + ", imageFileUrl=" + imageFileUrl + ", albumName=" + albumName
				+ ", reactions=" + reactions + "]";
	}
}
