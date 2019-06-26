<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>银行产品列表</title>
<t:base type="jquery,easyui,tools"></t:base>
<script type="text/javascript" src="${webRoot}/static/js/gisplan/common.js"></script>
<script type="text/javascript" src="${webRoot}/static/js/gisplan/product/register_list.js"></script>
<link rel="stylesheet" href="${webRoot}/static/style/css/commcon.css">
</head>
<body>
	<table id="registerList" name="registerList" class="easyui-datagrid" title="银行产品列表"
		data-options="rownumbers:true,singleSelect:true,pagination:true,pageSize:20,fit:true,striped:true,url:'register.do?datagrid&field=id,business,name,isEffect,effectDate,invalidDate,isCommit',method:'post',toolbar:'#tb'">
		<thead>
			<tr>
				<th data-options="field:'id',hidden:true">id</th>
				<th data-options="field:'business',align:'center'">业务大类</th>
				<th data-options="field:'name',align:'center'">名称</th>
				<th data-options="field:'isEffect',align:'center',formatter:formatRw">是否生效</th>
				<th data-options="field:'effectDate',align:'center'">生效时间</th>
				<th data-options="field:'invalidDate',align:'center'">失效时间</th>
				<th data-options="field:'isCommit',align:'center',formatter:formatRw">是否提交</th>
				<th data-options="field:'opt',align:'center',formatter:operate_formatter">操作</th>
			</tr>
		</thead>
	</table>

	<div id="tb" style="padding: 2px 5px;">
		<table>
			<tr>
				<td><a id="add" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addProduct()">新增</a></td>
				<td><label>业务大类</label></td>
				<td><select name="businessCode" id="businessCode">
						<option value="" selected="selected">---请选择---</option>
						<option value="d">贷款</option>
						<option value="z">支付</option>
						<option value="c">存款</option>
						<option value="r">中间业务</option>
				</select></td>
				<td><a id="search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="doQuery()">查询</a></td>
				<td><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-le-back'" onclick="doClear()">清空</a></td>
			</tr>
		</table>
	</div>
</body>
</html>