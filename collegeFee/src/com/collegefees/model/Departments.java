package com.collegefees.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.collegefees.dao.DAO;
import com.collegefees.dao.DBImplementation;

public class Departments implements Serializable{
	private int departmentId;
	private String departmentName;
	private String hod;
	private String mailId;
	private String password;
	private String cmd;
	
	public Departments(){
		departmentId=0;
		departmentName="";
		hod="";
		mailId="";
		password="";
		cmd="Save";
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getHod() {
		return hod;
	}

	public void setHod(String hod) {
		this.hod = hod;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public int saveDepartments() {
		int rows = -1;
		com.collegefees.dao.DAO dao = new com.collegefees.dao.DBImplementation();
		String query = "select departmentId from departments where departmentName='" + getDepartmentName() + "'";
		if (!dao.isExists(query)) {
			query = "insert into departments(departmentName,hod,mailId,password) " + "values('"
					+ getDepartmentName() + "','" 
					+ getHod() + "','"
					+ getMailId() + "','" 
					+ getPassword() + "')";
			rows = dao.putData(query);
		} else {
			rows = -2;
		}
		dao.closeConnection();
		return rows;
	}
	
	public int updateDepartments() {
		int rows = -1;
		com.collegefees.dao.DAO dao = new com.collegefees.dao.DBImplementation();
		String query = "update departments set " + "departmentName='" + getDepartmentName() 
						+ "', hod = '" + getHod()
						+ "',mailId='" + getMailId() 
						+ "',password = '" + getPassword() 
						+ "' where departmentId = " + getDepartmentId();
		rows = dao.putData(query);
		dao.closeConnection();
		return rows;
	}
	
	public int deleteDepartments() {
		int rows = -1;
		com.collegefees.dao.DAO dao = new com.collegefees.dao.DBImplementation();
		String query = "delete from departments where departmentId=" + getDepartmentId();
		rows = dao.putData(query);
		dao.closeConnection();
		return rows;
	}
	
	public JSONArray getDepartments() {
		JSONArray array = new JSONArray();
		DAO dao = new DBImplementation();
		 String query = "select * from departments";
		ResultSet resultSet = dao.getData(query);
		try {
			while (resultSet.next()) {
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.put("departmentId", resultSet.getInt("departmentId"));
					jsonObject.put("departmentName", resultSet.getString("departmentName"));
					jsonObject.put("hod",resultSet.getString("hod"));
					jsonObject.put("mailId",resultSet.getString("mailId"));
					jsonObject.put("password",resultSet.getString("password"));
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
	
	public JSONObject getDepartmentsById() {
		JSONObject jsonObject = new JSONObject();
		DAO dao = new DBImplementation();
		String query = "select * from departments where departmentId = "+getDepartmentId();
		ResultSet resultSet = dao.getData(query);
		try {
			if(resultSet.next()) {
					setDepartmentId(resultSet.getInt("departmentId"));
					setDepartmentName(resultSet.getString("departmentName"));
					setHod(resultSet.getString("hod"));
					setMailId(resultSet.getString("mailId"));
					setPassword(resultSet.getString("password"));
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
