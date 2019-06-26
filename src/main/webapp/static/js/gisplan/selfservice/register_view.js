$(function() {
  // 自助设备类型选择事件
  selectDomInit('typeCode', 'selfservice\.type', typeCode);

  selectDomInit('dotId', '', dotId);
  theLocation_point(true);
  initValidform_list_def('formobj');
});