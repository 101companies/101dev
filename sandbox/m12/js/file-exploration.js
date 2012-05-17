var initFileExplorer = function(contrib) {
  	var tree = new dhtmlXTreeObject("files","30%","30%",0);
  	tree.setImagePath("./imgs/")
  	tree.enableCheckBoxes(false)
  	tree.loadXML("filebase.xml")
  	tree.insertNewChild(0,"/",contrib,0,0,0,0)
  	$("#files").css("overflow-y", "auto")
  	tree.attachEvent("onClick", function(id) {
  		viewSource(contrib, id)
  		return true
  	})
  	tree.attachEvent("onDblClick", function() {return false})
  	$.ajax({url: "../101repo/contributions/" + contrib + "/index.summary.json",
  			dataType: 'json',
  			success:  function(data) {
  				insertDir(tree, contrib, data.files, data.subDirectories, "/", [])
  			},
  			error: function(s) {
  				if (s.status == 404) 
  					$("#selectionview .viewinfo").append($("<b>").addClass("noSelInfo").text("Contribution " + contrib + " not found."))
  			}
  		});
}

var loadDir = function(tree, contrib, id, loadedDirs) {
	if (loadedDirs.indexOf(id) == -1) {
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
  		tree.insertNewChild(id, id + i, i ,0,0,0,0)
  	})
  	$.each(dirs, function(j, fn) {
  		tree.insertNewChild(id, id + j + "/", j ,0,0,0,0)
  		tree._hAdI = true;
  		tree.insertNewChild(id + j + "/", "", "" ,0,0,0,0)
  		tree._hAdI = false;
  	})

  	var handlerid = tree.attachEvent("onOpenStart", function(id,state){
  		if (state < 0)
  			loadDir(tree, contrib, id, loadedDirs)
  		return true
  	});
  	var handlernum = parseInt(handlerid.substring(handlerid.length - 1))
	if (handlernum != 0)
		tree.detachEvent("ev_onopenstart:" + (handlernum - 1))
}