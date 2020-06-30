package com.collegefees.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.collegefees.dao.DAO;
import com.collegefees.dao.DBImplementation;

public class Subjects implements Serializable{
	private int subjectId;
	private int sem;
	private String subject;
	private String subjectCode;
	private int departmentId;
	private String departmentName;
	private String cmd;
	
	public Subjects(){
		subjectId=0;
		sem=0;
		subject="";
		subjectCode="";
		departmentId=0;
		departmentName="";
		cmd="Save";
	}

	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getSem() {
		return sem;
	}
	public void setSem(int sem) {
		this.sem = sem;
	}

	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	
	public int saveSubjects() {
		int rows = -1;
		com.collegefees.dao.DAO dao = new com.collegefees.dao.DBImplementation();
		String query = "select subjectId from subjects where subject='" + getSubject() + "'";
		if (!dao.isExists(query)) {
			query = "insert into subjects(sem,subject,subjectCode,departmentId) " + "values('"
					+ getSem() + "','" 
					+ getSubject() + "','"
					+ getSubjectCode() + "','"
					+ getDepartmentId() + "')";
			rows = dao.putData(query);
		} else {
			rows = -2;
		}
		dao.closeConnection();
		return rows;
	}
	
	public int updateSubjects() {
		int rows = -1;
		com.collegefees.dao.DAO dao = new com.collegefees.dao.DBImplementation();
		String query = "update subjects set " + "sem='" + getSem() 
						+ "',subject = '" + getSubject()
						+ "',subjectCode = '" + getSubjectCode()
						+ "',departmentId = '" + getDepartmentId() 
						+ "' where subjectId = " + getSubjectId();
		rows = dao.putData(query);
		dao.closeConnection();
		return rows;
	}
	
	public int deleteSubjects() {
		int rows = -1;
		com.collegefees.dao.DAO dao = new com.collegefees.dao.DBImplementation();
		String query = "delete from subjects where subjectId=" + getSubjectId();
		rows = dao.putData(query);
		dao.closeConnection();
		return rows;
	}
	
	public JSONArray getSubjects() {
		JSONArray array = new JSONArray();
		DAO dao = new DBImplementation();
		String query = "	SELECT * FROM subjects " + 
				"		INNER JOIN departments ON " + 
				"		departments.departmentId=subjects.departmentId";
			ResultSet resultSet = dao.getData(query);
		try {
			while (resultSet.next()) {
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.put("subjectId", resultSet.getInt("subjectId"));
					jsonObject.put("sem", resultSet.getInt("sem"));
					jsonObject.put("subject", resultSet.getString("subject"));
					jsonObject.put("subjectCode",resultSet.getString("subjectCode"));
					jsonObject.put("departmentName",resultSet.getString("departmentName"));
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
	
	public JSONObject getSubjectsById() {
		JSONObject jsonObject = new JSONObject();
		DAO dao = new DBImplementation();
		String query = "	SELECT * FROM subjects " + 
				"		INNER JOIN departments ON" + 
				"		departments.departmentId=subjects.departmentId  where subjectId = "+getSubjectId();
	
		ResultSet resultSet = dao.getData(query);
		try {
			if(resultSet.next()) {
					setSubjectId(resultSet.getInt("subjectId"));
					setSem(resultSet.getInt("sem"));
					setSubject(resultSet.getString("subject"));
					setSubjectCode(resultSet.getString("subjectCode"));
					setDepartmentId(resultSet.getInt("departmentId"));
					setDepartmentName(resultSet.getString("departmentName"));
					setCmd("Update");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.closeConnection();
		return jsonObject;
	}
	
	public JSONArray getDepartmentss() {
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
			
}
