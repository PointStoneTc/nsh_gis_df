var datagridId = 'dg';

$(function() {
  $('#' + datagridId).datagrid({
    singleSelect: true,
    rownumbers: true
  });

  // 查询网点
  $('#dot_add_btn').on('click', addDot);

  // 商户类型选择事件
  selectDomInit('typeCode', 'merchant\.type', typeCode);
  // 经营大类选择事件
  selectDomInit('managementTypeCode', 'merchant\.managementType', managementTypeCode);
  // 经营范围
  selectDomInit('managementRangeCode', 'merchant\.managementRange', managementRangeCode);

  setDotIds();
  theLocation_point(true);
  initValidform_list_def('formobj');
});

/**
 * 选择网点弹出窗口
 * 
 * @param event
 * @returns
 */
function addDot(event) {
  var url = 'dot/select.do?oneOrMult&singleSelect=false&excludeIds=' + $('#dotIds').val();
  $.dialog({
    content: 'url:' + url,
    zIndex: 2100,
    title: '选择网点',
    lock: true,
    width: 550,
    height: 400,
    left: '50%',
    top: '30%',
    opacity: 0.4,
    button: [{
      name: '确定',
      callback: function() {
        iframe = this.iframe.contentWindow;
        var res = iframe.getAllBasic();
        if (res == '' || res == null) return;

        $.each(res, function(i, n) {
          $('#' + datagridId).datagrid('appendRow', {
            'id': n.id,
            'dotName': n.name
          });
        });
        setDotIds();
      },
      focus: true
    }, {
      name: '取消',
      callback: function() {
      }
    }]
  });
}

/**
 * 操作按钮加载
 * 
 * @param value
 * @param rec
 * @param index
 * @returns
 */
function operate_formatter(value, rec, index) {
  return '<a href="#" class="ace_button" alt="删除" onclick="del()" style="background-color:#f5270f;"><i class="fa fa-trash-o"></i>删除</a>';
}

/**
 * 删除操作
 * 
 * @returns
 */
function del() {
  var row = $('#' + datagridId).datagrid('getSelected');
  if (row == undefined) {
    tip('请先选择删除项目!');
    return false;
  }
  var index = $('#' + datagridId).datagrid('getRowIndex', row);
  $('#' + datagridId).datagrid('deleteRow', index);
  setDotIds();
}

/**
 * 设置网点id序列
 * 
 * @returns
 */
function setDotIds() {
  var dotIds = '';
  $.each($('#' + datagridId).datagrid('getRows'), function(i, n) {
    dotIds += n.id + ',';
  });

  if (dotIds.length > 0) {
    dotIds = dotIds.substr(0, dotIds.length - 1);
    $('#dotIds').val(dotIds);
  } else {
    $('#dotIds').val('');
  }
}