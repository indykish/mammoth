<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<link rel="shortcut icon"
	href='<c:url value="/resources/favicon.ico" />' />
<!-- Bootstrap -->
<link rel="stylesheet"
	href='<c:url value="/resources/css/bootstrap.min.css" />'
	type="text/css" media="screen, projection" />
<link rel="stylesheet" href='<c:url value="/resources/css/megam.css"/>'
	type="text/css" media="screen" />
<script type="text/javascript"
	src='<c:url value="/resources/js/bootstrap.min.js"/>'></script>
<script type="text/javascript"
	src='<c:url value="/resources/js/jquery.min.js"/>'></script>
<script type="text/javascript"
	src='<c:url value="/resources/js/spin.min.js"/>'></script>
</head>
<body>
	<div class="container">
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="body" />
		<hr>
		</hr>
		<div class="section">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>

	<!-- JQuery will invoke via the spinner and display a loading modal lock. -->
	<div id="loading" />	
		
		<!-- Modal -->
	<div class="modal  hide fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
			<h3 id="myModalLabel">Modal Heading</h3>
		</div>
		<div class="modal-body"></div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal">Close</button>
			<button class="btn btn-primary">Save changes</button>			
		</div>
	</div>
</body>
</html>