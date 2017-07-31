package org.ftd.service;
/**
 * 商品相关服务
 * @author tom
 *
 */

import org.ftd.common.pojo.EasyUIDataGridResult;
import org.ftd.pojo.TbItem;
import org.ftd.utils.E3Result;

public interface ItemService {
	/*
	 * 根据商品id查询商品
	 */
	public TbItem getItemById(long id);
	
	/*
	 * 列表查询所有商品 
	 */
	public EasyUIDataGridResult findItemByList(int currentPage, int pageSize);

	/*
	 * 新增商品
	 */
	public E3Result addItem(TbItem item, String desc);
	
	/*
	 * 编辑商品
	 */
	public E3Result updateItem(TbItem item);
	
	/*
	 * 商品刪除
	 */
	public E3Result deleteItem(String ids);
	
	/*
	 * 商品上架
	 */
	public E3Result itemReshelf(String ids);
	
	/*
	 * 商品下架
	 */
	public E3Result itemInstock(String ids);
}
