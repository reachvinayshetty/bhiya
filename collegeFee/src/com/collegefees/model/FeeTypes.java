package com.collegefees.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.collegefees.dao.DAO;
import com.collegefees.dao.DBImplementation;

public class FeeTypes implements Serializable{
	
	private int typeId;
	private String typeName;
	private String cmd;
	
	public FeeTypes(){
		typeId=0;
		typeName="";
		cmd="save";
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	
	public int saveFeeTypes() {
		int rows = -1;
		com.collegefees.dao.DAO dao = new com.collegefees.dao.DBImplementation();
		String query = "select typeId from fee_types where typeName='" + getTypeName() + "'";
		if (!dao.isExists(query)) {
			query = "insert into fee_types(typeName) " + "values('"+ getTypeName() + "')";
			rows = dao.putData(query);
		} else {
			rows = -2;
		}
		dao.closeConnection();
		return rows;
	}
	
	public int updateFeeTypes() {
		int rows = -1;
		DAO dao = new DBImplementation();
		String query = "update fee_types set "
					+ "typeName='" + getTypeName() + 
					"' where typeId = " + getTypeId();
		rows = dao.putData(query);
		dao.closeConnection();
		return rows;
	}
	
	public int deleteFeeTypes() {
		int rows = -1;
		DAO dao = new DBImplementation();
		String query = "delete from fee_Types where typeId=" + getTypeId();
		rows = dao.putData(query);
		dao.closeConnection();
		return rows;
	}
	
	public JSONArray getFeeTypes() {
		JSONArray array = new JSONArray();
		DAO dao = new DBImplementation();
		 String query = "select * from fee_types";
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
	
	public JSONObject getFeeTypesById() {
		JSONObject jsonObject = new JSONObject();
		DAO dao = new DBImplementation();
		String query = "select * from fee_types where typeId = "+getTypeId();
		ResultSet resultSet = dao.getData(query);
		try {
			if(resultSet.next()) {
					setTypeId(resultSet.getInt("typeId"));
					setTypeName(resultSet.getString("typeName"));
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
