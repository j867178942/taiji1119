package com.example.demo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Yanshengma;
import com.example.demo.utils.RedisCacheManager;



@Service
public class YanzhengServiceImpl implements YanzhengService {
	
	@Autowired  
	private StringRedisTemplate stringRedisTemplate;
	
	@Override
	public Yanshengma getYanshengma() {
		
		//用于存储信息；
		String number1 =  "123456" ;
		Yanshengma yanzhengma = new Yanshengma();
		yanzhengma.setYanzhengma(number1);
		if(stringRedisTemplate.opsForList().size("number") ==2) {
			stringRedisTemplate.expire("number", 600, TimeUnit.SECONDS);
		}
		if(stringRedisTemplate.opsForList().size("number") >3) {
			stringRedisTemplate.delete("number");
			yanzhengma.setYanzhengma("验证码已经多余三次，等60秒钟后再次获取");
		}else {
		 stringRedisTemplate.opsForList().leftPush("number","123456");
		 System.out.println(stringRedisTemplate.opsForList().index("number", 0));
		 System.out.println("number的长度=="+stringRedisTemplate.opsForList().size("number"));
		 System.out.println("剩余的时间=="+stringRedisTemplate.getExpire("number"));
		}
		return yanzhengma;
	}

}
