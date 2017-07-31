package org.ftd.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ftd.common.pojo.EasyUITreeNode;
import org.ftd.content.service.ContentCategoryService;
import org.ftd.mapper.TbContentCategoryMapper;
import org.ftd.pojo.TbContentCategory;
import org.ftd.pojo.TbContentCategoryExample;
import org.ftd.pojo.TbContentCategoryExample.Criteria;
import org.ftd.utils.E3Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	@Autowired
	TbContentCategoryMapper contentCategoryMapper;
	@Override
	public List<EasyUITreeNode> getContentCategoryListByParentId(long parentId) {
		//创建一个查询example
		TbContentCategoryExample example = new TbContentCategoryExample();
		//设置查询条件
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		//新建一个返回结果列表
		List<EasyUITreeNode> result = new ArrayList<>();
		//封装数据
		for (TbContentCategory category : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(category.getId());
			node.setText(category.getName());
			node.setState(category.getIsParent()?"closed":"open");
			result.add(node);
		}
		return result;
	}
	
	@Override
	public E3Result addContentCategory(long parentId, String name) {
		TbContentCategory category = new TbContentCategory();
		//设置父节点id
		category.setParentId(parentId);
		//设置分类名称
		category.setName(name);
		//叶子结点
		category.setIsParent(false);
		//排列序号,取值范围：大于0的整数
		category.setSortOrder(1);
		category.setStatus(1);
		category.setCreated(new Date());
		category.setUpdated(new Date());
		//插入数据
		contentCategoryMapper.insert(category);
		//判断父节点的isParent是否为true，不是true需要改为true
		TbContentCategory parentContentCategory = contentCategoryMapper.selectByPrimaryKey(parentId);
		if(!parentContentCategory.getIsParent()){
			parentContentCategory.setIsParent(true);
			//更新父节点
			contentCategoryMapper.updateByPrimaryKey(parentContentCategory);
		}
		return E3Result.ok(category);
	}

	@Override
	public E3Result updateContentCategory(long id, String name) {
		TbContentCategory category = new TbContentCategory();
		category.setId(id);
		category.setName(name);
		contentCategoryMapper.updateByPrimaryKeySelective(category);
		return E3Result.ok();
	}

	@Override
	public E3Result deleteContentCategory(long id) {
		TbContentCategory category = contentCategoryMapper.selectByPrimaryKey(id);
		//先判断该节点是否是父节点
		if(category.getIsParent()){
			return E3Result.build(500, "父节点不能删除！");
		}
		//求出父节点id
		Long parentId = category.getParentId();
		//删除该节点
		contentCategoryMapper.deleteByPrimaryKey(id);
		//判断该节点的父节点是否还有其他的子节点，若无，则要修改其isParent属性
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		//判断是否有结果集
		if(list.size()==0){
			//record
			TbContentCategory parent = new TbContentCategory();
			parent.setId(parentId);
			parent.setIsParent(false);
			parent.setUpdated(new Date());
			contentCategoryMapper.updateByPrimaryKeySelective(parent);
		}
		return E3Result.ok();
	}

}
