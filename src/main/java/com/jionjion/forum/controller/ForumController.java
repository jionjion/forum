package com.jionjion.forum.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jionjion.forum.bean.EsForum;
import com.jionjion.forum.bean.User;
import com.jionjion.forum.repository.EsForumRepository;
import com.jionjion.forum.repository.UserRepository;

/**
 * @author JionJion
 * 	博客的控制器
 */
@RestController
@RequestMapping("/pages")
public class ForumController {

	@Autowired
	EsForumRepository esForumRepository;

	@RequestMapping
	public List<EsForum> list(@RequestParam(value = "title") String title,
			@RequestParam(value = "summary") String summary, @RequestParam(value = "content") String content,
			@RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		// 分页
		Pageable pageable = new PageRequest(pageIndex, pageSize);
		// 分页查询
		Page<EsForum> page = esForumRepository.findDistinctEsForumByTitleContainingOrSummaryContainingOrContentContaining(title, summary,
				content, pageable);
		return page.getContent();
	}
}
