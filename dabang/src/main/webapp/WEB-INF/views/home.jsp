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

<style>
.imgs_wrap {
	display: flex;
	width: 1000px;
	height: 600px;
}

.imgs_wrap img {
	width: 200px;
	height: 200px;
}
</style>

</head>
<body>
	<div class="container">
		<form name="dataForm" id="dataForm" onsubmit="return registerAction()"
			enctype="multipart/form-data">
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

			<div>
				<h2>
					<b>파일 업로드</b>
				</h2>
				<div class="input_wrap">
					<input type="file" id="input_imgs" multiple />
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
			console.log("index : " + index);
			sel_files.splice(index, 1);

			var img_id = "#img_id_" + index;
			$(img_id).remove();

			console.log(sel_files);
		}

		/*
		 * 폼 submit 로직
		 */
		function registerAction() {
			console.log(sel_files);
			//var form = $("form")[0];
			var formData = new FormData();
			for (var x = 0; x < sel_files.length; x++) {

				formData.append("article_file", sel_files[x]);
			}
			/*
			 * 파일업로드 multiple ajax처리
			 */

			console.log(formData);
			$.ajax({
				type : "POST",
				enctype : "multipart/form-data",
				url : "/fileUpload",
				data : formData,
				processData : false,
				contentType : false,
				success : function(data) {
					console.log(data);
					/* if (JSON.parse(data)['result'] == "OK") {
						alert("파일업로드 성공");
					} else
						alert("서버내 오류로 처리가 지연되고있습니다. 잠시 후 다시 시도해주세요"); */
				},
				error : function(xhr, status, error) {
					alert("서버오류로 지연되고있습니다. 잠시 후 다시 시도해주시기 바랍니다.");
					return false;
				}
			});
			return false;
		}
	</script>


</body>
</html>
