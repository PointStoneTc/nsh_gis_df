<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>网点详情</title>
<t:base type="jquery,easyui,tools,bdmap"></t:base>
<script type="text/javascript"
	src="${webRoot}/static/js/gisplan/common.js"></script>
<script type="text/javascript"
	src="${webRoot}/static/js/gisplan/dot/register_detail.js"></script>
<link rel="stylesheet" href="${webRoot}/static/style/css/commcon.css">
</head>
<body style="width: 98%;">
	<!-- 网点详情 -->
	<div>
		<h3 class="lrq">网点信息</h3>
		<table style="width: 100%;" cellpadding="0" cellspacing="1"
			class="viewtable">
			<tbody>
				<tr>
					<th><label>编号</label></th>
					<td>${view.dot.code }</td>
					<th><label>名称</label></th>
					<td>${view.dot.name }</td>
				</tr>
				<tr>
					<th><label>网点类型</label></th>
					<td>${view.dot.type }</td>
					<th><label>负责人</label></th>
					<td>${view.dot.chargePerson }</td>
				</tr>
				<tr>
					<th><label>电话</label></th>
					<td>${view.dot.phone }</td>
					<th><label>外网网关</label></th>
					<td>${view.dot.gatewayInIp }</td>
				</tr>
				<tr>
					<th><label>内网网关</label></th>
					<td>${view.dot.gatewayOutIp }</td>
					<th><label>地址</label></th>
					<td>${view.dot.address }</td>
				</tr>
				<tr>
					<th><label>经度</label></th>
					<td>${view.sg.longitude }</td>
					<th><label>纬度</label></th>
					<td>${view.sg.latitude }</td>
				</tr>
			</tbody>
		</table>
		<div class="split blue_line"></div>
	</div>

	<!-- 产品信息 -->
	<div>
		<h3 class="lrq" style="display: inline-block">产品信息</h3>
		<table id="dg" style="width: 100%;">
			<thead>
				<tr>
					<th data-options="field:'business',align:'center'">产品大类</th>
					<th data-options="field:'name',width:80,align:'center'">产品名称</th>
					<th
						data-options="field:'isEffect',align:'center',formatter:formatRw">是否生效</th>
					<th data-options="field:'effectDate',width:80,align:'center'">生效时间</th>
					<th data-options="field:'invalidDate',width:80,align:'center'">失效时间</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${view.products}" var="item" varStatus="status">
					<tr>
						<td>${item.business }</td>
						<td>${item.name }</td>
						<td>${item.isEffect }</td>
						<td>${item.effectDate }</td>
						<td>${item.invalidDate }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="split blue_line"></div>
	</div>

	<div>
		<h3 class="lrq">地图信息</h3>
		<div id="allmap" class="map"></div>
	</div>
</body>
</html>
<script type="text/javascript">
  var longitude = '${view.sg.longitude}';
  var latitude = '${view.sg.latitude}';
</script>