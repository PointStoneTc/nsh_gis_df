var datagridId = "registerList", datagridId2 = 'merchantProductList';

$(function() {
  $('#' + datagridId).datagrid({
    onClickRow: function(rowIndex, rowData) {
      if ($('#parentId').val() == rowData.id) return false;
      $('#' + datagridId2).datagrid('reload', {
        merchantId: rowData.id
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
          + '\', \'m\', 500, 500)" style="background-color:#2075c6;"><i class="fa fa-map-marker"></i>修改坐标</a> ' + '<a href="#" class="ace_button" alt="增加产品" onclick="addProduct('
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
function addMerchant() {
  add('商家登记', 'merchant/register.do?toAdd', datagridId, 600, 700);
}

/**
 * 详情
 * 
 * @returns
 */
function detailMerchant() {
  detail('商家详情', 'merchant/register.do?toDetail', datagridId, 600, 700);
}

/**
 * 查询
 * 
 * @returns
 */
function doQuery() {
  $('#' + datagridId).datagrid('load', {
    name: $('#name').val(),
    dotId: $('#dotId').val(),
    typeCode: $('#typeCode').val(),
    managementTypeCode: $('#managementTypeCode').val(),
    managementRangeCode: $('#managementRangeCode').val()
  });
}

/**
 * 清空
 * 
 * @returns
 */
function doClear() {
  $('#name').textbox('clear');
  $('#dotId').val('');
  $('#typeCode').val('');
  $('#managementTypeCode').val('');
  $('#managementRangeCode').val('');
}

/**
 * 修改
 * 
 * @returns
 */
function edit(index) {
  $('#' + datagridId).datagrid('selectRow', index);
  $('#' + datagridId).datagrid('getSelected');
  update('商家修改', 'merchant/register.do?toUpdate', datagridId, 600, 700);
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
 * 增加产品列表
 */
function addProduct(index) {
  var bankProductIds = '';
  $('#' + datagridId).datagrid('selectRow', index);
  var row = $('#' + datagridId).datagrid('getSelected');
  $.each($('#' + datagridId2).datagrid('getRows'), function(i, n) {
    bankProductIds += n.bankProductId + ',';
  });

  if (bankProductIds.length > 0) {
    bankProductIds = bankProductIds.substr(0, bankProductIds.length - 1);
  }

  var url = basePath + '/product/merchant/select.do?oneOrMult&merchantId=' + row.id + '&singleSelect=false&excludeIds=' + bankProductIds;
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
 * @param merchantId
 * @param res
 * @returns
 */
function addProductCallback(merchantId, res) {
  var bankProductIds = '';
  $.each(res, function(i, n) {
    bankProductIds += n.id + ',';
  });

  bankProductIds = bankProductIds.substr(0, bankProductIds.length - 1);

  $.ajax({
    url: basePath + '/product/merchant.do?save',
    type: 'post',
    data: {
      merchantId: merchantId,
      bankProductIds: bankProductIds
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
  createDialogWithCallback('删除', '确定删除产品?', basePath + '/product/merchant.do?del&id=' + row.id, '', reloadTable1, true);
}