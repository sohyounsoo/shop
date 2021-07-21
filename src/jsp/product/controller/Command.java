package jsp.product.controller;

import javax.servlet.*;
import javax.servlet.http.*;

/**
	template 패턴과 Command 패턴이 사용됨
*/
public abstract class Command {
	private Command nextCommand;
	
	//실질적으로 수행하는 메서드는 하위 클래스에서 override 해주어야한다.
	//template메서드
	public String doExecute(HttpServletRequest request,HttpServletResponse response)
		throws Exception {
		String next;
		next = execute(request,response);
		if(nextCommand != null) 
			next = nextCommand.doExecute(request,response);

		return next;
	}

	//추상 메서드
	//hook 메서드 : 이 객체의 기본적인 실행방식을 정의해놓은 메서드
	public abstract String execute(HttpServletRequest request,HttpServletResponse response)
		throws Exception ;

	public void setNextCommand(Command cmd) {
		this.nextCommand = cmd;
	}
	public Command getNextCommand() {
		return nextCommand;
	}

}