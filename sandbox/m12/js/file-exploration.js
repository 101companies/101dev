var initFileExplorer = function(contrib, summary) {
  	var tree = new dhtmlXTreeObject("files","30%","30%",0);
  	tree.setImagePath("./imgs/")
  	tree.enableCheckBoxes(false)
  	tree.loadXML("filebase.xml")
  	tree.insertNewChild(0,"/",contrib)
  	$("#files").css("overflow-y", "auto")
  	tree.attachEvent("onClick", function(id) {
      if (id.substr(-1) !== "/") {
  		  viewSource(id)
      }
  		return true
  	})
  	tree.attachEvent("onDblClick", function() {return false})
  	insertDir(tree, contrib, summary.files, summary.subDirectories, "/", [])
}

var loadDir = function(tree, contrib, id, loadedDirs) {
	if (id != "/" & loadedDirs.indexOf(id) == -1) {
		$.ajax({url: "../101repo/contributions/" + contrib + "/" + id + "/index.summary.json",
				dataType: 'json',
				success : function(data) {
					insertDir(tree, contrib, data.files, data.subDirectories, id, loadedDirs.concat([id]))
				}
		})
	}
}

var insertDir = function(tree, contrib, files, dirs, id, loadedDirs) {
	tree.deleteChildItems(id)
	$.each(files, function(i, fn) {
  		tree.insertNewChild(id, fn.filename, i)
  	})
  	$.each(dirs, function(j, fn) {
  		tree.insertNewChild(id, id + j + "/", j)
  		tree._hAdI = true;
  		tree.insertNewChild(id + j + "/", "temp", "Loading directory content...")
  		tree._hAdI = false;
  	})

  	var handlerid = tree.attachEvent("onOpenStart", function(id,state){
  		if (state < 0)
  			loadDir(tree, contrib, id, loadedDirs)
  		else if (id == "/")
  			return true
  		return true
  	});
  	var handlernum = parseInt(handlerid.substring(handlerid.length - 1))
	if (handlernum != 0)
		tree.detachEvent("ev_onopenstart:" + (handlernum - 1))
}