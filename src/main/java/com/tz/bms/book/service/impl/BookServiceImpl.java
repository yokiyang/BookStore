package com.tz.bms.book.service.impl;

import java.util.List;

import com.tz.bms.book.dao.IBookDao;
import com.tz.bms.book.service.IBookService;
import com.tz.bms.entity.Book;
import com.tz.bms.entity.Category;
import com.tz.bms.entity.Pageing;
import com.tz.bms.util.Beanfactory;
/**
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:47:15
 */
public class BookServiceImpl implements IBookService {
	//通过bean工厂,获得数据访问层接口对象
	IBookDao bookDao=(IBookDao) Beanfactory.getBean("bookDao");
	
	@Override
	public Pageing queryBookByCondition(int now, int size, String cate) {
		return bookDao.selectBookByCondition(now, size, cate);
	}

	@Override
	public Book queryBookById(long id) {
		return bookDao.selectBookById(id);
	}

	@Override
	public Category querytById(long id) {
		return bookDao.selectById(id);
	}

	@Override
	public List<Category> selectAllCategory() {
		return bookDao.selectAll();
	}

}
