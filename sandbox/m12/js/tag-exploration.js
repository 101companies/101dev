var TagExplorer = function($) {
	var contrib
	var fdata

	return {
		init : function(contribname) {
			contrib = contribname
			$.ajax({url: "../101repo/contributions/" + contribname + "/index.fragments.json",
	        	dataType: 'json',
	        	success:  function(data) {
	        		fdata = data
	        		var treedata = []
	        		var tags = []
	        		$.each(data.tags, function(tag,files) {
	        			tags.push(("t:"+ tag).escape())
	        			var node = {
							data : tag,
								"attr" : { id : "t:" + tag,  class : "label"},
								children : []
						}
						$.each(files, function(i, filename){
							fragmentid = data.files[filename].tags[tag][0]
							fragment = data.files[filename].fragments[fragmentid]
							path = filename.split("/");
							node.children.push({
								data : path[path.length - 1].replace(".fragments.json", ""),
								attr : { id : tag + ":" + filename},
								children : [ 
									{data : "Specification: " + JSON.stringify(fragment.specification), attr : {id : "spec:" + tag + ":" + filename}}, 
									{data : "Locator: " + fragment.locator.split("/")[0], attr : {id : "locator:" + fragment.locator.split("/")[0]}}
								]
							})
						})
						treedata.push(node)
	        		})
	        		$("#tagtree").jstree({
						"json_data" : {data : treedata},
						"plugins" : [ "themes", "json_data", "crrm" , "ui", "sort"],
					}).bind("loaded.jstree", function(){
						$.each(tags, function(i, tag){
							$("#tagtree").jstree("open_node", $("#" + tag))
						})
					}).bind("click.jstree", function(event,data){
						var nodeid = $(event.target).closest("li").attr("id")
						if (!nodeid.startswith("t:") && !nodeid.startswith("spec:") && !nodeid.startswith("locator:")) {
							filepath = "/" + nodeid.split(":")[1].replace(".fragments.json", "")
							FileExplorer.selectFile(filepath)
							LanguageExplorer.selectFile(filepath)
							TechnologyExplorer.selectFile(filepath)
							fid = fdata.files[nodeid.split(":")[1]].tags[nodeid.split(":")[0]];
							ranges = []
							range = fdata.files[nodeid.split(":")[1]].fragments[fid].location
							if (range)
								ranges = [range]
							SourceExplorer.showSourceAndHighlight(filepath, ranges)
						} 
					})	
	         	}
	     	 });
		},
		selectFile : function(filepath) {
			$("#tagtree").jstree("deselect_all")
			var jsonpath = filepath.slice(1) + ".fragments.json"
			if (fdata.files[jsonpath]) {
				$.each(fdata.files[jsonpath].tags, function(i, t){
					tag = fdata.files[jsonpath].fragments[t].tags[0]
					$("#tagtree").jstree("select_node","#" + (tag + ":" + jsonpath).escape())
				})
			}
		}
	}

}(jQuery)