package com.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableTransactionManagement
// @RestController = @Controller + @ResponseBody
// Spring Boot核心注解，用于开启自动配置
@SpringBootApplication
// 扫描 mybatis mapper 包路径
@MapperScan("com.springboot.*.dao")
// 开启定时任务
@EnableScheduling
// 开启异步调用方法
@EnableAsync
public class demoApplication {

	@RequestMapping("/")
	String index() {
		return "Hello Spring Boot";
	}

	public static void main(String[] args) {
		SpringApplication.run(demoApplication.class, args);
		System.out.println(
				"ヾ(◍°∇°◍)ﾉﾞ    bootdo启动成功      ヾ(◍°∇°◍)ﾉﾞ\n" + " ______                    _   ______            \n"
						+ "|_   _ \\                  / |_|_   _ `.          \n"
						+ "  | |_) |   .--.    .--. `| |-' | | `. \\  .--.   \n"
						+ "  |  __'. / .'`\\ \\/ .'`\\ \\| |   | |  | |/ .'`\\ \\ \n"
						+ " _| |__) || \\__. || \\__. || |, _| |_.' /| \\__. | \n"
						+ "|_______/  '.__.'  '.__.' \\__/|______.'  '.__.'  ");
	}

	@RequestMapping("/start")
	public String Stater() {
		return "开始测试";
	}

}