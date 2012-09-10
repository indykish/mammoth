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
	<link rel="stylesheet" href='<c:url value="/resources/css/slick.grid.css"/>'
	type="text/css" />
	

<script type="text/javascript"
	src='<c:url value="/resources/js/bootstrap.min.js"/>'></script>
<script type="text/javascript"
	src='<c:url value="/resources/js/jquery.min.js"/>'></script>
<script type="text/javascript"
	src='<c:url value="/resources/js/spin.min.js"/>'></script>
	<script type="text/javascript"
	src='<c:url value="/resources/js/jquery-ui-1.8.16.custom.min.js"/>'></script>
	<script type="text/javascript"
	src='<c:url value="/resources/js/jquery.event.drag-2.0.min.js"/>'></script>
		<script type="text/javascript"
	src='<c:url value="/resources/js/slick.core.js"/>'></script>
<script type="text/javascript"
	src='<c:url value="/resources/js/slick.editors.js"/>'></script>
<script type="text/javascript"
	src='<c:url value="/resources/js/slick.grid.js"/>'></script>
	<script type="text/javascript"
	src='<c:url value="/resources/js/slick.dataview.js"/>'></script>
<script type="text/javascript"
	src='<c:url value="/resources/js/slick.formatters.js"/>'></script>
	<script type="text/javascript"
	src='<c:url value="/resources/js/slick.rowselectionmodel.js"/>'></script>
	<script type="text/javascript"
	src='<c:url value="/resources/js/slick.cellselectionmodel.js"/>'></script>
	<script type="text/javascript"
	src='<c:url value="/resources/js/slick.cellrangeselector.js"/>'></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.3/jquery.min.js"></script>
	
</head>
<body>
	<div class="container">
	
	
		<tiles:insertAttribute name="header" />
		
	<div class="container-fluid">
    <div class="row-fluid">
    <div class="span3">
    <tiles:insertAttribute name="menu"/>
    </div>
    <div class="offset1 span7">
    <tiles:insertAttribute name="subhead"/>
    <tiles:insertAttribute name="body" />
    </div>
    </div>
    </div>
		
		
		
		
		<div class="section">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>

	<!-- JQuery will invoke via the spinner and display a loading modal lock. -->
	<div id="loading" />
	</body>
</html>