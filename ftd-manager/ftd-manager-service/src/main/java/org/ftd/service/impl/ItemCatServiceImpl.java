package org.ftd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.ftd.common.pojo.EasyUITreeNode;
import org.ftd.mapper.TbItemCatMapper;
import org.ftd.pojo.TbItemCat;
import org.ftd.pojo.TbItemCatExample;
import org.ftd.pojo.TbItemCatExample.Criteria;
import org.ftd.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ItemCatServiceImpl implements ItemCatService{
	@Autowired
	TbItemCatMapper itemCatMapper;
	/*
	 * 查询商品分类的列表
	 */
	@Override
	public List<EasyUITreeNode> selectItemCatByList(long parentId) {
		//创建查询example
		TbItemCatExample example = new TbItemCatExample();
		//设置查询条件
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> itemCats = itemCatMapper.selectByExample(example);
		//创建接收容器
		List<EasyUITreeNode> list = new ArrayList<>();
		//将结果集的数据装配到List中的载体
		for (TbItemCat itemCat : itemCats) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(itemCat.getId());
			node.setText(itemCat.getName());
			node.setState(itemCat.getIsParent()?"closed":"open");
			list.add(node);
		}
		return list;
	}

}
