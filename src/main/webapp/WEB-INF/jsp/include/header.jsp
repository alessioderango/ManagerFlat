<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Manager App <img
					src="resource/img/service-manager-icon.png"
					style="height: auto; width: auto; max-width: 30px; max-height: 30px;"></a>
			</div>



			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<c:if test="${not empty admin}">
						<li><a href="logout.html">LOG OUT ${admin.name}</a></li>
					</c:if>
					
				</ul>
			</div>

		</div>
	</div>
</body>
</html>