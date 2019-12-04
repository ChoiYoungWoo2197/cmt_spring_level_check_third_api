package co.kr.controller;

import static org.junit.Assert.*;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class DataSourceTest {

	@Autowired
	private DataSource ds;
	
	@Test
	public void test() {
		//fail("Not yet implemented");
		
		try(Connection con = ds.getConnection()) {
			System.out.println(con);
			System.out.println("connect !!");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
