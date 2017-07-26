package com.tgweb.spring4.day01;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class JDBCTest {

	private ApplicationContext ctx;
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private StudentDao studentDao;
	private ScoreDao scoreDao;

	{
		ctx = new ClassPathXmlApplicationContext("spring-applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
		namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) ctx.getBean("namedParameterJdbcTemplate");
		studentDao = (StudentDao) ctx.getBean("studentDao");
		scoreDao = (ScoreDao) ctx.getBean("scoreDao");
	}

	@Test
	public void test() throws SQLException {
		DataSource dataSource = (DataSource) ctx.getBean("dataSource");
		System.out.println(dataSource.getConnection());
	}

	@Test
	public void testUpdate() {
		String sql = "INSERT INTO student (name,age) VALUES (?,?)";
		// jdbcTemplate.update(sql, "Jack", 20);
		jdbcTemplate.update(sql, new Object[] { "Jack0", 20 });
	}

	@Test
	public void testBatchUpdate() {
		String sql = "INSERT INTO student (name,age) VALUES (?,?)";
		List<Object[]> batchArgs = new ArrayList<>();
		batchArgs.add(new Object[] { "Jack1", 21 });
		batchArgs.add(new Object[] { "Jack2", 22 });
		batchArgs.add(new Object[] { "Jack3", 23 });
		batchArgs.add(new Object[] { "Jack4", 24 });
		jdbcTemplate.batchUpdate(sql, batchArgs);
	}

	@Test
	public void testQueryForObject() {
		String sql = "SELECT id,name,age,score_id scoreId FROM student WHERE id = ?";
		RowMapper<Student> rowMapper = new BeanPropertyRowMapper<>(Student.class);
		// 不是使用这个方法：jdbcTemplate.queryForObject(sql, requiredType, args);
		Student student1 = jdbcTemplate.queryForObject(sql, rowMapper, 30);
		System.out.println(student1);
	}

	@Test
	public void testQueryForList() {
		String sql = "SELECT id,name,age,score_id scoreId FROM student WHERE id > ?";
		RowMapper<Student> rowMapper = new BeanPropertyRowMapper<>(Student.class);
		// 不是使用这个方法：jdbcTemplate.queryForList(sql);
		List<Student> students = jdbcTemplate.query(sql, rowMapper, 30);
		System.out.println(students);
	}

	@Test
	public void testQueryForValue() {
		String sql = "SELECT count(*) FROM student";
		Long count = jdbcTemplate.queryForObject(sql, Long.class);
		System.out.println(count);
	}

	@Test
	public void testGetStudent() {
		Student student1 = studentDao.getStudent(40);
		System.out.println(student1);
	}

	@Test
	public void testGetScore() {
		Score score1 = scoreDao.getScore(0);
		System.out.println(score1);
	}

	@Test
	public void testNamedParameterJdbcTemplate() {
		String sql = "insert into student (name,age,score_id) values (:name,:age,:scoreId)";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("name", "May1");
		paramMap.put("age", 21);
		paramMap.put("scoreId", 0);
		namedParameterJdbcTemplate.update(sql, paramMap);
	}

	@Test
	public void testNameParameterJdbcTemplate2() {
		String sql = "insert into student (name,age,score_id) values (:name,:age,:scoreId)";
		Student student = new Student();
		student.setName("James1");
		student.setAge(22);
		student.setScoreId(0);
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(student);
		namedParameterJdbcTemplate.update(sql, paramSource);
	}
}
