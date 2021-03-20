package com.paternocode.springbootbackend.model;

public class HeroSearchCriteria {
	private String searchVal;
	private SearchCategory category;
	
	public String getSearchVal() {
		return searchVal;
	}
	public void setSearchVal(String searchVal) {
		this.searchVal = searchVal;
	}
	public SearchCategory getCategory() {
		return category;
	}
	public void setCategory(SearchCategory category) {
		this.category = category;
	}
}
