package com.fb.app.service.fb.types;

import com.restfb.Facebook;

public class Summary {
	

	@Facebook(value = "total_count")
	private Long totalCount;

	public Summary() {
		super();
	}

	public Summary(Long totalCount) {
		super();
		this.totalCount = totalCount;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
	
}