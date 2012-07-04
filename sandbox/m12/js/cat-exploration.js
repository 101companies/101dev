var CategoryExplorer = function($,sel) {
	var kind2Prefix = {
		"concepts" : "",
		"features" : "101feature:",
		"languages" : "Language:",
		"technologies" : "Technology:",
		"terms" : "101term:",
		"phrases" : ""
	}

	var insertStat = function(stats) {
		Flotr.draw(document.getElementById(kind + "stats"), stats, {
				HtmlText : false,
				colors: colorSchema,
				fontSize: 6.5,
				grid : {
					verticalLines : false,
					horizontalLines : false,
					outlineWidth: 0,
					lineColor: "red"
				},
				xaxis : {showLabels : true, margin:false},
				yaxis : {showLabels : true, margin:false},
				pie : {show : true, explode : 5},
				mouse : { 
					track : true,
					lineColor: "#274779",
					trackFormatter: function(obj){return obj.y +' NCLOC'}, 
				},
				legend : {show: false,position : "no"}
			})

	}

	return {
		populate : function(contribname, fdata, selection) {
			data = fdata[selection];
			var treedata = []
			var stats = []
			var i = 0;
			var cats = []
			$.each(data, function(cat, catdata) {
				cats.push(("c:" + cat).escape())
				stats.push({data : [[0,catdata.metrics.ncloc]], label : cat})
				var node = {
					data : cat,
					"attr" : { id : "c:" + cat, class : "label " + "color" + i},
					children : [],
					"initially_open" : []
				}
				$.each(catdata.files, function(f, fn){
					node.children.push({
						data : f.split("/").last(),
						"attr" : { "id" : contribname + "/" +  f }
					})
				})
				treedata.push(node)
			})
			$("#" + sel + "tree").jstree({
				"json_data" : {data : treedata},
				"plugins" : [ "themes", "json_data", "crrm" , "ui"],
				 "initially_open" : []
			}).bind("click.jstree", function(event,data){	
				var nodeid = $("#" + sel + "tree").jstree('get_selected').attr("id")
				if (nodeid && !nodeid.startswith("c:")) {
					FileExplorer.selectFile(nodeid)
					SourceExplorer.showSource(nodeid)
					$.each(CExplorers, function(s,e){
						e.selectFile(nodeid)
					})
				} else {
					if (nodeid) {
						window.location = "http://www.101companies.org/index.php/" + kind2Prefix[selection] + nodeid.replace("c:", "")
					}
				}
			})	
			$("#" + sel + "tree").jstree("close_all", -1);
			//insertStat(stats)
		},
		selectFile : function(filepath) {
			$("#" + sel + "tree").jstree("deselect_all").jstree("select_node","#" + filepath.escape());
		}
	}
}

var CExplorer1 = CategoryExplorer(jQuery, "sel1")
var CExplorer2 = CategoryExplorer(jQuery, "sel2")
var CExplorer3 = CategoryExplorer(jQuery, "sel3")

var CExplorers = {"sel1":CExplorer1,"sel2":CExplorer2,"sel3":CExplorer3}