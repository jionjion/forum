package com.jionjion.forum.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.jionjion.forum.bean.EsForum;

/**
 * @author JionJion
 *	文档的资源接口类
 */
public interface EsForumRepository extends ElasticsearchRepository<EsForum, String> {

	/**
	 * @param title 标题
	 * @param summary 摘要
	 * @param content 内容
	 * @return 分页
	 * select distinct * from esforum where title contain ? or summary contain ? and content ?
	 */
	public Page<EsForum> findDistinctEsForumByTitleContainingOrSummaryContainingOrContentContaining(String title, String summary, String content,Pageable pageable);
	
}
