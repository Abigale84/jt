package com.jt.manage.service;

import java.util.List;

import com.jt.common.vo.EasyUIResult;
import com.jt.manage.pojo.Item;

public interface ItemService {
	
	public List<Item> findItemList();
	
	//根据指定的页面和条数 进行分页查询
	public EasyUIResult findItemPageList(int page, int rows);
	
	//根据商品分类Id查询商品名称
	public String findItemCatNameByItemId(Long itemId);
	
}
