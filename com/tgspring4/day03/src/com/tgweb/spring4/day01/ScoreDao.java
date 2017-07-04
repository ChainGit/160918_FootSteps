package com.tgweb.spring4.day01;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class ScoreDao extends JdbcDaoSupport {

	@Autowired
	public void setDataSource2(DataSource dataSource) {
		setDataSource(dataSource);
	}

	public Score getScore(Integer id) {
		String sql = "SELECT id,yuwen,shuxue,yinyu FROM score WHERE id = ?";
		RowMapper<Score> rowMapper = new BeanPropertyRowMapper<>(Score.class);
		Score score = getJdbcTemplate().queryForObject(sql, rowMapper, id);
		return score;
	}

}
