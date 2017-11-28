package com.jionjion.forum.repository;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.jionjion.forum.bean.EsForum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsForumRepositoryTest {
	
	@Autowired
	private EsForumRepository esForumRepository;
	
	@Before
	public void initEsForumRepositoryData() {
		// 清除所有数据
		esForumRepository.deleteAll();
		
		//保存数据
		esForumRepository.save(new EsForum("Spring", "Spring的教程", "Spring是一个现代化的开发方式"));
		esForumRepository.save(new EsForum("Mybatis", "Mybatis的教程", "Mybatis是工作在数据持久化层的框架"));
		esForumRepository.save(new EsForum("JQuery", "JQuery的教程", "JQuery是一个古老的前端开发语言"));
	}

	@Test
	public void testFindDistinctEsForumByTitleContainingOrSummaryContainingOrContentContaining() {
		//分页对象,查询前五页
		Pageable pageable = new PageRequest(0, 5);
		Page<EsForum> page = esForumRepository.findDistinctEsForumByTitleContainingOrSummaryContainingOrContentContaining("Spring", "Mybatis", "Mybatis",pageable);
		//是否出现期望的记录数条数
		assertThat(page.getTotalElements()).isEqualTo(2);
		for (EsForum esForum : page.getContent()) {
			System.out.println(esForum.toString());
		}
	}
}