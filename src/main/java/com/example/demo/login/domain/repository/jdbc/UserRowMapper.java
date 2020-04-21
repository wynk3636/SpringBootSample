package com.example.demo.login.domain.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.login.domain.model.User;

//RowMapperでデータベースのレコードとJavaのオブジェクトをマッピング
public class UserRowMapper implements RowMapper<User> {

	//ResultSetには取得結果が入るので取得
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setUserId(rs.getString("user_id"));
        user.setPassword(rs.getString("password"));
        user.setUserName(rs.getString("user_name"));
        user.setBirthday(rs.getDate("birthday"));
        user.setAge(rs.getInt("age"));
        user.setMarriage(rs.getBoolean("marriage"));
        user.setRole(rs.getString("role"));
        
        return user;
	}
	
}
