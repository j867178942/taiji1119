package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Yanshengma;
import com.example.demo.service.YanzhengService;

@Controller
public class MainController {
	@Autowired
	private YanzhengService yzs;
	
	
	@RequestMapping("/")
	public String a(Model m) {
		Yanshengma yzm1 = new Yanshengma();
		yzm1.setYanzhengma("123456");
		m.addAttribute("msg",yzm1);
		return "NewFile";
	}
	
	@RequestMapping("/getNumber")
	public String ab(Model m) {
		Yanshengma yzm =  yzs.getYanshengma();
		m.addAttribute("msg",yzm);
		return "NewFile";
	}
	
	@Autowired  
	private StringRedisTemplate stringRedisTemplate;
	
	@RequestMapping("/delkey")
	public String abc(Model m) {
		if(stringRedisTemplate.hasKey("number")) {
			stringRedisTemplate.delete("number");
			System.out.println(stringRedisTemplate.opsForList().size("number"));
		}
		Yanshengma yzm =  yzs.getYanshengma();
		m.addAttribute("msg",yzm);
		return "NewFile";
	}
}
