
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

<title>Manage Student</title>

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
					<li class="breadcrumb-item active">MANAGE STUDENT</li>
				</ol>
				<jsp:useBean id="students"
					class="com.collegefees.model.Students"></jsp:useBean>
				<jsp:setProperty property="*" name="students" />
				<%
				students.getStudentsById();
				%>
				<!-- DataTables Example -->
				<div class="card mb-3">

					<form action="" method="post">
				<div class="form-group">
					<label for="recipient-name" class="col-form-label">Student
						Name</label> <input type="text" class="form-control" placeholder=""
						name="studentName" id="studentName" 
						value='<jsp:getProperty property="studentName" name="students"/>'>
					<input type="hidden" class="form-control" placeholder=""
						name="studentId" id="studentId"
						value='<jsp:getProperty property="studentId" name="students"/>'>
				</div>
				<div class="form-group">
					<label for="mobile" class="col-form-label">Mobile</label> <input
						type="text" class="form-control" placeholder="" name="mobile"
						id="mobile" 
						value='<jsp:getProperty property="mobile" name="students"/>'>
				</div>
				<div class="form-group">
					<label for="regNo" class="col-form-label">Register No</label> <input
						type="text" class="form-control" placeholder="" name="regNo"
						id="regNo" 
						value='<jsp:getProperty property="regNo" name="students"/>'>
				</div>
				<div class="form-group">
					<label for="address" class="col-form-label">Address</label> <input
						type="text" class="form-control" placeholder="" name="address"
						id="address" 
						value='<jsp:getProperty property="address" name="students"/>'>
				</div>
				<div class="form-group">
					<label for="mailId" class="col-form-label">mailId</label> <input
						type="email" class="form-control" placeholder="" name="mailId"
						id="mailId" 
						value='<jsp:getProperty property="mailId" name="students"/>'>
				</div>
				<div class="form-group">
					<label for="password" class="col-form-label">password</label> <input
						type="password" class="form-control" placeholder=""
						name="password" id="password" 
						value='<jsp:getProperty property="password" name="students"/>'>
				</div>
				<div class="form-group">
					<label for="parentName" class="col-form-label">Parent Name</label> <input
						type="text" class="form-control" placeholder="" name="parentName"
						id="parentName" 
						value='<jsp:getProperty property="parentName" name="students"/>'>
				</div>
				<div class="form-group">
					<label for="sem" class="col-form-label">Semester</label> 
					<select class="form-control" name="sem" id="sem">
						<option value="">Select Semester</option>
						<%
							int semNo = students.getSem();
							for(int i=1;i<=6;i++){
								if(semNo == i){
									%>
						<option value="<%=i%>" selected="selected"><%= i %></option>
						<%
								}
								else{
									%>
						<option value="<%=i%>"><%= i %></option>
						<%
								}
							}
						%>
					</select>
				</div>
				<div class="form-group">
					<label for="categoryId" class="col-form-label">Category</label>
					 <select class="form-control" name="categoryId">
					 	<option>Select Category</option>
					 	<%
					 	JSONArray jsonArray=students.getCategoryTypes();
					 	
					 		for(int i=0;i<jsonArray.length();i++){
					 			
					 			JSONObject cat = jsonArray.getJSONObject(i);
					 			if(cat.getInt("categoryId")==students.getCategoryId()){
					 	%>
					 	<option value='<%= cat.getInt("categoryId") %>' selected="selected"><%= cat.getString("categoryName") %></option>
					 	<%	
					 			}
					 			else{
								 	%>
								 	<option value='<%= cat.getInt("categoryId") %>' ><%= cat.getString("categoryName") %></option>
								 	<%	
								 			}
					 		}
					 	%>
					 </select>
				</div>
				<div class="form-group">
					<label for="incomeId" class="col-form-label">Income</label>
					 <select class="form-control" name="incomeId">
					 	<option>Select Income</option>
					 	<%
					 	JSONArray inc=students.getIncomeTypes();
					 	
					 		for(int i=0;i<inc.length();i++){
					 			
					 			JSONObject inco = inc.getJSONObject(i);
					 			if(inco.getInt("incomeId")==students.getIncomeId()){
					 	%>
					 	<option value='<%= inco.getInt("incomeId") %>' selected="selected"><%= inco.getString("income") %></option>
					 	<%	
					 			}
					 			else{
								 	%>
								 	<option value='<%= inco.getInt("incomeId") %>' ><%= inco.getString("income") %></option>
								 	<%	
								 			}
					 		}
					 	%>
					 </select>
				</div>			
				<div class="form-group">
					<label for="academicYear" class="col-form-label">AcademicYear</label> <input
						type="text" class="form-control" placeholder="" name="academicYear"
						id="academicYear" 
						value='<jsp:getProperty property="academicYear" name="students"/>'>
				</div>
				<div class="form-group">
					<label for="departmentId" class="col-form-label">Department</label>
					 <select class="form-control" name="departmentId">
					 	<option>Select Department</option>
					 	<%
					 	JSONArray dep=students.getDepartmentTypes();
					 	
					 		for(int i=0;i<dep.length();i++){
					 			
					 			JSONObject depa = dep.getJSONObject(i);
					 			if(depa.getInt("departmentId")==students.getDepartmentId()){
					 	%>
					 	<option value='<%= depa.getInt("departmentId") %>' selected="selected"><%= depa.getString("departmentName") %></option>
					 	<%	
					 			}
					 			else{ 
								 	%>
								 	<option value='<%= depa.getInt("departmentId") %>' ><%= depa.getString("departmentName") %></option>
								 	<%	
								 			}
					 		}
					 	%>
					 </select>
				</div>
				<div class="right-w3l">
					<input type="submit" class="form-control bg-theme1" name="submit"
						value='<jsp:getProperty property="cmd" name="students"/>'>
				</div>
				</form>
				</div>
			</div>
		</div>
	</div>

<%
		if(request.getParameter("submit")!=null){
	%>
	<jsp:setProperty property="*" name="students"/>	
	<%		
	String message="";
			String cmd = request.getParameter("submit");
			if(cmd.equalsIgnoreCase("Save")){
				int rows =students.saveStudents() ;
				if(rows>0){
					message = "Student Saved";
				}
				else if(rows==-2){
					message = "Only one Student can be Added";
				}
				else{
					message = "Unable to Save Student";
				}
				
			}
			else if(cmd.equalsIgnoreCase("Update")){
				if(students.updateStudents()>0){
					message = "Student Updated";
				}
				else{
					message = "Unable to Update Student";
				}
			}
			else{
				if(students.deleteStudents()>0){
					message = "Student Deleted";
				}
				else{
					message = "Unable to Delete Student";
				}
			}
			response.sendRedirect("viewstudents.jsp");
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