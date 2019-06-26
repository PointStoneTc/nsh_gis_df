var theme = 'dark';
$(function() {
  Common.eventInit();
});

var Common = {
  categories: [],
  newCounts: [],
  users: [],
  userImgs: [],
  categoriesArr: [],
  eventInit: function() {
    this.getHomeData();
  },
  // 获取首页数据
  getHomeData: function(callback) {
    var url = 'bigscreen.do?getData';
    $.ajax({
      type: 'GET',
      url: url,
      dataType: 'json',
      async: true,
      error: function() {
      },
      success: function(data) {
        if (data) {
          createDmc(data.obj.dm_count);
          createBusinessBig(data.obj.pm_count_business);
          createBusinessSmall(data.obj.pm_count_name);
          createMap(data.obj.dot_list, data.obj.dot_type_list);
          if (callback) {
            callback(data);
          }
        }
      }
    });
  }
}

/**
 * 创建支行所辖商户数量(柱状图)
 * 
 * @param data
 * @returns
 */
function createDmc(data) {
  var myChart = echarts.init(document.getElementById('dmc'), theme);
  var yAxis_data = new Array();
  var series = new Array();

  $.each(data, function(i, n) {
    yAxis_data.push(n.dotName);
    series.push(n.merchantCt);
  });

  option = {
    color: ['#FF4940'],
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow'
      }
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel: true
    },
    xAxis: {
      type: 'value',
      boundaryGap: [0, 0.01]
    },
    yAxis: {
      type: 'category',
      data: yAxis_data
    },
    series: [{
      name: '商户数量',
      barWidth: '75%',
      type: 'bar',
      data: series,
      label: {
        normal: {
          show: true,
          position: 'inside'
        }
      }
    }],
    grid: {
      y: 10,
      x: '25%'
    }
  };

  myChart.setOption(option);
}

/**
 * 按产品大类分析(环图)
 * 
 * @param data
 * @returns
 */
function createBusinessBig(data) {
  var myChart = echarts.init(document.getElementById('business_big'), theme);
  var data_vn = new Array();
  var data_legend = new Array();

  $.each(data, function(i, n) {
    data_vn.push(new vnObj(n.merchantCt, n.business));
    data_legend.push(n.business);
  });

  option = {
    title: {
      text: '[ 产品大类商户占比 ]',
      textAlign: 'center',
      left: '50%',
      textStyle: {
        fontSize: 14,
        fontWeight: 'bold'
      }
    },
    tooltip: {
      trigger: 'item',
      formatter: "{b}: {c}个商户 ({d}%)"
    },
    legend: {
      orient: 'vertical',
      x: 'left',
      data: data_legend
    },
    series: [{
      type: 'pie',
      radius: ['50%', '70%'],
      avoidLabelOverlap: false,
      label: {
        normal: {
          show: false,
          position: 'center'
        },
        emphasis: {
          show: true,
          textStyle: {
            fontSize: '16',
            fontWeight: 'bold'
          }
        }
      },
      labelLine: {
        normal: {
          show: false
        }
      },
      data: data_vn
    }]
  };

  myChart.setOption(option);
}

/**
 * 按产品小类分析(饼图)
 * 
 * @param data
 * @returns
 */
function createBusinessSmall(data) {
  $.each(data, function(i, n) {
    var id = "small_" + i;
    $('#business_small').append('<div id="' + id + '" class="ct"></div>');
    createChart(n, '' + id);
  });

  function createChart(data, id) {
    var myChart = echarts.init(document.getElementById(id), theme);
    var data_vn = new Array();
    var data_legend = new Array();

    $.each(data, function(i, n) {
      data_vn.push(new vnObj(n.merchantCt, n.productName));
      data_legend.push(n.business);
    });

    option = {
      tooltip: {
        trigger: 'item',
        formatter: "{b} : {c}个商户 ({d}%)"
      },
      legend: {
        type: 'scroll',
        orient: 'vertical',
        right: 5,
        top: 5,
        bottom: 5,
        data: data_legend
      },
      series: [{
        type: 'pie',
        center: ['50%', '50%'],
        data: data_vn,
        itemStyle: {
          emphasis: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        },
      }]
    };

    myChart.setOption(option);
  }
}

/**
 * 构建地图
 * 
 * @param data
 * @param dotTypes
 * @returns
 */
function createMap(data, dotTypes) {
  var data_vn = new Array();
  var visualMap_categories = new Array();
  var visualMap_color = ['#A71D36', '#77AAAD', '#9055A2', "#FEEE7D", '#30A9DE'];

  $.each(data, function(i, n) {
    var temp = [n.longitude, n.latitude];
    data_vn.push({
      name: n.name,
      value: temp.concat(n.type)
    });
  });

  $.each(dotTypes, function(i, n) {
    visualMap_categories.push(n.value);
  });

  var myChart = echarts.init(document.getElementById('mapt'), theme);
  $.get('../static/js/gisplan/anlysis/DF.json', function(geoJson) {
    echarts.registerMap('maps', geoJson);

    option = {
      tooltip: {
        trigger: 'item',
        formatter: function(params) {
          return params.name + ' : ' + params.value[2];
        }
      },
      geo: {
        map: 'maps',
        label: {
          emphasis: {
            show: false
          }
        },
        itemStyle: {
          normal: {
            areaColor: '#AACD6E',
            borderColor: '#111'
          },
          emphasis: {
            areaColor: '#BFB381'
          }
        }
      },
      visualMap: { // 左边选择器
        categories: visualMap_categories,
        top: 'top',
        seriesIndex: [0], // 选择器对应的数据，不能有0
        textStyle: {
          color: '#fff'
        },
        dimension: 2,
        inRange: {
          symbolSize: 12,
          color: visualMap_color
        }
      },
      series: [{
        name: 'dot_list',
        type: 'scatter',
        coordinateSystem: 'geo',
        symbol: 'pin', // 气泡
        data: data_vn,
        symbolSize: 12,
        label: {
          normal: {
            show: false
          },
          emphasis: {
            show: false
          }
        },
        itemStyle: {
          emphasis: {
            borderColor: '#fff',
            borderWidth: 1
          }
        }
      }]
    }

    myChart.setOption(option)
  });
}

/**
 * 名称值的对象
 * 
 * @param value
 * @param name
 * @returns
 */
function vnObj(value, name) {
  this.value = value;
  this.name = name;
}