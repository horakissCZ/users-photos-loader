package com.fb.app.service.fb.types;

import com.restfb.Facebook;
import com.restfb.types.Photo;

public class PhotoWithReactionsType extends Photo {

	private static final long serialVersionUID = 1L;

	@Facebook(value="none")
	private ReactionSummary none;
	
	@Facebook(value="like")
	private ReactionSummary like;
	
	@Facebook(value="love")
	private ReactionSummary love;
	
	@Facebook(value="wow")
	private ReactionSummary wow;

	@Facebook(value="haha")
	private ReactionSummary haha;

	@Facebook(value="sad")
	private ReactionSummary sad;

	@Facebook(value="angry")
	private ReactionSummary angry;

	@Facebook(value="thankful")
	private ReactionSummary thankful;

	@Facebook(value="pride")
	private ReactionSummary pride;

	
	public PhotoWithReactionsType() {
		super();
	}

	public PhotoWithReactionsType(ReactionSummary none, ReactionSummary like, ReactionSummary love,
			ReactionSummary wow, ReactionSummary haha, ReactionSummary sad, ReactionSummary angry,
			ReactionSummary thankful, ReactionSummary pride) {
		super();
		this.none = none;
		this.like = like;
		this.love = love;
		this.wow = wow;
		this.haha = haha;
		this.sad = sad;
		this.angry = angry;
		this.thankful = thankful;
		this.pride = pride;
	}

	public ReactionSummary getNone() {
		return none;
	}

	public void setNone(ReactionSummary none) {
		this.none = none;
	}

	public ReactionSummary getLike() {
		return like;
	}

	public void setLike(ReactionSummary like) {
		this.like = like;
	}

	public ReactionSummary getLove() {
		return love;
	}

	public void setLove(ReactionSummary love) {
		this.love = love;
	}

	public ReactionSummary getWow() {
		return wow;
	}

	public void setWow(ReactionSummary wow) {
		this.wow = wow;
	}

	public ReactionSummary getHaha() {
		return haha;
	}

	public void setHaha(ReactionSummary haha) {
		this.haha = haha;
	}

	public ReactionSummary getSad() {
		return sad;
	}

	public void setSad(ReactionSummary sad) {
		this.sad = sad;
	}


	public ReactionSummary getAngry() {
		return angry;
	}


	public void setAngry(ReactionSummary angry) {
		this.angry = angry;
	}

	public ReactionSummary getThankful() {
		return thankful;
	}

	public void setThankful(ReactionSummary thankful) {
		this.thankful = thankful;
	}

	public ReactionSummary getPride() {
		return pride;
	}

	public void setPride(ReactionSummary pride) {
		this.pride = pride;
	}
	
}