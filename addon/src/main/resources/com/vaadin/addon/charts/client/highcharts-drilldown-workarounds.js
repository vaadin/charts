  // Workaround for https://github.com/vaadin/charts/issues/460 
  // from https://github.com/highcharts/highcharts/issues/5565#issuecomment-237712885

(function(Highcharts) {

  Highcharts.wrap(Highcharts.Chart.prototype, 'drillUp', function (p) {
    Highcharts.each(this.xAxis, function (axis) {
        axis.names = [];
    });
    p.call(this);
  });
}(Highcharts));