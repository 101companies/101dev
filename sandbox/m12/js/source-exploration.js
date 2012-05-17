var viewSource = function(contrib, path) {
  if (path.substr(-1) !== "/") {
    $("#detailcontent").
      html("Loading source code...").
      load("../101repo/contributions/" + contrib + path + ".html")
    $("#files").css("overflow-y", "auto")
  }
}