/**
 * 渲染普通表单或者内容中的性别
 * 
 * @returns
 */
function rendering_sex() {
  $.each($('.sex'), function(i, n) {
    var text = $(n).text();
    if (text == 'm')
      $(n).text('男');
    else
      $(n).text('女');
  });
}

/**
 * 渲染普通表单或者内容中的是非
 * 
 * @returns
 */
function rendering_rw() {
  $.each($('.rw'), function(i, n) {
    var text = $(n).text();
    if (text == '1')
      $(n).text('是');
    else
      $(n).text('否');
  });
}

/**
 * easy-ui渲染货币
 * 
 * @param val
 * @param row
 * @returns
 */
function formatMoney(val, row) {
  if (row.isFooter) { // 判断出是否footer
    return val;
  }

  if (isNaN(val)) return 'NaN';

  var res = '-';
  if (parseFloat(val) != 0) res = formatNull(Math.abs(val), row);

  return '<span class="easyui-formatter-money rmb">' + res + '</span>';
}

/**
 * easy-ui渲染性别
 * 
 * @param val
 * @param row
 * @returns
 */
function formatSex(val, row) {
  if (row.isFooter) { // 判断出是否footer
    return val;
  }

  var res = '';
  if (val == 'm')
    res = '男';
  else if (val == 'f') res = '女';

  return '<span>' + res + '</span>';
}

/**
 * easy-ui渲染是否
 * 
 * @param val
 * @param row
 * @returns
 */
function formatRw(val, row) {
  if (row.isFooter) { // 判断出是否footer
    return val;
  }

  var res = '';
  if (val == 0)
    res = '否';
  else if (val == '1') res = '是';

  return '<span>' + res + '</span>';
}

/**
 * easy-ui渲染时间
 * 
 * @param val
 * @param row
 * @returns
 */
function formatDate(val, row) {
  if (row.isFooter) { // 判断出是否footer
    return val;
  }

  var d = new Date(val);
  return '<span>' + d.format1('yyyy-MM-dd') + '</span>';
}

/**
 * 通用selectDom对象选择change事件和初始化
 * 
 * @param domId
 *          selectDom对象的Id
 * @param textDomName
 *          关联inputDmo对象的名字
 * @param domIdVal
 *          初始化selectDom对象的值
 * @returns
 */
function selectDomInit(domId, textDomName, domIdVal) {
  // 商户类型选择事件
  if (textDomName != '') {
    $('#' + domId).on('change', function(e) {
      var selectText = $(this).find('option:selected').text();
      $(':input[name="' + textDomName + '"]').val(selectText);
    });
  }
  $('#' + domId + ' option[value=' + domIdVal + ']').attr('selected', 'selected');
}

/**
 * 默认表单验证加载
 * 
 * @param formobjId
 *          表单对象id
 * @returns
 */
function initValidform_list_def(formobjId) {
  $("#" + formobjId).Validform({
    tiptype: function(msg, o, cssctl) {
      if (o.type == 3) {
        ValidationMessage(o.obj, msg);
      } else {
        removeMessage(o.obj);
      }
    },
    btnSubmit: "#btn_sub",
    ajaxPost: true,
    beforeSubmit: function(curform) {
      var tag = false;
      subDlgIndex = $.dialog({
        content: '正在加载中',
        zIndex: 19910320,
        lock: true,
        width: 100,
        height: 50,
        opacity: 0.3,
        title: '提示',
        cache: false
      });
      var infoTable = subDlgIndex.DOM.t.parent().parent().parent();
      infoTable.parent().append('<div id="infoTable-loading" style="text-align:center;"><img src="plug-in/layer/skin/default/loading-0.gif"/></div>');
      infoTable.css('display', 'none');
    },
    callback: function(data) {
      if (subDlgIndex && subDlgIndex != null) {
        $('#infoTable-loading').hide();
        subDlgIndex.close();
      }
      var win = frameElement.api.opener;
      if (data.success == true) {
        frameElement.api.close();
        win.tip(data.msg);
        win.reloadTable();
      } else {
        if (data.responseText == '' || data.responseText == undefined) {
          $.messager.alert('错误', data.msg);
          $.Hidemsg();
        } else {
          try {
            var emsg = data.responseText.substring(data.responseText.indexOf('错误描述'), data.responseText.indexOf('错误信息'));
            $.messager.alert('错误', emsg);
            $.Hidemsg();
          } catch (ex) {
            $.messager.alert('错误', data.responseText + "");
            $.Hidemsg();
          }
        }
        return false;
      }
    }
  });
}