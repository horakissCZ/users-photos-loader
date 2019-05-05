package com.fb.app.dto;

public class ReactionDto {
	
	private String  id;
	private String  type;
	private Integer numOfReactions;
	private PhotoDto photo;
	
	public ReactionDto() {
		super();
	}
	
	public ReactionDto(String type, Integer numOfReactions) {
		super();
		this.type = type;
		this.numOfReactions = numOfReactions;
	}

	public ReactionDto(String type, Integer numOfReactions, PhotoDto photo) {
		super();
		this.type = type;
		this.numOfReactions = numOfReactions;
		this.photo = photo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getNumOfReactions() {
		return numOfReactions;
	}

	public void setNumOfReactions(Integer numOfReactions) {
		this.numOfReactions = numOfReactions;
	}

	public PhotoDto getPhoto() {
		return photo;
	}

	public void setPhoto(PhotoDto photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "ReactionDto [id=" + id + ", type=" + type + ", numOfReactions=" + numOfReactions + "]";
	}

}
