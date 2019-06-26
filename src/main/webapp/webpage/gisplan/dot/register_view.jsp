<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>网点详情</title>
<t:base type="jquery,easyui,tools,validform,bdmap"></t:base>
<script type="text/javascript" src="${webRoot}/static/js/gisplan/common.js"></script>
<script type="text/javascript" src="${webRoot}/static/js/gisplan/dot/register_view.js"></script>
<link rel="stylesheet" href="${webRoot}/static/style/css/commcon.css">
</head>
<body style="width: 98%;">
	<form name="formobj" id="formobj" method="post" action="register.do?saveOrUpdate">
		<input type="hidden" name="dot.id" value="${view.dot.id }" /> <input type="hidden" name="dot.type" value="${view.dot.type }" /> <input type="hidden" name="sg.id"
			value="${view.sg.id }" /><input type="hidden" name="sg.fkId" value="${view.sg.fkId }" /><input type="hidden" name="sg.fkKey" value="${view.sg.fkKey }" />
		<!-- 网点详情 -->
		<div>
			<h3 class="lrq">网点信息</h3>
			<table style="width: 100%;" cellpadding="0" cellspacing="1" class="viewtable">
				<tbody>
					<tr>
						<th><label>编号</label></th>
						<td><input type="text" name="dot.code" value="${view.dot.code }" maxlength="10" /></td>
						<th><label class="required_sign">名称</label></th>
						<td><input type="text" name="dot.name" value="${view.dot.name }" datatype="*" sucmsg="名称验证通过!" nullmsg="请输入名称!" maxlength="30" /></td>
					</tr>
					<tr>
						<th><label class="required_sign">网点类型</label></th>
						<td><select name="dot.typeCode" id="typeCode" datatype="*" nullmsg="请选择网点类型!">
								<option value="" selected="selected">---请选择---</option>
								<option value="z">支行</option>
								<option value="f">分理处</option>
								<option value="s">直属分理处</option>
								<option value="b">便民服务点</option>
						</select></td>
						<th><label>负责人</label></th>
						<td><input type="text" name="dot.chargePerson" value="${view.dot.chargePerson }" maxlength="10" /></td>
					</tr>
					<tr>
						<th><label>电话</label></th>
						<td><input type="text" name="dot.phone" value="${view.dot.phone }" datatype="*" ignore="ignore" maxlength="15" /></td>
						<th><label>外网网关</label></th>
						<td><input type="text" name="dot.gatewayInIp" value="${view.dot.gatewayInIp }" maxlength="15" /></td>
					</tr>
					<tr>
						<th><label>内网网关</label></th>
						<td><input type="text" name="dot.gatewayAddIpv4" value="${view.dot.gatewayOutIp }" maxlength="15" /></td>
						<th><label>地址</label></th>
						<td><input type="text" name="dot.address" value="${view.dot.address }" maxlength="50" /></td>
					</tr>
					<tr>
						<th><label>经度</label></th>
						<td><input type="text" name="sg.longitude" id="longitude" value="${view.sg.longitude }" readonly="readonly" /></td>
						<th><label>纬度</label></th>
						<td><input type="text" name="sg.latitude" id="latitude" value="${view.sg.latitude }" readonly="readonly" /></td>
				</tbody>
			</table>
			<div class="split blue_line"></div>
		</div>
		<input type="hidden" id="btn_sub" class="btn_sub" />
	</form>

	<div>
		<h3 class="lrq">地图信息</h3>
		<div id="allmap" class="map"></div>
	</div>
</body>
</html>
<script type="text/javascript">
  var typeCode = '${view.dot.typeCode}';
  var longitude = '${view.sg.longitude}';
  var latitude = '${view.sg.latitude}';
</script>