
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
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

<title>Fees</title>

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





				<!-- DataTables Example -->
				<div class="card mb-3">

					<form class="form" action="managefees.jsp">

						<button>Add FEES+</button>

					</form>
<jsp:useBean id="fees" class="com.collegefees.model.Fees"></jsp:useBean>
	<jsp:setProperty property="*" name="fees"/>
					<div class="card-body">
						<div class="table-responsive">
							<table class="table table-bordered" id="dataTable" width="100%"
								cellspacing="0">
								<thead>

									<tr>
										<th>Type</th>
										<th>Category</th>
										<th>Income</th>
										
									</tr>
								</thead>
								<tbody>

<%
JSONArray array = fees.getFees();
						for(int i=0;i<array.length();i++){
							JSONObject jsonObject = array.getJSONObject(i);
					%>
					<tr>
						
						<td><%= jsonObject.getInt("fee") %></td>
						<td ><%= jsonObject.optString("typeName") %></td>
						<td ><%= jsonObject.optString("categoryName") %></td>
						<td ><%= jsonObject.optInt("income") %></td>
						<td>
							<a href="fees.jsp?feesId=<%= jsonObject.getInt("feesId")%>">Edit</a> / 
							<a href="managefees.jsp?submit=Delete&feesId=<%= jsonObject.getInt("feesId")%>" onclick="return onDelete();">Delete</a>
						</td>
					</tr>
					<%		
						}
					%>
								</tbody>
							</table>
						</div>
					</div>
					<div class="card-footer small text-muted"></div>

				</div>

				<p class="small text-center text-muted my-5">

					<em>More table examples coming soon...</em>
				</p>

			</div>


		</div>
	</div>
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
						<span aria-hidden="true">�</span>
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
