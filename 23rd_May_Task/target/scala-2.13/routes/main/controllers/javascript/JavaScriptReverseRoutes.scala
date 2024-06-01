// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:7
package controllers.javascript {

  // @LINE:11
  class ReversePersonController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:11
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PersonController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "persons"})
        }
      """
    )
  
    // @LINE:12
    def addPerson: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PersonController.addPerson",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "persons"})
        }
      """
    )
  
  }

  // @LINE:23
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:23
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }

  // @LINE:8
  class ReverseStoreController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:8
    def store: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.StoreController.store",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "store"})
        }
      """
    )
  
    // @LINE:9
    def report: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.StoreController.report",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "report"})
        }
      """
    )
  
  }

  // @LINE:7
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:7
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
  }

  // @LINE:15
  class ReversePeopleController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:17
    def createPerson: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PeopleController.createPerson",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "people"})
        }
      """
    )
  
    // @LINE:16
    def getPerson: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PeopleController.getPerson",
      """
        function(sno0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "people/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Int]].javascriptUnbind + """)("sno", sno0))})
        }
      """
    )
  
    // @LINE:15
    def getPeople: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PeopleController.getPeople",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "people"})
        }
      """
    )
  
    // @LINE:19
    def deletePerson: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PeopleController.deletePerson",
      """
        function(sno0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "people/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Int]].javascriptUnbind + """)("sno", sno0))})
        }
      """
    )
  
    // @LINE:18
    def updatePerson: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PeopleController.updatePerson",
      """
        function(sno0) {
          return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "people/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Int]].javascriptUnbind + """)("sno", sno0))})
        }
      """
    )
  
  }


}
