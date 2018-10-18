package com.wangzunbin.day03.template;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.wangzunbin.day01._05_smis.domain.Column;
import com.wangzunbin.day01._05_smis.domain.Table;
import com.wangzunbin.util.JdbcTemplate;

public class HibernateTemplate {

	/**
	 * 通用的保存操作
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public static void save(Object obj) throws Exception {
		StringBuilder sql = new StringBuilder(80);
		String tableName = obj.getClass().getSimpleName();
		if (obj.getClass().isAnnotationPresent(Table.class)) {
			tableName = obj.getClass().getAnnotation(Table.class).value();
		}
		sql.append("INSERT INO").append(tableName).append("(");
		// 拼接
		StringBuilder columnList = new StringBuilder(40);
		StringBuilder signList = new StringBuilder(40);
		List<Object> params = new ArrayList<>(10);
		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass(), Object.class);
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor pd : pds) {
			String name = pd.getName();
			if (!"id".equals(name)) {
				String columnName = name;
				Field field = obj.getClass().getDeclaredField(name);
				if (field.isAnnotationPresent(Column.class)) {
					columnName = field.getAnnotation(Column.class).value();
				}
				columnList.append(columnName).append(",");
				signList.append("?").append(",");
				Object value = pd.getReadMethod().invoke(obj);
				params.add(value);
			}
		}
		// ===================================
		columnList.deleteCharAt(columnList.length() - 1);
		signList.deleteCharAt(signList.length() - 1);
		sql.append(columnList);
		sql.append(")").append(" VALUES (");
		sql.append(signList);
		sql.append("");
		System.out.println(sql);
		System.out.println(params);
		JdbcTemplate.update(sql.toString(), params.toArray());
	}

}
