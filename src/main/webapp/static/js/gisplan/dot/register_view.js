$(function() {
  // 网点类型选择事件
  selectDomInit('typeCode', 'dot\.type', typeCode);

  theLocation_point(true);
  initValidform_list_def('formobj');
});