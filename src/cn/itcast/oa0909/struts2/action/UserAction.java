package cn.itcast.oa0909.struts2.action;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.dispatcher.Dispatcher;
import org.apache.struts2.json.JSONResult;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.DefaultActionInvocation;

import cn.itcast.oa0909.domain.Department;
import cn.itcast.oa0909.domain.Post;
import cn.itcast.oa0909.domain.User;
import cn.itcast.oa0909.service.DepartmentService;
import cn.itcast.oa0909.service.PostService;
import cn.itcast.oa0909.service.UserService;
import cn.itcast.oa0909.struts2.action.base.BaseAction;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends BaseAction<User>{
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="departmentService")
	private DepartmentService departmentService;
	
	@Resource(name="postService")
	private PostService postService;
	
	private Long did;
	
	private Long[] pids;
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public Long getDid() {
		return did;
	}

	public void setDid(Long did) {
		this.did = did;
	}

	public Long[] getPids() {
		return pids;
	}

	public void setPids(Long[] pids) {
		this.pids = pids;
	}

	public String getAllUser(){
		Collection<User> userList = this.userService.getAllUser();
		ActionContext.getContext().getValueStack().push(userList);
		return listAction;
	}
	
	public String addUI(){
		/**
		 * 1、把部门表的所有的数据查询出来
		 * 2、把岗位表的数据查询出来
		 * 3、跳转到增加页面
		 */
		Collection<Department> departmentList = this.departmentService.getAllDepartment();
		ActionContext.getContext().put("departmentList", departmentList);
		Collection<Post> postList = this.postService.getAllPost();
		ActionContext.getContext().put("postList", postList);
		return addUI;
	}
	
	public String add(){
		/**
		 * 如何获取页面中的数据
		 *    *  如果页面中的数据来源于一张表，直接用模型驱动获取就可以了
		 *    *  如果页面中的数据来源于多张表，则可以采用模型驱动结合属性驱动的方式
		 */
		/**
		 * 1、创建一个user对象
		 * 2、把模型驱动的值赋值给user对象
		 * 3、根据 did提取出该部门
		 * 4、根据pids提取出岗位
		 * 5、建立user对象和部门和岗位之间的关系
		 * 6、执行save操作
		 */
		User user = new User();
		//一般属性的赋值
		BeanUtils.copyProperties(this.getModel(), user);
		//建立user与department之间的关系
		Department department = this.departmentService.getDepartmentById(this.did);
		user.setDepartment(department);
		//建立user与posts之间的关系
		Set<Post> posts = this.postService.getPostsByIds(this.pids);
		user.setPosts(posts);
		this.userService.saveUser(user);
		return action2action;
	}
	/**
	 * 如果是页面跳转到action,在action中有一个Long[]数组，struts2的拦截器可以自动赋值
	 * 但是如果是回显数据，必须在程序中创建数组的对象
	 * @return
	 */
	public String updateUI(){
		/**
		 * 1、值的回显
		 *    * 用户的一般属性的回显
		 *    * 部门的回显
		 *    * 岗位的回显
		 * 2、 把部门的数据全部提取出来
		 * 3、把岗位的数据全部提取出来
		 */
		//把用户放入到对象栈中
		User user = this.userService.getUserById(this.getModel().getUid());
		ActionContext.getContext().getValueStack().push(user);
		this.did = user.getDepartment().getDid();
		Set<Post> posts = user.getPosts();
		int index = 0;
		this.pids = new Long[posts.size()];
		for(Post post:posts){
			this.pids[index] = post.getPid();
			index++;
		}
		//把部门表和岗位表的数据提取出来
		Collection<Department> departmentList = this.departmentService.getAllDepartment();
		ActionContext.getContext().put("departmentList", departmentList);
		Collection<Post> postList = this.postService.getAllPost();
		ActionContext.getContext().put("postList", postList);
		return updateUI;
	}
	
	public String update(){
		/**
		 * 1、利用模型驱动获取用户的一般数据
		 * 2、利用属性驱动获取最新的did和pids
		 * 3、根据用户的id提取出user对象
		 * 4、把模型驱动的值复制到user对象中
		 * 5、重新建立user对象和岗位和部门之间的关系
		 */
		//一般属性的赋值
		User user = this.userService.getUserById(this.getModel().getUid());
		BeanUtils.copyProperties(this.getModel(), user);
		//重新建立user和部门之间的关系
		Department department = this.departmentService.getDepartmentById(this.did);
		user.setDepartment(department);
		//重新建立user与岗位之间的关系
		Set<Post> posts = this.postService.getPostsByIds(this.pids);
		user.setPosts(posts);
		this.userService.updateUser(user);
		return action2action;
	}
	
	public String checkUsername(){
		int a = 1/0;
		User user = this.userService.getUserByName(this.getModel().getUsername());
		if(user==null){
			this.message = "该用户名可以使用";
		}else{
			this.message = "该用户名已经存在";
		}
		return SUCCESS;
	}
}
