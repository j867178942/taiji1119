package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Utils.GetFileName;
import com.example.demo.Utils.SpringContextUtils;
import com.example.demo.pojo.User;


@Controller
public class Controller1 {
	private List<User> list=null ;
	private long url ;
	
	@RequestMapping("/hello")
	public String hello() {
		System.out.println("123123123");
		return "fileUpload" ;
	}
	
	@RequestMapping("/")
	public String index1() {
		System.out.println("1231234444444444444");
		return "success" ;
	}
	
	@RequestMapping("/getList")
	public String getList(HttpServletRequest request, HttpSession session){
		 list = new ArrayList<User>();
		for(int i =0;i<10;i++) {
			User  user1 =(User)	SpringContextUtils.getBean(User.class);
			user1.setAge(i+10);
			list.add(user1);
		}
		for(User u:list) {
			System.out.println(u);
		}
		session.setAttribute("userList", list);
		return  "index" ;
	}
    @RequestMapping("/fileUpload4")
    public String fileUpload4(MultipartFile file,HttpServletRequest request,HttpServletResponse reponse,HttpSession session) throws IllegalStateException, IOException {
    	System.out.println("文件上传");
    	url =  System.currentTimeMillis() ;
    	file.transferTo(new File("D:\\"+url));
    	session.setAttribute("url", url);
    	return "adduser" ;
    }
    
    @RequestMapping("/delUser")
    public String delUser(@RequestParam int age) {
    	System.out.println(age);
    	for(int i=0;i<list.size();i++) {
    		if(age == list.get(i).getAge()) {
    			list.remove(i);
    		}
    	}
    	return "index";
    }
    
    @RequestMapping("/addS1")
    public String addUser1() {
    	System.out.println("跳转到上传文件页面");
    	return "touxiang";
    }
    
    
    @RequestMapping("/addS2")
    public String addUser2(MultipartFile file,HttpServletRequest request,HttpServletResponse reponse,HttpSession session) throws IllegalStateException, IOException {
    	System.out.println("文件上传并生成url返回");
    	url =  System.currentTimeMillis() ;
    	file.transferTo(new File("D:\\"+url));
    	session.setAttribute("url", url);
    	return "adduser" ;
    }
    
    @RequestMapping("/addS3")
    public String addUser3(HttpSession session) {
    	System.out.println("添加学生");
    	int age = list.get(list.size()-1).getAge()+1 ;
    	User  user1 =(User)	SpringContextUtils.getBean(User.class);
    	user1.setAge(age);
    	list.add(user1);
    	session.setAttribute("userList",list);
    	return "index";
    }
    
}
