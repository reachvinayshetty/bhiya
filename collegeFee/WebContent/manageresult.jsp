
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

<title>Manage Results</title>

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
					<li class="breadcrumb-item active">MANAGE RESULT</li>
				</ol>
				<jsp:useBean id="results"
					class="com.collegefees.model.Results"></jsp:useBean>
				<jsp:setProperty property="*" name="results" />
				<%
				results.getResultsById();
				%>
				<!-- DataTables Example -->
				<div class="card mb-3">

					<form action="" method="post">
				<div class="form-group">
					<label for="studentId" class="col-form-label">Student</label>
					 <select class="form-control" name="studentId">
					 	<option>Select Student</option>
					 	<%
					 	JSONArray stu=results.getStudentTypes();
					 	
					 		for(int i=0;i<stu.length();i++){
					 			
					 			JSONObject stud = stu.getJSONObject(i);
					 			if(stud.getInt("studentId")==results.getStudentId()){
					 	%>
					 	<option value='<%= stud.getInt("studentId") %>' selected="selected"><%= stud.getString("studentName") %></option>
					 	<%	
					 			}
					 			else{
								 	%>
								 	<option value='<%= stud.getInt("studentId") %>' ><%= stud.getString("studentName") %></option>
								 	<%	
								 			}
					 		}
					 	%>
					 </select>
				</div>
				<div class="form-group">
					<label for="subjectId" class="col-form-label">Subject</label>
					 <select class="form-control" name="subjectId">
					 	<option>Select Subject</option>
					 	<%
					 	JSONArray  sub=results.getSubjectTypes();
					 	
					 		for(int i=0;i<sub.length();i++){
					 			
					 			JSONObject subj = sub.getJSONObject(i);
					 			if(subj.getInt("subjectId")==results.getSubjectId()){
					 	%>
					 	<option value='<%= subj.getInt("subjectId") %>' selected="selected"><%= subj.getString("subject") %></option>
					 	<%	
					 			}
					 			else{
								 	%>
								 	<option value='<%= subj.getInt("subjectId") %>' ><%= subj.getString("subject") %></option>
								 	<%	
								 			}
					 		}
					 	%>
					 </select>
				</div>
				<div class="form-group">
					<label for="result" class="col-form-label">Result</label> 
					<select class="form-control" name="result" id="result">
						<option value="">Select Result</option>
						<%
						//String res=results.getResult();
						%>
						<option value="Pass">Pass</option>
						<option value="Fail">Fail</option>
						
					</select>
				</div>
				<div class="right-w3l">
					<input type="submit" class="form-control bg-theme1" name="submit"
						value='<jsp:getProperty property="cmd" name="results"/>'>
				</div>
				</form>

				</div>
			</div>
		</div>
	</div>

<%
		if(request.getParameter("submit")!=null){
	%>
	<jsp:setProperty property="*" name="results"/>	
	<%		String message="";
			String cmd = request.getParameter("submit");
			if(cmd.equalsIgnoreCase("Save")){
				int rows =results.saveResults();
				if(rows>0){
					message = "Results Saved";
				}
				else if(rows==-2){
					message = "Only one Results can be Added";
				}
				else{
					message = "Unable to Save Results";
				}
				
			}
			else if(cmd.equalsIgnoreCase("Update")){
				if(results.updateResults()>0){
					message = "Results Updated";
				}
				else{
					message = "Unable to Update Results";
				}
			}
			else{
				if(results.deleteResults()>0){
					message = "Results Deleted";
				}
				else{
					message = "Unable to Delete Results";
				}
			}
			response.sendRedirect("viewresults.jsp");
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