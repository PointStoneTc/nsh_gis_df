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
  var res = '<a href="#" class="ace_button" alt="修改" onclick="edit(' + index + ')" style="background-color:#63c91a;"><i class="fa fa-pencil"></i>修改</a> '
          + '<a href="#" class="ace_button" alt="删除" onclick="del(' + index + ')" style="background-color:#f5270f;"><i class="fa fa-trash-o"></i>删除</a> '
          + '<a href="#" class="ace_button" alt="修改坐标" onclick="editCoordinate(' + index + ',\'' + datagridId
          + '\', \'s\', 500, 500)" style="background-color:#2075c6;"><i class="fa fa-map-marker"></i>修改坐标</a> '
  if (rec.parentLongitude != '' && rec.parentLatitude != '')
    res += '<a href="#" class="ace_button" alt="使用支行坐标" onclick="setParentCoordinate(' + index + ',' + rec.id + ',' + rec.dotId + ',' + rec.parentLongitude + ','
            + rec.parentLatitude + ')" style="background-color:#2075c6;"><i class="fa fa-map-marker"></i>使用支行坐标</a>';
  return res;
}

/**
 * 新增
 * 
 * @returns
 */
function addContract() {
  add('自助设备登记', 'selfservice/register.do?toAdd', datagridId, 600, 550);
}

/**
 * 详情
 * 
 * @returns
 */
function detailContract() {
  detail('自助设备详情', 'selfservice/register.do?toDetail', datagridId, 600, 550);
}

/**
 * 查询
 * 
 * @returns
 */
function doQuery() {
  $('#' + datagridId).datagrid('load', {
    dotId: $('#dotId').val(),
    typeCode: $('#typeCode').val()
  });
}

/**
 * 清空
 * 
 * @returns
 */
function doClear() {
  $('#dotId').val('');
  $('#typeCode').val('');
}

/**
 * 修改
 * 
 * @returns
 */
function edit(index) {
  $('#' + datagridId).datagrid('selectRow', index);
  $('#' + datagridId).datagrid('getSelected');
  update('自助设备修改', 'selfservice/register.do?toUpdate', datagridId, 600, 550);
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
 * 使用支行坐标
 * 
 * @param index
 * @param rec
 * @returns
 */
function setParentCoordinate(index, selfId, parentId, lng, lat) {
  $('#' + datagridId).datagrid('selectRow', index);
  var row = $('#' + datagridId).datagrid('getSelected');
  var selectedIndex = index;
  createDialogWithCallback('确认', '确认使用所属支行坐标为本设备坐标!', '../storeGeometry.do?setParentCoordinate', {
    parentId: parentId,
    parentKey: 'd',
    selfId: selfId,
    selfKey: 's'
  }, function() {
    $('#' + datagridId).datagrid('updateRow', {
      index: selectedIndex,
      row: {
        longitude: lng,
        latitude: lat
      }
    });
  });
}