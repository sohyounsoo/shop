package jsp.product.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.security.*;

/**
	유일한 트랜잭션 토큰을 하나 생성한 후에 사용자의session과 request 에다 16진수로 바꾸어서 저장한다.
	사용자의 세션ID와 현재시스템시간을 합쳐서 만들어 낸 MD5타입의 메시지 digest(checksum의 일종)로서
	세션ID와 시스템시간으로 만들어지기 때문에 생성되는 모든 토큰이 사용자마다 유일해질 수 있다.

	어떤 데이타로 부터 MessageDigest를 구하는 방법은 Hash를 사용한다.
	update() : 데이타를 해쉬한다.
	digest() : 바이트배열로 해쉬를 반환한다.
*/
public class CommandToken {
	public static void set(HttpServletRequest req) {
		HttpSession session = req.getSession();
		long systime = System.currentTimeMillis();
		byte[] time = new Long(systime).toString().getBytes();
		byte[] id = session.getId().getBytes();

		try{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(id);
			md5.update(time);
			String token = toHex(md5.digest());
			req.setAttribute("token",token);
			session.setAttribute("token",token);
		}catch(Exception e) {
		}
	}
/*
	request 와 session에 저장되어 있는 토큰을 찾아내어 서로 같은지 비교한다
	같으면 true을 return 아니면 false을 return 한다.
*/
	public static boolean isValid(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String requestToken = req.getParameter("token");
		String sessionToken = (String)session.getAttribute("token");

		System.out.println("aaa :" + (String)req.getAttribute("token"));
		System.out.println("requestToken : " + requestToken);
		System.out.println("sessionToken : " + sessionToken);
		if(requestToken == null || sessionToken == null)
			return false;
		else
			return requestToken.equals(sessionToken);

	}
	private static String toHex(byte[] digest) {
		StringBuffer buf = new StringBuffer();
		for(int i=0;i<digest.length;i++)
			buf.append(Integer.toHexString((int)digest[i]& 0x00ff));
		return buf.toString();
	}
}