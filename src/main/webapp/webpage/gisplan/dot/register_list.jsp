<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>网点列表</title>
<t:base type="jquery,easyui,tools,bdmap"></t:base>
<script type="text/javascript" src="${webRoot}/static/js/gisplan/common.js"></script>
<script type="text/javascript" src="${webRoot}/static/js/gisplan/dot/register_list.js"></script>
<link rel="stylesheet" href="${webRoot}/static/style/css/commcon.css">
</head>
<body class="easyui-layout">
	<div region="center" style="padding: 0px; border: 0px">
		<table id="registerList" name="registerList" title="网点列表"
			data-options="rownumbers:true,singleSelect:true,pagination:true,pageSize:20,fit:true,striped:true,url:'register.do?datagrid&field=id,code,name,address,chargePerson,phone,type,gatewayInIp,gatewayOutIp,longitude,latitude',method:'post',toolbar:'#tb'">
			<thead>
				<tr>
					<th data-options="field:'id',hidden:true">id</th>
					<th data-options="field:'code',align:'center'">编号</th>
					<th data-options="field:'name',align:'center'">名称</th>
					<th data-options="field:'address',align:'center'">地址</th>
					<th data-options="field:'chargePerson',align:'center'">负责人</th>
					<th data-options="field:'phone',align:'center'">电话</th>
					<th data-options="field:'type',align:'center'">网点类型</th>
					<th data-options="field:'gatewayInIp',align:'center'">外网网关</th>
					<th data-options="field:'gatewayOutIp',align:'center'">内网网关</th>
					<th data-options="field:'longitude',align:'center'">经度</th>
					<th data-options="field:'latitude',align:'center'">纬度</th>
					<th data-options="field:'opt',align:'center',formatter:operate_formatter">操作</th>
				</tr>
			</thead>
		</table>

		<div id="tb" style="padding: 2px 5px;">
			<table>
				<tr class="queryToolbarTable">
					<td><a id="add" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addDot()">新增</a></td>
					<td class="borderRight"><a id="view" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-tip'" onclick="detailDot()">查看详情</a></td>
					<td><label>名称</label></td>
					<td><input name="name" id="name" class="easyui-textbox" style="width: 110px" /></td>
					<td><label>网点类型</label></td>
					<td><select name="typeCode" id="typeCode">
							<option value="" selected="selected">---请选择---</option>
							<option value="z">支行</option>
							<option value="f">分理处</option>
							<option value="s">直属分理处</option>
							<option value="b">便民服务点</option>
							<option value="l">离行自助</option>
					</select></td>
					<td class="borderLeft"><a id="search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="doQuery()">查询</a></td>
					<td><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-le-back'" onclick="doClear()">清空</a></td>
				</tr>
			</table>
		</div>
	</div>

	<div style="width: 300px;" data-options="region:'east',split:true">
		<table id="bankProductList" class="easyui-datagrid" title="产品列表"
			data-options="rownumbers:true,singleSelect:true,fit:true,striped:true,url:'${webRoot}/product/bank.do?datagrid&field=id,productId,business,name',method:'post'">
			<thead>
				<tr>
					<th data-options="field:'id',hidden:true">id</th>
					<th data-options="field:'productId',hidden:true">productId</th>
					<th data-options="field:'business',align:'center'">业务大类</th>
					<th data-options="field:'name',align:'center'">名称</th>
					<th data-options="field:'opt',align:'center',formatter:operate_product_formatter">操作</th>
				</tr>
			</thead>
		</table>
	</div>

	<form>
		<input type="hidden" name="parentId" id="parentId" />
	</form>
</body>
</html>