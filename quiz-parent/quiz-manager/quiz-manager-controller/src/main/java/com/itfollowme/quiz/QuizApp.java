package com.itfollowme.quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.spring.JSONPResponseBodyAdvice;


@SpringBootApplication
@ComponentScan(basePackages="com.itfollowme.quiz")
@DubboComponentScan(basePackages="com.itfollowme.quiz")
public class QuizApp {

	
	public static void main(String[] args) {
		SpringApplication.run(QuizApp.class, args);
	}
	
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		FastJsonConfig config = new FastJsonConfig();
		config.setSerializerFeatures(SerializerFeature.PrettyFormat);
		converter.setFastJsonConfig(config);
		return new HttpMessageConverters(converter);
	}
	
	@Bean
	public ResponseBodyAdvice<Object> fastJsonpResponseBodyAdvice() {
		JSONPResponseBodyAdvice responseBodyAdvice = new JSONPResponseBodyAdvice();
		return responseBodyAdvice;
	}


}
