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
				<li><a href="#" id="log" onclick="log()">Log<i
						class="icon-chevron-right"></i></a></li>
			</ul>
		</div>
		<div class="span9">
			<h2>Raw Identity</h2>
			<div id="message_display_box" class="alert alert-success">Right
				now we are supporting interface to Amazon EC2. Refer our
				documentation for link here.....</div>
			<div id="error_message_box" class="alert alert-error"></div>
			<div class="span3">
				<button id="stickintotheidentity" class="btn btn-large btn-primary btn btn-inverse btn-block"
					type="button" onclick="stickIntoTheIdentity()">
					<i class="icon-white icon-road"></i> Stick
				</button>
				<p></p>
				<div id="divMessage" style="display: none;">
					loading... Please wait&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img
						src="./resources/images/loading.gif" />
				</div>
			</div>
			<div class="span2">
				<button id="starttheinstance" class="btn btn-large btn-primary btn-block" type="button"
					onclick="startTheInstance()">
					<i class="icon-white icon-play"> </i> Start
				</button>
			</div>
			<div class="span2">
				<button id="runtheinstance" class="btn btn-large btn-primary btn-block" type="button"
					onclick="runTheInstance()">
					<i class="icon-white icon-plus"></i> Run
				</button>
			</div>
			<div class="span2">
				<button id="stoptheinstance" class="btn btn-large btn-primary btn-block" type="button"
					onclick="stopTheInstance()">
					<i class="icon-white icon-stop"></i> Stop
				</button>
			</div>	 
			<br></br>
			<table class="table table-bordered"><tbody><div class="span6" id="listgrid"></div></tbody></table>			
		</div>
	</div>
</div>

<script type="text/javascript">
	var spinner;
	var listgrid;
	var upgrid;
	var selectedInstanceId=null;

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
		behavior: "selectAndMove",
	    selectable: true,
	}, {
		id : "imageId",
		name : "ImageId",
		field : "imageId",
		width : 90,
		cssClass: "cell-title",
	    selectable: true,
		rerenderOnResize : true
	}, {
		id : "instanceType",
		name : "InstanceType",
		field : "instanceType",
		width : 100,
	    selectable: true,
		rerenderOnResize : true
	}, {
		id : "state",
		name : "State",
		field : "state",
	    selectable: true,
		width : 100,
		rerenderOnResize : true
	}, {
		id : "owner",
		name : "OwnerId",
		field : "owner",
	    selectable: true,
		width : 90,
		rerenderOnResize : true
	}, {
		id : "publicDnsName",
		name : "PublicDnsName",
		field : "publicDnsName",
	    selectable: true,
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
					url : "./cloudidentity/describe",
					beforeSend : function() {
					},
					success : function(data) {
						var disp_info = "Description of 'mammoth' (Cloud Identity) REST API.<br></br>";
						$.each(data, function() {
							disp_info += this.apistr + "    : " + this.desc + "<br></br>";
						});
						$('#message_display_box').html(disp_info);
					},
					error : function(jqXHR, textStatus, errorThrown) {
						print_error(jqXHR, textStatus,errorThrown);	

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
					url : "./cloudidentity/up",
					beforeSend : function() {
					},
					success : function(data) {
						clearup_stuff();
						var disp_info = "Yeehaw ! Compute engine is alive.\n";
						upgrid = new Slick.Grid("#alivegrid", data.out.regions,
								alivecolumns, options);
						$('#alivegrid').show();
						$("#message_display_box").html(disp_info);

					},
					error : function(jqXHR, textStatus, errorThrown) {
						print_error(jqXHR, textStatus,errorThrown);	


					},
					always : function() {
					}
				});
	}

	function listAllRawIdentity() {
		$
				.ajax({
					type : "GET",
					dataType : "json",
					async : true,
					url : "./cloudidentity/list/{"+selectedInstanceId+"}",
					beforeSend : function() {
					},
					success : function(data) {
						clearup_stuff();
						listgrid = new Slick.Grid("#listgrid", data.out,
								listcolumns, options);
						$('#listgrid').show();

						listgrid
								.setSelectionModel(new Slick.RowSelectionModel());

						listgrid.onClick
								.subscribe(function(e, args) {
									var selectedRows = listgrid.getSelectedRows();
									if (selectedRows.length > 0) {
										var cell = listgrid.getCellFromEvent(e);
										selectedInstanceId = data.out[args.row][listgrid.getColumns()[0].field];
										var selectedColumnName = listgrid
												.getColumns()[cell.cell]; // object containing name, field, id, etc
									
									}
								});
					},
					error : function(jqXHR, textStatus, errorThrown) {
						print_error(jqXHR, textStatus,errorThrown);	

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
					url : "./cloudidentity/stick",
					data : instid,
					beforeSend : function() {
					},
					success : function(data) {
						clearup_stuff();

					},
					error : function(jqXHR, textStatus, errorThrown) {
						print_error(jqXHR, textStatus,errorThrown);	

					},
					always : function() {
					}
				});
		return false;

	}

	function runTheInstance() {

		$
				.ajax({
					type : "POST",
					dataType : "json",
					async : true,
					url : "./run",
					data : JSON.stringify("ami-00067852"),
					contentType : 'application/json',
					beforeSend : function() {
					},
					success : function(data) {
						clearup_stuff();

					},
					error : function(jqXHR, textStatus, errorThrown) {
						print_error(jqXHR, textStatus,errorThrown);	

					},
					always : function() {
					}
				});
		return false;
	}

	function startTheInstance() {
		var instid = "i-82ce88d6";

		$
				.ajax({
					type : "POST",
					dataType : "json",
					async : true,
					url : "./start",
					data : instid,
					beforeSend : function() {
					},
					success : function(data) {
						clearup_stuff();

					},
					error : function(jqXHR, textStatus, errorThrown) {
						print_error(jqXHR, textStatus,errorThrown);	


					},
					always : function() {
					}
				});
		return false;

	}

	function stopTheInstance() {
		var instid = "i-82ce88d6";

		$
				.ajax({
					type : "POST",
					dataType : "json",
					async : true,
					url : "./cloudidentity/stop",
					data : instid,
					beforeSend : function() {
					},
					success : function(data) {
						clearup_stuff();

					},
					error : function(jqXHR, textStatus, errorThrown) {
						print_error(jqXHR, textStatus,errorThrown);	


					},
					always : function() {
					}
				});
		return false;

	}

	function log() {
		var instid = "i-c8599f9c";
		$
				.ajax({
					type : "GET",
					dataType : "json",
					async : true,
					url : "/mammoth/cloudidentity/log/{instid}",

					beforeSend : function() {
					},
					success : function(data) {
						clearup_stuff();

					},
					error : function(jqXHR, textStatus, errorThrown) {
					print_error(jqXHR, textStatus,errorThrown);	
					},
					always : function() {
					}

				});
		return false;
	}
	
		
	function print_error(jqXHR, textStatus,errorThrown) {
		clearup_stuff();
		$('#loading').fadeOut();
		spinner.stop();
		var errorStr = "An error occurred when the attemping an ajax request. [Status :"
				+ jqXHR.status
				+ ",   Status Text :"
				+ jqXHR.statusText
				+ ",   Exception :"
				+ errorThrown + "]";

		$("#error_message_box").html(errorStr);
		$("#error_message_box").show();
	}
	
	function clearup_stuff() {
		$('#error_message_box').hide();
		$('#alivegrid').hide();
	}
	
</script>
