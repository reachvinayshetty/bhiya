package com.collegefees.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.collegefees.dao.DAO;
import com.collegefees.dao.DBImplementation;

public class Fees implements Serializable{
	private int feesId;
	private int typeId;
	private int categoryId;
	private int incomeId;
	private int fee;
	private String cmd;
	
	public Fees(){
		feesId=0;
		typeId=0;
		categoryId=0;
		incomeId=0;
		fee=0;
		cmd="save";
	}

	public int getFeesId() {
		return feesId;
	}

	public void setFeesId(int feesId) {
		this.feesId = feesId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getIncomeId() {
		return incomeId;
	}

	public void setIncomeId(int incomeId) {
		this.incomeId = incomeId;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	
	public int saveFees() {
		int rows = -1;
		com.collegefees.dao.DAO dao = new com.collegefees.dao.DBImplementation();
		String query = "select feesId from fees where fee='" + getFee() + "'";
		if (!dao.isExists(query)) {
			query = "insert into fees(typeId,categoryId,incomeId,fee) " + "values('"
					+ getTypeId() + "','" 
					+ getCategoryId() + "','"
					+ getIncomeId() + "','" 
					+ getFee() + "')";
			rows = dao.putData(query);
		} else {
			rows = -2;
		}
		dao.closeConnection();
		return rows;
	}
	
	public int updateFees() {
		int rows = -1;
		com.collegefees.dao.DAO dao = new com.collegefees.dao.DBImplementation();
		String query = "update fees set " + "typeId='" + getTypeId() 
						+ "', categoryId = '" + getCategoryId()
						+ "',incomeId='" + getIncomeId() 
						+ "',fee = '" + getFee() 
						+ "' where feesId = " + getFeesId();
		rows = dao.putData(query);
		dao.closeConnection();
		return rows;
	}
	
	public int deleteFees() {
		int rows = -1;
		com.collegefees.dao.DAO dao = new com.collegefees.dao.DBImplementation();
		String query = "delete from fees where feesId=" + getFeesId();
		rows = dao.putData(query);
		dao.closeConnection();
		return rows;
	}
	
	public JSONArray getFees() {
		JSONArray array = new JSONArray();
		DAO dao = new DBImplementation();
		 String query = "SELECT * FROM fees  INNER JOIN fee_types ON fee_types.typeId = fees.typeId " + 
		 		"INNER JOIN categories ON categories.categoryId = fees.categoryId " + 
		 		"INNER JOIN income_range ON income_range.incomeId = fees.incomeId";
		ResultSet resultSet = dao.getData(query);
		try {
			while (resultSet.next()) {
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.put("feesId", resultSet.getInt("feesId"));
					jsonObject.put("typeName", resultSet.getString("typeName"));
					jsonObject.put("categoryName", resultSet.getString("categoryName"));
					jsonObject.put("income", resultSet.getInt("income"));
					jsonObject.put("fee",resultSet.getInt("fee"));
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
	public JSONArray getFeeTypes()
	{
		JSONArray array = new JSONArray();
		DAO dao = new DBImplementation();
		 String query = "SELECT * FROM fee_types" ;
		 //System.out.println(query);
		ResultSet resultSet = dao.getData(query);
		try {
			while (resultSet.next()) {
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.put("typeId", resultSet.getInt("typeId"));
					jsonObject.put("typeName", resultSet.getString("typeName"));
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
	
	public JSONArray getCategoryTypes()
	{
		JSONArray array = new JSONArray();
		DAO dao = new DBImplementation();
		 String query = "SELECT * FROM categories" ;
		 //System.out.println(query);
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
	
	public JSONArray getIncomeTypes()
	{
		JSONArray array = new JSONArray();
		DAO dao = new DBImplementation();
		 String query = "SELECT * FROM income_range" ;
		 //System.out.println(query);
		ResultSet resultSet = dao.getData(query);
		try {
			while (resultSet.next()) {
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.put("incomeId", resultSet.getInt("incomeId"));
					jsonObject.put("income", resultSet.getString("income"));
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
	public JSONObject getFeesById() {
		JSONObject jsonObject = new JSONObject();
		DAO dao = new DBImplementation();
		String query = "select * from fees where feesId = "+getFeesId();
		
		ResultSet resultSet = dao.getData(query);
		try {
			if(resultSet.next()) {
					setFeesId(resultSet.getInt("feesId"));
					setTypeId(resultSet.getInt("typeId"));
					setCategoryId(resultSet.getInt("categoryId"));
					setIncomeId(resultSet.getInt("incomeId")); 
					setFee(resultSet.getInt("fee"));
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
