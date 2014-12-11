/**
 * @license Highcharts JS v2.3.3 (2012-11-02)
 * 
 * (c) 2009-2011 Gert Vaartjes
 * 
 * License: www.highcharts.com/license
 * 
 * 
 * Modified 1/2013 at Vaadin Ltd - Adapted to fit better for usage in Vaadin
 * Charts. Uses server so that phantomjs instances can be recycled. Also now no need
 * to use temp files.
 */

/* get the arguments from the commandline and map them */

var system = require('system');
args = {};
for ( var i = 0; i < system.args.length; i += 1) {
	if (system.args[i].charAt(0) === '-') {
		args[system.args[i].substr(1, i.length)] = system.args[i + 1];
	}
}

var fs = require('fs');

function render(configstr, themeStr, width, height) {

	var page = require('webpage').create(), HC = {}, pick, scaleAndClipPage, input, constr, callback, callbackStr, optionsStr, output, outputExtension, pdfOutput, svg, svgFile, svgElem, timer;

	HC.imagesLoaded = 'Highcharts.imagesLoaded:7a7dfcb5df73aaa51e67c9f38c5b07cb';
	window.imagesLoaded = false;

	page.onConsoleMessage = function(msg) {
		console.log(msg);
		/*
		 * Ugly hack, but only way to get messages out of the 'page.evaluate()'
		 * sandbox. If any, please contribute with improvements on this!
		 */
		if (msg === HC.imagesLoaded) {
			window.imagesLoaded = true;
		}
	};

	page.onAlert = function(msg) {
		console.log(msg);
	};

	pick = function() {
		var args = arguments, i, arg, length = args.length;
		for (i = 0; i < length; i += 1) {
			arg = args[i];
			if (arg !== undefined && arg !== null && arg !== 'null'
					&& arg != '0') {
				return arg;
			}
		}
	};

	/* scale and clip the page */
	scaleAndClipPage = function(svg, pdf) {
		/*
		 * param: svg: The scg configuration object param: pdf: boolean, if true
		 * set papersize
		 */

		var zoom = 1, pageWidth = pick(args.width, svg.width), clipwidth, clipheight;

		if (parseInt(pageWidth, 10) == pageWidth) {
			zoom = pageWidth / svg.width;
		}

		/*
		 * set this line when scale factor has a higher precedence scale has
		 * precedence : page.zoomFactor = args.scale ? zoom * args.scale : zoom;
		 */

		/*
		 * args.width has a higher precedence over scaling, to not break
		 * backover compatibility
		 */
		page.zoomFactor = args.scale && args.width == undefined ? zoom
				* args.scale : zoom;

		clipwidth = svg.width * page.zoomFactor;
		clipheight = svg.height * page.zoomFactor;

		/* define the clip-rectangle */
		page.clipRect = {
			top : 0,
			left : 0,
			width : clipwidth,
			height : clipheight
		};

		/*
		 * for pdf we need a bit more paperspace in some cases for example
		 * (w:600,h:400), I don't know why.
		 */
		if (pdf) {
			page.paperSize = {
				width : clipwidth,
				height : clipheight + 2
			};
		}
	};

	callback = args.callback;

	// load necessary libraries
	page.injectJs(args["jsstuff"]);

	// load callback from file
	if (callback !== undefined) {
		callbackStr = fs.read(callback);
	}
	
	theme = args.theme;
	
	// load callback from file
	if (theme !== undefined) {
		themesStr = fs.read(theme);
	}

	var renderer = function(width, height, str, callbackStr, themeStr) {
		opt = JSON.parse(str);

		var imagesLoadedMsg = 'Highcharts.imagesLoaded:7a7dfcb5df73aaa51e67c9f38c5b07cb', chart, nodes, nodeIter, elem, opacity;

		// dynamic script insertion
		function loadScript(varStr, codeStr) {
			var $script = $('<script>').attr('type', 'text/javascript');
			$script.html('var ' + varStr + ' = ' + codeStr);
			document.getElementsByTagName("head")[0].appendChild($script[0]);
		}

		// are all images loaded in time?
		function logCounter(counter) {
			counter -= 1;
			if (counter < 1) {
				console.log(imagesLoadedMsg);
			}
		}

		function loadImages() {
			// are images loaded?
			var $images = $('svg image'), counter, i, img;

			if ($images.length > 0) {

				counter = $images.length;

				for (i = 0; i < $images.length; i += 1) {
					img = new Image();
					img.onload = logCounter(counter);
					/*
					 * force loading of images by setting the src attr.
					 */
					img.src = $images[i].getAttribute('href');
				}
			} else {
				// no images set property to all images
				// loaded
				console.log(imagesLoadedMsg);
			}
		}

		// formatJsJson is needed to fix javascript
		// formatters in json
		formatJsJson(opt);

		if (callbackStr !== 'undefined') {
			loadScript('callback', callbackStr);
		}
		
		if (themeStr !== 'undefined') {
			loadScript('highcharts_theme', themeStr);
		}

		document.body.style.margin = '0px';
		var cont = document.createElement("div");
		document.body.appendChild(cont);

		// disable animations
		Highcharts.SVGRenderer.prototype.Element.prototype.animate = Highcharts.SVGRenderer.prototype.Element.prototype.attr;

		Highcharts.setOptions({ 
			plotOptions: {
				series: {
					animation: false
				}
			}
		});

		if (!opt.chart) {
			opt.chart = {};
		}

		opt.chart.renderTo = cont;

		console.log(opt);

		// check if width is set. Order of precedence:
		// args.width, options.chart.width and 600px

		opt.chart.width = width;
		opt.chart.height = height;
		
		if (highcharts_theme !== undefined) {
			Highcharts.theme = highcharts_theme;
			Highcharts.setOptions(Highcharts.theme);
		}

		chart = new Highcharts.Chart(opt, callback);

		// ensure images are all loaded
		loadImages();

		return {
			html : cont.firstChild.innerHTML,
			width : chart.chartWidth,
			height : chart.chartHeight
		};

	};
	// load chart in page and return svg height and width
	svg = page.evaluate(renderer, width, height, configstr, callbackStr, themeStr);

	page.close();
	return svg.html;

}

// TODO change this to use asynchronous api when phantomjs supports it
// https://github.com/ariya/phantomjs/issues/10980

function serve() {
	var configstr = system.stdin.readLine();
	if(configstr) {
		var line;
		var width = parseInt(configstr);
		var height = parseInt(system.stdin.readLine());
		if(width < 0) {
			width = 600;
		}
		if(height < 0) {
			height = 400;
		}
		var themeStr = system.stdin.readLine();
		while((line = system.stdin.readLine()) != "___Config:start") {
			themeStr += line;
		}
		configstr = system.stdin.readLine();
		while((line = system.stdin.readLine()) != "___VaadinSVGGenerator:run") {
			configstr += line;
		}
		try {
			var svgresponse = render(configstr, themeStr, width, height);
			console.log(svgresponse);
			setTimeout(serve(), 5);
		} catch (e) {
			console.log("Render failed:\n" + e);
		}
	} else {
		setTimeout(serve(), 5);
	}
}

console.log("OK, ready.");

serve();

