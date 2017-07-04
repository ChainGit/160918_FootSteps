package com.tgweb.spring4.day01;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao extends JdbcDaoSupport {

	@Autowired
	public void setDataSource2(DataSource dataSource) {
		setDataSource(dataSource);
	}

	public Student getStudent(Integer id) {
		String sql = "SELECT id,name,age,score_id scoreId FROM student WHERE id = ?";
		RowMapper<Student> rowMapper = new BeanPropertyRowMapper<>(Student.class);
		Student student = getJdbcTemplate().queryForObject(sql, rowMapper, id);
		return student;
	}

}
