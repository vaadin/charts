function formatJsJson(obj) {
	var re_fn_prop = /^_fn_/;
	for ( var prop in obj) {
		if (obj.hasOwnProperty(prop)) {
			var childobj = obj[prop];
			if (prop.indexOf("_fn_") == 0) {
				try {
					var script = childobj;
					var actualPropName = prop.replace(re_fn_prop, '');
					if (script.indexOf("function()") != 0) {
						if (script.indexOf("return") != 0) {
							script = "return " + script;
						}
						script = "function() {" + script + "}";
					}
					obj[actualPropName] = eval('(' + script + ')');
					obj[prop] = null;
				} catch (e) {
					console.log("Failed to evaluate formatter");
					console.log(e);
				}
			} else {
				if (typeof childobj == 'object' && childobj != null) {
					formatJsJson(childobj);
				}
			}
		}
	}
}
