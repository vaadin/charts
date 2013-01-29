/**
 * @license Highcharts JS v2.3.3 (2012-11-02)
 *
 * (c) 2009-2011 Gert Vaartjes
 *
 * License: www.highcharts.com/license
 * 
 * 
 * Modified 1/28/2013
 * - SVG gets printed in console with a 'SVG[' prefix and ']' suffix. Writing to file is not necessary.
 */

/*global window, require, phantom, console, $, document, Image, Highcharts, clearTimeout, options */

(function () {
	"use strict";

	var config = {
		/* define locations of mandatory javascript files */
		HIGHCHARTS: 'highcharts.js',
		HIGHCHARTS_MORE: 'highcharts-more',
		JQUERY: 'jquery.min.js',
		VAADINTHEME: 'vaadintheme.js'
	},
	/* Internal */
		page = require('webpage').create(),
		fs = require('fs'),
		system = require('system'),
		args,
		HC = {},
		pick,
		mapArguments,
		scaleAndClipPage,
		input,
		constr,
		callback,
		width,
		callbackStr,
		optionsStr,
		output,
		outputExtension,
		pdfOutput,
		svg,
		svgFile,
		svgElem,
		timer;

	HC.imagesLoaded = 'Highcharts.imagesLoaded:7a7dfcb5df73aaa51e67c9f38c5b07cb';
	window.imagesLoaded = false;

	page.onConsoleMessage = function (msg) {
		console.log(msg);
		/*
		 * Ugly hack, but only way to get messages out of the 'page.evaluate()'
		 * sandbox. If any, please contribute with improvements on this!
		 */
		if (msg === HC.imagesLoaded) {
			window.imagesLoaded = true;
		}
	};

	page.onAlert = function (msg) {
		console.log(msg);
	};

	pick = function () {
		var args = arguments, i, arg, length = args.length;
		for (i = 0; i < length; i += 1) {
			arg = args[i];
			if (arg !== undefined && arg !== null && arg !== 'null' && arg != '0') {
				return arg;
			}
		}
	};

	mapArguments = function () {
		var map = {},
			i;
		for (i = 0; i < system.args.length; i += 1) {
			if (system.args[i].charAt(0) === '-') {
				map[system.args[i].substr(1, i.length)] = system.args[i + 1];
			}
		}
		return map;
	};

	/* scale and clip the page */
	scaleAndClipPage = function (svg, pdf) {
		/*	param: svg: The scg configuration object
				param: pdf: boolean, if true set papersize
		*/

		var zoom = 1,
			pageWidth = pick(args.width, svg.width),
			clipwidth, clipheight;

		if (parseInt(pageWidth, 10) == pageWidth) {
			zoom = pageWidth / svg.width;
		}

		/* set this line when scale factor has a higher precedence
		scale has precedence : page.zoomFactor = args.scale  ? zoom * args.scale : zoom;*/

		/* args.width has a higher precedence over scaling, to not break backover compatibility */
		page.zoomFactor = args.scale && args.width == undefined ? zoom * args.scale : zoom;

		clipwidth = svg.width * page.zoomFactor;
		clipheight = svg.height * page.zoomFactor;

		/* define the clip-rectangle */
		page.clipRect = {
			top: 0,
			left: 0,
			width: clipwidth,
			height: clipheight
		};

		/* for pdf we need a bit more paperspace in some cases for example (w:600,h:400), I don't know why.*/
		if (pdf) {
			page.paperSize = { width: clipwidth, height: clipheight + 2};
		}
	};

	/* get the arguments from the commandline and map them */
	args = mapArguments();

	if (args.length < 1) {
		console.log('Usage: highcharts-convert.js -infile URL -outfile filename -scale 2.5 -width 300 -constr Chart -callback callback.js');
		console.log('Commandline parameter width is used for scaling, not for creating the chart');
		phantom.exit(1);
	} else {
		var inputjson = args.jsonopt;
		
		constr = 'Chart';
		callback = args.callback;
		width = args.width;

			// load necessary libraries
			page.injectJs(config.JQUERY);
			page.injectJs(config.HIGHCHARTS);
			page.injectJs(config.HIGHCHARTS_MORE);
			page.injectJs(config.VAADINTHEME);

			// load options
			if (inputjson !== undefined) {
				optionsStr = inputjson;
			} else {
				console.log('No options file specified!');
				phantom.exit();
			}

			// load callback from file
			if (callback !== undefined) {
				callbackStr = fs.read(callback);
			}

			// load chart in page and return svg height and width
			svg = page.evaluate(function (width, constr, optionsStr, callbackStr) {

				var imagesLoadedMsg = 'Highcharts.imagesLoaded:7a7dfcb5df73aaa51e67c9f38c5b07cb', $container, chart,
					nodes, nodeIter, elem, opacity;

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
							/* force loading of images by setting the src attr.*/
							img.src = $images[i].getAttribute('href');
						}
					} else {
						// no images set property to all images
						// loaded
						console.log(imagesLoadedMsg);
					}
				}

				if (optionsStr !== 'undefined') {
					loadScript('options', optionsStr);
				}

				if (callbackStr !== 'undefined') {
					loadScript('callback', callbackStr);
				}

				$(document.body).css('margin', '0px');
				$container = $('<div>').appendTo(document.body);
				$container.attr('id', 'container');

				// disable animations
				Highcharts.SVGRenderer.prototype.Element.prototype.animate = Highcharts.SVGRenderer.prototype.Element.prototype.attr;

				if (!options.chart) {
					options.chart = {};
				}

				options.chart.renderTo = $container[0];

				// check if width is set. Order of precedence:
				// args.width, options.chart.width and 600px

				// OLD. options.chart.width = width || options.chart.width || 600;
				// Notice we don't use commandline parameter width here. Commandline parameter width is used for scaling.
				options.chart.width = (options.exporting && options.exporting.sourceWidth) || options.chart.width || 600;
				options.chart.height = (options.exporting && options.exporting.sourceHeight) || options.chart.height || 400;

				chart = new Highcharts[constr](options, callback);

				// ensure images are all loaded
				loadImages();

				return {
					html: $container[0].firstChild.innerHTML,
					width: chart.chartWidth,
					height: chart.chartHeight
				};

			}, width, constr, optionsStr, callbackStr);
			
			// Write svg in console.
			console.log("SVG:[" + svg.html + "]");
			
			try {
				phantom.exit();
			} catch (e) {
				console.log(e);
			}
	}
}());