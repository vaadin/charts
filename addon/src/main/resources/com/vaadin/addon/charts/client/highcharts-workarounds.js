// workaround for plotband labels issues with modern browsers
// https://github.com/highcharts/highcharts/issues/8997 (Fixed in Highcharts 6.0.2)

// Workaround for #13559 for Highcharts versions prior to 8.1.1. Note that this is
// only necessary if you accept unfiltered input from end users into Highcharts.chart
// configuration.
// copied from: https://github.com/highcharts/highcharts/issues/13559

(function(Highcharts) {

  const {
  	attr,
      css,
      doc,
      isString,
      objectEach,
  	pick,
      svg,
      SVGRenderer
  } = H;

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

    SVGRenderer.prototype.buildText = function(wrapper) {
        var textNode = wrapper.element,
            renderer = this,
            forExport = renderer.forExport,
            textStr = pick(wrapper.textStr, '').toString(),
            hasMarkup = textStr.indexOf('<') !== -1,
            lines, childNodes = textNode.childNodes,
            truncated, parentX = attr(textNode, 'x'),
            textStyles = wrapper.styles,
            width = wrapper.textWidth,
            textLineHeight = textStyles && textStyles.lineHeight,
            textOutline = textStyles && textStyles.textOutline,
            ellipsis = textStyles && textStyles.textOverflow === 'ellipsis',
            noWrap = textStyles && textStyles.whiteSpace === 'nowrap',
            fontSize = textStyles && textStyles.fontSize,
            textCache, isSubsequentLine, i = childNodes.length,
            tempParent = width && !wrapper.added && this.box,
            getLineHeight = function(tspan) {
                var fontSizeStyle;
                if (!renderer.styledMode) {
                    fontSizeStyle =
                        /(px|em)$/.test(tspan && tspan.style.fontSize) ?
                        tspan.style.fontSize :
                        (fontSize || renderer.style.fontSize || 12);
                }
                return textLineHeight ?
                    pInt(textLineHeight) :
                    renderer.fontMetrics(fontSizeStyle,
                        // Get the computed size from parent if not explicit
                        (tspan.getAttribute('style') ? tspan : textNode)).h;
            },
            unescapeEntities = function(inputStr, except) {
                objectEach(renderer.escapes, function(value, key) {
                    if (!except || except.indexOf(value) === -1) {
                        inputStr = inputStr.toString().replace(new RegExp(value, 'g'), key);
                    }
                });
                return inputStr;
            },
            parseAttribute = function(s, attr) {
                var start, delimiter;
                start = s.indexOf('<');
                s = s.substring(start, s.indexOf('>') - start);
                start = s.indexOf(attr + '=');
                if (start !== -1) {
                    start = start + attr.length + 1;
                    delimiter = s.charAt(start);
                    if (delimiter === '"' || delimiter === "'") { // eslint-disable-line quotes
                        s = s.substring(start + 1);
                        return s.substring(0, s.indexOf(delimiter));
                    }
                }
            };
        var regexMatchBreaks = /<br.*?>/g;
        // The buildText code is quite heavy, so if we're not changing something
        // that affects the text, skip it (#6113).
        textCache = [
            textStr,
            ellipsis,
            noWrap,
            textLineHeight,
            textOutline,
            fontSize,
            width
        ].join(',');
        if (textCache === wrapper.textCache) {
            return;
        }
        wrapper.textCache = textCache;
        // Remove old text
        while (i--) {
            textNode.removeChild(childNodes[i]);
        }
        // Skip tspans, add text directly to text node. The forceTSpan is a hook
        // used in text outline hack.
        if (!hasMarkup &&
            !textOutline &&
            !ellipsis &&
            !width &&
            (textStr.indexOf(' ') === -1 ||
                (noWrap && !regexMatchBreaks.test(textStr)))) {
            textNode.appendChild(doc.createTextNode(unescapeEntities(textStr)));
            // Complex strings, add more logic
        } else {
            if (tempParent) {
                // attach it to the DOM to read offset width
                tempParent.appendChild(textNode);
            }
            if (hasMarkup) {
                lines = renderer.styledMode ? (textStr
                    .replace(/<(b|strong)>/g, '<span class="highcharts-strong">')
                    .replace(/<(i|em)>/g, '<span class="highcharts-emphasized">')) : (textStr
                    .replace(/<(b|strong)>/g, '<span style="font-weight:bold">')
                    .replace(/<(i|em)>/g, '<span style="font-style:italic">'));
                lines = lines
                    .replace(/<a/g, '<span')
                    .replace(/<\/(b|strong|i|em|a)>/g, '</span>')
                    .split(regexMatchBreaks);
            } else {
                lines = [textStr];
            }
            // Trim empty lines (#5261)
            lines = lines.filter(function(line) {
                return line !== '';
            });
            // build the lines
            lines.forEach(function(line, lineNo) {
                var spans, spanNo = 0,
                    lineLength = 0;
                line = line
                    // Trim to prevent useless/costly process on the spaces
                    // (#5258)
                    .replace(/^\s+|\s+$/g, '')
                    .replace(/<span/g, '|||<span')
                    .replace(/<\/span>/g, '</span>|||');
                spans = line.split('|||');
                spans.forEach(function buildTextSpans(span) {
                    if (span !== '' || spans.length === 1) {
                        var attributes = {},
                            tspan = doc.createElementNS(renderer.SVG_NS, 'tspan'),
                            a, classAttribute, styleAttribute, // #390
                            hrefAttribute;
                        classAttribute = parseAttribute(span, 'class');
                        if (classAttribute) {
                            attr(tspan, 'class', classAttribute);
                        }
                        styleAttribute = parseAttribute(span, 'style');
                        if (styleAttribute) {
                            styleAttribute = styleAttribute.replace(/(;| |^)color([ :])/, '$1fill$2');
                            attr(tspan, 'style', styleAttribute);
                        }
                        // For anchors, wrap the tspan in an <a> tag and apply
                        // the href attribute as is (#13559). Not for export
                        // (#1529)
                        hrefAttribute = parseAttribute(span, 'href');
                        if (hrefAttribute && !forExport) {
                            if (
                                // Stop JavaScript links, vulnerable to XSS
                                hrefAttribute.split(':')[0].toLowerCase()
                                .indexOf('javascript') === -1) {
                                a = doc.createElementNS(renderer.SVG_NS, 'a');
                                attr(a, 'href', hrefAttribute);
                                attr(tspan, 'class', 'highcharts-anchor');
                                a.appendChild(tspan);
                                if (!renderer.styledMode) {
                                    css(tspan, {
                                        cursor: 'pointer'
                                    });
                                }
                            }
                        }
                        // Strip away unsupported HTML tags (#7126)
                        span = unescapeEntities(span.replace(/<[a-zA-Z\/](.|\n)*?>/g, '') || ' ');
                        // Nested tags aren't supported, and cause crash in
                        // Safari (#1596)
                        if (span !== ' ') {
                            // add the text node
                            tspan.appendChild(doc.createTextNode(span));
                            // First span in a line, align it to the left
                            if (!spanNo) {
                                if (lineNo && parentX !== null) {
                                    attributes.x = parentX;
                                }
                            } else {
                                attributes.dx = 0; // #16
                            }
                            // add attributes
                            attr(tspan, attributes);
                            // Append it
                            textNode.appendChild(a || tspan);
                            // first span on subsequent line, add the line
                            // height
                            if (!spanNo && isSubsequentLine) {
                                // allow getting the right offset height in
                                // exporting in IE
                                if (!svg && forExport) {
                                    css(tspan, {
                                        display: 'block'
                                    });
                                }
                                // Set the line height based on the font size of
                                // either the text element or the tspan element
                                attr(tspan, 'dy', getLineHeight(tspan));
                            }
                            // Check width and apply soft breaks or ellipsis
                            if (width) {
                                var words = span.replace(/([^\^])-/g, '$1- ').split(' '), // #1273
                                    hasWhiteSpace = !noWrap && (spans.length > 1 ||
                                        lineNo ||
                                        words.length > 1),
                                    wrapLineNo = 0,
                                    dy = getLineHeight(tspan);
                                if (ellipsis) {
                                    truncated = renderer.truncate(wrapper, tspan, span, void 0, 0,
                                        // Target width
                                        Math.max(0,
                                            // Substract the font face to make
                                            // room for the ellipsis itself
                                            width - parseInt(fontSize || 12, 10)),
                                        // Build the text to test for
                                        function(text, currentIndex) {
                                            return text.substring(0, currentIndex) + '\u2026';
                                        });
                                } else if (hasWhiteSpace) {
                                    while (words.length) {
                                        // For subsequent lines, create tspans
                                        // with the same style attributes as the
                                        // parent text node.
                                        if (words.length &&
                                            !noWrap &&
                                            wrapLineNo > 0) {
                                            tspan = doc.createElementNS(renderer.SVG_NS, 'tspan');
                                            attr(tspan, {
                                                dy: dy,
                                                x: parentX
                                            });
                                            if (styleAttribute) { // #390
                                                attr(tspan, 'style', styleAttribute);
                                            }
                                            // Start by appending the full
                                            // remaining text
                                            tspan.appendChild(doc.createTextNode(words.join(' ')
                                                .replace(/- /g, '-')));
                                            textNode.appendChild(tspan);
                                        }
                                        // For each line, truncate the remaining
                                        // words into the line length.
                                        renderer.truncate(wrapper, tspan, null, words, wrapLineNo === 0 ? lineLength : 0, width,
                                            // Build the text to test for
                                            function(text, currentIndex) {
                                                return words
                                                    .slice(0, currentIndex)
                                                    .join(' ')
                                                    .replace(/- /g, '-');
                                            });
                                        lineLength = wrapper.actualWidth;
                                        wrapLineNo++;
                                    }
                                }
                            }
                            spanNo++;
                        }
                    }
                });
                // To avoid beginning lines that doesn't add to the textNode
                // (#6144)
                isSubsequentLine = (isSubsequentLine ||
                    textNode.childNodes.length);
            });
            if (ellipsis && truncated) {
                wrapper.attr('title', unescapeEntities(wrapper.textStr || '', ['&lt;', '&gt;']) // #7179
                );
            }
            if (tempParent) {
                tempParent.removeChild(textNode);
            }
            // Apply the text outline
            if (isString(textOutline) && wrapper.applyTextOutline) {
                wrapper.applyTextOutline(textOutline);
            }
        }
    };
})(Highcharts);
