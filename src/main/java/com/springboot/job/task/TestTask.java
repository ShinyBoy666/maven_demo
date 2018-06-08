package com.springboot.job.task;

import org.springframework.stereotype.Component;
import com.springboot.common.util.DateUtil;

@Component
public class TestTask {

	// 定义每过3秒执行任务
	// @Scheduled(fixedRate = 3000)
	// @Scheduled(cron = "4-40 * * * * ?")
	public void reportCurrentTime() {
		System.out.println("现在时间：" + DateUtil.getDateFormat(DateUtil.getNowDate(), DateUtil.DEFAULT_DATE_FORMAT));
	}

	public void test(String params) {
		System.out.println("我是带参数的test方法，正在被执行，参数为：" + params);
	}

	public void test1() {
		System.out.println("我是不带参数的test1方法，正在被执行");
	}

}