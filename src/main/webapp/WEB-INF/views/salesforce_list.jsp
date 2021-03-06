<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
	<div class="row">
		<div class="span8">
			<h1 class="label label-important">List</h1>
			<c:if test="${!empty userList}">
				<h3>Users</h3>
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>Name</th>
							<th>&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${userList}" var="user">
							<tr>
								<td>${user.lastName}, ${user.firstName}</td>
								<td><form action="./delete/${user.id}" method="post">
										<input type="submit" class="btn btn-danger btn-mini"
											value="Delete" />
									</form></td>

							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</div>
</div>