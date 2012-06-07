var FileExplorer = function($) {
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
			 $("#files").jstree("create", $("#" + id.escape()), "inside",  { "data" : i , "attr" : { "id" : fn.path , "class" : "filenode" }}, null, true);
		})
		$.each(dirs, function(j, fn) {
			if (fn.path) {
				$("#files").jstree("create", $("#" + id.escape()), "inside",  { "data" : j,  "attr" : { "id" : fn.path + "/"}, "state" : "closed", "children" : [ { "data" : "Loading directory content..." , "attr" : { "id" : fn.path + "dummy"}}]}, function(e) {
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
						"children" : []
					},
				]},
				"plugins" : [ "themes", "json_data", "crrm" , "ui"]
			}).bind("loaded.jstree", function(){
				fillDir("root", summary.directFiles.directFiles, summary.directSubdirectories)	
				$("#files").bind("open_node.jstree", function(event, data){
					var node = data.args[0][0]
					if (node && node.id != "root") {
						loadDir(node.id)
					}
				})
			}).bind("click.jstree", function(event,data){
				var nodeid = $(event.target).closest("li").attr("id")
				if (!nodeid.endswith("/") && nodeid != "root") {
					SourceExplorer.showSource(nodeid)
					LanguageExplorer.selectFile(nodeid)
					TechnologyExplorer.selectFile(nodeid)
				}
			})	
		},

		selectFile : function(filepath) {
			var pwds = []
			filepath.split("/").reduce(function(agg, x, i) {return pwds[i] = agg + "/" + x})
			pwds.pop()
			var last = pwds.pop()
			if (pwds.length > 3) {
				$.each(pwds.splice(3), function(i,x) {
						loadDir(x + "/")
				})
				loadDir(last + "/",function() {
					$("#files").jstree("deselect_all").jstree("select_node","#" + filepath.escape());
				})
			}
			$("#files").jstree("deselect_all").jstree("select_node","#" + filepath.escape());
		}
	}

}(jQuery)