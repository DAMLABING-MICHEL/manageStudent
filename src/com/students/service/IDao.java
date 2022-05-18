package com.students.service;

import java.sql.Connection;
import java.util.List;

public abstract  class IDao<T> {
	public Connection connect = MysqlConnection.getInstance();
	abstract public List<T> findAll();
	abstract public T findById(int id);
	abstract public T create(T o);
	abstract public T update(T o);
	abstract public T delete(T o);
}
