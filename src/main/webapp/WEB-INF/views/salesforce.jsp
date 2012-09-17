<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div class="container">
	<h1>Create new user</h1>
	<form method="post" action="/mammoth/salesforce/create">
		<fieldset>
			<table>
				<tr>
					<td>Username:<input type="text" id="username" name="username" />
					</td>
					<td>Firstname:<input type="text" id="firstname"
						name="firstname" />
				</tr>
				<tr>
					<td>Email:<input type="text" id="email" name="email" /></td>
					<td>Alias:<input type="text" id="alias" /></td>
				</tr>
				<tr>
					<td>ProfileId:<select name="profileid" id="profile"
						style="width: 158px;">
							<option value="none">----Select ----</option>
							<option value="00e90000000Gmi2">Standard-user</option>
					</select>
					</td>
					<td>LastName:<input type="text" id="lastname" name="lastname" /></td>
				</tr>
				<tr>
					<td>TimeZoneSidekey:<select name="timezone" id="timezon"
						style="width: 158px;">
							<option value="none">----Select ----</option>
							<option value="America/Los_Angeles">LosAngeles</option>
					</select>
					<td>LocaleSideKey:<select name="locale" id="localy"
						style="width: 158px;">
							<option value="none">----Select ----</option>
							<option value="en_US">US</option>
					</select>
					</td>
				</tr>
				<tr>
					<td>EmailNcodingkey:<input type="text" id="encode"
						value="UTF-8" name="encoding" readonly="true" />
					</td>
					<td>LanguageLocaleKey:<select name="language" id="languagekey"
						style="width: 158px;">
							<option value="none">----Select ----</option>
							<option value="en_US">English -US</option>
					</select></td>
				</tr>
			</table>
			<p align="center">
				<input type="submit" id="createuser" value="Create"
					onclick="createUser()" />
		</fieldset>
	</form>
</div>


<script type="text/javascript">
	var spinner;
	var listgrid;
	var upgrid;
	var selectedInstanceId = null;

	$(document).ready(function() {
		clearup_stuff();
	});

	var alivecolumns = [ {
		id : "endpoint",
		name : "End Point",
		field : "endpoint",
		width : 400,
		minWidth : 200,
		rerenderOnResize : true
	}, {
		id : "regionName",
		name : "RegionName",
		field : "regionName",
		width : 200,
		minWidth : 150,
		rerenderOnResize : true
	} ];

	var listcolumns = [ {
		id : "instanceId",
		name : "InstanceId",
		field : "instanceId",
		width : 90,
		rerenderOnResize : true,
		behavior : "selectAndMove",
		selectable : true,
	}, {
		id : "imageId",
		name : "ImageId",
		field : "imageId",
		width : 90,
		cssClass : "cell-title",
		selectable : true,
		rerenderOnResize : true
	}, {
		id : "instanceType",
		name : "InstanceType",
		field : "instanceType",
		width : 100,
		selectable : true,
		rerenderOnResize : true
	}, {
		id : "state",
		name : "State",
		field : "state",
		selectable : true,
		width : 100,
		rerenderOnResize : true
	}, {
		id : "owner",
		name : "OwnerId",
		field : "owner",
		selectable : true,
		width : 90,
		rerenderOnResize : true
	}, {
		id : "publicDnsName",
		name : "PublicDnsName",
		field : "publicDnsName",
		selectable : true,
		width : 130,
		rerenderOnResize : true
	} ];

	var options = {
		editable : false,
		enableCellNavigation : true,
		showHeaderRow : true,
	};

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
		var salesforceuser = $(this).serialize();
		alert(salesforceuser);

		$.ajax({
			type : "POST",
			dataType : "json",
			async : true,
			url : "/mammoth/salesforce/create",
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
		$('#alivegrid').hide();
	}
</script>



