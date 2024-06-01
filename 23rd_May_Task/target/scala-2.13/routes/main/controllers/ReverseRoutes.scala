// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:7
package controllers {

  // @LINE:11
  class ReversePersonController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:11
    def index: Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "persons")
    }
  
    // @LINE:12
    def addPerson: Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "persons")
    }
  
  }

  // @LINE:23
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:23
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }

  // @LINE:8
  class ReverseStoreController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:8
    def store(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "store")
    }
  
    // @LINE:9
    def report(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "report")
    }
  
  }

  // @LINE:7
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:7
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
  }

  // @LINE:15
  class ReversePeopleController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:17
    def createPerson: Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "people")
    }
  
    // @LINE:16
    def getPerson(sno:Int): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "people/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Int]].unbind("sno", sno)))
    }
  
    // @LINE:15
    def getPeople: Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "people")
    }
  
    // @LINE:19
    def deletePerson(sno:Int): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "people/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Int]].unbind("sno", sno)))
    }
  
    // @LINE:18
    def updatePerson(sno:Int): Call = {
      
      Call("PUT", _prefix + { _defaultPrefix } + "people/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Int]].unbind("sno", sno)))
    }
  
  }


}
