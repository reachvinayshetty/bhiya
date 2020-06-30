
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

<title>Manage Subjects</title>

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
					<li class="breadcrumb-item active">MANAGE SUBJECT</li>
				</ol>
				<jsp:useBean id="subjects"
					class="com.collegefees.model.Subjects"></jsp:useBean>
				<jsp:setProperty property="*" name="subjects" />
				<%
				subjects.getSubjectsById();
				%>
				<!-- DataTables Example -->
				<div class="card mb-3">

					<form action="" method="post">
				<div class="form-group">
					<label for="class" class="col-form-label">Semester</label>
					<select	class="form-control" name="sem" id="sem">
						<option value="">Select Sem</option>
						<%
							int classNo = subjects.getSem();
							for(int i=1;i<=6;i++){
								if(classNo == i){
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
					<label for="recipient-name" class="col-form-label">Subject</label> 
					<input type="text" class="form-control" placeholder=""
						name="subject" id="subject" 
						value='<jsp:getProperty property="subject" name="subjects"/>'>
					<input type="hidden" class="form-control" placeholder=""
						name="subjectId" id="subjectId"
						value='<jsp:getProperty property="subjectId" name="subjects"/>'>
				</div>
				<div class="form-group">
					<label for="subjectCode" class="col-form-label">Subject Code</label> <input
						type="text" class="form-control" placeholder="" name="subjectCode"
						id="subjectCode" 
						value='<jsp:getProperty property="subjectCode" name="subjects"/>'>
				</div>
		<% 		
				
		if(request.getParameter("submit")!=null){
	%>
	<jsp:setProperty property="*" name="subjects"/>	
	<%		
	String message="";
			String cmd = request.getParameter("submit");
			if(cmd.equalsIgnoreCase("Save")){
				int rows =subjects.saveSubjects(); 
				if(rows>0){
					message = "Subjects Saved";
				}
				else if(rows==-2){
					message = "Only one Subjects can be Added";
				}
				else{
					message = "Unable to Save Subjects";
				}
				
			}
			else if(cmd.equalsIgnoreCase("Update")){
				if(subjects.updateSubjects()>0){
					message = "Subjects Updated";
				}
				else{
					message = "Unable to Update Subjects";
				}
			}
			else{
				if(subjects.deleteSubjects()>0){
					message = "Subjects Deleted";
				}
				else{
					message = "Unable to Delete Subjects";
				}
			}
			response.sendRedirect("viewsubject.jsp");
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