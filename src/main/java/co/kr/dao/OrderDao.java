package co.kr.dao;

import java.util.List;

import javax.sql.DataSource;

import co.kr.domain.Order;

public interface OrderDao {
	public List<Order> selectAll() throws Exception;
}
