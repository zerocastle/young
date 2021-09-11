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
	<div class="container">
		<form action="/post" method="post" enctype="multipart/form-data">
			<div class="form-group row">
				<label for="inputTitle" class="col-sm-2 col-form-label"><strong>서브타이틀</strong></label>
				<div class="col-sm-10">
					<input type="text" name="title" class="form-control"
						id="inputTitle">
				</div>
			</div>

			<div class="form-group row">
				<label for="inputContent" class="col-sm-2 col-form-label"><strong>내용</strong></label>
				<div class="col-sm-10">
					<input type="text" name="content" class="form-control"
						id="inputContent"></input>
				</div>
			</div>

			<div class="form-group row">
				<label for="inputContent" class="col-sm-2 col-form-label"><strong>가격</strong></label>
				<div class="col-sm-10">
					<input type="text" name="content" class="form-control"
						id="inputPrice"></input>
				</div>
			</div>

			<div class="wrapper">
		<div class="header">
			<h1>사진 첨부</h1>
		</div>
		<div class="body">
			<!-- 첨부 버튼 -->
			<div id="attach">
				<label class="waves-effect waves-teal btn-flat" for="uploadInputBox">사진첨부</label>
				<input id="uploadInputBox" style="display: none" type="file"
					name="filedata" multiple />
			</div>

			<!-- 미리보기 영역 -->
			<div id="preview" class="content"></div>

			<!-- multipart 업로드시 영역 -->
			<form id="uploadForm" style="display: none;" />
		</div>
		<div class="footer">
			<button class="submit">
				<a href="#" title="등록" class="btnlink">등록</a>
			</button>
		</div>
	</div>

			<div>
				<div class="imgs_wrap">
					<img id="img" />
				</div>
			</div>
			<div class="row">
				<div class="col-auto mr-auto"></div>
				<div class="col-auto">
					<input class="btn btn-primary" type="submit" role="button"
						value="글쓰기">
				</div>
			</div>
		</form>
	</div>

	<script type="text/javascript">
		// 이미지 정보들을 담을 배열
		var sel_files = [];

		$(document).ready(function() {
			$("#input_imgs").on("change", handleImgFileSelect);
		});

		function fileUploadAction() {
			console.log("fileUploadAction");
			$("#input_imgs").trigger('click');
		}

		function handleImgFileSelect(e) {

			// 이미지 정보들을 초기화
			sel_files = [];
			$(".imgs_wrap").empty();

			var files = e.target.files;
			var filesArr = Array.prototype.slice.call(files);

			var index = 0;
			filesArr
					.forEach(function(f) {
						if (!f.type.match("image.*")) {
							alert("확장자는 이미지 확장자만 가능합니다.");
							return;
						}

						sel_files.push(f);

						var reader = new FileReader();
						reader.onload = function(e) {
							//var html = "CONTENT";
							var html = "<a href=\"javascript:void(0);\" onclick=\"deleteImageAction("
									+ index
									+ ")\" id=\"img_id_"
									+ index
									+ "\"><img src=\"" + e.target.result + "\" data-file='"+f.name+"' class='selProductFile' title='Click to remove'></a>";

							$(".imgs_wrap").append(html);
							index++;

						}
						reader.readAsDataURL(f);

					});
		}

		function deleteImageAction(index) {
			
			var files = $("#input_imgs").target.files;
			var filesArr = Array.prototype.slice.call(files);
			
			console.log("index : " + index);
			sel_files.splice(index, 1);

			var img_id = "#img_id_" + index;
			$(img_id).remove();

			console.log(sel_files);
		}
	</script>

</body>
</html>
