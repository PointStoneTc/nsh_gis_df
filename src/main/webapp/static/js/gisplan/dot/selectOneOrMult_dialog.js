var datagridId = "dotList";

$(function() {
});

function getAllBasic() {
  var rows = $('#' + datagridId).datagrid('getSelections');
  if (rows == null) return '';
  return rows;
}