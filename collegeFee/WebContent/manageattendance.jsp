
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Manage Attendence</title>

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">

<!-- Page level plugin CSS-->
<link href="vendor/datatables/dataTables.bootstrap4.css"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="css/sb-admin.css" rel="stylesheet">

</head>

<body id="page-top">

	<%@ include file="topnav.html"%>

	<div id="wrapper">

		<!-- Sidebar -->

		<%@ include file="sidenav.html"%>

		<div id="content-wrapper">

			<div class="container-fluid">


				<!-- Breadcrumbs-->
				<ol class="breadcrumb">
					<li class="breadcrumb-item active">MANAGE ATTENDENCE</li>
				</ol>
				<jsp:useBean id="attendance"
					class="com.collegefees.model.Attendance"></jsp:useBean>
				<jsp:setProperty property="*" name="attendance" />
				<%
				attendance.getAttendanceById();
				%>
				<!-- DataTables Example -->
				<div class="card mb-3">

					<div class="container py-sm-4 form-container">
		<h3 class="heading mb-4">Attendance</h3>

				<form action="" method="post">
				<%
					JSONArray att = attendance.getStudent();
				%>
				<div class="form-group" > 
				<label for="class" class="col-form-label">Student</label>
					<select	class="form-control" name="studentId" id="studentId">
					 	<option value="">Select Student</option>
					 	<%
					 		for(int i=0;i<att.length();i++){
					 			JSONObject atten = att.getJSONObject(i);
					 			if(atten.getInt("studentId")==attendance.getStudentId()){
					 	%>
					 	<option value='<%= atten.getInt("studentId") %>' selected="selected"><%= atten.getString("studentName") %></option>
					 	<%		
					 		}else{
								%>
								<option value='<%= atten.getInt("studentId") %>'><%= atten.getString("studentName") %></option>
								<%
								}
						}
					 	%>
					 </select>
				</div>
				
				
				<%
					JSONArray sub = attendance.getSubject();
				%>
				<div class="form-group" > 
				<label for="class" class="col-form-label">Attendance</label>
					<select	class="form-control" name="subjectId" id="subjectId">
					 	<option value="">Select Subject</option>
					 	<%
					 		for(int i=0;i<att.length();i++){
					 			JSONObject subj = sub.getJSONObject(i);
					 			if(subj.getInt("subjectId")==attendance.getSubjectId()){
					 	%>
					 	<option value='<%= subj.getInt("subjectId") %>' selected="selected"><%= subj.getString("subject") %></option>
					 	<%		
					 		}else{
								%>
								<option value='<%= subj.getInt("subjectId") %>'><%= subj.getString("subject") %></option>
								<%
								}
						}
					 	%>
					 </select>
				</div>
				<div class="form-group">
					<label for="recipient-name" class="col-form-label">Total Class</label> 
					<input type="text" class="form-control" placeholder=""
						name="totalClass" id="totalClass" 
						value='<jsp:getProperty property="totalClass" name="attendance"/>'>
					<input type="hidden" class="form-control" placeholder=""
						name="attendanceId" id="attendanceId"
						value='<jsp:getProperty property="attendanceId" name="attendance"/>'>
				</div>
				<div class="form-group">
					<label for="attendedClass" class="col-form-label">Attended Class</label> <input
						type="text" class="form-control" placeholder="" name="attendedClass"
						id="attendedClass" 
						value='<jsp:getProperty property="attendedClass" name="attendance"/>'>
				</div>
				<div class="right-w3l">
					<input type="submit" class="form-control bg-theme1" name="submit"
						value='<jsp:getProperty property="cmd" name="attendance"/>'>
				</div>
				</form>
				</div>
			</div>
		</div>
	</div>

<%
		if(request.getParameter("submit")!=null){
	%>
	<jsp:setProperty property="*" name="attendance"/>	
	<%		String message="";
			String cmd = request.getParameter("submit");
			if(cmd.equalsIgnoreCase("Save")){
				int rows =attendance.saveAttendance(); 
				if(rows>0){
					message = "Attendance Saved";
				}
				else if(rows==-2){
					message = "Only one Attendance can be Added";
				}
				else{
					message = "Unable to Save Attendance";
				}
				
			}
			else if(cmd.equalsIgnoreCase("Update")){
				if(attendance.updateAttendance()>0){
					message = "Attendance Updated";
				}
				else{
					message = "Unable to Update Attendance";
				}
			}
			else{
				if(attendance.deleteAttendance()>0){
					message = "Attendance Deleted";
				}
				else{
					message = "Unable to Delete Attendance";
				}
			}
			response.sendRedirect("viewattendance.jsp");
		}
	%>
	<!-- /#wrapper -->


	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary" href="index.jsp">Logout</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Page level plugin JavaScript-->
	<script src="vendor/datatables/jquery.dataTables.js"></script>
	<script src="vendor/datatables/dataTables.bootstrap4.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin.min.js"></script>

	<!-- Demo scripts for this page-->
	<script src="js/demo/datatables-demo.js"></script>

</body>
</html>