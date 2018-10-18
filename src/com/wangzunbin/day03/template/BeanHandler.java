package com.wangzunbin.day03.template;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.wangzunbin.day03.dao.IResultSetHandler;

public class BeanHandler<T> implements IResultSetHandler<T> {
	
	// JavaBean对象的类型
	private Class<T> classType;
	
	
	public BeanHandler(Class<T> classType) {
		super();
		this.classType = classType;
	}

	@Override
	public T handle(ResultSet rs) throws Exception {
		if (rs.next()) {
			T obj = classType.newInstance();
			BeanInfo beanInfo = Introspector.getBeanInfo(classType, Object.class);
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor pd : pds) {
				String name = pd.getName();
				// 获取该列的值
				Object value = rs.getObject(name);
				// 调用属性的setter方法, 给对象设置数据
				pd.getWriteMethod().invoke(obj, value);
			}
			return obj;
		}
		return null;
	}
}
