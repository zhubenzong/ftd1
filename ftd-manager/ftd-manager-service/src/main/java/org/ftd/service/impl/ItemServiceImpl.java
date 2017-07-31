package org.ftd.service.impl;

import java.util.Date;
import java.util.List;

import org.ftd.common.pojo.EasyUIDataGridResult;
import org.ftd.mapper.TbItemMapper;
import org.ftd.pojo.TbItem;
import org.ftd.pojo.TbItemDesc;
import org.ftd.pojo.TbItemExample;
import org.ftd.service.ItemService;
import org.ftd.utils.E3Result;
import org.ftd.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class ItemServiceImpl implements ItemService{
	@Autowired
	TbItemMapper itemMapper;
	
	@Override
	public TbItem getItemById(long id) {
		TbItem item = itemMapper.selectByPrimaryKey(id);
		return item;
	}
	
	@Override
	public EasyUIDataGridResult findItemByList(int currentPage,int pageSize) {
		//设置分页信息
		PageHelper.startPage(currentPage, pageSize);
		//执行查询
		List<TbItem> items = itemMapper.selectByExample(new TbItemExample());
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		//取分页结果
		PageInfo<TbItem> info = new PageInfo<>(items);
		result.setRows(items);
		//取总记录数
		long total = info.getTotal();
		result.setTotal(total);
		return result;
	}
	
	@Override
	public E3Result addItem(TbItem item,String desc) {
		//已有字段：类目，标题，卖点，价格，库存，条形码，商品图片，商品描述，商品规格
		//需要补全的字段：商品id，上架状态，创建时间，修改时间
		//生成商品id
		long id = IDUtils.genItemId();
		item.setId(id);
		//创建及修改时间
		item.setCreated(new Date());
		item.setUpdated(new Date());
		//上架状态
		item.setStatus((byte) 1);
		itemMapper.insert(item);
		//创建一个向商品描述表插入数据的pojo
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(id);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		return E3Result.ok();
	}
	
	@Override
	public E3Result updateItem(TbItem item) {
		itemMapper.updateByPrimaryKeySelective(item);
		return E3Result.ok();
	}

	@Override
	public E3Result deleteItem(String ids) {
		String[] strings = ids.split(",");
		for (String id : strings) {
			itemMapper.deleteByPrimaryKey(Long.valueOf(id));
		}
		return E3Result.ok();
	}

	@Override
	public E3Result itemReshelf(String ids) {
		String[] strings = ids.split(",");
		for (String id : strings) {
			TbItem item = new TbItem();
			item.setId(Long.valueOf(id));
			item.setStatus((byte) 1);
			itemMapper.updateByPrimaryKeySelective(item);
		}
		return E3Result.ok();
	}

	@Override
	public E3Result itemInstock(String ids) {
		String[] strings = ids.split(",");
		for (String id : strings) {
			TbItem item = new TbItem();
			item.setId(Long.valueOf(id));
			item.setStatus((byte) 2);
			itemMapper.updateByPrimaryKeySelective(item);
		}
		return E3Result.ok();
	}
	
}
