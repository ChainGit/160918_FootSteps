<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../../base/jquery-1.8.3.js"></script>
<script type="text/javascript">
	$(function() {
		update();

		$("a").click(function() {
			afun(this);
			return false;
		});

		function afun(obj) {
			var url = obj.href;
			var args = {
				"time" : new Date().getTime()
			};

			$.post(url, args, function(data) {
				var p = eval("(" + data + ")");
				var msg = p.msg;
				if (msg == 1)
					$("#msg").text("操作成功");
				else
					$("#msg").text("操作失败");
			});

			update();

			return false;
		}

		function update() {
			var url = "/161027_TgWebBasic/getBook";
			var args = {
				"time" : new Date().getTime()
			};

			$.post(url, args, function(data) {
				var p = eval("(" + data + ")");
				var books = p.books;
				$("#cart").empty();
				$("#cart").append(
						$("<span>总共有" + p.length + "种书,共" + p.mount + "本书,共计"
								+ p.total + "元</span>")).append("<br/>");
				for (var t = 0; t < books.length; t++) {
					var name = books[t].name;
					var price = books[t].info[0].price;
					var count = books[t].info[1].count;
					var $a = $("<a></a>");
					$a[0].href = "/161027_TgWebBasic/deleteBook?name=" + name;
					$a.text("删除一个");
					$a.bind("click", function() {
						afun(this);
						return false;
					});
					$("#cart").append(
							$("<span>" + name + "&nbsp;&nbsp;&nbsp;" + price
									+ "&nbsp;&nbsp;&nbsp;" + count
									+ "&nbsp;&nbsp;&nbsp;</span>")).append($a)
							.append("<br/>");
				}
			});

			return false;
		}
	})
</script>
</head>
<body>

	<h2>图书购物</h2>

	<span id="msg"></span>
	<br />

	<span>书籍1</span>
	<a href="/161027_TgWebBasic/addBook?name=book1&price=10">添加一本</a>
	<br />
	<span>书籍2</span>
	<a href="/161027_TgWebBasic/addBook?name=book2&price=50">添加一本</a>
	<br />
	<span>书籍3</span>
	<a href="/161027_TgWebBasic/addBook?name=book3&price=70">添加一本</a>
	<br />

	<hr />

	<span>购物车</span>
	<div id="cart"></div>

</body>
</html>