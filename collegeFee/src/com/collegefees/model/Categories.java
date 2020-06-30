package com.collegefees.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.collegefees.dao.DAO;
import com.collegefees.dao.DBImplementation;

public class Categories implements Serializable{

	private int categoryId;
	private String categoryName;
	private String cmd;
	
	public Categories(){
		categoryId=0;
		categoryName="";
		cmd="save";
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	
	public int saveCategories() {
		int rows = -1;
		com.collegefees.dao.DAO dao = new com.collegefees.dao.DBImplementation();
		String query = "select categoryId from categories where categoryName='" + getCategoryName() + "'";
		if (!dao.isExists(query)) {
			query = "insert into categories(categoryName) " + "values('"+ getCategoryName() + "')";
			rows = dao.putData(query);
		} else {
			rows = -2;
		}
		dao.closeConnection();
		return rows;
	}
	
	public int updateCategories() {
		int rows = -1;
		DAO dao = new DBImplementation();
		String query = "update categories set "
					+ "categoryName='" + getCategoryName() + 
					"' where categoryId = " + getCategoryId();
		rows = dao.putData(query);
		dao.closeConnection();
		return rows;
	}
	
	public int deleteCategories() {
		int rows = -1;
		DAO dao = new DBImplementation();
		String query = "delete from categories where categoryId=" + getCategoryId();
		rows = dao.putData(query);
		dao.closeConnection();
		return rows;
	}
	
	public JSONArray getCategories() {
		JSONArray array = new JSONArray();
		DAO dao = new DBImplementation();
		 String query = "select * from categories";
		ResultSet resultSet = dao.getData(query);
		try {
			while (resultSet.next()) {
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.put("categoryId", resultSet.getInt("categoryId"));
					jsonObject.put("categoryName", resultSet.getString("categoryName"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				array.put(jsonObject);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.closeConnection();
		return array;
	}
	
	public JSONObject getCategoryById() {
		JSONObject jsonObject = new JSONObject();
		DAO dao = new DBImplementation();
		String query = "select * from categories where categoryId = "+getCategoryId();
		ResultSet resultSet = dao.getData(query);
		try {
			if(resultSet.next()) {
					setCategoryId(resultSet.getInt("categoryId"));
					setCategoryName(resultSet.getString("categoryName"));
					setCmd("Update");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.closeConnection();
		return jsonObject;
	}

}
