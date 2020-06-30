package com.collegefees.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.collegefees.dao.DAO;
import com.collegefees.dao.DBImplementation;

public class Payments implements Serializable{
	private int paymentId;
	private String paymentOn;
	private int studentId;
	private int feesAmount;
	private String cmd;
	
	public Payments(){
		paymentId=0;
		paymentOn="";
		studentId=0;
		feesAmount=0;
		cmd="save";
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentOn() {
		return paymentOn;
	}

	public void setPaymentOn(String paymentOn) {
		this.paymentOn = paymentOn;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getFeesAmount() {
		return feesAmount;
	}

	public void setFeesAmount(int feesAmount) {
		this.feesAmount = feesAmount;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	

	public int savePayments() {
		int rows = -1;
		com.collegefees.dao.DAO dao = new com.collegefees.dao.DBImplementation();
		String query = "select paymentId from payments where studentId='" + getStudentId() + "'";
		if (!dao.isExists(query)) {
			query = "insert into payments(paymentOn,studentId,feesAmount) " + "values('"
					+ getPaymentOn() + "','" 
					+ getStudentId() + "','"
					+ getFeesAmount() + "')";
			rows = dao.putData(query);
		} else {
			rows = -2;
		}
		dao.closeConnection();
		return rows;
	}
	
	
	public int updatePayments() {
		int rows = -1;
		com.collegefees.dao.DAO dao = new com.collegefees.dao.DBImplementation();
		String query = "update payments set " + "paymentOn='" + getPaymentOn() 
						+ "',studentId = '" + getStudentId()
						+ "',feesAmount = '" + getFeesAmount() 
						+ "' where paymentId = " + getPaymentId();
		rows = dao.putData(query);
		dao.closeConnection();
		return rows;
	}
	
	public int deletePayments() {
		int rows = -1;
		com.collegefees.dao.DAO dao = new com.collegefees.dao.DBImplementation();
		String query = "delete from payments where paymentId=" + getPaymentId();
		rows = dao.putData(query);
		dao.closeConnection();
		return rows;
	}
	
	public JSONArray getPayments() {
		JSONArray array = new JSONArray();
		DAO dao = new DBImplementation();
		 String query = "select * from payments";
		ResultSet resultSet = dao.getData(query);
		try {
			while (resultSet.next()) {
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.put("paymentId", resultSet.getInt("paymentId"));
					jsonObject.put("paymentOn", resultSet.getString("paymentOn"));
					jsonObject.put("studentId", resultSet.getInt("studentId"));
					jsonObject.put("feesAmount",resultSet.getInt("feesAmount"));
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
	
	public JSONObject getPaymentsById() {
		JSONObject jsonObject = new JSONObject();
		DAO dao = new DBImplementation();
		String query = "select * from payments where paymentId = "+getPaymentId();
		ResultSet resultSet = dao.getData(query);
		try {
			if(resultSet.next()) {
					setPaymentId(resultSet.getInt("paymentId"));
					setPaymentOn(resultSet.getString("paymentOn"));
					setStudentId(resultSet.getInt("studentId"));
					setFeesAmount(resultSet.getInt("feesAmount"));
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
