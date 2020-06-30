<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--

=========================================================
* Gaia Bootstrap Template - v1.0.1
=========================================================

* Product Page: https://www.creative-tim.com/product/gaia-bootstrap-template
* Licensed under MIT (https://github.com/creativetimofficial/gaia-bootstrap-template/blob/master/LICENSE.md)
* Copyright 2019 Creative Tim (http://www.creative-tim.com)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

-->
<html lang="en">

<head>
<meta charset="utf-8" />
<link rel="apple-touch-icon" sizes="76x76"
	href="assets/img/apple-icon.png">
<link rel="icon" type="image/png" sizes="96x96"
	href="assets/img/favicon.png">

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>COLLEGE FEE INDEX PAGE</title>
<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
	name='viewport' />
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<link href="assets/css/gaia.css" rel="stylesheet" />
<link href="assets/css/login-register.css" rel="stylesheet" />

<!--     Fonts and icons     -->
<link
	href='https://fonts.googleapis.com/css?family=Cambo|Poppins:400,600'
	rel='stylesheet' type='text/css'>
<link
	href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"
	rel="stylesheet">
<link href="assets/css/fonts/pe-icon-7-stroke.css" rel="stylesheet">
</head>

<body>

	<nav class="navbar navbar-default navbar-transparent navbar-fixed-top"
		color-on-scroll="200"> <!-- if you want to keep the navbar hidden you can add this class to the navbar "navbar-burger"-->
	<div class="container">
		<div class="navbar-header">
			<button id="menu-toggle" type="button" class="navbar-toggle"
				data-toggle="collapse" data-target="#example">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar bar1"></span> <span class="icon-bar bar2"></span> <span
					class="icon-bar bar3"></span>
			</button>
			<a href="http://www.creative-tim.com" class="navbar-brand"> COLLEGE FEE
			</a>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav navbar-right navbar-uppercase">
				<li><a href="#" class="btn btn-danger btn-fill"
					data-toggle="modal" data-target="#loginModal">Login</a></li>
			</ul>
		</div>
	</div>
	</nav>


	<div class="section section-header">
		<div class="parallax filter filter-color-red">
			<div class="image"
				style="background-image: url('assets/img/collegefee1.jpg')">
			</div>
			<div class="container">
				<div class="content">
					<div class="title-area">
						<p></p>
						<h1 class="title-modern">COLLEGE FEE</h1>
						<h3>
							College Fee Management Web Application Software!
							</h2>
					</div>
				</div>

			</div>
		</div>
	</div>


	
	<div class="modal fade login" id="loginModal">
		<div class="modal-dialog login animated">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Login</h4>
				</div>
				<div class="modal-body">
					<div class="box">
						<div class="content">
							<div class="error"></div>
							<div class="form loginBox">
								<form method="post" action="" accept-charset="UTF-8">
									<input id="adminId" class="form-control" type="text"
										placeholder="Email" name="adminId"> <input
										id="password" class="form-control" type="password"
										placeholder="Password" name="password"> <input
										class="btn btn-default btn-login" type="submit" value="Login"
										name="login">
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>
	
	<!--   core js files    -->
	<script src="assets/js/jquery.min.js" type="text/javascript"></script>
	<script src="assets/js/bootstrap.js" type="text/javascript"></script>

	<!--  js library for devices recognition -->
	<script type="text/javascript" src="assets/js/modernizr.js"></script>

	<!--  script for google maps   -->
	<script type="text/javascript"
		src="https://maps.googleapis.com/maps/api/js"></script>

	<!--   file where we handle all the script from the Gaia - Bootstrap Template   -->
	<script type="text/javascript" src="assets/js/gaia.js"></script>
</body>
</html>
