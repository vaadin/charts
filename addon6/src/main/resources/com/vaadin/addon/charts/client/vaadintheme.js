Highcharts.setOptions({
	"colors" : [ "#4A7093", "#49CFE5", "#FFBC41", "#C24758", "#77AA54",
			"#7071C8", "#C58FBA", "#B5B9C6" ],
	"chart" : {
		"backgroundColor" : "#FFFFFF",
		"plotBackgroundColor" : "rgba(255,255,255,0.00)",
		"plotBackgroundImage" : "",
		"plotShadow" : false,
		"plotBorderWidth" : 0,
		"className" : "vaadin-chart",
		"borderRadius" : 0,
		"style" : {
			"fontFamily" : "Arial",
			"fontSize" : "12px"
		}
	},
	"title" : {
		"style" : {
			"color" : "#44698B",
			"fontWeight" : "bold",
			"fontSize" : "16px"
		}
	},
	"subtitle" : {
		"style" : {
			"color" : "#979797",
			"fontWeight" : "normal"
		}
	},
	"xAxis" : {
		"lineColor" : "#E5E5E5",
		"lineWidth" : 1,
		"tickColor" : "#979797",
		"gridLineColor" : "#979797",
		"alternateGridColor" : "rgba(255,255,255,0.00)",
		"title" : {
			"style" : {
				"color" : "#44698B",
				"fontWeight" : "bold"
			}
		},
		"subtitle" : {
			"style" : {
				"color" : "#979797",
				"fontWeight" : "normal",
				"fontSize" : "10px"
			}
		},
		"labels" : {
			"style" : {
				"color" : "#979797",
				"fontWeight" : "normal"
			}
		}
	},
	"yAxis" : {
		"lineColor" : "#979797",
		"lineWidth" : 0,
		"tickColor" : "#979797",
		"gridLineColor" : "#E5E5E5",
		"alternateGridColor" : "rgba(0,0,0,0.02)",
		"title" : {
			"style" : {
				"color" : "#44698B",
				"fontWeight" : "bold"
			}
		},
		"subtitle" : {
			"style" : {
				"color" : "#979797",
				"fontWeight" : "normal",
				"fontSize" : "10px"
			}
		},
		"labels" : {
			"style" : {
				"color" : "#979797",
				"fontWeight" : "normal"
			}
		}
	},
	"labels" : {
		"style" : {
			"color" : "#979797"
		}
	},
	"legend" : {
		"borderColor" : "#E5E5E5",
		"borderRadius" : 2,
		"backgroundColor" : "rgba(0,0,0,0.02)",
		"itemStyle" : {
			"color" : "#555555"
		},
		"itemHoverStyle" : {
			"color" : "#000000"
		},
		"itemHiddenStyle" : {
			"color" : "#808080"
		}
	},
	"tooltip" : {
		"backgroundColor" : "#FFFFFF",
		"borderWidth" : 1,
		"borderRadius" : 2,
		"style" : {
			"color" : "#44698B"
		}
	},
	"plotOptions" : {
		"series" : {},
		"bar" : {
			"shadow" : true
		},
		"area" : {"shadow" : true},
		"arearange" : { "shadow" : false},
		"areaspline" : { "shadow" : true},
		"areasplinerange" : { "shadow" : false},
		"pie" : {
			"shadow" : true
		},
		"line" : {
			"lineWidth" : 1,
			"shadow" : true
		},
		"column" : {
			"shadow" : true
		},
		"spline" : {
			"shadow" : true
		}
	},
	"credits" : {
		"text" : "Vaadin Charts",
		"href" : "https://vaadin.com/add-ons/charts",
		"style" : {
			"color" : "#808080",
			"fontSize" : "10px"
		}
	}
});

// Workaround for #10999, TODO check if this is generic Vaadin layout issue
if (window.matchMedia && /chrom(e|ium)/.test(navigator.userAgent.toLowerCase())) {
	var mediaQueryList = window.matchMedia('print');
	var inprintmode = false;
	mediaQueryList.addListener(function(mql) {
		if (mql.matches) {
			inprintmode = true;
		} else {
			if (inprintmode) {
				setTimeout(function() {
					// Force layout phase just after HC has returned from print mode
					// Without this Vaadin breaks layouts in Chrome, layout
					// phase is once executed with 0,0 size and then not fixed
					// afterwards
					vaadin.forceLayout();
				}, 1010);
			}
		}
	});
}
