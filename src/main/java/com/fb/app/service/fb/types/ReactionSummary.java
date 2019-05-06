package com.fb.app.service.fb.types;

import com.restfb.Facebook;

public class ReactionSummary {

	@Facebook
	private Summary summary;
	
	public ReactionSummary() {
		super();
	}

	public ReactionSummary(Summary summary) {
		super();
		this.summary = summary;
	}

	public Summary getSummary() {
		return summary;
	}

	public void setSummary(Summary summary) {
		this.summary = summary;
	}
	
}