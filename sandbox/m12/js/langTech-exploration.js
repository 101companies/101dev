var LangTechExplorer = function($, kind) {
	var colorSchema = ['#00A8F0', '#C0D800', '#CB4B4B', '#4DA74D', '#9440ED']
	var contrib
	var graph
	var kind

	var insertStat = function(stats) {
		graph = Flotr.draw(document.getElementById(kind + "stats"), stats, {
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
		init : function(contribname, data) {
			contrib = contribname
			var treedata = []
			var stats = []
			var i = 0;
			var langTechs = []
			$.each(data, function(langTech, langTechdata) {
				langTechs.push(("c:" + langTech).escape())
				i++;
				stats.push({data : [[0,langTechdata.ncloc]], label : langTech})
				var node = {
					data : langTech,
					"attr" : { id : "c:" + langTech, class : "label " + "color" + i},
					children : []
				}
				$.each(langTechdata.allFiles, function(f, fn){
					node.children.push({
						data : f.split("/").last(),
						"attr" : { "id" : fn.path }
					})
				})
				treedata.push(node)
			})
			$("#" + kind + "tree").jstree({
				"json_data" : {data : treedata},
				"plugins" : [ "themes", "json_data", "crrm" , "ui"]
			}).bind("loaded.jstree", function(){
				$.each(langTechs, function(i, lt){
					$("#" + kind + "tree").jstree("open_node", $("#" + lt))
				})
			}).bind("click.jstree", function(event,data){
				var nodeid = $(event.target).closest("li").attr("id")
				if (!nodeid.startswith("c:")) {
					TagExplorer.selectFile(nodeid)
					FileExplorer.selectFile(nodeid)
					SourceExplorer.showSource(nodeid)
					LanguageExplorer.selectFile(nodeid)
					TechnologyExplorer.selectFile(nodeid)
				} else {
					window.location = "http://www.101companies.org/index.php/" + kind.ucfirst() + ":" + nodeid.replace("c:", "")
				}
			})	
			insertStat(stats)
		},
		selectFile : function(filepath) {
			$("#" + kind + "tree").jstree("deselect_all").jstree("select_node","#" + filepath.escape());
		}
	}
}

var LanguageExplorer = LangTechExplorer(jQuery, "language")
var TechnologyExplorer = LangTechExplorer(jQuery, "technology")
