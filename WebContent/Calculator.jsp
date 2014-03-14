<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的计算器</title>
<script type="text/javascript">	
	function checkNum(){
		if(form1.num1.value==""){
			window.alert("第一个数字不能为空");
			return false;
		}
		else if(form1.num2.value==""){
			window.alert("第二个数字不能为空");
			return false;
		}
		else if(Math.round(form1.num1.value)!=form1.num1.value){
			window.alert("第一个参数不是数字");
			return false;
		}
		else if(Math.round(form1.num2.value)!=form1.num2.value){
			window.alert("第二个参数不是数字");
			return false;
		}
	}	
</script>
</head>
<body>
	<%
		double num1=0;
		double num2=0;
		double result=0;
		if(request.getParameter("num1")!=null && request.getParameter("num2")!=null && request.getParameter("option")!=null){			
			num1=Double.parseDouble(request.getParameter("num1"));
			num2=Double.parseDouble(request.getParameter("num2"));
			String option=request.getParameter("option");
			if(option.equals("+")){
				result=num1+num2;
			} else if(option.equals("-")){
				result=num1-num2;
			} else if(option.equals("*")){
				result=num1*num2;
			}else{
				result=num1/num2;
			}
		}
	%>
<hr>
	<form name="form1" action="Calculator.jsp">
		请输入第一个数字：<input type="text" size="15" name="num1" value="<%= num1%>"><br>
		<select name="option">
			<option value="+">+</option>
			<option value="-">-</option>
			<option value="*">*</option>
			<option value="/">/</option>
		</select>
		<br>
		请输入第二个数字：<input type="text" size="15" name="num2" value="<%= num2%>"><br>
		<input type="submit" value="计算" onclick="return checkNum()">
	</form>
<hr>
计算结果是：<%= result%>
</body>
</html>