<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>

<%-- 静态包含 base标签，css样式，jQuery文件 --%>
<%@ include file="/pages/common/head.jsp"%>

<script type="text/javascript">
		// 页面加载完成之后
		$(function () {
			$("#sub_btn").click(function () {

				// 验证用户名：必须由字母，数字下划线组成，并且长度为5到12位
				//1 获取用户名输入框里的内容
				var usernameText = $('#username').val();
				//2 创建正则表达式对象
				var usernamePatt = /^\w{5,12}$/;
				//3 使用test方法验证
				if(!usernamePatt.test(usernameText)) {
					//4 提示用户结果
					$("span.errorMsg").text("用户名不合法！");
					return false; // 让其不跳转
				}

				// 验证密码：必须由字母，数字下划线组成，并且长度为5到12位
				//1 获取密码输入框里的内容
				var passwordText = $('#password').val();
				//2 创建正则表达式对象
				var passwordPatt = /^\w{5,12}$/;
				//3 使用test方法验证
				if(!passwordPatt.test(passwordText)) {
					//4 提示用户结果
					$("span.errorMsg").text("密码不合法！");
					return false; // 让其不跳转
				}

				// 验证确认密码：和密码相同
				//1 获取确认密码内容
				var repwdText = $("#repwd").val();
				//2 与密码相比较
				if(repwdText != passwordText){
					//3 提示用户
					$("span.errorMsg").text("确认密码和密码不一致！");
					return false;
				}



				// 邮箱验证；xxxxx@xxx.com
				//1 获取邮箱里的内容
				var emailText = $("#email").val();
				//2 创建正则表达式对象
				var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/
				//3 使用test方法测试
				if(!emailPatt.test(emailText)) {
					//4 提示用户
					$("span.errorMsg").text("邮箱不合法！");
					return false;
				}

				// 验证码：现在只需要验证用户已输入，因为还没讲到服务器，验证码生成。
				var codeText = $("#code").val();
				// alert("去空格前"+codeText);
				codeText = $.trim(codeText); // 去除多余的空格
				// alert("去空格后"+codeText);
				if (codeText == null || codeText == ""){
					// 提示用户
					$("span.errorMsg").text("验证码不能为空！");
					return false;
				}

				$("span.errorMsg").text(""); //合法将不合法信息去除
			});

			// 给验证码图片绑定单击事件
			$("#code_img").click(function () {
				// 在事件响应的function函数中有一个this对象，这个this对象，是当前正在响应事件的dom对象
				// src 属性表示验证码img标签的图片路径。它可读可写
				// 添加随机变量，防止调用缓存
				this.src = "${basePath}kaptcha.jpg?d=" + new Date();
			});

			// 给用户名绑定失去焦点事件
			$("#username").blur(function () {
				// 1. 获取用户名
				var username = this.value;
				//2 创建正则表达式对象
				var usernamePatt = /^\w{5,12}$/;
				//3 使用test方法验证
				if(!usernamePatt.test(username)) { // 用户名不合法
					//4 提示用户结果
					$("span.errorMsg").text("用户名不合法！");
				} else{
					// 用户名合法判断用户名是否存在
					// alert("用户名合法");
					$.getJSON("${pageScope.basePath}userServlet","action=ajaxExistsUsername&username=" + username,
							function (data) {
								if(data.existsUsername) { // 用户名存在
									$("span.errorMsg").text("用户名已存在！");
								} else { // 用户名可用
									$("span.errorMsg").text("用户名可用！");
								}
							});
				}
			});
		});
</script>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">
									${ requestScope.msg }
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="regist">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
										   name="username" id="username"
											value="${ requestScope.username }"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
										   name="password" id="password"
											value="${ requestScope.password }"/>
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1"
										   name="repwd" id="repwd"
										   value="${ requestScope.password }"/>
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1"
										   name="email" id="email"
										   value="${ requestScope.email }"/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 150px;" name="code" id="code"/>
									<img id="code_img" alt="" src="kaptcha.jpg" style="float: right; margin-right: 40px"; width="90px"; height="40px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>

		<%-- include包含脚部信息 --%>
		<%@ include file="/pages/common/footer.jsp"%>

</body>
</html>