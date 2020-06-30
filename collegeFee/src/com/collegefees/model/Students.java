package com.collegefees.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.collegefees.dao.DAO;
import com.collegefees.dao.DBImplementation;

public class Students implements Serializable{
	private int studentId;
	private String studentName;
	private String mobile;
	private String regNo;
	private String address;
	private String mailId;
	private String password;
	private String parentName;
	private int sem;
	private int categoryId;
	private int incomeId;
	private String academicYear;
	private int departmentId;
	private String cmd;
	
	public Students(){
		studentId=0; 
		studentName="";
		mobile="";
		regNo="";
		address="";
		mailId="";
		password="";
		parentName="";
		sem=0;
		categoryId=0;
		incomeId=0;
		academicYear="";
		departmentId=0;
		cmd="save";
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public int getSem() {
		return sem;
	}

	public void setSem(int sem) {
		this.sem = sem;
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

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
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
	
	public int saveStudents() {
		int rows = -1;
		com.collegefees.dao.DAO dao = new com.collegefees.dao.DBImplementation();
		String query = "select studentId from students where studentName='" + getStudentName() + "'";
		if (!dao.isExists(query)) {
			query = "insert into students(studentName,mobile,regNo,address,mailId,password,parentName,sem,categoryId,incomeId,academicYear,departmentId) " + "values('"
					+ getStudentName() + "','" 
					+ getMobile() + "','"
					+ getRegNo() + "','"
					+ getAddress() + "','"
					+ getMailId() + "','"
					+ getPassword() + "','"
					+ getParentName() + "','"
					+ getSem() + "','"
					+ getCategoryId() + "','"
					+ getIncomeId() + "','"
					+ getAcademicYear() + "','"
					+ getDepartmentId() + "')";
			rows = dao.putData(query);
		} else {
			rows = -2;
		}
		dao.closeConnection();
		return rows;
	}
	
	public int updateStudents() {
		int rows = -1;
		com.collegefees.dao.DAO dao = new com.collegefees.dao.DBImplementation();
		String query = "update students set " + "studentName='" + getStudentName() 
						+ "',mobile = '" + getMobile()
						+ "',regNo = '" + getRegNo()
						+ "',address = '" + getAddress()
						+ "',mailId = '" + getMailId()
						+ "',password = '" + getPassword()
						+ "',parentName = '" + getParentName()
						+ "',sem = '" + getSem()
						+ "',categoryId = '" + getCategoryId()
						+ "',incomeId = '" + getIncomeId()
						+ "',academicYear = '" + getAcademicYear()
						+ "',departmentId = '" + getDepartmentId()
						+ "' where studentId = " + getStudentId();
		rows = dao.putData(query);
		dao.closeConnection();
		return rows;
	}
	
	public int deleteStudents() {
		int rows = -1;
		com.collegefees.dao.DAO dao = new com.collegefees.dao.DBImplementation();
		String query = "delete from students where studentId=" + getStudentId();
		rows = dao.putData(query);
		dao.closeConnection();
		return rows;
	}
	
	public JSONArray getStudents() {
		JSONArray array = new JSONArray();
		DAO dao = new DBImplementation();
		 String query = "SELECT * FROM students  "
		 		   + 
			 		"INNER JOIN categories ON categories.categoryId = students.categoryId " + 
			 		"INNER JOIN income_range ON income_range.incomeId = students.incomeId "
			 		+ "INNER JOIN departments ON departments.departmentId = students.departmentId ";

		ResultSet resultSet = dao.getData(query);
		try {
			while (resultSet.next()) {
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.put("studentId", resultSet.getInt("studentId"));
					jsonObject.put("studentName", resultSet.getString("studentName"));
					jsonObject.put("mobile", resultSet.getString("mobile"));
					jsonObject.put("regNo",resultSet.getString("regNo"));
					jsonObject.put("address",resultSet.getString("address"));
					jsonObject.put("mailId",resultSet.getString("mailId"));
					jsonObject.put("password",resultSet.getString("password"));
					jsonObject.put("parentName",resultSet.getString("parentName"));
					jsonObject.put("sem",resultSet.getInt("sem"));
					jsonObject.put("categoryName",resultSet.getString("categoryName"));
					jsonObject.put("income",resultSet.getInt("income"));
					jsonObject.put("academicYear",resultSet.getString("academicYear"));
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
	
	public JSONObject getStudentsById() {
		JSONObject jsonObject = new JSONObject();
		DAO dao = new DBImplementation();
		String query = "select * from students where studentId = "+getStudentId();
		ResultSet resultSet = dao.getData(query);
		try {
			if(resultSet.next()) {
					setStudentId(resultSet.getInt("studentId"));
					setStudentName(resultSet.getString("studentName"));
					setMobile(resultSet.getString("mobile"));
					setRegNo(resultSet.getString("regNo"));
					setAddress(resultSet.getString("address"));
					setMailId(resultSet.getString("mailId"));
					setPassword(resultSet.getString("password"));
					setParentName(resultSet.getString("parentName"));
					setSem(resultSet.getInt("sem"));
					setCategoryId(resultSet.getInt("categoryId"));
					setIncomeId(resultSet.getInt("incomeId"));
					setAcademicYear(resultSet.getString("academicYear"));
					setDepartmentId(resultSet.getInt("departmentId"));
					setCmd("Update");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.closeConnection();
		return jsonObject;
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
	public JSONArray getDepartmentTypes()
	{
		JSONArray array = new JSONArray();
		DAO dao = new DBImplementation();
		 String query = "SELECT * FROM departments" ;
		 //System.out.println(query);
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
