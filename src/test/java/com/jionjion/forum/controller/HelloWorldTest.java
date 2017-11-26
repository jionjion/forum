package com.jionjion.forum.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloWorldTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testHello() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders			//请求构建,构造者方法
				.get("/hello")							//访问路径
				.accept(MediaType.APPLICATION_JSON))	//接收类型
				.andExpect(status().isOk())				//响应状态
				.andExpect(content().string(equalTo("Hello World")));	//响应字符串是否为...
	}
}
