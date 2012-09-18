<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="container">
	<div class="row-fluid">

		<div class="span7">
			<h2>Welcome to Mammoth</h2>
			<div id="message_display_box" class="alert alert-success">Right
				now we are building connectors for Salesforce, Google App, Liferay,
				SugarCRM,.. Refer our documentation from blog.megam.co (or)
				www.megam.co</div>
			<div class="span7">
				<h3>Our hunter is there to help you.</h3>
			</div>
			<div class="span3">
				<div id="message_display_box">
					<a href="<c:url value="/" />"> <img
						src="<c:url value="/resources/images/mammoth_hunter.png"/>"
						alt="Megam" width="240" height="100" />
					</a>
				</div>
				<div id="message_display">Read our blog links for more information. Feel free to give a try. Refer our
					documentation from blog.megam.co (or) www.megam.co</div>
			</div>			
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
	});
</script>
