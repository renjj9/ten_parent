package com.ten.gathering.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ten.entity.Result;
import com.ten.entity.StatusCode;
import com.ten.gathering.pojo.Gathering;
import com.ten.gathering.service.GatheringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/gathering")
@DefaultProperties(defaultFallback = "fallBackMethod") // 指定一个类的全局熔断方法
public class GatheringController {

	@Autowired
	private GatheringService gatheringService;

	@Autowired
	private DiscoveryClient discoveryClient; // eureka客户端，可以获取到eureka中服务的信息
	@Autowired
	private RestTemplate restTemplate;
	/**
	 * 熔断方法
	 * 返回值要和被熔断的方法的返回值一致
	 * 熔断方法不需要参数
	 * @return
	 */
	public Result fallBackMethod(){
		return new Result(true,StatusCode.OK,"查询成功","服务器繁忙，等会再来试试！");
	}

	public Result findByIdFallBack(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功","服务器繁忙，等会再来试试！");
	}
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	//@HystrixCommand(fallbackMethod = "findByIdFallBack")
	@HystrixCommand // 标记该方法需要熔断
	public Result findById(@PathVariable String id){
		// 1根据服务名称，获取服务实例。有可能是集群，所以是service实例集合
		//List<ServiceInstance> instances = discoveryClient.getInstances("ten-article");
		//ServiceInstance instance = instances.get(0);
		//String baseUrl = "http://" + instance.getHost() + ":" + instance.getPort() + "/article/" + id;
		//2
		String baseUrl = "http://ten-article/article/" + id;

		String forObject = this.restTemplate.getForObject(baseUrl, String.class);
		return new Result(true,StatusCode.OK,"查询成功",forObject);
		//return new Result(true,StatusCode.OK,"查询成功", gatheringService.findById(id));
	}

	/**
	 * 增加
	 * @param article
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Gathering article){
		gatheringService.add(article);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param article
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Gathering article, @PathVariable String id ){
		article.setId(id);
		gatheringService.update(article);
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		gatheringService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}

}
