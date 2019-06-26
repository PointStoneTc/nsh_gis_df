<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>地图区域绘制</title>
<t:base type="jquery,easyui,tools,validform,bdmap"></t:base>
<script type="text/javascript"
	src="${webRoot}/static/js/gisplan/common.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
<script type="text/javascript"
	src="${webRoot}/static/js/gisplan/bdmap/draw_ploygon.js"></script>
<link rel="stylesheet"
	href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
<link rel="stylesheet" href="${webRoot}/static/style/css/commcon.css">
</head>
<body style="width: 99%">
	<form name="formobj" id="formobj" method="post"
		action="bdMap.do?saveOrUpdate">
		<table style="width: 100%;" cellpadding="0" cellspacing="1"
			class="viewtable">
			<tbody>
				<tr>
					<th><label class="required_sign">经度</label></th>
					<td><input type="text" name="longitude" id="longitude"
						value="" readonly="readonly" /></td>
					<th><label class="required_sign">纬度</label></th>
					<td><input type="text" name="latitude" id="latitude" value=""
						readonly="readonly" /></td>
				</tr>
			</tbody>
		</table>
		<input type="hidden" id="btn_sub" class="btn_sub" />
	</form>
	<div id="allmap" class="map"
		style="margin-top: 5px; width: 100%; height: 700px"></div>
</body>
</html>
<script type="text/javascript">
  var longitude = '';
  var latitude = '';
</script>