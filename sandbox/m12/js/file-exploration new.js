var FileExplorer = function($) {
	var tree
	var contrib
	var loadedDirs = []

	var loadDir = function(id, callback) {
		if (id != "/" & loadedDirs.indexOf(id) == -1) {
			$.ajax({url: "../101repo" + id + "/index.summary.json",
					dataType: 'json',
					success : function(data) {
						loadedDirs = loadedDirs.concat([id])
						fillDir(id, data.directFiles.directFiles, data.directSubdirectories)
						if (callback)
							callback()
					}
			})
		}
	}

	var fillDir = function(id, files, dirs) {
		$.each(files, function(i, fn) {
			 $("#files").jstree("create", $("#" + id), "inside",  { "data" : i , "attr" : { "id" : fn.path }}, null, true);
		})
		$.each(dirs, function(j, fn) {
			if (fn.path) {
				$("#files").jstree("create", $("#" + id), "inside",  { "data" : j , "attr" : { "id" : j }}, function() {
						$("#files").jstree("create", $("#" + j), "inside",  { "data" : "Loading directory content..." , "attr" : { "id" : fn.path }}, null, true);
					}, true
				);
			}
		})
	}

	return {
		getTree : function(){return tree},		

		init : function(contribname, summary) {
			contrib = contribname
			$("#files").jstree({
				"json_data" : { "data" : [
                    { 
                        "data" : contribname, 
                        "attr" : { "id" : "root" },
                        "children" : [ ]

                    },
                ]},
				"plugins" : [ "themes", "json_data", "crrm" , "ui"]
			}).bind("loaded.jstree", function(){
				fillDir("root", summary.directFiles.directFiles, summary.directSubdirectories)	
			})

			
			
		},

		selectFile : function(filepath) {
			pwds = []
			filepath.split("/").reduce(function(agg, x, i) {return pwds[i] = agg + "/" + x})
			pwds.pop()
			last = pwds.pop()
			$.each(pwds.splice(3), function(i,x) {
					loadDir(x)
			})
			loadDir(last,function() {tree.focusItem(filepath); tree.selectItem(filepath)})
			//tree.focusItem(filepath)
			//tree.selectItem(filepath)
			
		}
	}

}(jQuery)