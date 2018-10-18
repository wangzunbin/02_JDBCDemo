package com.wangzunbin.day03.template;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.wangzunbin.day01._05_smis.domain.Column;
import com.wangzunbin.day03.dao.IResultSetHandler;

public class BeanListHandler<T> implements IResultSetHandler<List<T>> {

	// JavaBean对象的类型
	private Class<T> classType;
	
	
	public BeanListHandler(Class<T> classType) {
		super();
		this.classType = classType;
	}

	/*@Override
	public List<T> handle(ResultSet rs) throws Exception {
		ArrayList<T> list = new ArrayList<>();
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
			list.add(obj);
		}
		return list;
	}*/
	public List<T> handle(ResultSet rs) throws Exception {
		List<T> list = new ArrayList<>();
		while(rs.next()){
			T obj = classType.newInstance();
			BeanInfo beanInfo = Introspector.getBeanInfo(classType,Object.class);
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor pd : pds) {
				String name = pd.getName();//属性名称(列名/字段名)
				
				String fieldName = name;//表中的列名,缺省就是属性名
				//======================================
				Field field =classType.getDeclaredField(name);
				//判断字段上是否有Column注解
				if(field.isAnnotationPresent(Column.class)){
					Column co = field.getAnnotation(Column.class);
					fieldName  = co.value();
				}
				//======================================
				//获取该列的值
				Object value = rs.getObject(fieldName);
				//调用属性的setter方法,给对象设置数据
				pd.getWriteMethod().invoke(obj, value);
			}
			list.add(obj);
		}
		return list;
	}
}
