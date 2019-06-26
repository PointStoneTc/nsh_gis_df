<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>自助设备列表</title>
<t:base type="jquery,easyui,tools,bdmap"></t:base>
<script type="text/javascript" src="${webRoot}/static/js/gisplan/common.js"></script>
<script type="text/javascript" src="${webRoot}/static/js/gisplan/selfservice/register_list.js"></script>
<link rel="stylesheet" href="${webRoot}/static/style/css/commcon.css">
</head>
<body>
	<table id="registerList" name="registerList" class="easyui-datagrid" title="自助设备列表"
		data-options="rownumbers:true,singleSelect:true,pagination:true,pageSize:20,fit:true,striped:true,url:'register.do?datagrid&field=id,dotId,parentLongitude,parentLatitude,code,name,address,type,dotName,longitude,latitude',method:'post',toolbar:'#tb'">
		<thead>
			<tr>
				<th data-options="field:'id',hidden:true">id</th>
				<th data-options="field:'dotId',hidden:true">dotId</th>
				<th data-options="field:'parentLongitude',hidden:true">parentLongitude</th>
				<th data-options="field:'parentLatitude',hidden:true">parentLatitude</th>
				<th data-options="field:'code',align:'center'">编号</th>
				<th data-options="field:'name',align:'center'">名称</th>
				<th data-options="field:'address',align:'left'">地址</th>
				<th data-options="field:'type',align:'center'">网点类型</th>
				<th data-options="field:'dotName',align:'center'">所属支行</th>
				<th data-options="field:'longitude',align:'center'">经度</th>
				<th data-options="field:'latitude',align:'center'">纬度</th>
				<th data-options="field:'opt',align:'center',formatter:operate_formatter">操作</th>
			</tr>
		</thead>
	</table>

	<div id="tb" style="padding: 2px 5px;">
		<table class="queryToolbarTable">
			<tr>
				<td><a id="add" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addContract()">新增</a></td>
				<td class="borderRight"><a id="view" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-tip'" onclick="detailContract()">查看详情</a></td>
				<td><label>所属支行</label></td>
				<td><select name="dotId" id="dotId" style="width: 250px">
						<option value="" selected="selected">---请选择---</option>
						<c:forEach items="${dots}" var="item" varStatus="status">
							<option value="${item.id }">${item.name }</option>
						</c:forEach>
				</select></td>
				<td><label>自助设备类型</label></td>
				<td><select name="typeCode" id="typeCode">
						<option value="" selected="selected">---请选择---</option>
						<option value="z">智能柜员机</option>
						<option value="f">填单机</option>
						<option value="c">查询机</option>
						<option value="a">ATM</option>
						<option value="s">CRS</option>
						<option value="w">网银体验机</option>
				</select></td>
				<td class="borderLeft"><a id="search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="doQuery()">查询</a></td>
				<td><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-le-back'" onclick="doClear()">清空</a></td>
			</tr>
		</table>
	</div>
</body>
</html>