<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<div class="container">
	<h1>Create new user</h1>
	<form:form method="POST" id="createuser" action="create"
		modelAttribute="user">
		<table>
			<tr>
				<td>Username</td>
				<td><form:input path="userName" /></td>
			</tr>
			<tr>
				<td>ProfileId</td>
				<td><form:select path="profileId" label="Profile">
						<form:option value="none" label="----Select ----" />
						<form:option value="00e90000000yb2W" label="Standard-user" />
							<form:option value="00e90000000GVYJ" label="Force.com free user"
							selected="selected" />							
					</form:select>
					</td>
			</tr>
			<tr>
				<td>Firstname</td>
				<td><form:input path="firstName" /></td>
			</tr>
			<tr>
				<td>Lastname</td>
				<td><form:input path="lastName" /></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><form:input path="email" /></td>
			</tr>
			<tr>
				<td>Alias</td>
				<td><form:input path="alias" /></td>
			</tr>
			<tr>
				<td>Time Zone</td>
				<td><form:select path="timeZoneSidKey">
						<form:option value="none" label="----Select ----" />
						<form:option value="America/Los_Angeles" label="LosAngeles"
							selected="selected" />
					</form:select></td>
			</tr>
			<tr>
				<td>Locale</td>
				<td><form:select path="localeSidKey">
						<form:option value="none" label="----Select ----" />
						<form:option value="en_US" label="US" selected="selected" />
					</form:select></td>
			</tr>
			<tr>
				<td>Characterset Encoding</td>
				<td><form:input path="emailEncodingKey" value="UTF-8"
						readonly="true" /></td>
			</tr>
			<tr>
				<td>Language</td>
				<td><form:select path="languageLocaleKey">
						<form:option value="none" label="----Select ----" />
						<form:option value="en_US" label="English -US" selected="selected" />
					</form:select></td>
			</tr>
			<tr>
				<td><input type="submit" value="Create"
					class="btn btn-primary btn-large" onclick="createUser()" /></td>
			</tr>
		</table>
	</form:form>
</div>


<script type="text/javascript">
	var spinner;

	$(document).ready(function() {
		clearup_stuff();
	});

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

	function createUser() {
		var salesforceuser = $(this).serializeObject();

		$.ajax({
			type : "POST",
			dataType : "json",
			async : true,
			url : "/salesforce/create",
			data : salesforceuser,
			beforeSend : function() {
			},
			success : function(data) {
				clearup_stuff();

			},
			error : function(jqXHR, textStatus, errorThrown) {
				print_error(jqXHR, textStatus, errorThrown);

			},
			always : function() {
			}
		});
		return false;

	}

	function clearup_stuff() {
		$('#error_message_box').hide();
	}
</script>