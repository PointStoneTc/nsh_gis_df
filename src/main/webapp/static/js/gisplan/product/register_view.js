$(function() {
  $('#businessCode').on('change', function(e) {
    var selectText = $(this).find('option:selected').text();
    $(':input[name="business"]').val(selectText);
  });

  $('input[type=radio][name=isEffect]').change(function() {
    if (this.value == '1') {
      $('#effectDate').removeAttr('disabled');
      $('#invalidDate').removeAttr('disabled');
    } else if (this.value == '0') {
      $('#effectDate').val('').attr('disabled', 'disabled');
      $('#invalidDate').val('').attr('disabled', 'disabled');
    }
  });

  $('#businessCode option[value=' + businessCode + ']').attr('selected', 'selected');
  if (isEffect != '') $(':input[name="isEffect"]:eq(' + isEffect + ')').attr('checked', 'checked');

  initValidform_list_def('formobj');
});