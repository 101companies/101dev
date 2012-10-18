var CategoryExplorer = function($,sel) {
	var kind2Prefix = {
		"concepts" : "",
		"features" : "101feature:",
		"languages" : "Language:",
		"technologies" : "Technology:",
		"terms" : "101term:",
		"phrases" : ""
	}

	var data = {};
	var reverseData = {};
	var filledCats = [];
	var contribname;

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

	var fillCat = function(id) {
		if ($.inArray(id, filledCats) < 0) {
			var cat = id.replace("c:", "");
			$("#" + id.escape() + " .jstree-leaf").remove();
			var i = 0;
			$.each(data[cat].files, function(f, fn) {
				var classes = "jstree-leaf";
				if (i == Object.keys(data[cat].files).length - 1)
					classes = classes + " jstree-last";
				$("#" + sel + "tree " + "#" + id.escape() + " ul").append(
					$("<li>").attr("id", contribname + "/" + f).addClass(classes).append(
						$("<ins>").addClass("jstree-icon").text(" ")
					).append(
						$("<a>").attr("href", "#").append(
								$("<ins>").addClass("jstree-icon").text(" ")
							).append(
								f.split("/").last()
							)
						)
				)
				i++;
				/*$("#" + sel + "tree").jstree("create", $("#" + id.escape()), "inside",  { 
					"data" : f.split("/").last(),
					"attr" : { "id" : contribname + "/" + f}
					}, 
					function(e) {console.log(".")}, 
					true
				);*/
			})
			console.log("done")
			filledCats.push(id);
		}
	}

	var selectFile = function(filepath) {
		var cats = reverseData[filepath.replace(contribname + "/", "")];
		if (cats)
			$.each(cats, function(i, cat) {
				fillCat("c:" + cat)	
			})
		$("#" + sel + "tree").jstree("deselect_all").jstree("select_node","#" + filepath.escape()).jstree("select_node","#" + filepath.escape());;
	}

	var populate = function(contribn, fdata, selection) {
		data = fdata[selection];
		contribname = contribn;
		var treedata = []
		var stats = []
		var i = 0;
		var cats = []
		$.each(data, function(cat, catdata) {
			cats.push(("c:" + cat).escape())
			stats.push({data : [[0,catdata.metrics.ncloc]], label : cat})
			var node = {
				data : cat,
				"attr" : {cat: cat, id : "c:" + cat, class : "cat label " + "color" + i},
				children : [],
				"initially_open" : []
			}
			node.children.push({data: "Loading..."})
			$.each(catdata.files, function(f, fn){
				if (!reverseData[f])
					reverseData[f] = [];
				reverseData[f].push(cat)
			})
			treedata.push(node)
		})
		$("#" + sel + "tree").jstree({
			"json_data" : {data : treedata},
			"plugins" : [ "themes", "json_data", "crrm" , "ui","sort"],
			 "initially_open" : [],
			 "progressive_render" : true
		}).bind("loaded.jstree", function (event, data) {
			console.log(selection + "tree loaded.");
		}).bind("open_node.jstree", function(event, edata){
			fillCat($(edata.rslt.obj[0]).attr("id"));
		})
		$("#" + sel + "tree").bind("click.jstree", function(event,edata){	
			var nodeid = $("#" + sel + "tree").jstree('get_selected').attr("id")				
			if (nodeid && !nodeid.startswith("c:")) {
				var cat = $("#" + sel + "tree").jstree('get_selected').closest(".cat").attr("id").split(":")[1]
				meta = fdata[selection][cat]['files'][nodeid.replace(contribname + "/","")]
				SourceExplorer.showSource(nodeid,meta)
				FileExplorer.selectFile(nodeid)					
				$.each(CExplorers, function(s,e){
					e.selectFile(nodeid)
				})
			} else {
				if (nodeid) {
					var url = "http://www.101companies.org/index.php/" + kind2Prefix[selection] + nodeid.replace("c:", "")
					SourceExplorer.showPage(url)
				}
			}
		}).bind("loaded.jstree",function() {
			$("#" + sel + "tree .jstree-closed").each(function(i,element){
				if (element.id) {
					var cat = element.id.replace("c:","")
					$("." + cat + "leaf").each(function(j,element2){
						$(this).tooltip({
							track : true,
							delay: 0,
							showURL: false,
							bodyHandler: function() {
								return ('This is <b class="first">' + this.id.split("/").last() + '</b> in <b class="second">' + cat + '</b>');
							}
						});
					})
				}
			});
		})
	}

	return {
		populate: populate,
		selectFile : selectFile
	}

	
}

var CExplorer1 = CategoryExplorer(jQuery, "sel1")
var CExplorer2 = CategoryExplorer(jQuery, "sel2")
var CExplorer3 = CategoryExplorer(jQuery, "sel3")

var CExplorers = {"sel1":CExplorer1,"sel2":CExplorer2,"sel3":CExplorer3}