<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>商户详情</title>
<t:base type="jquery,easyui,tools,validform,bdmap"></t:base>
<script type="text/javascript"
	src="${webRoot}/static/js/gisplan/common.js"></script>
<script type="text/javascript"
	src="${webRoot}/static/js/gisplan/merchant/register_view.js"></script>
<link rel="stylesheet" href="${webRoot}/static/style/css/commcon.css">
</head>
<body style="width: 98%;">
	<form name="formobj" id="formobj" method="post"
		action="register.do?saveOrUpdate">
		<input type="hidden" name="merchant.id" value="${view.merchant.id }" />
		<input type="hidden" name="merchant.type"
			value="${view.merchant.type }" /> <input type="hidden"
			name="merchant.managementType"
			value="${view.merchant.managementType }" /> <input type="hidden"
			name="merchant.managementRange"
			value="${view.merchant.managementRange }" /> <input type="hidden"
			name="sg.id" value="${view.sg.id }" /><input type="hidden"
			name="sg.fkId" value="${view.sg.fkId }" /><input type="hidden"
			name="sg.fkKey" value="${view.sg.fkKey }" /><input type="hidden"
			name="dotIds" id="dotIds" />
		<!-- 商户详情 -->
		<div>
			<h3 class="lrq">商户信息</h3>
			<table style="width: 100%;" cellpadding="0" cellspacing="1"
				class="viewtable">
				<tbody>
					<tr>
						<th><label>编号</label></th>
						<td><input type="text" name="merchant.code"
							value="${view.merchant.code }" maxlength="10" /></td>
						<th><label class="required_sign">名称</label></th>
						<td><input type="text" name="merchant.name"
							value="${view.merchant.name }" datatype="*" sucmsg="名称验证通过!"
							nullmsg="请输入名称!" maxlength="30" /></td>
					</tr>
					<tr>
						<th><label>负责人</label></th>
						<td><input type="text" name="merchant.chargePerson"
							value="${view.merchant.chargePerson }" maxlength="10" /></td>
						<th><label>电话</label></th>
						<td><input type="text" name="merchant.phone"
							value="${view.merchant.phone }" datatype="*" ignore="ignore"
							maxlength="15" /></td>
					</tr>
					<tr>
						<th><label class="required_sign">商户类型</label></th>
						<td><select name="merchant.typeCode" id="typeCode"
							datatype="*" nullmsg="请选择商户类型!">
								<option value="" selected="selected">---请选择---</option>
								<option value="d">对公</option>
								<option value="g">个体</option>
								<option value="r">个人</option>
						</select></td>
						<th><label class="required_sign">经营大类</label></th>
						<td><select name="merchant.managementTypeCode"
							id="managementTypeCode" datatype="*" nullmsg="请选择经营大类!">
								<option value="" selected="selected">---请选择---</option>
								<option value="l">零售</option>
								<option value="p">批发</option>
								<option value="f">服务</option>
								<option value="c">餐饮</option>
						</select></td>
					</tr>
					<tr>
						<th><label class="required_sign">经营范围</label></th>
						<td><select name="merchant.managementRangeCode"
							id="managementRangeCode" datatype="*" nullmsg="请选择经营范围!">
								<option value="" selected="selected">---请选择---</option>
								<option value="c">餐饮</option>
								<option value="f">服装</option>
								<option value="j">建材</option>
						</select></td>
						<th><label>营销客户经理</label></th>
						<td><input type="text" name="merchant.accountManager"
							value="${view.merchant.accountManager }" maxlength="10" /></td>
					</tr>
					<tr>
						<th><label>拓展时间</label></th>
						<td><input type="text" name="merchant.expansionTime"
							value="${view.merchant.expansionTime }" maxlength="20" /></td>
						<th><label>地址</label></th>
						<td><input type="text" name="merchant.address"
							value="${view.merchant.address }" maxlength="50" /></td>
					</tr>
					<tr>

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

		<!-- 所属网点信息 -->
		<div>
			<h3 class="lrq" style="display: inline-block">所属网点信息</h3>
			<a id="dot_add_btn" class="easyui-linkbutton"
				data-options="iconCls:'icon-add'">新增</a>
			<table id="dg" style="width: 97%;">
				<thead>
					<tr>
						<th data-options="field:'id',hidden:true">id</th>
						<th data-options="field:'dotName',align:'center'">网点名称</th>
						<th
							data-options="field:'opt',align:'center',width:80,formatter:operate_formatter">操作</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${view.dots}" var="item" varStatus="status">
						<tr>
							<td>${item.dotId }</td>
							<td>${item.dotName }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
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
  var typeCode = '${view.merchant.typeCode}';
  var managementTypeCode = '${view.merchant.managementTypeCode}';
  var managementRangeCode = '${view.merchant.managementRangeCode}';
  var longitude = '${view.sg.longitude}';
  var latitude = '${view.sg.latitude}';
</script>