<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


 <div class="container">
<h1>Create new user</h1>
<form:form commandName="usr">
<fieldset>
<table>
 <tr><td><form:label path="username">username</form:label>
 <form:input path="username"/> Username:<input type="text" id="username"/>
</td><td>Firstname:<input type="text" id="firstname"/>
</tr>
<tr><td>Email:<input type="text" id="email"/></td>
<td>Alias:<input type="text" id="alias"/></td></tr>
<tr><td>ProfileId:<select name="number" id="profile" style="width:158px;">
  <option value="none">----Select ----</option>
  <option value="00e90000000Gmi2">Standard-user</option>
  </select>
</td>
<td>LastName:<input type="text" id="lastname"/></td></tr>
<tr><td>TimeZoneSidekey:<select name="number" id="timezon" style="width:158px;">
  <option value="none">----Select ----</option>
  <option value="America/Los_Angeles">LosAngeles</option>
  </select>
 <td>LocaleSideKey:<select name="number" id="localy" style="width:158px;">
  <option value="none">----Select ----</option>
  <option value="en_US">US</option>
  </select>
 </td></tr>
 <tr><td>EmailNcodingkey:<input type="text" id="encode" value="UTF-8" readonly="true"/></td>
 <td>LanguageLocaleKey:<select name="number" id="languagekey" style="width:158px;">
  <option value="none">----Select ----</option>
  <option value="en_US">Englisg-US</option>
  </select>
 </td></tr>
 </table>
 <p align="center"><input type="button" id="createuser" value="Create" onclick="sforcecreate()"/>
 </fieldset>
</form:form>
</div>
  
  
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.3/jquery.min.js"></script>
  
 
  
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

	$('#sforcecreate').ajaxStart(function() {
		fireSpinner();
	}).ajaxStop(function() {
		$('#loading').fadeOut();
		spinner.stop();
	});
	
	
	
	
	function sforcecreate() {		

		alert("entry");
		$(document).ready(function() {alert("entry2");
						
		var data = $('#username').value();
		alert(data);
		$.ajax({
			type : "POST",
			dataType : "json",
			async : true,
			url : "./sforcecreate",
			data : data,
	        contentType : 'application/json',
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
				});
			}
			
	 $(document).ready(function(){
		   $("#createuser").click(function(event){
		     alert("Thanks for visiting!");
		     //var data=$(this).serializeObject();
		     //alert(data);
		     var dat=$('#username').val();
		     alert(dat);
		   });
		 });

	function clearup_stuff() {
		$('#error_message_box').hide();
		$('#alivegrid').hide();
	}
	
	
</script>
 
  

    