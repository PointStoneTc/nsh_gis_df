$(function() {
  theLocation_s();
  initValidform();
});

/**
 * 表单验证加载
 * 
 * @returns
 */
function initValidform() {
  $("#formobj").Validform({
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
        win.editCoordinateCallBack(index, gridId, longitude, latitude);
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

/**
 * 地图定位
 * 
 * @returns
 */
function theLocation_s() {
  var map = new BMap.Map('allmap');
  map.centerAndZoom(gis_map_city, gis_map_grade);
  map.enableScrollWheelZoom(true);

  if (longitude != '' && latitude != '') showInfo(map, longitude, latitude, false);

  map.addEventListener("click", function(e) {
    showInfo(this, e.point.lng, e.point.lat, true);
  });
}

function showInfo(map_s, lng, lat, isAssignment) {
  map_s.clearOverlays();
  var new_point = new BMap.Point(lng, lat);
  var marker = new BMap.Marker(new_point); // 创建标注
  map_s.addOverlay(marker); // 将标注添加到地图中
  map_s.panTo(new_point);

  if (isAssignment) {
    $('#longitude').val(lng);
    $('#latitude').val(lat);
    longitude = lng;
    latitude = lat;
  }
}