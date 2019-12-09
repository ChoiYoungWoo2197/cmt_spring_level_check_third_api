package co.kr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.kr.vo.Total;

@Repository
public class TotalDaoImpl implements TotalDao {
	@Autowired
	private DataSource dataSource;

	@Override
	public List<Total> selectAll() throws Exception {
		// TODO Auto-generated method stub

		List<Total> totalList = new ArrayList<Total>();
		String selectSql = "select orders.Order_id, orders.Customer_id, customer.Customer_name, product.Product_id, product.Product_name " + 
				" FROM orders " + 
				" LEFT JOIN customer ON customer.Customer_id = orders.Customer_id " + 
				" LEFT JOIN product ON product.Product_id = orders.Product_id " + 
				";";

		Connection connection = dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			Total object = new Total();
			object.setOrderId(resultSet.getInt("Order_id"));
			object.setCustomId(resultSet.getInt("Customer_id"));
			object.setCustomName(resultSet.getString("Customer_name"));
			object.setProductId(resultSet.getInt("Product_id"));
			object.setProductName(resultSet.getString("Product_name"));
			totalList.add(object);	
		}

		connection.close();
		preparedStatement.close();
		resultSet.close();

		return totalList;
	}

	@Override
	public void UpdateAll(List<Total> totalList) throws Exception {
		// TODO Auto-generated method stub
		String selectSql = "update customer set Customer_name = ? where Customer_id = ?";

		Connection connection = dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
		
		for (int i = 0; i < totalList.size(); i++) {
			preparedStatement.setString(1, totalList.get(i).getCustomName());
			preparedStatement.setString(2, Integer.toString(totalList.get(i).getCustomId()));
			int result = preparedStatement.executeUpdate();     
			System.out.println("변경된 row : " + result);
		}
		
		connection.close();
		preparedStatement.close();
		
		
	}
}
