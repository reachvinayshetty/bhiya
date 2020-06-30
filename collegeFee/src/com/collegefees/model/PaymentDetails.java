package com.collegefees.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.collegefees.dao.DAO;
import com.collegefees.dao.DBImplementation;

public class PaymentDetails implements Serializable{
	private int detailsId;
	private int paymentId;
	private int feesId;
	private int subjectId;
	private String cmd;
	
	public PaymentDetails(){
		detailsId=0;
		paymentId=0;
		feesId=0;
		subjectId=0;
		cmd="save";
	}

	public int getDetailsId() {
		return detailsId;
	}

	public void setDetailsId(int detailsId) {
		this.detailsId = detailsId;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public int getFeesId() {
		return feesId;
	}

	public void setFeesId(int feesId) {
		this.feesId = feesId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	
	public int savePaymentDetails() {
		int rows = -1;
		com.collegefees.dao.DAO dao = new com.collegefees.dao.DBImplementation();
		String query = "select detailsId from payment_details where subjectId='" + getSubjectId() + "'";
		if (!dao.isExists(query)) {
			query = "insert into payment_details(paymentId,feesId,subjectId) " + "values('"
					+ getPaymentId() + "','" 
					+ getFeesId() + "','"
					+ getSubjectId() + "')";
			rows = dao.putData(query);
		} else {
			rows = -2;
		}
		dao.closeConnection();
		return rows;
	}
	
	public int deletePaymentDetails() {
		int rows = -1;
		com.collegefees.dao.DAO dao = new com.collegefees.dao.DBImplementation();
		String query = "delete from payment_details where detailsId=" + getDetailsId();
		rows = dao.putData(query);
		dao.closeConnection();
		return rows;
	}
	
	public int updatePaymentDetails() {
		int rows = -1;
		com.collegefees.dao.DAO dao = new com.collegefees.dao.DBImplementation();
		String query = "update payment_details set " + "paymentId='" + getPaymentId() 
						+ "',feesId = '" + getFeesId()
						+ "',subjectId = '" + getSubjectId() 
						+ "' where detailsId = " + getDetailsId();
		rows = dao.putData(query);
		dao.closeConnection();
		return rows;
	}
	
	public JSONArray getPaymentDetails() {
		JSONArray array = new JSONArray();
		DAO dao = new DBImplementation();
		 String query = "SELECT * FROM payment_details\r\n" + 
		 		"INNER	JOIN payments\r\n" + 
		 		"ON payments.paymentId = payment_details.paymentId\r\n" + 
		 		"INNER	JOIN fees\r\n" + 
		 		"ON fees.feesId = payment_details.feesId\r\n" + 
		 		"INNER	JOIN subjects\r\n" + 
		 		"ON subjects.subjectId = payment_details.subjectId";
		ResultSet resultSet = dao.getData(query);
		try {
			while (resultSet.next()) {
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.put("detailsId", resultSet.getInt("detailsId"));
					jsonObject.put("paymentId", resultSet.getInt("paymentId"));
					jsonObject.put("paymentOn", resultSet.getString("paymentOn"));
					jsonObject.put("feesId", resultSet.getInt("feesId"));
					jsonObject.put("fee", resultSet.getInt("fee"));
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
	
	
	public JSONObject getPaymentDetailsById() {
		JSONObject jsonObject = new JSONObject();
		DAO dao = new DBImplementation();
		String query = "select * from payment_details where detailsId = "+getDetailsId();
		ResultSet resultSet = dao.getData(query);
		try {
			if(resultSet.next()) {
					setDetailsId(resultSet.getInt("detailsId"));
					setPaymentId(resultSet.getInt("paymentId"));
					setFeesId(resultSet.getInt("feesId"));
					setSubjectId(resultSet.getInt("subjectId"));
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
