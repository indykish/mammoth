<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
	<tiles:insertAttribute name="subhead" />

	<h1>Create new user</h1>
	<form:form modelAttribute="user" method="post">
		<table>
			<tr>
				<td>Username</td>
				<td><form:input path="userName" /></td>
			</tr>
			<tr>
				<td>Firstname</td>
				<td><form:input path="givenName" /></td>
			</tr>
			<tr>
				<td>Lastname</td>
				<td><form:input path="familyName" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><form:input path="password" /></td>
			</tr>
			<tr>
				<td>Domain</td>
				<td><form:select path="domainName">
						<form:option value="none" label="----Select ----" />
						<form:option value="megam.co.in" label="megam.co.in"
							selected="selected" />
					</form:select></td>
			</tr>
			<tr>
				<td>Admin Email</td>
				<td><form:select path="adminEmail">
						<form:option value="none" label="----Select ----" />
						<form:option value="admin@megam.co.in" label="admin@megam.co.in"
							selected="selected" />
					</form:select></td>
			</tr>
			<tr>
				<td>AdminPassword</td>
				<td><form:password path="adminPassword" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Create" id="createuser"
					class="btn btn-primary btn-large" onclick="createUser()" /></td>
			</tr>
		</table>
	</form:form>
</div>



<script type="text/javascript">
	var spinner;

	function fireSpinner() {
		var opts = {
			lines : 12, // The number of lines to draw
			length : 12, // The length of each line
			width : 7, // The line thickness
			radius : 23, // The radius of the inner circle
			color : '#fff', // #rgb or #rrggbb
			speed : 1, // Rounds per second
			trail : 60, // Afterglow percentage
			shadow : true, // Whether to render a shadow
			hwaccel : false
		// Whether to use hardware acceleration
		}, target = document.getElementById('loading');
		$('#loading').fadeIn();
		spinner = new Spinner(opts).spin(target);
	}

	$('#createuser').ajaxStart(function() {
		fireSpinner();
	}).ajaxStop(function() {
		$('#loading').fadeOut();
		spinner.stop();
	});

	$(document).ready(function() {
		$('form').submit(function(event) {
			var dat = $(this).serializeArray();

			$.post('./googleapps/create', dat, function(data) {
				window.location.reload(true);

			}, function(data) {
				var response = JSON.parse(data.response);
				alert("Error: " + response[0].id);
			});
			return false;
		});
	});

	function clearup_stuff() {
		$('#error_message_box').hide();
	}
</script>



