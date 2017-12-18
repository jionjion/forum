package com.jionjion.forum.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jionjion.forum.bean.EsForum;
import com.jionjion.forum.repository.EsForumRepository;

/**
 * 	论坛的控制器
 * @author JionJion
 */
@RestController
@RequestMapping("/forum")
public class ForumController {

	@Autowired
	EsForumRepository esForumRepository;

	/**
	 * 	匹配搜索功能
	 * @param title 	标题匹配
	 * @param summary	摘要匹配
	 * @param content	内容匹配
	 * @param pageIndex	分页起始条数
	 * @param pageSize	分页每页条数
	 * @return			分页模糊查询结果
	 */
	@RequestMapping("/pages")
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
	
	
	/**
	 * 	根据关键词和排序查询
	 * @param order	排序
	 * @param tag 	标签
	 * @return
	 */
	public String listForum(@RequestParam(value = "order", required = false, defaultValue = "new") String order,
			@RequestParam(value = "keyword", required = false,defaultValue="") String keyword) {
		
		System.out.println("收到查询条件" + order + keyword);
		return "redirect:/index?order=" + order + "&keyword" + keyword;
	}
}
