package com.collegefees.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.collegefees.dao.DAO;
import com.collegefees.dao.DBImplementation;

public class IncomeRange implements Serializable{
	private int incomeId;
	private int income;
	private String cmd;
	
	public IncomeRange(){
		incomeId=0;
		income=0;
		cmd="save";
	}


	public int getIncomeId() {
		return incomeId;
	}


	public void setIncomeId(int incomeId) {
		this.incomeId = incomeId;
	}


	public int getIncome() {
		return income;
	}


	public void setIncome(int income) {
		this.income = income;
	}


	public String getCmd() {
		return cmd;
	}


	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	
	public int saveIncomeRange() {
		int rows = -1;
		com.collegefees.dao.DAO dao = new com.collegefees.dao.DBImplementation();
		String query = "select incomeId from income_range where income='" + getIncome() + "'";
		if (!dao.isExists(query)) {
			query = "insert into income_range(income) " + "values('"
					+ getIncome() + "')";
			rows = dao.putData(query);
		} else {
			rows = -2;
		}
		dao.closeConnection();
		return rows;
	}
	
	public int updateIncomeRange() {
		int rows = -1;
		com.collegefees.dao.DAO dao = new com.collegefees.dao.DBImplementation();
		String query = "update income_range set " + "income='" + getIncome() 
						+ "' where incomeId = " + getIncomeId();
		rows = dao.putData(query);
		dao.closeConnection();
		return rows;
	}
	
	public int deleteIncomeRange() {
		int rows = -1;
		com.collegefees.dao.DAO dao = new com.collegefees.dao.DBImplementation();
		String query = "delete from income_range where incomeId=" + getIncomeId();
		rows = dao.putData(query);
		dao.closeConnection();
		return rows;
	}
	
	public JSONArray getIncomeRange() {
		JSONArray array = new JSONArray();
		DAO dao = new DBImplementation();
		 String query = "select * from income_range";
		ResultSet resultSet = dao.getData(query);
		try {
			while (resultSet.next()) {
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.put("incomeId", resultSet.getInt("incomeId"));
					jsonObject.put("income",resultSet.getInt("income"));
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
	
	public JSONObject getIncomeRangeById() {
		JSONObject jsonObject = new JSONObject();
		DAO dao = new DBImplementation();
		String query = "select * from income_range where incomeId = "+getIncomeId();
		ResultSet resultSet = dao.getData(query);
		try {
			if(resultSet.next()) {
					setIncomeId(resultSet.getInt("incomeId"));
					setIncome(resultSet.getInt("income"));
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
