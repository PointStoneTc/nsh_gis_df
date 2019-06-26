var datagridId = "registerList", datagridId2 = 'bankProductList';

$(function() {
  $('#' + datagridId).datagrid({
    onClickRow: function(rowIndex, rowData) {
      if ($('#parentId').val() == rowData.id) return false;
      $('#' + datagridId2).datagrid('reload', {
        dotId: rowData.id
      });

      $('#parentId').val(rowData.id);
    }
  });
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
 * 刷新列表
 * 
 * @returns
 */
function reloadTable1() {
  $('#' + datagridId2).datagrid('reload');
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
          + '\', \'d\', 500, 500)" style="background-color:#2075c6;"><i class="fa fa-map-marker"></i>修改坐标</a> ' + '<a href="#" class="ace_button" alt="增加产品" onclick="addProduct('
          + index + ')" style="background-color:#2f2c2a;"><i class="fa fa-pencil"></i>增加产品</a>';
  return res;
}

/**
 * 产品操作按钮加载
 * 
 * @param value
 * @param rec
 * @param index
 * @returns
 */
function operate_product_formatter(value, rec, index) {
  var res = '<a href="#" class="ace_button" alt="删除" onclick="delProduct(' + index + ')" style="background-color:#f5270f;"><i class="fa fa-pencil"></i>删除</a> ';
  return res;
}

/**
 * 新增
 * 
 * @returns
 */
function addDot() {
  add('网点登记', 'dot/register.do?toAdd', datagridId, 600, 600);
}

/**
 * 详情
 * 
 * @returns
 */
function detailDot() {
  detail('网点详情', 'dot/register.do?toDetail', datagridId, 600, 600);
}

/**
 * 查询
 * 
 * @returns
 */
function doQuery() {
  $('#' + datagridId).datagrid('load', {
    name: $('#name').val(),
    typeCode: $('#typeCode').val()
  });
}

/**
 * 清空
 * 
 * @returns
 */
function doClear() {
  $('#name').textbox('clear');
  $('#typeCode').val('');
}

/**
 * 修改
 * 
 * @returns
 */
function edit(index) {
  $('#' + datagridId).datagrid('selectRow', index);
  update('网点修改', 'dot/register.do?toUpdate', datagridId, 600, 600);
}

/**
 * 删除
 * 
 * @param index
 * @returns
 */
function del(index) {
  $('#' + datagridId).datagrid('selectRow', index);
  var row = $('#' + datagridId).datagrid('getSelected');
  delObj('register.do?del&id=' + row.id, datagridId);
}

/**
 * 增加产品列表
 */
function addProduct(index) {
  var productIds = '';
  $('#' + datagridId).datagrid('selectRow', index);
  var row = $('#' + datagridId).datagrid('getSelected');
  $.each($('#' + datagridId2).datagrid('getRows'), function(i, n) {
    productIds += n.productId + ',';
  });

  if (productIds.length > 0) {
    productIds = productIds.substr(0, productIds.length - 1);
  }

  var url = basePath + '/product/bank/select.do?oneOrMult&singleSelect=false&excludeIds=' + productIds;
  $.dialog({
    content: 'url:' + url,
    zIndex: 2100,
    title: '选择银行产品',
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
        if (res != '' && res != null) {
          addProductCallback(row.id, res);
        }
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
 * 添加产品回调
 * 
 * @param dotId
 * @param res
 * @returns
 */
function addProductCallback(dotId, res) {
  var productIds = '';
  $.each(res, function(i, n) {
    productIds += n.id + ',';
  });

  productIds = productIds.substr(0, productIds.length - 1);

  $.ajax({
    url: basePath + '/product/bank.do?save',
    type: 'post',
    data: {
      dotId: dotId,
      productIds: productIds
    },
    cache: false,
    success: function(data) {
      var d = $.parseJSON(data);
      if (d.success) {
        var msg = d.msg;
        tip(msg);
        $('#' + datagridId2).datagrid('reload');
      }
    }
  });
}

/**
 * 删除产品
 * 
 * @param index
 * @returns
 */
function delProduct(index) {
  $('#' + datagridId2).datagrid('selectRow', index);
  var row = $('#' + datagridId2).datagrid('getSelected');
  createDialogWithCallback('删除', '确定删除产品?', basePath + '/product/bank.do?del&id=' + row.id, '', reloadTable1, true);
}
