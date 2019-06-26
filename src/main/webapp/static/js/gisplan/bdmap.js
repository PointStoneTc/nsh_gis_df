var gis_map_city = '登封', gis_map_grade = 14;

/**
 * 用经纬度设置地图中心点
 * 
 * @returns
 */
function theLocation(isAddClickEvent) {
  var map = new BMap.Map('allmap');
  map.centerAndZoom(gis_map_city, gis_map_grade);
  map.enableScrollWheelZoom(true);

  if (longitude != '' && latitude != '') showInfo(map, '', longitude, '', latitude, false);
 }

/**
 * 地图定位
 * 
 * @param isAddClickEvent
 *          是否添加单击事件
 * @returns
 */
function theLocation_point(isAddClickEvent) {
  var map = new BMap.Map('allmap');
  map.centerAndZoom(gis_map_city, gis_map_grade);
  map.enableScrollWheelZoom(true);

  if (longitude != '' && latitude != '') showInfo(map, '', longitude, '', latitude, false);

  if(isAddClickEvent)
    map.addEventListener("click", function(e) {
      showInfo(this, 'longitude', e.point.lng, 'latitude', e.point.lat, true);
    });
}

/**
 * 坐标赋值
 * 
 * @param map_s
 *          百度地图对象
 * @param lngDomId
 *          经度定位的Dom对象id
 * @param lng
 *          经度值
 * @param latDomId
 *          纬度定位的Dom对象id
 * @param lat
 *          纬度值
 * @param isAssignment
 *          是否给Dom对象赋值
 * @returns
 */
function showInfo(map_s, lngDomId, lng, latDomId, lat, isAssignment) {
  map_s.clearOverlays();
  var new_point = new BMap.Point(lng, lat);
  var marker = new BMap.Marker(new_point); // 创建标注
  map_s.addOverlay(marker); // 将标注添加到地图中
  marker.setAnimation(BMAP_ANIMATION_BOUNCE);
  map_s.panTo(new_point);

  if (isAssignment) {
    $('#' + lngDomId).val(lng);
    $('#' + latDomId).val(lat);
    longitude = lng;
    latitude = lat;
  }
}

/**
 * 修改坐标
 * 
 * @param index
 *          datagrid row 的索引
 * @param gridId
 *          datagrid 的Dom ID
 * @param key
 *          哪种业务类型d, m, s
 * @param width
 *          打开窗口的宽度
 * @param height
 *          打开窗口的高度
 * @returns
 */
function editCoordinate(index, gridId, key, width, height) {
  $('#' + gridId).datagrid('selectRow', index);
  var row = $('#' + gridId).datagrid('getSelected');
  createWindowWithCallBack('修改坐标', 'storeGeometry.do?toUpdate&key=' + key + '&gridId=' + gridId + '&id=' + row.id + '&index=' + index, width, height);
}

/**
 * 修改坐标回调函数
 * 
 * @param index
 *          datagrid row 的索引
 * @param gridId
 *          datagrid 的Dom ID
 * @param long
 *          经度
 * @param lat
 *          纬度
 * @returns
 */
function editCoordinateCallBack(index, gridId, long, lat) {
  var row = $('#' + gridId).datagrid('getSelected');
  $('#' + gridId).datagrid('updateRow', {
    index: parseInt(index),
    row: {
      longitude: long,
      latitude: lat
    }
  });
}