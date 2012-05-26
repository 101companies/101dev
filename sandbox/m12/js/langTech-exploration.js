var LangTechExplorer = function($, kind, domid) {
	var colorSchema = ['#00A8F0', '#C0D800', '#CB4B4B', '#4DA74D', '#9440ED']
	var tree
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
			alert(domid)
			contrib = contribname
			tree = new dhtmlXTreeObject(domid,"30%","30%",0)
			tree.setImagePath("./imgs/")
			tree.enableCheckBoxes(false)
			tree.enableTreeImages(false)
			tree.loadXML("filebase.xml")
			tree.enableHighlighting(true)
			$("#" + domid).css("overflow-y", "auto")
			$(".containerTableStyle").css({width: "100%" , height:"100%"})
			tree.attachEvent("onClick", function(id) {
					tree.selectItem(id, false)
					if (id.indexOf("l:") != 0) {
						FileExplorer.selectFile(id)
						SourceExplorer.showSource(id)
						LanguageExplorer.selectFile(id)
						TechnologyExplorer.selectFile(id)
					}
					return true
				})
				stats = []
				i = 0
				$.each(data, function(x, data) {
					if (x) {
						stats.push({data : [[0,data.ncloc]], label : x})
						tree.insertNewChild(0, kind + ":" + x, "<b class=\"node\" style=\"background-color: " + colorSchema[i] +"\">" + x + "</b>")
						$.each(data.allFiles, function(id, fn) {
							tree.insertNewChild(kind + ":" + x, fn.path, "<div class=\"crossselection\"></div>" + id.split("/").pop())
						})
						i++
					}
				})
				insertStat(stats)
		},
		selectFile : function(filepath) {
			tree.focusItem(filepath)
			tree.selectItem(filepath)
		}
	}
}

var LanguageExplorer = LangTechExplorer(jQuery, "language", "languages")
var TechnologyExplorer = LangTechExplorer(jQuery, "technology", "technologies")