package com.ten.article.service;

import com.ten.article.dao.GatheringDao;
import com.ten.article.pojo.Gathering;
import com.ten.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


/**
 * 服务层
 * 
 * @author Administrator
 *
 */
@Service
public class GatheringService {

	@Autowired
	private GatheringDao gatheringDao;
	
	@Autowired
	private IdWorker idWorker;

    //@Autowired
    //private RedisTemplate redisTemplate;


	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	@Cacheable(value = "gathering", key = "#id")
	public Gathering findById(String id) {
		return gatheringDao.findById(id).get();
	}

	/**
	 * 修改
	 * @param article
	 */
	@CacheEvict(value = "gathering", key = "#gathering.id")
	public void update(Gathering article) {
		gatheringDao.save(article);
	}

	/**
	 * 删除
	 * @param id
	 */
	@CacheEvict(value="gathering",key="#id")
	public void deleteById(String id) {
		//redisTemplate.delete("article_"+id);
		gatheringDao.deleteById(id);
	}

	/**
	 * 增加
	 * @param gathering
	 */
	public void add(Gathering gathering) {
		gathering.setId( idWorker.nextId()+"" );
		gatheringDao.save(gathering);
	}

}
