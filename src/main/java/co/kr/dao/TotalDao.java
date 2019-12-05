package co.kr.dao;

import java.util.List;
import co.kr.vo.Total;

public interface TotalDao {
	public List<Total> selectAll() throws Exception;
}
