package com.example.demo.trySpring;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

//DIに登録
@Repository
public class HelloRepository {
	
	//オートワイヤリング:デフォルトで対象の方が一致するBeanをDIコンテナから探し出し、見つかった場合にインジェクション
	//以下の定義により、自動でインスタンスが生成される
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Map<String, Object> findOne(int id){
		
		String query = "SELECT employee_id, employee_name, age FROM employee WHERE employee_id=?";
		
		Map<String,Object> employee = jdbcTemplate.queryForMap(query,id);
		
		return employee;
		
	}
}
