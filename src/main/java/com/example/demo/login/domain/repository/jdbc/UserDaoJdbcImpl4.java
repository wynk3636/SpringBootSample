package com.example.demo.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;


//ResultSetExtractorの方法
//Selectだけオーバーライドする
@Repository("UserDaoJdbcImpl4")
public class UserDaoJdbcImpl4 extends UserDaoJdbcImpl {

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public List<User> selectMany(){
    	String sql = "SELECT * FROM m_user";
    	UserResultSetExtractor extractor = new UserResultSetExtractor();
    	return jdbc.query(sql, extractor);
    }
}

