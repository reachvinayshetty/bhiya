package com.collegefees.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.collegefees.dao.DAO;
import com.collegefees.dao.DBImplementation;

public class Results implements Serializable{
	private int resultId;
	private int studentId;
	private int subjectId;
	private String result;
	private String cmd;
	
	public Results(){
		resultId=0;
		studentId=0;
		subjectId=0;
		result="";
		cmd="save";
	}

	public int getResultId() {
		return resultId;
	}

	public void setResultId(int resultId) {
		this.resultId = resultId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	

	public int saveResults() {
		int rows = -1;
		com.collegefees.dao.DAO dao = new com.collegefees.dao.DBImplementation();
		String query = "select resultId from results where subjectId='" + getSubjectId() + "'";
		if (!dao.isExists(query)) {
			query = "insert into results(studentId,subjectId,result) " + "values('"
					+ getStudentId() + "','" 
					+ getSubjectId() + "','"
					+ getResult() + "')";
			rows = dao.putData(query);
		} else {
			rows = -2;
		}
		dao.closeConnection();
		return rows;
	}
	 
	public int updateResults() {
		int rows = -1;
		com.collegefees.dao.DAO dao = new com.collegefees.dao.DBImplementation();
		String query = "update results set " + "studentId='" + getStudentId() 
						+ "',subjectId = '" + getSubjectId()
						+ "',result = '" + getResult() 
						+ "' where resultId = " + getResultId();
		rows = dao.putData(query);
		dao.closeConnection();
		return rows;
	}
	
	public int deleteResults() {
		int rows = -1;
		com.collegefees.dao.DAO dao = new com.collegefees.dao.DBImplementation();
		String query = "delete from results where resultId=" + getResultId();
		rows = dao.putData(query);
		dao.closeConnection();
		return rows;
	}
	
	public JSONArray getResults() {
		JSONArray array = new JSONArray();
		DAO dao = new DBImplementation();
		 String query = " \r\n" + 
		 		" SELECT * FROM results INNER JOIN students ON students.studentId = results.studentId \r\n" + 
		 		"                        INNER JOIN subjects ON subjects.subjectId = results.subjectId ";
		ResultSet resultSet = dao.getData(query);
		try {
			while (resultSet.next()) {
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.put("resultId", resultSet.getInt("resultId"));
					jsonObject.put("studentName", resultSet.getString("studentName"));
					jsonObject.put("subject", resultSet.getString("subject"));
					jsonObject.put("result",resultSet.getString("result"));
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
	
	public JSONObject getResultsById() {
		JSONObject jsonObject = new JSONObject();
		DAO dao = new DBImplementation();
		String query = "select * from results where resultId = "+getResultId();
		ResultSet resultSet = dao.getData(query);
		try {
			if(resultSet.next()) {
					setResultId(resultSet.getInt("resultId"));
					setStudentId(resultSet.getInt("studentId"));
					setSubjectId(resultSet.getInt("subjectId"));
					setResult(resultSet.getString("result"));
					setCmd("Update");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.closeConnection();
		return jsonObject;
	}
	
	public JSONArray getStudentTypes()
	{
		JSONArray array = new JSONArray();
		DAO dao = new DBImplementation();
		 String query = "SELECT * FROM students" ;
		 //System.out.println(query);
		ResultSet resultSet = dao.getData(query);
		try {
			while (resultSet.next()) {
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.put("studentId", resultSet.getInt("studentId"));
					jsonObject.put("studentName", resultSet.getString("studentName"));
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
	
	public JSONArray getSubjectTypes()
	{
		JSONArray array = new JSONArray();
		DAO dao = new DBImplementation();
		 String query = "SELECT * FROM subjects" ;
		
		ResultSet resultSet = dao.getData(query);
		try {
			while (resultSet.next()) {
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.put("subjectId", resultSet.getInt("subjectId"));
					jsonObject.put("subject", resultSet.getString("subject"));
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
