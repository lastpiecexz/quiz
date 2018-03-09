package com.itfollowme.quiz.uid.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.baidu.fsg.uid.UidGenerator;
import com.itfollowme.quiz.uid.service.UIDService;

@Component
@Service(version="1.0.0")
public class UIDServiceImpl implements UIDService{

	@Resource
	private UidGenerator uidGenerator;
	
	@Override
	public Long getDefaultUID() {
		// TODO Auto-generated method stub
		return uidGenerator.getUID();
	}

	@Override
	public Long getCachedUID() {
		// TODO Auto-generated method stub
		return null;
	}

}
