package net.kbw.wook;

import javax.servlet.http.HttpSession;

import net.kbw.domain.User;

public class HttpSessionUtils {
	
	public static final String USER_SESSION_KEY="suser";
	public static boolean isLoginUser(HttpSession session) {
		
		
		Object suser = session.getAttribute(USER_SESSION_KEY);
		if(suser == null) {
			
			return false;
		}
		return true;//
		
	}
	
	public static User getUserFromSession(HttpSession session) {
		if(!isLoginUser(session)) {
			return null;
		}
		
		
		return (User)session.getAttribute(USER_SESSION_KEY);
		
	}

}
