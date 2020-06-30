
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

<title>Manage Department</title>

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
					<li class="breadcrumb-item active">MANAGE DEPARTMENT</li>
				</ol>
				<jsp:useBean id="departments"
					class="com.collegefees.model.Departments"></jsp:useBean>
				<jsp:setProperty property="*" name="departments" />
				<%
					departments.getDepartmentsById();
				%>
				<!-- DataTables Example -->
				<div class="card mb-3">
<div class="form-group" > 
				<label for="class" class="col-form-label">Department</label>
					<form class="form" method="post" action="">



						<input type="hidden" name="departmentId" id="departmentId" class="form-control"
							value='<jsp:getProperty property="departmentId" name="departments"/>'> 
						<input class="form-control" type="text" placeholder="Department Name" name="departmentName" required
							value='<jsp:getProperty property="departmentName" name="departments"/>'> <br>
						<input class="form-control" type="text" placeholder="HOD"
							name="hod" required
							value='<jsp:getProperty property="hod" name="departments"/>'> <br>
						<input class="form-control" type="email" placeholder="MailId" name="mailId"
							required
							value='<jsp:getProperty property="mailId" name="departments"/>'>
						<br>
						<input class="form-control" type="password" placeholder="Password"
							name="password" required
							value='<jsp:getProperty property="password" name="departments"/>'> <br>
				
						<br> <input class="submit-btn" type="submit" name="cmd"
							value='<jsp:getProperty property="cmd" name="departments"/>'>

					</form>

				</div>
			</div>
		</div>
	</div>

<%
		if(request.getParameter("cmd")!=null){
	%>
	<jsp:setProperty property="*" name="departments"/>	
	<%		
			String message = "";
			String cmd = request.getParameter("cmd");
			if(cmd.equalsIgnoreCase("Save")){
				int rows =departments.saveDepartments() ;
				if(rows>0){
					message = "Department Saved";
				}
				else if(rows==-2){
					message = "Department Exists";
				}
				else{
					message = "Unable to Save Department";
				}
				
			}
			else if(cmd.equalsIgnoreCase("Update")){
				if(departments.updateDepartments()>0){
					message = "Department Updated";
				}
				else{
					message = "Unable to Update Department";
				}
			}
			else{
				if(departments.deleteDepartments()>0){
					message = "Department Deleted";
				}
				else{
					message = "Unable to Delete Department";
				}
			}
			session.setAttribute("message", message);
			response.sendRedirect("department.jsp");
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