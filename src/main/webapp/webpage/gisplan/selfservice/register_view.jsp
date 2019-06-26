<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>自助设备详情</title>
<t:base type="jquery,easyui,tools,validform,bdmap"></t:base>
<script type="text/javascript"
	src="${webRoot}/static/js/gisplan/common.js"></script>
<script type="text/javascript"
	src="${webRoot}/static/js/gisplan/selfservice/register_view.js"></script>
<link rel="stylesheet" href="${webRoot}/static/style/css/commcon.css">
</head>
<body style="width: 98%;">
	<form name="formobj" id="formobj" method="post"
		action="register.do?saveOrUpdate">
		<input type="text" name="selfservice.id"
			value="${view.selfservice.id }" /> <input type="text"
			name="selfservice.type" value="${view.selfservice.type }" /> <input
			type="hidden" name="sg.id" value="${view.sg.id }" />
		<!-- 自助设备详情 -->
		<div>
			<h3 class="lrq">自助设备信息</h3>
			<table style="width: 100%;" cellpadding="0" cellspacing="1"
				class="viewtable">
				<tbody>
					<tr>
						<th><label>编号</label></th>
						<td><input type="text" name="selfservice.code"
							value="${view.selfservice.code }" maxlength="10" /></td>
						<th><label class="required_sign">名称</label></th>
						<td><input type="text" name="selfservice.name"
							value="${view.selfservice.name }" datatype="*" sucmsg="名称验证通过!"
							nullmsg="请输入名称!" maxlength="30" /></td>
					</tr>
					<tr>
						<th><label class="required_sign">设备类型</label></th>
						<td><select name="selfservice.typeCode" id="typeCode"
							datatype="*" nullmsg="请选择设备类型!">
								<option value="" selected="selected">---请选择---</option>
								<option value="z">智能柜员机</option>
								<option value="f">填单机</option>
								<option value="c">查询机</option>
								<option value="a">ATM</option>
								<option value="s">CRS</option>
								<option value="w">网银体验机</option
						</select></td>
						<th><label class="required_sign">所属支行</label></th>
						<td><select name="selfservice.dotId" id="dotId" datatype="*"
							nullmsg="请选择所属支行!">
								<option value="" selected="selected">---请选择---</option>
								<c:forEach items="${dots}" var="item" varStatus="status">
									<option value="${item.id }">${item.name }</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<th><label>地址</label></th>
						<td colspan="3"><input type="text" name="selfservice.address"
							value="${view.selfservice.address }" maxlength="50"
							style="width: 440px" /></td>
					</tr>
					<tr>
						<th><label>经度</label></th>
						<td><input type="text" name="sg.longitude" id="longitude"
							value="${view.sg.longitude }" readonly="readonly" /></td>
						<th><label>纬度</label></th>
						<td><input type="text" name="sg.latitude" id="latitude"
							value="${view.sg.latitude }" readonly="readonly" /></td>
				</tbody>
			</table>
			<div class="split blue_line"></div>
		</div>
		<input type="hidden" id="btn_sub" class="btn_sub" />
	</form>

	<div>
		<h3 class="lrq">地图信息</h3>
		<div id="allmap" class="map" style="width: 100%; height: 250px"></div>
	</div>
</body>
</html>
<script type="text/javascript">
  var typeCode = '${view.selfservice.typeCode}';
  var dotId = '${view.selfservice.dotId}';
  var longitude = '${view.sg.longitude}';
  var latitude = '${view.sg.latitude}';
</script>