<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="description" content="" />
<meta name="author" content="" />
<!--[if IE]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <![endif]-->
<link rel="shortcut icon" href="resource/img/service-manager-icon.png"
	type="image/png">
<title>ManagerApp</title>
<!-- BOOTSTRAP CORE STYLE CSS -->
<link href="resource/css/bootstrap.css" rel="stylesheet" />
<link href="resource/css/my.css" rel="stylesheet" />
<!-- FONTAWESOME STYLE CSS -->
<link href="resource/css/font-awesome.min.css" rel="stylesheet" />
<!-- CUSTOM STYLE CSS -->
<link href="resource/css/style.css" rel="stylesheet" />
<!-- GOOGLE FONT -->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />

</head>

<body>
	<!-- Logo & Navigation starts -->
	<jsp:include page="include/header.jsp" />
	<!-- Logo & Navigation end -->

	<!--/.NAVBAR END-->

	<section id="intro">
		<div class="container">
			<div class="row text-center">
				<div class="col-md-16">
					<div class="row text-center pad-row  ">
						<div class="col-md-4 col-sm-4 ">
							<div class="container">

								<form class="form-signin" role="form" _lpchecked="1" action="adminLogIn.html">
									<h2 class="form-signin-heading">Please sign in</h2>
									<input class="form-control" name="User"
										placeholder="User Name"  autofocus=""
										autocomplete="off"
										style="cursor: auto; ">
									<input type="password" class="form-control" name="Password"
										placeholder="Password" autocomplete="off"
										style="cursor: auto; ">
										<br/><br/>
									<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
										in</button>
								</form>

							</div>
						</div>
						
					</div>

				</div>

			</div>
		</div>
	</section>

	<!--/.INTRO END-->



	<section id="footer-sec"></section>
	<!--/.FOOTER END-->
	<!-- JAVASCRIPT FILES PLACED AT THE BOTTOM TO REDUCE THE LOADING TIME  -->
	<!-- CORE JQUERY  -->
	<script src="resource/plugins/jquery-1.10.2.js"></script>
	<!-- BOOTSTRAP SCRIPTS  -->
	<script src="resource/plugins/bootstrap.js"></script>
	<!-- CUSTOM SCRIPTS  -->
	<script src="resource/js/custom.js"></script>
</body>
</html>
