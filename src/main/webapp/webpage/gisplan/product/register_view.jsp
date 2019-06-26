<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>银行产品查看</title>
<t:base type="jquery,easyui,tools,DatePicker,validform"></t:base>
<script type="text/javascript" src="${webRoot}/static/js/gisplan/common.js"></script>
<script type="text/javascript" src="${webRoot}/static/js/gisplan/product/register_view.js"></script>
<link rel="stylesheet" href="${webRoot}/static/style/css/commcon.css">
</head>
<body style="width: 90%;">
	<form name="formobj" id="formobj" method="post" action="register.do?saveOrUpdate">
		<!-- 借款信息 -->
		<div>
			<input type="hidden" name="id" id="id" value="${view.id }" />
			<input type="hidden" name="business" value="${view.business }" />
			<table style="width: 100%;" cellpadding="0" cellspacing="1" class="viewtable">
				<tbody>
					<tr>
						<th><label class="required_sign">业务大类</label></th>
						<td><select name="businessCode" id="businessCode" datatype="*" nullmsg="请选择业务大类!">
								<option value="" selected="selected">---请选择---</option>
								<option value="d">贷款</option>
								<option value="z">支付</option>
								<option value="c">存款</option>
								<option value="r">中间业务</option>
						</select></td>
					</tr>
					<tr>
						<th><label class="required_sign">名称</label></th>
						<td><input type="text" name="name" value="${view.name }" datatype="s1-10" sucmsg="名称验证通过!" nullmsg="请输入名称!" maxlength="10" /></td>
					</tr>
					<tr>
						<th><label>是否生效</label></th>
						<td><input type="radio" value="0" name="isEffect" id="isEffect_y">否<input type="radio" value="1" name="isEffect" checked="checked">是</td>
					</tr>
					<tr>
						<th><label>生效时间</label></th>
						<td><input type="text" class="Wdate" onClick="WdatePicker({isShowClear:false,readOnly:true})" name="effectDate" id="effectDate" value="${view.effectDate }" datatype="*"
								ignore="ignore" sucmsg="生效时间验证通过!" /></td>
					</tr>
					<tr>
						<th><label>失效时间</label></th>
						<td><input type="text" class="Wdate" onClick="WdatePicker({isShowClear:false,readOnly:true})" name="invalidDate" id="invalidDate" value="${view.invalidDate }"
								datatype="*" ignore="ignore" sucmsg="失效时间验证通过!" /></td>
					</tr>
				</tbody>
			</table>
		</div>

		<input type="hidden" id="btn_sub" class="btn_sub" />
	</form>
</body>
</html>
<script type="text/javascript">
  var isEffect = '${view.isEffect}';
  var businessCode = '${view.businessCode}';
</script>