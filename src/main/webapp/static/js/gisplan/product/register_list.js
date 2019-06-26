var datagridId = "registerList";

$(function() {

});

/**
 * 刷新列表
 * 
 * @returns
 */
function reloadTable() {
  $('#' + datagridId).datagrid('reload');
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
  if (rec.isCommit == '0')
    var res = '<a href="#" class="ace_button" alt="修改" onclick="edit(' + index + ')" style="background-color:#63c91a;"><i class="fa fa-pencil"></i>修改</a> '
            + '<a href="#" class="ace_button" alt="删除" onclick="del(' + index + ')" style="background-color:#f5270f;"><i class="fa fa-trash-o"></i>删除</a> '
            + '<a href="#" class="ace_button" alt="提交" onclick="commit(' + index + ')" style="background-color:#1a7bb9;"><i class="fa fa-check"></i>提交</a> ';
  return res;
}

/**
 * 新增
 * 
 * @returns
 */
function addProduct() {
  add('银行产品登记', 'product/register.do?toAdd', datagridId, 300, 300);
}

/**
 * 查询
 * 
 * @returns
 */
function doQuery() {
  $('#' + datagridId).datagrid('load', {
    businessCode: $('#businessCode').val()
  });
}

/**
 * 清空
 * 
 * @returns
 */
function doClear() {
  $('#businessCode').val('');
}

/**
 * 修改
 * 
 * @returns
 */
function edit(index) {
  $('#' + datagridId).datagrid('selectRow', index);
  update('银行产品修改', 'product/register.do?toUpdate', datagridId, 300, 300);
}

/**
 * 删除
 * 
 * @param index
 * @returns
 */
function del(index, url, param) {
  $('#' + datagridId).datagrid('selectRow', index);
  var row = $('#' + datagridId).datagrid('getSelected');
  delObj('register.do?del&id=' + row.id, datagridId);
}

/**
 * 提交
 * 
 * @param index
 * @returns
 */
function commit(index) {
  $('#' + datagridId).datagrid('selectRow', index);
  var row = $('#' + datagridId).datagrid('getSelected');
  var selectedIndex = index;
  createDialogWithCallback('确认', '确认提交', 'register.do?commit', {
    id: row.id
  }, function() {
    $('#' + datagridId).datagrid('updateRow', {
      index: selectedIndex,
      row: {
        isCommit: 1
      }
    });
  });
}