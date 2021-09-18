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
<title>Home</title>
</head>
<body>

	<div class="wrap">
		<button id="moveShop">쇼핑 리스트로 이동</button>
		<button id="moveMemberList">고객 관리 페이지 이동</button>
	</div>


	<script>
		$(function(){
			$('#moveShop, #moveMemberList').click(function(){
				var id = $(this).attr('id');
				console.log(id);
				switch (id) {
				case "moveShop":
					window.open("/shop/moveShop");
					break;
				case "moveMemberList":
					window.alert('oh no');
					break;

				default:
					break;
				}
			})
		})
	</script>
</body>
</html>