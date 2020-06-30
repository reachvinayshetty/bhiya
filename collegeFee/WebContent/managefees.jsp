
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

<title>Manage Fees</title>

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
					<li class="breadcrumb-item active">MANAGE FEES</li>
				</ol>
				<jsp:useBean id="fees"
					class="com.collegefees.model.Fees"></jsp:useBean>
				<jsp:setProperty property="*" name="fees" />
				<%
					fees.getFeesById();
				%>
				<!-- DataTables Example -->
				<div class="card mb-3">

					<div class="form-group">
					<label for="categoryId" class="col-form-label">Category</label>
					 <select name="categoryId"  style="padding-left: 10px; padding-right: 70px;">
					 	<option>Select Category</option>
					 	<%
					 	JSONArray c=fees.getCategoryTypes();
					 		for(int i=0;i<c.length();i++){
					 			
					 			JSONObject cat = c.getJSONObject(i);
					 			if(cat.getInt("categoryId")==fees.getCategoryId()){
					 	%>
					 	<option value='<%= cat.getInt("categoryId") %>' selected="selected"><%= cat.getString("categoryName") %></option>
					 	<%		
					 		}else{
							 	%>
							 	<option value='<%= cat.getInt("categoryId") %>'><%= cat.getString("categoryName") %></option>
							 	<%	
							 			}
				 		}
				 	%>
					 </select>
				</div>
				<div class="form-group">
					<label for="incomeId" class="col-form-label" >Income</label>
					 <select name="incomeId"  style="margin-left: 12px; padding-left: 10px;padding-right: 85px;">
					 	<option>Select Income</option>
					 	<%
					 	JSONArray inc=fees.getIncomeTypes();
					 		for(int i=0;i<c.length();i++){
					 			
					 			JSONObject inco = inc.getJSONObject(i);
					 			if(inco.getInt("incomeId")==fees.getIncomeId()){
					 	%>
					 	<option value='<%= inco.getInt("incomeId") %>' selected="selected"><%= inco.getString("income") %></option>
					 	<%		
					 		}else{
							 	%>
							 	<option value='<%= inco.getInt("incomeId") %>'><%= inco.getString("income") %></option>
							 	<%	
							 			}
				 		}
				 	%>
					 </select>
				</div>
				
				<div class="form-group">
					<label for="recipient-name" class="col-form-label">Fee
						</label> <input type="text" class="form-control" placeholder=""
						name="fee" id="fee" 
						value='<jsp:getProperty property="fee" name="fees"/>'>
					<input type="hidden" class="form-control" placeholder=""
						name="feesId" id="departmentId"
						value='<jsp:getProperty property="feesId" name="fees"/>'>
				</div>
				<div class="right-w3l">
					<input type="submit" class="form-control bg-theme1" name="submit"
						value='<jsp:getProperty property="cmd" name="fees"/>'>
				</div>
				</form>
								</div>
			</div>
		</div>
	</div>

<%
		if(request.getParameter("submit")!=null){
	%>
	<jsp:setProperty property="*" name="fees"/>	
	<%		
	String message="";
			String cmd = request.getParameter("submit");
			if(cmd.equalsIgnoreCase("Save")){
				int rows =fees.saveFees() ;
				if(rows>0){
					message = "Fees Saved";
				}
				else if(rows==-2){
					message = "Only one Fees can be Added";
				}
				else{
					message = "Unable to Save Fees";
				}
				
			}
			else if(cmd.equalsIgnoreCase("Update")){
				if(fees.updateFees()>0){
					message = "Fees Updated";
				}
				else{
					message = "Unable to Update Fees";
				}
			}
			else{
				if(fees.deleteFees()>0){
					message = "Fees Deleted";
				}
				else{
					message = "Unable to Delete Fees";
				}
			}
			response.sendRedirect("viewfees.jsp");
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