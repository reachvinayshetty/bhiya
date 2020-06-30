package com.collegefees.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.collegefees.dao.DAO;
import com.collegefees.dao.DBImplementation;

public class Attendance implements Serializable{

	private int attendanceId;
	private int studentId;
	private int subjectId;
	private int totalClass;
	private int attendedClass;
	private String cmd;
	
	public Attendance(){
		attendanceId=0;
		studentId=0;
		subjectId=0;
		totalClass=0;
		attendedClass=0;
		cmd="save";
	}
	
	public int getAttendanceId() {
		return attendanceId;
	}
	public void setAttendanceId(int attendanceId) {
		this.attendanceId = attendanceId;
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
	public int getTotalClass() {
		return totalClass;
	}
	public void setTotalClass(int totalClass) {
		this.totalClass = totalClass;
	}
	public int getAttendedClass() {
		return attendedClass;
	}
	public void setAttendedClass(int attendedClass) {
		this.attendedClass = attendedClass;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	
	
	public int saveAttendance() {
		int rows = -1;
		com.collegefees.dao.DAO dao = new com.collegefees.dao.DBImplementation();
		String query = "select attendanceId from attendance where studentId='" + getStudentId() + "'";
		System.out.println(query);
		if (!dao.isExists(query)) {
			query = "insert into attendance(studentId,subjectId,totalClass,attendedClass) " + "values('" 
					+ getStudentId()
					+ "','" + getSubjectId() 
					+ "','" + getTotalClass() 
					+ "','" + getAttendedClass() 
					+ "')";
			System.out.println(query);
			rows = dao.putData(query);
		} else {
			rows = -2;
		}
		dao.closeConnection();
		return rows;
	}
	
	public int updateAttendance() {
		int rows = -1;
		DAO dao = new DBImplementation();
		String query = "update attendance set " + "studentId='" + getStudentId() 
				+ "', subjectId = '" + getSubjectId()
				+ "',totalClass='" + getTotalClass() 
				+ "',attendedClass = '" + getAttendedClass() 
				+ "' where attendanceId = " + getAttendanceId();
		rows = dao.putData(query);
		dao.closeConnection();
		return rows;
	}
	
	public int deleteAttendance() {
		int rows = -1;
		DAO dao = new DBImplementation();
		String query = "delete from attendance where attendanceId=" + getAttendanceId();
		rows = dao.putData(query);
		dao.closeConnection();
		return rows;
	}
	
	public JSONArray getAttendance() {
		JSONArray array = new JSONArray();
		DAO dao = new DBImplementation();
		 String query = "SELECT * FROM attendance\r\n" + 
		 		"INNER	JOIN students\r\n" + 
		 		"ON students.studentId = attendance.studentId\r\n" + 
		 		"INNER JOIN subjects\r\n" + 
		 		"ON subjects.subjectId=attendance.subjectId";
		ResultSet resultSet = dao.getData(query);
		try {
			while (resultSet.next()) {
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.put("attendanceId", resultSet.getInt("attendanceId"));
					jsonObject.put("studentId", resultSet.getString("studentId"));
					jsonObject.put("studentName", resultSet.getString("studentName"));
					jsonObject.put("subjectId",resultSet.getString("subjectId"));
					jsonObject.put("subject",resultSet.getString("subject"));
					jsonObject.put("totalClass",resultSet.getString("totalClass"));
					jsonObject.put("attendedClass",resultSet.getString("attendedClass"));
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
	
	public JSONObject getAttendanceById() {
		JSONObject jsonObject = new JSONObject();
		DAO dao = new DBImplementation();
		String query = "select * from attendance where attendanceId = "+getAttendanceId();
		ResultSet resultSet = dao.getData(query);
		try {
			if(resultSet.next()) {
					setAttendanceId(resultSet.getInt("attendanceId"));
					setStudentId(resultSet.getInt("studentId"));
					setSubjectId(resultSet.getInt("subjectId"));
					setTotalClass(resultSet.getInt("totalClass"));
					setAttendedClass(resultSet.getInt("attendedClass"));
					setCmd("Update");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.closeConnection();
		return jsonObject;
	}
	
	public JSONArray getStudent() {
		JSONArray array = new JSONArray();
		DAO dao = new DBImplementation();
		 String query = "select * from students";
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
	
	public JSONArray getSubject() {
		JSONArray array = new JSONArray();
		DAO dao = new DBImplementation();
		 String query = "select * from subjects";
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
