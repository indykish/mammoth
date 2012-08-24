<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
	<div class="row-fluid">
		<div class="span3 bs-docs-sidebar">
			<ul class="nav nav-list bs-docs-sidenav">
				<li class="nav-header">Actions- Immutable</li>
				<li><a href="#" id="iscomputeengineup"
					onclick="isComputeEngineUp()">Cloud - Alive ?<i
						class="icon-chevron-right"></i></a></li>
				<li><a href="#" id="printinfo" onclick="printInfo()">Describe<i
						class="icon-chevron-right"></i></a></li>				
				<li><a href="#" id="listallrawidentity"
					onclick="listAllRawIdentity()">List<i
						class="icon-chevron-right"></i></a></li>
				<li><a href="#" id="list"
					onclick="log()">List<i
						class="icon-chevron-right"></i></a></li>
			</ul>
		</div>
		<div class="span9">
			<h2>Raw Identity</h2>
			<div id="message_display_box" class="alert alert-success">Right
				now we are supporting interface to Amazon EC2. Refer our
				documentation for link here.....</div>
			<div id="error_message_box" class="alert alert-error"></div>
			<h2>Baked identity</h2>
			<div class="span3">
				<button class="btn btn-large btn-primary btn btn-inverse btn-block" type="button"
					onclick="stickIntoTheIdentity()">Stick</button>
			</div>
			<div class="span2">
				<button class="btn btn-large btn-primary btn-block" type="button"
					onclick="startTheInstance()">Start</button>
			</div>
			<div class="span2">
				<button class="btn btn-large btn-primary btn-block" type="button"
					onclick="runTheInstance()">Run</button>
			</div>
			<div class="span2">
				<button class="btn btn-large btn-primary btn-block" type="button"
					onclick="stopTheInstance()">Stop</button>
			</div>
			<div class="span9">
				<table class="table table-hover table-striped">
					<caption></caption>
					<thead>
						<tr>
							<th>Id</th>
							<th>Description</th>
							<th>Status</th>
							<th>Owner</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$('#error_message_box').hide();
	});

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

	$('#printinfo').ajaxStart(function() {
		fireSpinner();
	}).ajaxStop(function() {
		$('#loading').fadeOut();
		spinner.stop();
	});

	$('#iscomputeengineup').ajaxStart(function() {
		fireSpinner();
	}).ajaxStop(function() {
		$('#loading').fadeOut();
		spinner.stop();
	});

	$('#listallrawidentity').ajaxStart(function() {
		fireSpinner();
	}).ajaxStop(function() {
		$('#loading').fadeOut();
		spinner.stop();
	});

	$('#stickinfotheidentity').ajaxStart(function() {
		fireSpinner();
	}).ajaxStop(function() {
		$('#loading').fadeOut();
		spinner.stop();
	});

	function printInfo() {

		$
				.ajax({
					type : "GET",
					dataType : "json",
					async : true,
					url : "/mammoth/cloudidentity/describe",
					beforeSend : function() {
					},
					success : function(data) {
						var disp_info = "Description of 'mammoth' (Cloud Identity) REST API.\n";
						$.each(data,function() {
							disp_info += "<p><span class=\"label label-success\">"+this.apistr+"</span>"+
								 " - " + 	"<span class=\"label label-important\">"+this.desc + "</span></p>\n";
						});
						$('#message_display_box').html(disp_info);
					},
					error : function(jqXHR, textStatus, errorThrown) {
						$('#loading').fadeOut();
						spinner.stop();
						var errorStr = 'An error occurred when the attemping an ajax request for \n'						
							+	'Response Text: ' + jqXHR.responseText + '<br />'
				            + 'Status: ' + jqXHR.status + '<br />'
				            + 'Status Text: ' + jqXHR.statusText + '<br /><hr>'
				            + 'Data: ' + textStatus.data + '<br />'
				            + 'Data Types: ' + textStatus.dataTypes + '<br />'
				            + 'Type: ' + textStatus.type + '<br />'
				            + 'Url: ' + textStatus.url + '<br /><hr>'
				            + 'Exception: ' + errorThrown + '<br />';					
							$("#error_message_box").html(errorStr);
							$("#error_message_box").show();
					},
					always : function() {
					}
				});
	}

	function isComputeEngineUp() {

		$
				.ajax({
					type : "GET",
					dataType : "json",
					async : true,
					url : "/mammoth/cloudidentity/up",
					beforeSend : function() {
					},
					success : function(data) {
						var disp_info = "Yeehaw ! Compute engine is alive.\n";
						$.each(data,function() {
							disp_info += "<p><span class=\"label label-success\">"+this.apistr+"</span>"+
								 " - " + 	"<span class=\"label label-important\">"+this.desc + "</span></p>\n";
						});
						$('#message_display_box').html(disp_info);
					},
					error : function(jqXHR, textStatus, errorThrown) {
						$('#loading').fadeOut();
						spinner.stop();
						var errorStr = 'An error occurred when the attemping an ajax request for \n'						
						+	'Response Text: ' + jqXHR.responseText + '<br />'
			            + 'Status: ' + jqXHR.status + '<br />'
			            + 'Status Text: ' + jqXHR.statusText + '<br /><hr>'
			            + 'Data: ' + textStatus.data + '<br />'
			            + 'Data Types: ' + textStatus.dataTypes + '<br />'
			            + 'Type: ' + textStatus.type + '<br />'
			            + 'Url: ' + textStatus.url + '<br /><hr>'
			            + 'Exception: ' + errorThrown + '<br />';					
						$("#error_message_box").html(errorStr);
						$("#error_message_box").show();
					},
					always : function() {
					}
				});
	}

	function listAllRawIdentity() {
                var instid="i-c8599f9c";
		$
				.ajax({
					type : "GET",
					dataType : "json",
					async : true,
					url : "/mammoth/cloudidentity/list/{instid}",
					
					beforeSend : function() {
					},
					success : function(data) {
					},
					error : function(jqXHR, textStatus, errorThrown) {
						$('#loading').fadeOut();
						spinner.stop();
						var errorStr = "An error occurred when the attemping an ajax request for \n"
								+ jqXHR
								+ "\n"
								+ "status :"
								+ textStatus
								+ "\n"
								+ "error  :" + errorThrown;
						$("#error_message_box").prepend(errorStr);
						$("#error_message_box").show();
					},
					always : function() {
					}

				});
		return false;
	}

	function stickIntoTheIdentity() {
		var instid = "i-82ce88d6";

		$
				.ajax({
					type : "POST",
					dataType : "json",
					async : true,
					url : "/mammoth/cloudidentity/stick",
					data : instid,
					beforeSend : function() {
					},
					success : function(data) {
					},
					error : function(jqXHR, textStatus, errorThrown) {
						$('#loading').fadeOut();
						spinner.stop();
						var errorStr = "An error occurred when the attemping an ajax request for \n"
								+ jqXHR
								+ "\n"
								+ "status :"
								+ textStatus
								+ "\n"
								+ "error  :" + errorThrown;
						$("#error_message_box").prepend(errorStr);
						$("#error_message_box").show();
					},
					always : function() {
					}
				});

	}

	function runTheInstance() {

		$
				.ajax({
					type : "POST",
					dataType : "json",
					async : true,
					url : "/mammoth/run",
					data : JSON.stringify("ami-00067852"),
					contentType : 'application/json',
					beforeSend : function() {
					},
					success : function(data) {
					},
					error : function(jqXHR, textStatus, errorThrown) {
						$('#loading').fadeOut();
						spinner.stop();
						var errorStr = "An error occurred when the attemping an ajax request for \n"
								+ jqXHR
								+ "\n"
								+ "status :"
								+ textStatus
								+ "\n"
								+ "error  :" + errorThrown;
						$("#error_message_box").prepend(errorStr);
						$("#error_message_box").show();
					},
					always : function() {
					}
				});
	}

	function startTheInstance() {
		var instid = "i-82ce88d6";

		$
				.ajax({
					type : "POST",
					dataType : "json",
					async : true,
					url : "/mammoth/start",
					data : instid,
					beforeSend : function() {
					},
					success : function(data) {
					},
					error : function(jqXHR, textStatus, errorThrown) {
						$('#loading').fadeOut();
						spinner.stop();
						var errorStr = "An error occurred when the attemping an ajax request for \n"
								+ jqXHR
								+ "\n"
								+ "status :"
								+ textStatus
								+ "\n"
								+ "error  :" + errorThrown;
						$("#error_message_box").prepend(errorStr);
						$("#error_message_box").show();
					},
					always : function() {
					}
				});

	}
	
	function stopTheInstance() {
		var instid = "i-82ce88d6";

		$
				.ajax({
					type : "POST",
					dataType : "json",
					async : true,
					url : "/mammoth/cloudidentity/stop",
					data : instid,
					beforeSend : function() {
					},
					success : function(data) {
					},
					error : function(jqXHR, textStatus, errorThrown) {
						$('#loading').fadeOut();
						spinner.stop();
						var errorStr = "An error occurred when the attemping an ajax request for \n"
								+ jqXHR
								+ "\n"
								+ "status :"
								+ textStatus
								+ "\n"
								+ "error  :" + errorThrown;
						$("#error_message_box").prepend(errorStr);
						$("#error_message_box").show();
					},
					always : function() {
					}
				});

	}
	
	function log() {
        var instid="i-c8599f9c";
$
		.ajax({
			type : "GET",
			dataType : "json",
			async : true,
			url : "/mammoth/cloudidentity/log/{instid}",
			
			beforeSend : function() {
			},
			success : function(data) {
			},
			error : function(jqXHR, textStatus, errorThrown) {
				$('#loading').fadeOut();
				spinner.stop();
				var errorStr = "An error occurred when the attemping an ajax request for \n"
						+ jqXHR
						+ "\n"
						+ "status :"
						+ textStatus
						+ "\n"
						+ "error  :" + errorThrown;
				$("#error_message_box").prepend(errorStr);
				$("#error_message_box").show();
			},
			always : function() {
			}

		});
return false;
}


	
</script>