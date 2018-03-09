package com.itfollowme.quiz.controller;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itfollowme.quiz.dao.QuizDao;
import com.itfollowme.quiz.pojo.Quiz;
import com.itfollowme.quiz.uid.service.UIDService;


@RequestMapping("/quiz")
@RestController
public class QuizController {
	
	@Resource
	private QuizDao quizDao;
	
	@Reference(version = "1.0.0", interfaceClass=com.itfollowme.quiz.uid.service.UIDService.class)
	public UIDService uidService;
	
	@PostMapping("/create")
	public String addQuiz(Quiz quiz) {
		return "OK";
	}
	
	@GetMapping("/uid")
	public Long getUID() {
		return uidService.getDefaultUID();
	}

	@GetMapping("/list")
	public Page<Quiz> list(@RequestParam(value="page",defaultValue="1") Integer page, @RequestParam(value="size",defaultValue="20")Integer size ) {
		Sort sort = new Sort(Sort.Direction.DESC,"id");
		return quizDao.findAll(PageRequest.of(page, size, sort));
	}
}
