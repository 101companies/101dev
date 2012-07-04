var FileExplorer = function($) {
	var path
	var loadedDirs = []

	var loadDir = function(id, callback) {
		if (id != "/" & loadedDirs.indexOf(id) == -1) {
			$.ajax({url: "../" + path + "/" + id + "index.json",
					dataType: 'json',
					success : function(data) {
						loadedDirs = loadedDirs.concat([id])
						fillDir(id, data.files, data.dirs)
						if (callback)
							callback()
					}
			})
		}
	}

	var fillDir = function(id, files, dirs) {
		$.each(files.sort().reverse(), function(i, fn) {
			 $("#files").jstree("create", $("#" + id.escape()), "inside",  { "data" : fn , "attr" : { "id" :  id + fn , "class" : "filenode" }}, null, true);
		})
		$.each(dirs.sort().reverse(), function(j, fn) {
			if (fn) {
				$("#files").jstree("create", $("#" + id.escape()), "inside",  { "data" : fn,  "attr" : { "id" : id + fn + "/"}, "state" : "closed", "children" : [ { "data" : "Loading directory content..." , "attr" : { "id" : fn + "dummy"}}]}, function(e) {
					}, true
				);
			}
		})
	}

	return {
		getTree : function(){return tree},		

		init : function(pathname, summary) {
			var pwds = []
			if (!pathname.endswith("/"))
				pathname += "/"
			pathname.split("/").reduce(function(agg, x, i) {return pwds[i] = agg + "/" + x})
			pwds.pop()
			pwds[0] = "contributions"
			$.each(pwds,function(i,dir){
					$("#pathselection").append($("<a/>").attr({href : "show.html?" + dir}).html(pathname.split("/")[i])).append("/")
			})
			path = pathname
			splity =pathname.split("/")
			last = splity[splity.length - 2]
			$("#files").jstree({
				"json_data" : { "data" : [
					{ 
						"data" : last, 
						"attr" : { "id" : "/" },
						"children" : []
					},
				]},
				"plugins" : [ "themes", "json_data", "crrm" , "ui"]
			}).bind("loaded.jstree", function(){
				fillDir("/", summary.files, summary.dirs)	
				$("#files").bind("open_node.jstree", function(event, data){
					var node = data.args[0][0]
					if (node && node.id != "/") {
						loadDir(node.id)
					}
				})
			}).bind("click.jstree", function(event,data){
				var nodeid = $(event.target).closest("li").attr("id")
				if (!nodeid.endswith("/") && nodeid != "/") {
					SourceExplorer.showSource(path + nodeid)
					$.each(CExplorers, function(s,e){
						e.selectFile((path + nodeid).replace("//","/"))
					})
				} 
			}).bind("dblclick.jstree", function(event,data){
				var nodeid = $(event.target).closest("li").attr("id")
				if (nodeid.endswith("/")) {
					var newlocation = (path + nodeid).replace("//","/")
					window.location = "show.html?" + newlocation
				}
			
			})

		},

		selectFile : function(filepath) {
			filepath = filepath.replace(path,"")
			var pwds = []
			filepath.split("/").reduce(function(agg, x, i) {return pwds[i] = agg + "/" + x},"")
			pwds.pop()
			var last = pwds.pop()
			//alert(pwds.length)
			if (pwds && last) {
				$.each(pwds, function(i,x) {
						loadDir(x + "/")
				})
				loadDir(last + "/",function() {
					$("#files").jstree("deselect_all").jstree("select_node","#" + ("/" + filepath).escape());
				})
			}
			$("#files").jstree("deselect_all").jstree("select_node","#" + ("/" + filepath).escape());
		}
	}

}(jQuery)