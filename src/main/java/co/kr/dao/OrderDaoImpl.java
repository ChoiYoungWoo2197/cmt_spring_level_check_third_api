package co.kr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.kr.domain.Order;

@Repository
public class OrderDaoImpl implements OrderDao {
	@Autowired
	private DataSource dataSource;
		
	@Override
	public List<Order> selectAll() throws Exception {
		// TODO Auto-generated method stub
		
		//connectDB();
		List<Order> orderList = new ArrayList<Order>();
		String selectSQL = "select * from `dbcheck`.`order`";
		
		Connection conn = dataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(selectSQL);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			Order obj = new Order();
			obj.setOrderId(rs.getString("Order_id"));
			obj.setCustomId(rs.getString("Customer_id"));
			obj.setProductId(rs.getString("Product_id"));
			orderList.add(obj);
			
		}
		
		return orderList;
	}
}
