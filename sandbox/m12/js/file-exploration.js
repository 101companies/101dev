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
		tree.deleteChildItems(id)
		$.each(files, function(i, fn) {
			tree.insertNewChild(id, fn.path, i)
		})
		$.each(dirs, function(j, fn) {
			if (fn.path) {
				tree.insertNewChild(id, fn.path, j)
				tree._hAdI = true;
				tree.insertNewChild(fn.path, "temp", "Loading directory content...")
				tree._hAdI = false;
			}
		})
	}

	return {
		getTree : function(){return tree},		

		init : function(contribname, summary) {
			contrib = contribname
			tree = new dhtmlXTreeObject("files","30%","30%",0);
			tree.setImagePath("./imgs/")
			tree.loadXML("filebase.xml")
			tree.enableHighlighting(true)
			tree.insertNewChild(0,"/",contrib)
			$("#files").css("overflow-y", "auto")
			$(".containerTableStyle").css({width: "100%" , height:"100%"})
			tree.attachEvent("onClick", function(id) {
				if (id.substr(-1) !== "/") {
					SourceExplorer.showSource(id)
				}
				return true
			})
			tree.attachEvent("onDblClick", function() {return false})
			tree.attachEvent("onOpenStart", function(id,state){
				if (state < 0)
					loadDir(id)
				return true
			});
			fillDir("/", summary.directFiles.directFiles, summary.directSubdirectories)
		},

		selectFile : function(filepath) {
			pwds = []
			filepath.split("/").reduce(function(agg, x, i) {return pwds[i] = agg + "/" + x})
			pwds.pop()
			$.each(pwds.splice(3), function(i,x) {
				if (i == pwds.length - 1) {
					loadDir(x, function() {tree.focusItem(filepath); tree.selectItem(filepath)})
					return
				}
				else 
					loadDir(x)
			})
			tree.focusItem(filepath)
			tree.selectItem(filepath)
			
		}
	}

}(jQuery)