<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

 <div class="container">
<h1>Create new user</h1>
<form:form modelAttribute="gapps" method="post">
<table>
 <tr>Username:<input type="text" id="username" name="username"/>
</td><td>Firstname:<input type="text" id="firstname" name="firstname"/>
</tr>
<tr><td>givenName:<input type="text" id="givname" name="givenname"/></td>
<td>Familyname:<input type="text" id="familyname" name="familyname"/></td></tr>
<tr><td>Password:<input type="text" id="password" name="password"/></td>
<td>AdminEmail:<input type="text" id="ademail" name="adminemail"/></td></tr>
<tr><td>AdminPassword:<input type="text" id="adpass" name="adminpassword"/></td>
<td>Domain:<input type="text" id="domain" name="domain"/></td></tr>
 </table>
 <p align="center"><input type="submit" id="createuser" value="Create"/>
 
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

	$('#createuser').ajaxStart(function() {
		fireSpinner();
	}).ajaxStop(function() {
		$('#loading').fadeOut();
		spinner.stop();
	});
	
	
	
	
	/*function sforcecreate() {		

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
			}*/
			
	 $(document).ready(function(){
		   $('form').submit(function(event){
		     //alert("Thanks for visiting!");
		     var dat=new Array();
		     
		      dat=$(this).serializeArray();
		      
		      
		      
		      
		      //var jsonstring=JSON.stringify(dat);
		     alert(JSON.stringify(dat));
		    //var dat=$('#username').val();
		   //alert(obj);		     
		     $.post('./googleapps/create', dat, function(data) {
		    	 window.location.reload(true);	    	
							
				}, function(data) {
					var response = JSON.parse(data.response);
					alert("Error: "+response[0].id);
				});
				return false;
		   });
		 });
			
			

	function clearup_stuff() {
		$('#error_message_box').hide();
		$('#alivegrid').hide();
	}
	
	
</script>
 
  

    