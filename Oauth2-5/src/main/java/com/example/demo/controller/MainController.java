package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.Msg;
import com.example.demo.security.CustomMobileAuthenticationProvider;
import com.example.demo.utils.RandUtil;
import com.zhenzi.sms.ZhenziSmsClient;

/**
 * 主页控制器.
 *
 */
@Controller
public class MainController {

	@GetMapping("/")
	public String root() {
		return "redirect:/index";
	}

	@GetMapping("/index")
	public String index(Model model) {
		Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");
		model.addAttribute("msg", msg);
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		model.addAttribute("errorMsg", "登陆失败，用户名或者密码错误！");
		return "login";
	}

	@GetMapping("/403")
	public String accesssDenied() {
		return "403";
	}

	@Autowired
	CustomMobileAuthenticationProvider customMobileAuthenticationProvider;

	@GetMapping("/getCode")
	@ResponseBody
	public String getValidateCode(String phoneNum, HttpSession session) {
		String apiUrl = "https://sms_developer.zhenzikj.com";
		String appId = "100250";
		String appSecret = "a4b36fe5-a2db-4d79-8b0d-593dbc1bb961";
		ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
		String validateCode = RandUtil.getRandNum();
		String res;
		try {
			res = client.send(phoneNum, "您的验证码为：" + validateCode);
			customMobileAuthenticationProvider.setValidateCode(validateCode);
		} catch (Exception e) {
			res = "{'code':1,'data':'发送失败'}";
			customMobileAuthenticationProvider.setValidateCode("000000");
		}
		return res;
	}

}
