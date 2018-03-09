package com.itfollowme.quiz.uid;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.baidu.fsg.uid.impl.DefaultUidGenerator;
import com.baidu.fsg.uid.worker.DisposableWorkerIdAssigner;
import com.itfollowme.quiz.uid.service.UIDService;

@SpringBootApplication
@ComponentScan(basePackages= {"com.itfollowme.quiz.uid","com.baidu.fsg.uid"})
@DubboComponentScan(basePackages="com.itfollowme.quiz.uid")
@MapperScan(basePackages="com.baidu.fsg.uid.worker.dao")	
public class UIDApplication {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext cactx = SpringApplication.run(UIDApplication.class, args);
		UIDService service = cactx.getBean(UIDService.class);
		System.out.println(service.getDefaultUID());
		
	}
	
	@Bean
	public DisposableWorkerIdAssigner disposableWorkerIdAssigner() {
		return new DisposableWorkerIdAssigner();
	}
	
	@Bean
	public DefaultUidGenerator defaultUidGenerator() {
		
		DefaultUidGenerator defaultUidGenerator = new DefaultUidGenerator();
		defaultUidGenerator.setWorkerIdAssigner(disposableWorkerIdAssigner());
		defaultUidGenerator.setTimeBits(29);
		defaultUidGenerator.setWorkerBits(21);
		defaultUidGenerator.setSeqBits(13);
		defaultUidGenerator.setEpochStr("2017-03-09");
		return defaultUidGenerator;
		
	}
	
//	@Bean
//	public DataSource dataSource() {
//		return DruidDataSourceBuilder.create().build();
//	}
//	
//	@Bean
//	public SqlSessionFactoryBean sqlSessionFactory() {
//		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//		factoryBean.setDataSource(dataSource());
//		return factoryBean;
//	}
}
