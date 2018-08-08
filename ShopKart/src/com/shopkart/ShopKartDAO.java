package com.shopkart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.sun.org.apache.regexp.internal.recompile;

public class ShopKartDAO {
	JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public ArrayList<Users> getUsers(String sql) {
		List<Users> usr = new ArrayList<>();// or use List
		usr = jdbcTemplate.query(sql, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Users(rs.getString(1), rs.getString(2));

			}
		});
		return (ArrayList<Users>) usr;
	}

	public ArrayList<Product> getProucts(String sql) {
		List<Product> prd = new ArrayList<>();// or use List
		prd = jdbcTemplate.query(sql, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
						rs.getInt(6), Integer.parseInt(rs.getString(7)));

			}
		});
		return (ArrayList<Product>) prd;
	}

	public int updateTable(String sql) {
		int rows = jdbcTemplate.update(sql);
		return rows;
	}

	public int getOrderId() {
		List<Integer> orderId = new ArrayList<>();// or use List
		orderId = jdbcTemplate.query("select OrderId from Order", new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				return Integer.parseInt(rs.getString(1));

			}
		});
		if (orderId.size() > 0)
			return (orderId.get(0)+1);
		else
			return 1;
	}
}
