package net.kbw.domain;

import java.util.Objects;

public class Serch {
	public String getKeyword(String keyword) {
		
		
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	String keyword;
	
	
 public boolean Serch(String keyword) {
	 
	 if(keyword==null) {
		 return false;
	 }

	return true;
	 
 }
	
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((keyword == null) ? 0 : keyword.hashCode());
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
	Serch other = (Serch) obj;
	if (keyword == null) {
		if (other.keyword != null)
			return false;
	} else if (!keyword.equals(other.keyword))
		return false;
	return true;
}


}