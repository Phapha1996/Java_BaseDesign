package org.fage.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fage.domain.User;
import org.fage.service.in.UserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 
 * @author Caizhf
 * @date 2017年4月22日下午4:18:34
 * @version v.0.1
 * <p>Description:初步测试Hibernate超类Dao </p>
 *
 */
@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport{
	private final static Log log = LogFactory.getLog(UserAction.class);

	@Resource(name="userService")
	private UserService userService;
	
	public String add() throws Exception{
		log.info("尝试增加一个群众");
		userService.add(new User("梁媛",new Date()));
		return "success";
	}
	
	public String delete() throws Exception{
		userService.delete(1);
		return "success";
	}
	
	public String update() throws Exception{
		userService.saveOrUpdate(new User(1,"蔡智法",new Date()));
		return "success";
	}
	
	public String get() throws Exception{
		User user = userService.findById(1);
		log.info(user.getId()+"|"+user.getName()+"|"+user.getBirthday());
		return "success";
	}
}
