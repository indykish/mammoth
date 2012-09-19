<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="container">
	<div class="row-fluid">

		<div class="span7">
			<h2>Welcome to Mammoth</h2>
			<div id="message_display_box" class="alert alert-success">Right
				now we are building connectors for Salesforce, Google App, Liferay,
				SugarCRM,.. Refer our documentation from <a href="http://blog.megam.co/">http://blog.megam.co</a>(or) <a href="#">www.megam.co</a></div>
			<div class="span7">
				<div id="message_display_box">
					<a href="<c:url value="/" />"> <img
						src="<c:url value="/resources/images/MammothBack.png"/>"
						alt="Megam" width="240" height="100" />
					</a>
				</div>	
				
			</div>
			<div class="span3">
				<h3>Come on we will help you...</h3>
				<div id="message_display">Read our blog links for more information. Feel free to give a try. Refer our
					documentation from <a href="http://blog.megam.co/">http://blog.megam.co</a>(or) <a href="#">www.megam.co</a></div>
			</div>			
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
	});
</script>
