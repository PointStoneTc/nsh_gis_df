<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>产品列表</title>
<t:base type="jquery,easyui"></t:base>
<script type="text/javascript" src="${webRoot}/static/js/gisplan/common.js"></script>
<script type="text/javascript" src="${webRoot}/static/js/gisplan/product/bankSelectOneOrMult_dialog.js"></script>
<link rel="stylesheet" href="${webRoot}/static/style/css/commcon.css">
</head>
<body>
	<table id="productList" class="easyui-datagrid"
		data-options="rownumbers:true,singleSelect:${singleSelect},fit:true,url:'select.do?oneOrMultDatagrid&excludeIds=${excludeIds}&field=id,business,name,isEffect,effectDate,invalidDate',method:'post'">
		<thead>
			<tr>
				<th data-options="field:'id',hidden:true">id</th>
				<th data-options="field:'business',align:'center'">业务大类</th>
				<th data-options="field:'name',align:'center'">名称</th>
				<th data-options="field:'isEffect',align:'center',formatter:formatRw">是否生效</th>
				<th data-options="field:'effectDate',align:'center'">生效时间</th>
				<th data-options="field:'invalidDate',align:'center'">失效时间</th>
			</tr>
		</thead>
	</table>
</body>
</html>