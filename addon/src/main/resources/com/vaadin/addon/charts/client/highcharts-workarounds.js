//workaround for plotband labels issues with modern browsers
//https://github.com/highcharts/highcharts/issues/8997 (Fixed in Highcharts 6.0.2)

(function(Highcharts) {


  Highcharts.wrap(Highcharts.Axis.prototype, 'getPlotLinePath', function(proceed) {
    var path = proceed.apply(this, Array.prototype.slice.call(arguments, 1));
    if (path) {
      path.flat = false;
    }
    return path;
  });

  Highcharts.Axis.prototype.getPlotBandPath = function(from, to) {
    var toPath = this.getPlotLinePath(to, null, null, true), path = this
        .getPlotLinePath(from, null, null, true), result = [], i,
    // #4964 check if chart is inverted or plotband is on yAxis
    horiz = this.horiz, plus = 1, flat, outside = (from < this.min && to < this.min)
        || (from > this.max && to > this.max);

    if (path && toPath) {

      // Flat paths don't need labels (#3836)
      if (outside) {
        flat = path.toString() === toPath.toString();
        plus = 0;
      }

      // Go over each subpath - for panes in Highstock
      for (i = 0; i < path.length; i += 6) {

        // Add 1 pixel when coordinates are the same
        if (horiz && toPath[i + 1] === path[i + 1]) {
          toPath[i + 1] += plus;
          toPath[i + 4] += plus;
        } else if (!horiz && toPath[i + 2] === path[i + 2]) {
          toPath[i + 2] += plus;
          toPath[i + 5] += plus;
        }

        result.push('M', path[i + 1], path[i + 2], 'L', path[i + 4],
            path[i + 5], toPath[i + 4], toPath[i + 5],
            toPath[i + 1], toPath[i + 2], 'z');
        result.flat = flat;
      }

    } else { // outside the axis area
      path = null;
    }

    return result;
  };
  
  Highcharts.Axis.prototype.updateNames = function() {
    var axis = this;
    if (this.names.length > 0) {
      this.names.length = 0;
      this.minRange = undefined;
      Highcharts.each(this.series || [], function(series) {

        // When adding a series, points are not yet generated
        if (!series.points || series.isDirtyData) {
          series.processData();
          series.generatePoints();
        }
        Highcharts.each(series.points, function(point, i) {
          var x;
          if (point.options) {
            x = axis.nameToX(point);
            if (x !== point.x) {
              point.x = x;
              series.xData[i] = x;
            }
          }
        });
      });
    }
  };

}(Highcharts));