<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>shopList</title>
</head>
<body>

	<!-- 본격 게시판 -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">

					<button id='regBtn' type="button" class="btn btn-primary">글쓰기</button>
				</div>

				<!-- /.panel-heading -->
				<div class="panel-body">
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>#번호</th>
								<th>제목</th>
								<th>내용</th>
								<th>아이템링크</th>
								<th>작성일</th>
							</tr>
						</thead>
						<c:forEach items="${shopList}" var="board">
							<tr>
								<td><c:out value="${board.ITEMCD}" /></td>
								<td><c:out value="${board.SUBTITLE}" /></td>
								<td><c:out value="${board.ITEMCONT}" /></td>
								<td><c:out value="${board.ITEMURL}" /></td>
								<td><c:out value="${board.INDATE}" /></td>

							</tr>
						</c:forEach>
					</table>
				</div>

			</div>
			<!--  end panel-body -->
		</div>
		<!-- end panel -->
	</div>

	<script>
		$(function(){
			$('#regBtn').click(function(){
			 	window.open("/shop/writeShop");
			})
		})
	</script>

</body>
</html>