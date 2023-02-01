package net.kbw.domain;

import java.util.Objects;


//검색을 한지 안한지 확인하는 클래스//
public class Serch {
	public String getKeyword(String keyword) {

		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}//

	String keyword;

	public boolean Serch(Object keyword) {

		if (keyword == null) {
			return false;
		}

		return true;

	}



}