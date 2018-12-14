package com.example.demo.contorller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.demo.pojo.User;

@Controller
public class Controller1 {
	private List<User> list;
	// 0 代表编辑
	// 1代表新增
	private int flag = 0;
	
	private String url ;

	{
		list = new ArrayList<User>();
		for (int i = 0; i < 10; i++) {
			User u = new User();
			u.setAge(u.getAge() + i);
			list.add(u);
		}
	}

	@RequestMapping("/hello")
	public String hello() {
		System.out.println("jinru");
		return "guojihua";
	}

	@RequestMapping("/getList")
	public ModelAndView getList() {
		System.out.println("liebiao");
		ModelAndView mav = new ModelAndView();
		mav.addObject("userList", list);
		mav.setViewName("NewFile1");
		return mav;
	}

	@RequestMapping("/addUser1")
	public String addUser1(Model m) {
		flag = 1;
		m.addAttribute("flag", flag);
		User u = new User();
		m.addAttribute("user", u);
		return "addUser";
	}

	@RequestMapping("/editUser1")
	public String editUser1(Model m, @RequestParam int age) {
		flag = 0;
		m.addAttribute("flag", flag);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getAge() == age) {
				m.addAttribute("user", list.get(i));
			}
		}
		return "addUser";
	}

	@RequestMapping("/addUser2")
	public String addUser2(@Validated @ModelAttribute User user, BindingResult result, Model model,MultipartFile file,Model m) throws IOException {
		System.out.println("addUser");
		// 验证
		List<FieldError> errors = result.getFieldErrors();
		List<String> ms = new ArrayList<String>();
		if (errors.size() > 0) {
			return "addUser";
		}
		// 新增逻辑
		url = getPic(file);
		System.out.println(url);
		User u = new User();
		for(int i=0;i<list.size();i++) {
			if(u.getAge()==list.get(i).getAge()) {
				u.setAge(list.get(list.size()-1).getAge()+1);
			}
		}
		u.setName(user.getName());
		u.setPhoneNumber(user.getPhoneNumber());
		if(url!=null) {
			u.setUrl(url);
		}
		list.add(u);
		System.out.println(u);
		return "redirect:getList";
	}

	@RequestMapping("/delUser")
	public String delUser(@RequestParam int age) {
		System.out.println("删除" + age);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getAge() == age) {
				list.remove(i);
			}
		}
		return "redirect:getList";
	}

	@RequestMapping("/editUser2")
	public String editUser2(@Validated @ModelAttribute User user, BindingResult result, Model model,MultipartFile file) throws IOException {
		System.out.println("编辑" + user.getAge() + "flag");
		// 后台验证
		List<FieldError> errors = result.getFieldErrors();
		List<String> ms = new ArrayList<String>();
		if (errors.size() > 0) {
			return "addUser";
		}
		// 业务逻辑 
		url = getPic(file);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getAge() == user.getAge()) {
				list.get(i).setName(user.getName());
				list.get(i).setPhoneNumber(user.getPhoneNumber());
				if(url!=null) {
					list.get(i).setUrl(url);
				}
			}
		}
		return "redirect:getList";
	}
	// 文件上传
	/*
	 * @RequestMapping("/fileUpload") public String fileUpload4(MultipartFile
	 * file,HttpServletRequest request,HttpServletResponse reponse,HttpSession
	 * session) throws IllegalStateException, IOException {
	 * System.out.println("文件上传"); String url =file.getName();
	 * System.out.println(url); file.transferTo(new File("D:\\"+url));
	 * session.setAttribute("url", url); return "adduser" ; }
	 */

	/*
	 * public Model errorMessage(String message) { if(message.equals("0")) { return
	 * "年龄不能大于一百岁" ; }else if (message.equals("1")){ return "年龄不能少于0岁" ; }else
	 * if(message.equals("2")) { return "电话不能为空" ; } return "" ; }
	 */

	// 文件上传
	
	public String getPic(MultipartFile file) throws IOException {
		String userPic = "default.png";
		System.out.println(file);
		if(file != null) {
		if (!file.getOriginalFilename().equals("") && file.getOriginalFilename() != null) {
			String filePathAndName = "D:\\test\\" + file.getOriginalFilename();
			File sfile = new File(filePathAndName);
			if (!sfile.exists()) {
				sfile.createNewFile();
			}
			file.transferTo(sfile);
			userPic = file.getOriginalFilename();
		} else {
			userPic = "default.png";
		}
		}
		return userPic;
	}
	
	/*@RequestMapping("/fileUpload")
	public String fileupload1(MultipartFile file,Model m) throws IOException {
		url = getPic(file);
		m.addAttribute("url",url);
		return "addUser" ;
	}*/
	
	
}