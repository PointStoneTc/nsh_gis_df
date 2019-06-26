<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>大屏展示</title>
<t:base type="jquery,easyui,tools,DatePicker,validform"></t:base>
<script type="text/javascript" src="${webRoot}/plug-in/echart/echarts.js"></script>
<script src="${webRoot}/plug-in/echart/theme/vintage.js"></script>
<script type="text/javascript" src="${webRoot}/static/js/gisplan/anlysis/bigscreen_index.js"></script>
<link rel="stylesheet" href="${webRoot}/static/style/css/commcon.css">
</head>
<style type="text/css">
body {
	background-color: #333333;
	text-align: center;
	padding: 0;
	margin: 0;
}

h3 {
	font-size: 14px;
	color: #ffffff;
	font-weight: bold;
	padding: 0 5px;
}

h3::before {
	content: "[";
}

h3::after {
	content: "]";
}

#wrap {
	width: 1700px;
	height: 750px;
	color: #ffffff;
}

.head_corner, .foot_corner {
	width: 100%;
	height: 20px;
}

.box {
	border: 1px solid #fff6;
	height: 100%;
	padding: 5px;
	border-radius: 5px;
}

.part1_wrap {
	width: 450px;
	height: 100%;
	float: left;
	padding: 5px;
}

.part1_wrap .dmc {
	height: 90%;
}

.part2_wrap {
	width: 650px;
	height: 100%;
	float: left;
	padding: 5px;
}

.part2_wrap .mapt_wrap {
	height: 550px;
}

.part2_wrap .mapt_wrap .mapt {
	height: 520px;
}

.part2_wrap .trendLine {
	margin-top: 5px;
	height: 185px;
}

.part3_wrap {
	width: 550px;
	height: 100%;
	float: left;
}

.part3_wrap .business_big {
	margin-top: 5px;
	height: 200px;
}

.part3_wrap .business_big .ct {
	width: 50%;
	padding: 5px;
	height: 100%;
	float: left;
}

.part3_wrap .business_small {
	margin-top: 5px;
	height: 400px;
}

.part3_wrap .business_small .ct {
	width: 50%;
	margin-top: 5px;
	height: 180px;
	float: left;
}

.part3_wrap .other {
	margin-top: 5px;
	height: 130px;
}
</style>
<body>
	<div id="wrap">
		<!--  支行所辖商户数量  -->
		<div class="part1_wrap">
			<div class="box">
				<h3>支行所辖商户数量</h3>
				<div id="dmc" class="dmc"></div>
			</div>
		</div>

		<!--  地图  -->
		<div class="part2_wrap ">
			<div class="box mapt_wrap">
				<h3>网点分布</h3>
				<div class="mapt" id="mapt"></div>
			</div>
			<div class="box trendLine"></div>
		</div>

		<!--  其它  -->
		<div class="part3_wrap">
			<div class="box business_big" id="business_big"></div>
			<div class="box business_small" id="business_small">
				<h3>产品小类商户占比</h3>
			</div>
			<div class="box other"></div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
  
</script>