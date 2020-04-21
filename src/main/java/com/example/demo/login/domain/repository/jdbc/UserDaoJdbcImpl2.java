package com.example.demo.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

//RowMapperの方法->UsreRowMapperと動く
//Selectだけオーバーライドする
@Repository("UserDaoJdbcImpl2")
public class UserDaoJdbcImpl2 extends UserDaoJdbcImpl {

    @Autowired
    JdbcTemplate jdbc;
    
    //1件取得
    @Override
    public User selectOne(String userId) {
    	String sql = "SELECT * FROM m_user WHERE user_id = ?";
    	UserRowMapper rowMapper = new UserRowMapper();
    	return jdbc.queryForObject(sql, rowMapper, userId);
    }
    
    @Override
    public List<User> selectMany(){
    	String sql = "SELECT * FROM m_user";
    	RowMapper<User> rowMapper = new UserRowMapper();
    	return jdbc.query(sql, rowMapper);
    }
}

