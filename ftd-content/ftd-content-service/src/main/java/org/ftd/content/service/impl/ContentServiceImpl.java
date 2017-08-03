package org.ftd.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.ftd.common.jedis.JedisClient;
import org.ftd.content.service.ContentService;
import org.ftd.mapper.TbContentMapper;
import org.ftd.pojo.TbContent;
import org.ftd.pojo.TbContentExample;
import org.ftd.pojo.TbContentExample.Criteria;
import org.ftd.utils.E3Result;
import org.ftd.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	TbContentMapper contentMapper;
	@Autowired
	JedisClient jedisClient;

	@Override
	public List<TbContent> getContentListByCategoryId(long categoryId) {
		try {
			String json = jedisClient.hget("INDEX_CONTENT", categoryId + "");
			if (StringUtils.isNotBlank(json)) {
				List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			System.out.println("连接redis失败");
			e.printStackTrace();
		}
		// 新建查询Example
		TbContentExample example = new TbContentExample();
		// 设置查询条件
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		// 执行查询
		List<TbContent> list = contentMapper.selectByExample(example);
		try {
			jedisClient.hset("INDEX_CONTENT", categoryId + "", JsonUtils.objectToJson(list));
		} catch (Exception e) {
			System.out.println("连接redis失败");
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public E3Result addContent(TbContent tbContent) {
		// 补全TbContent的属性,主要是id(自增长)，两个时间
		tbContent.setCreated(new Date());
		tbContent.setUpdated(new Date());
		contentMapper.insert(tbContent);
		// 缓存同步
		jedisClient.hdel("INDEX_CONTENT", tbContent.getCategoryId().toString());
		return E3Result.ok();
	}

	@Override
	public E3Result deleteContent(String ids) {
		// 从ids中取出id
		List<Long> list = new ArrayList<>();
		String[] Strings = ids.split(",");
		for (String id : Strings) {
			// 应该一条条删除还是使用in
			list.add(Long.valueOf(id));
		}
		// 创建删除example
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(list);
		// 执行查询
		contentMapper.deleteByExample(example);
		// 缓存同步
		jedisClient.hdel("INDEX_CONTENT");
		return E3Result.ok();
	}

	@Override
	public E3Result updateContent(TbContent tbContent) {
		// 补全属性
		tbContent.setCreated(new Date());
		tbContent.setUpdated(new Date());
		contentMapper.updateByPrimaryKey(tbContent);
		// 缓存同步
		jedisClient.hdel("INDEX_CONTENT", tbContent.getCategoryId().toString());
		return E3Result.ok();
	}

}
