package org.ftd.search.test;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.ftd.common.pojo.SearchItem;
import org.ftd.mapper.TbItemMapper;
import org.ftd.utils.E3Result;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSolrJ {
	@Test
	public void testDeleteIndex() throws Exception, IOException{
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-solr.xml");
		SolrServer solrServer = context.getBean(SolrServer.class);
		solrServer.deleteByQuery("id:*");
		solrServer.commit();
	}
	
	@Test
	public void importIndex(){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		TbItemMapper itemMapper = context.getBean(TbItemMapper.class);
		SolrServer solrServer = context.getBean(SolrServer.class);
		try {
			//查询商品列表
			List<SearchItem> list = itemMapper.getItemList();
			//遍历商品列表
			for (SearchItem searchItem : list) {
				//创建文档对象
				SolrInputDocument document = new SolrInputDocument();
				//向文档对象添加域
				document.addField("id", searchItem.getId());
				document.addField("item_title", searchItem.getTitle());
				document.addField("item_sell_point", searchItem.getSell_point());
				document.addField("item_price", searchItem.getPrice());
				document.addField("item_image", searchItem.getImage());
				document.addField("item_category_name", searchItem.getCategory_name());
				//把文档对象写入索引库
				solrServer.add(document);
			}
			//提交
			solrServer.commit();
			//返回导入成功的信息
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
