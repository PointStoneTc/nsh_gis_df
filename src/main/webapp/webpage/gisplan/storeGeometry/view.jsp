<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>地图详情</title>
<t:base type="jquery,easyui,tools,validform,bdmap"></t:base>
<script type="text/javascript"
	src="${webRoot}/static/js/gisplan/common.js"></script>
<script type="text/javascript"
	src="${webRoot}/static/js/gisplan/storeGeometry/view.js"></script>
<link rel="stylesheet" href="${webRoot}/static/style/css/commcon.css">
</head>
<body style="width: 98%;">
	<form name="formobj" id="formobj" method="post"
		action="storeGeometry.do?saveOrUpdate">
		<input type="hidden" name="id" value="${sg.id }" /> <input
			type="hidden" name="fkId" value="${sg.fkId }" /> <input
			type="hidden" name="fkKey" value="${sg.fkKey }" />
		<table style="width: 100%;" cellpadding="0" cellspacing="1"
			class="viewtable">
			<tbody>
				<tr>
					<th><label class="required_sign">经度</label></th>
					<td><input type="text" name="longitude" id="longitude"
						value="${sg.longitude }" datatype="*" sucmsg="经度验证通过!"
						nullmsg="请在地图上标记坐标!" readonly="readonly" /></td>
					<th><label class="required_sign">纬度</label></th>
					<td><input type="text" name="latitude" id="latitude"
						value="${sg.latitude }" datatype="*" sucmsg="纬度验证通过!"
						nullmsg="请在地图上标记坐标!" readonly="readonly" /></td>
				</tr>
			</tbody>
		</table>
		<input type="hidden" id="btn_sub" class="btn_sub" />
	</form>
	<div id="allmap" class="map"
		style="margin-top: 5px; width: 100%; height: 440px"></div>
</body>
</html>
<script type="text/javascript">
  var index = '${index}';
  var gridId = '${gridId}';
  var longitude = '${sg.longitude}';
  var latitude = '${sg.latitude}';
</script>