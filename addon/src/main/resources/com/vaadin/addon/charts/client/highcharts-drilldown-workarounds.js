
(function(Highcharts) {

  // Workaround for https://github.com/vaadin/charts/issues/460 
  // from https://github.com/highcharts/highcharts/issues/5565#issuecomment-237712885
  Highcharts.wrap(Highcharts.Chart.prototype, 'drillUp', function (p) {
    Highcharts.each(this.xAxis, function (axis) {
        axis.names = [];
    });
    p.call(this);
  });

  // https://github.com/highcharts/highcharts/issues/5750
  // http://jsfiddle.net/4q8jyrvm/
  Highcharts.Axis.prototype.getDDPoints = function(x) {
    var ret = [];
    Highcharts.each(this.series, function(series) {
      var i,
          xData = series.xData,
          points = series.points;

      for (i = 0; i < xData.length; i++) {
        if (xData[i] === x && series.options.data[i] && series.options.data[i].drilldown) {
          ret.push(points ? points[i] : true);
          break;
        }
      }
    });
    return ret;
  };
}(Highcharts));