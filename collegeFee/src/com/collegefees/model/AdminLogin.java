package com.collegefees.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.collegefees.dao.Constants;
import com.collegefees.dao.DAO;
import com.collegefees.dao.DBImplementation;

public class AdminLogin implements Serializable{
	private String adminId;
	private String password;
	private String message;
	private String sessionKey;
	private Object sessionValue;
	private String target;
	public AdminLogin() {
		adminId = "";
		password = "";
		message = "";
		sessionKey = "";
		sessionValue = "";
		target = "";
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	public Object getSessionValue() {
		return sessionValue;
	}
	public void setSessionValue(Object sessionValue) {
		this.sessionValue = sessionValue;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public boolean isAdmin() {
		boolean flag = false;
		DAO dao = new DBImplementation();
		String query = "select * from admin where adminId='"+getAdminId()+"'";
		ResultSet resultSet = dao.getData(query);
		try {
			if(resultSet.next()) {
				String dbPassword = resultSet.getString("password");
				if(dbPassword.equals(getPassword())) {
					flag = true;
					setSessionKey("adminId");
					setSessionValue(resultSet.getString("adminId"));
					setTarget("viewdepartments.jsp");
				}
				else {
					setMessage("Invalid Password");
				}			
			}
			else {
				query = "select * from departments where mailId='"+getAdminId()+"'";
				resultSet = dao.getData(query);
					if(resultSet.next()) {
						
						String dbPassword = resultSet.getString("password");
						if(dbPassword.equals(getPassword())) {
							flag = true;
							String mailId = resultSet.getString("mailId");
							setSessionKey("mailId");
							setSessionValue(mailId);
							setTarget("viewsubject.jsp");
						}
						else {
							setMessage("Invalid Password");
						}			
					}
					else {
						query = "select * from students where mailId='"+getAdminId()+"'";
						resultSet = dao.getData(query);
							if(resultSet.next()) {
								
								String dbPassword = resultSet.getString("password");
								if(dbPassword.equals(getPassword())) {
									flag = true;
									String mailId = resultSet.getString("mailId");
									setSessionKey("mailId");
									setSessionValue(mailId);
									setTarget("viewfee_structure.jsp");
								}
								else {
									setMessage("Invalid Password");
								}			
							}
							else {
								setMessage("Invalid MailId");
							}
						
					}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.closeConnection();
		return flag;
	}
	
}

