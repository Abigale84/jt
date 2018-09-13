package com.jt.manage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.vo.EasyUIResult;
import com.jt.manage.pojo.Item;
import com.jt.manage.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/findItem")
	@ResponseBody		//将返回数据转化为JSON串
	public List<Item>findItemList(){
		
		return itemService.findItemList();
	}
	
	
	
	/**
	 *http://localhost:8091/item/query?page=1&rows=20
	 *String 类型的返回值 会经过视图解析器 进行页面跳转  
	 * EasyUI要求的JSON参数 {"total":2000,"rows":[{},{},{}]}  
	 * 通过Response注解 可以将对象转化为JSON串    对象：total(int),rows(List<item>)
	*/
	
	@RequestMapping("/query")
	@ResponseBody 
	public EasyUIResult findItemPageList(int page,int rows){
		
		EasyUIResult result = itemService.findItemPageList(page,rows);
		return result;
	}
	
	/**
	 * 当前方法调用是ajax参数调用，可以直接返回，也可以通过response对象返回
	 * @param itemId
	 */
	/*@RequestMapping("/cat/queryItemName")  
	public void findItemCatName(Long itemId,HttpServletResponse response){
		
		response.setContentType("text/html;charset=utf-8");
		try {
			//根据商品分类ID查询商品名称
			String name = itemService.findItemCatNameByItemId(itemId);
			
			//通过response对象 实现数据的返回
			response.getWriter().write(name);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}*/
	
	
	@RequestMapping(value="/cat/queryItemName",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findItemCatName(Long itemId){
		
		return itemService.findItemCatNameByItemId(itemId);
	}
	
	
	

}
