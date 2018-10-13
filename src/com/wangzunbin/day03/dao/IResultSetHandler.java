package com.wangzunbin.day03.dao;

import java.sql.ResultSet;

public interface IResultSetHandler<T> {

	T handle(ResultSet rs) throws Exception;
}
