<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
	<div class="row-fluid">
<div class="span3 bs-docs-sidebar">
			<ul class="nav nav-list bs-docs-sidenav">
				<li class="nav-header">Connectors</li>
				<li><a href="sforce">Sales force<i class="icon-chevron-right"></i></a></li>
				<li><a href="gapp">Google App<i class="icon-chevron-right"></i></a></li>
				<li><a href="life">Liferay<i class="icon-chevron-right"></i></a></li>
				<li><a href="scrm">SugarCRM<i class="icon-chevron-right"></i></a></li>
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
	</div>
</div>
