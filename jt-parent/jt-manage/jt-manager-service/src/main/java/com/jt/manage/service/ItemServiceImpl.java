package com.jt.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.vo.EasyUIResult;
import com.jt.manage.mapper.ItemMapper;
import com.jt.manage.pojo.Item;
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;

	public List<Item> findItemList() {

		return itemMapper.findItemList();
	}


	/**
	 * 分页的业务逻辑
	 * 0-20   SELECT * FROM tb_item ORDER BY updated DESC LIMIT 0,20   第1页
	 * 20-40  SELECT * FROM tb_item ORDER BY updated DESC LIMIT 20,20 第2页
	 * 40-60  SELECT * FROM tb_item ORDER BY updated DESC LIMIT 40,20 第3页
	 * 40-60  SELECT * FROM tb_item ORDER BY updated DESC LIMIT (page-1)* rows,20 第n页
	 * easyUI的页面格式要求   {"total":2000,"rows":[{},{},{}]}
	 */
	public EasyUIResult findItemPageList(int page, int rows) {

		/**
		 * JPA操作  item(id =1,name=2)
		 * 会自动根据对象中不为null的数据  拼接sql语句
		 * select * from tb_item where id = 1 and name = 2;
		 */

		int total = itemMapper.selectCount(null); //使用通用mapper的查询操作

		//表示分页后的数据
		int start = (page - 1) * rows;
		List<Item> itemList = itemMapper.findItemPageList(start,rows);


		return new EasyUIResult(total, itemList);
	}


	public String findItemCatNameByItemId(Long itemId) {

		return itemMapper.findItemCatNameByItemId(itemId);
	}

}
