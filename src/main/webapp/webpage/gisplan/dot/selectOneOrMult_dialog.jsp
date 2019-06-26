<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>网点列表</title>
<t:base type="jquery,easyui"></t:base>
<script type="text/javascript" src="${webRoot}/static/js/gisplan/common.js"></script>
<script type="text/javascript" src="${webRoot}/static/js/gisplan/dot/selectOneOrMult_dialog.js"></script>
<link rel="stylesheet" href="${webRoot}/static/style/css/commcon.css">
</head>
<body>
	<table id="dotList" class="easyui-datagrid"
		data-options="rownumbers:true,singleSelect:${singleSelect},fit:true,url:'select.do?oneOrMultDatagrid&excludeIds=${excludeIds}&field=id,name,address,type',method:'post'">
		<thead>
			<tr>
				<th data-options="field:'id',hidden:true">id</th>
				<th data-options="field:'name',align:'center'">名称</th>
				<th data-options="field:'address',align:'center'">地址</th>
				<th data-options="field:'type',align:'center'">网点类型</th>
			</tr>
		</thead>
	</table>
</body>
</html>