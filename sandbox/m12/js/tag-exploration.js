var TagExplorer = function($) {
	var tree
	var contrib
	var fdata

	var insertFragment = function(j, file) {
		tree.insertNewChild(0, j, j.split("/")[j.split("/").length -1].replace(".fragments.json",""))
		$.each(file.tags, function(t,ids) {
			tree.insertNewChild(j, j + ":" + t, "<b>Tag:</b> " + t)
			tree._hAdI = true;
			$.each(ids, function(i, idx) {
				//alert(t)
				tree.insertNewChild(j + ":" + t, j + ":" + t + ":" + idx, "<b>" + JSON.stringify(file.fragments[idx].specification) +  "</b> ")
				tree.insertNewChild(j + ":" + t + ":" + idx, j + ":" + t + ":" + idx + ":locator", "<b>Locator:</b> <a target=\"_blank\" href=\"https://github.com/101companies/101repo/tree/master/technologies/" +  file.fragments[idx].locator.split("/")[0] + "\">" + file.fragments[idx].locator.split("/")[0] + "</a>")
			})
			tree._hAdI = false;
		})
		
			/*
			$.ajax({url: "../101repo/" + filepath + ".fragments.json",
				dataType: 'json',
				success:  function(data) {
					$("#tagtree").empty()
					tree = new dhtmlXTreeObject("tagtree","30%","30%",0);
					tree.setImagePath("./imgs/")
					tree.loadXML("filebase.xml")
					tree.enableHighlighting(true)
					tree.enableCheckBoxes(false)
					tree.enableTreeImages(false)
					$(".containerTableStyle").css({width: "100%" , height:"100%"})
					tree.insertNewChild(0,"/",filepath.split("/").splice(3).join("/"))
					$.each(data.tags, function(t,ids) {
						tree.insertNewChild("/","t:" + t, "<b>Tag:</b> " + t)
						$.each(ids, function(i, idx) {
							tree.insertNewChild("t:" + t, "i:" + idx, "<b>" + JSON.stringify(data.fragments[idx].specification) +  "</b> ")
							tree.insertNewChild("i:" + idx,"l:" + idx, "<b>Locator:</b> <a target=\"_blank\" href=\"https://github.com/101companies/101repo/tree/master/technologies/" +  data.fragments[idx].locator.split("/")[0] + "\">" + data.fragments[idx].locator.split("/")[0] + "</a>")
						})
					})
					tree.attachEvent("click", function(id){
						if (id.match("^"+"l:")=="l:")
							;
						else if (id.match("^"+"i:")!="i:") {
							return
						}
						range = data.fragments[id.split(":")[1]].location
						SourceExplorer.showSourceAndHighlight(filepath, range.from, range.to)
					})
					tree.attachEvent("onSelect", function(id){
						if (id.match("^"+"l:")=="l:")
							tree.selectItem("i:" + id.split(":")[1])
						else if (id.match("^"+"i:")!="i:") {
							return
						}
						range = data.fragments[id.split(":")[1]].location
						SourceExplorer.showSourceAndHighlight(filepath, range.from, range.to)
					})
				},
				error: function(s) {
				  $("#tagtree").html($("<b>").text("Fragment file not found."))
					
				}
			});
		*/
		}

	return {
		init : function(contribname) {
			contrib = contribname
			$.ajax({url: "../101repo/contributions/" + contribname + "/index.fragments.json",
	        	dataType: 'json',
	        	success:  function(data) {
					tree = new dhtmlXTreeObject("tagtree","30%","30%",0);
					tree.setImagePath("./imgs/")
					tree.loadXML("filebase.xml")
					tree.enableHighlighting(true)
					tree.enableCheckBoxes(false)
					tree.enableTreeImages(false)
					$(".containerTableStyle").css({width: "100%" , height:"100%"})
	        		//tree.insertNewChild(0,"/", contribname)
	        		$.each(data, function(j,file) {
	        			insertFragment(j,file);
	        		})
	        		tree.attachEvent("onClick", function(id) {
						pathTag = id.split(":")
						path = "/" + pathTag[0].replace(".fragments.json", "")
						FileExplorer.selectFile(path)
						LanguageExplorer.selectFile(path)
						TechnologyExplorer.selectFile(id)
						if (pathTag.length > 1) {
							ids = data[pathTag[0]].tags[pathTag[1]]
							ranges = []
							$.each(ids, function(i, id){
								loc = data[pathTag[0]].fragments[id].location
								if (loc)
									ranges.push(loc)
							})
							SourceExplorer.showSourceAndHighlight(path,ranges)
						} else {
							SourceExplorer.showSource(path)
						}
					})
	         	}
	     	 });
		}
	}

}(jQuery)