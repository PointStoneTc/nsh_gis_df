<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>商家列表</title>
<t:base type="jquery,easyui,tools,bdmap"></t:base>
<script type="text/javascript"
	src="${webRoot}/static/js/gisplan/common.js"></script>
<script type="text/javascript"
	src="${webRoot}/static/js/gisplan/merchant/register_list.js"></script>
<link rel="stylesheet" href="${webRoot}/static/style/css/commcon.css">
</head>
<body class="easyui-layout">
	<div region="center" style="padding: 0px; border: 0px">
		<table id="registerList" name="registerList" class="easyui-datagrid"
			title="商家列表"
			data-options="rownumbers:true,singleSelect:true,pagination:true,pageSize:20,fit:true,striped:true,url:'register.do?datagrid&field=id,code,name,address,chargePerson,phone,type,managementType,managementRange,accountManager,expansionTime,longitude,latitude',method:'post',toolbar:'#tb'">
			<thead>
				<tr>
					<th data-options="field:'id',hidden:true">id</th>
					<th data-options="field:'code',align:'center'">编号</th>
					<th data-options="field:'name',align:'center'">名称</th>
					<th data-options="field:'address',align:'center'">地址</th>
					<th data-options="field:'chargePerson',align:'center'">负责人</th>
					<th data-options="field:'phone',align:'center'">电话</th>
					<th data-options="field:'type',align:'center'">商户类型</th>
					<th data-options="field:'managementType',align:'center'">经营大类</th>
					<th data-options="field:'managementRange',align:'center'">经营范围</th>
					<th data-options="field:'accountManager',align:'center'">营销客户经理</th>
					<th data-options="field:'expansionTime',align:'center'">拓展时间</th>
					<th data-options="field:'longitude',align:'center'">经度</th>
					<th data-options="field:'latitude',align:'center'">纬度</th>
					<th
						data-options="field:'opt',align:'center',formatter:operate_formatter">操作</th>
				</tr>
			</thead>
		</table>

		<div id="tb" style="padding: 2px 5px;">
			<table class="queryToolbarTable">
				<tbody>
					<tr>
						<td rowspan="2" colspan="1"><a id="add" href="#"
							class="easyui-linkbutton" data-options="iconCls:'icon-add'"
							onclick="addMerchant()">新增</a></td>
						<td rowspan="2" colspan="1" class="borderRight"><a id="view"
							href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-tip'" onclick="detailMerchant()">查看详情</a></td>
						<td><label>名称</label></td>
						<td><input name="name" id="name" class="easyui-textbox"
							style="width: 110px" /></td>
						<td><label>所属网点</label></td>
						<td rowspan="1" colspan="3"><select name="dotId" id="dotId"
							style="width: 250px">
								<option value="" selected="selected">---请选择---</option>
								<c:forEach items="${dots}" var="item" varStatus="status">
									<option value="${item.id }">${item.name }</option>
								</c:forEach>
						</select></td>
						<td rowspan="2" colspan="1" class="borderLeft"><a id="search"
							href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-search'" onclick="doQuery()">查询</a></td>
						<td rowspan="2" colspan="1"><a href="#"
							class="easyui-linkbutton" data-options="iconCls:'icon-le-back'"
							onclick="doClear()">清空</a></td>
					</tr>
					<tr>
						<td><label>商户类型</label></td>
						<td><select name="typeCode" id="typeCode">
								<option value="" selected="selected">---请选择---</option>
								<option value="d">对公</option>
								<option value="g">个体</option>
								<option value="r">个人</option>
						</select></td>
						<td><label>经营大类</label></td>
						<td><select name="managementTypeCode" id="managementTypeCode">
								<option value="" selected="selected">---请选择---</option>
								<option value="l">零售</option>
								<option value="p">批发</option>
								<option value="f">服务</option>
								<option value="c">餐饮</option>
						</select></td>
						<td><label>经营范围</label></td>
						<td><select name="managementRangeCode"
							id="managementRangeCode">
								<option value="" selected="selected">---请选择---</option>
								<option value="c">餐饮</option>
								<option value="f">服装</option>
								<option value="j">建材</option>
						</select></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<div style="width: 320px;" data-options="region:'east',split:true">
		<table id="merchantProductList" class="easyui-datagrid" title="产品列表"
			data-options="rownumbers:true,singleSelect:true,fit:true,striped:true,url:'${webRoot}/product/merchant.do?datagrid&field=id,bankProductId,dotName,business,name',method:'post'">
			<thead>
				<tr>
					<th data-options="field:'id',hidden:true">id</th>
					<th data-options="field:'bankProductId',hidden:true">bankProductId</th>
					<th data-options="field:'dotName',align:'center'">所属网点</th>
					<th data-options="field:'business',align:'center'">业务大类</th>
					<th data-options="field:'name',align:'center'">名称</th>
					<th
						data-options="field:'opt',align:'center',formatter:operate_product_formatter">操作</th>
				</tr>
			</thead>
		</table>
	</div>

	<form>
		<input type="hidden" name="parentId" id="parentId" />
	</form>
</body>
</html>