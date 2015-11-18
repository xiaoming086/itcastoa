package cn.itcast.oa0909.struts2.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa0909.domain.User;
import cn.itcast.oa0909.service.LoginService;
import cn.itcast.oa0909.struts2.action.base.BaseAction;
import cn.itcast.oa0909.utils.OAUtils;

@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends BaseAction<User>{
	@Resource(name="loginService")
	private LoginService loginService;
	
	public String login(){
		User user = this.loginService.checkUAndP(this.getModel().getUsername(),this.getModel().getPassword());
		if(user!=null){//成功
			OAUtils.putUser2Session(user);
			return "index";
		}else{//失败
			//自己实现
			return null;
		}
	}
}
