package com.collegefees.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;
import com.collegefees.dao.Constants;
import com.collegefees.dao.DAO;
import com.collegefees.dao.DBImplementation;


public class Login implements Serializable{
	
	private String emailId;
	private String password;
	private int error;
	
	
	public Login() {
		emailId="";
		password="";
	}

	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public JSONObject isValidUser() {
		JSONObject jsonObject = new JSONObject();
		DAO dao = new DBImplementation();
		String query = "select * from users where mailId='"+getEmailId()+"'";
		System.out.println(query);
		ResultSet resultSet = dao.getData(query);
		try {
			if(resultSet.next()) {
				String dbPassword = resultSet.getString("password");
				if(dbPassword.equals(getPassword())) {
					jsonObject.put("error", Constants.NO_ERROR);
					jsonObject.put("userType", "user");
					jsonObject.put("userId", resultSet.getInt("userId"));
				}
				else {
					jsonObject.put("error", Constants.INVALID_PASSWORD_ERROR);
				}			
			}
			else {
				jsonObject.put("error", Constants.INVALID_MAILID_ERROR);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.closeConnection();
		return jsonObject;
	}
}
