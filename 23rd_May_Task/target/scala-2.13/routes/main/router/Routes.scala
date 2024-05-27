// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:7
  HomeController_4: controllers.HomeController,
  // @LINE:8
  StoreController_2: controllers.StoreController,
  // @LINE:11
  PersonController_0: controllers.PersonController,
  // @LINE:15
  PeopleController_1: controllers.PeopleController,
  // @LINE:23
  Assets_3: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

  @javax.inject.Inject()
  def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:7
    HomeController_4: controllers.HomeController,
    // @LINE:8
    StoreController_2: controllers.StoreController,
    // @LINE:11
    PersonController_0: controllers.PersonController,
    // @LINE:15
    PeopleController_1: controllers.PeopleController,
    // @LINE:23
    Assets_3: controllers.Assets
  ) = this(errorHandler, HomeController_4, StoreController_2, PersonController_0, PeopleController_1, Assets_3, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_4, StoreController_2, PersonController_0, PeopleController_1, Assets_3, prefix)
  }

  private val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """store""", """controllers.StoreController.store()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """report""", """controllers.StoreController.report()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """persons""", """controllers.PersonController.index"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """persons""", """controllers.PersonController.addPerson"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """people""", """controllers.PeopleController.getPeople"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """people/""" + "$" + """sno<[^/]+>""", """controllers.PeopleController.getPerson(sno:Int)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """people""", """controllers.PeopleController.createPerson"""),
    ("""PUT""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """people/""" + "$" + """sno<[^/]+>""", """controllers.PeopleController.updatePerson(sno:Int)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """people/""" + "$" + """sno<[^/]+>""", """controllers.PeopleController.deletePerson(sno:Int)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    Nil
  ).foldLeft(Seq.empty[(String, String, String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String, String, String)]
    case l => s ++ l.asInstanceOf[List[(String, String, String)]]
  }}


  // @LINE:7
  private lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private lazy val controllers_HomeController_index0_invoker = createInvoker(
    HomeController_4.index(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:8
  private lazy val controllers_StoreController_store1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("store")))
  )
  private lazy val controllers_StoreController_store1_invoker = createInvoker(
    StoreController_2.store(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.StoreController",
      "store",
      Nil,
      "GET",
      this.prefix + """store""",
      """""",
      Seq()
    )
  )

  // @LINE:9
  private lazy val controllers_StoreController_report2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("report")))
  )
  private lazy val controllers_StoreController_report2_invoker = createInvoker(
    StoreController_2.report(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.StoreController",
      "report",
      Nil,
      "GET",
      this.prefix + """report""",
      """""",
      Seq()
    )
  )

  // @LINE:11
  private lazy val controllers_PersonController_index3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("persons")))
  )
  private lazy val controllers_PersonController_index3_invoker = createInvoker(
    PersonController_0.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PersonController",
      "index",
      Nil,
      "GET",
      this.prefix + """persons""",
      """""",
      Seq()
    )
  )

  // @LINE:12
  private lazy val controllers_PersonController_addPerson4_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("persons")))
  )
  private lazy val controllers_PersonController_addPerson4_invoker = createInvoker(
    PersonController_0.addPerson,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PersonController",
      "addPerson",
      Nil,
      "POST",
      this.prefix + """persons""",
      """""",
      Seq()
    )
  )

  // @LINE:15
  private lazy val controllers_PeopleController_getPeople5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("people")))
  )
  private lazy val controllers_PeopleController_getPeople5_invoker = createInvoker(
    PeopleController_1.getPeople,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PeopleController",
      "getPeople",
      Nil,
      "GET",
      this.prefix + """people""",
      """ Routes for People""",
      Seq()
    )
  )

  // @LINE:16
  private lazy val controllers_PeopleController_getPerson6_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("people/"), DynamicPart("sno", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_PeopleController_getPerson6_invoker = createInvoker(
    PeopleController_1.getPerson(fakeValue[Int]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PeopleController",
      "getPerson",
      Seq(classOf[Int]),
      "GET",
      this.prefix + """people/""" + "$" + """sno<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:17
  private lazy val controllers_PeopleController_createPerson7_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("people")))
  )
  private lazy val controllers_PeopleController_createPerson7_invoker = createInvoker(
    PeopleController_1.createPerson,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PeopleController",
      "createPerson",
      Nil,
      "POST",
      this.prefix + """people""",
      """""",
      Seq()
    )
  )

  // @LINE:18
  private lazy val controllers_PeopleController_updatePerson8_route = Route("PUT",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("people/"), DynamicPart("sno", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_PeopleController_updatePerson8_invoker = createInvoker(
    PeopleController_1.updatePerson(fakeValue[Int]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PeopleController",
      "updatePerson",
      Seq(classOf[Int]),
      "PUT",
      this.prefix + """people/""" + "$" + """sno<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:19
  private lazy val controllers_PeopleController_deletePerson9_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("people/"), DynamicPart("sno", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_PeopleController_deletePerson9_invoker = createInvoker(
    PeopleController_1.deletePerson(fakeValue[Int]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PeopleController",
      "deletePerson",
      Seq(classOf[Int]),
      "DELETE",
      this.prefix + """people/""" + "$" + """sno<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:23
  private lazy val controllers_Assets_versioned10_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""", encodeable=false)))
  )
  private lazy val controllers_Assets_versioned10_invoker = createInvoker(
    Assets_3.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:7
    case controllers_HomeController_index0_route(params@_) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_4.index())
      }
  
    // @LINE:8
    case controllers_StoreController_store1_route(params@_) =>
      call { 
        controllers_StoreController_store1_invoker.call(StoreController_2.store())
      }
  
    // @LINE:9
    case controllers_StoreController_report2_route(params@_) =>
      call { 
        controllers_StoreController_report2_invoker.call(StoreController_2.report())
      }
  
    // @LINE:11
    case controllers_PersonController_index3_route(params@_) =>
      call { 
        controllers_PersonController_index3_invoker.call(PersonController_0.index)
      }
  
    // @LINE:12
    case controllers_PersonController_addPerson4_route(params@_) =>
      call { 
        controllers_PersonController_addPerson4_invoker.call(PersonController_0.addPerson)
      }
  
    // @LINE:15
    case controllers_PeopleController_getPeople5_route(params@_) =>
      call { 
        controllers_PeopleController_getPeople5_invoker.call(PeopleController_1.getPeople)
      }
  
    // @LINE:16
    case controllers_PeopleController_getPerson6_route(params@_) =>
      call(params.fromPath[Int]("sno", None)) { (sno) =>
        controllers_PeopleController_getPerson6_invoker.call(PeopleController_1.getPerson(sno))
      }
  
    // @LINE:17
    case controllers_PeopleController_createPerson7_route(params@_) =>
      call { 
        controllers_PeopleController_createPerson7_invoker.call(PeopleController_1.createPerson)
      }
  
    // @LINE:18
    case controllers_PeopleController_updatePerson8_route(params@_) =>
      call(params.fromPath[Int]("sno", None)) { (sno) =>
        controllers_PeopleController_updatePerson8_invoker.call(PeopleController_1.updatePerson(sno))
      }
  
    // @LINE:19
    case controllers_PeopleController_deletePerson9_route(params@_) =>
      call(params.fromPath[Int]("sno", None)) { (sno) =>
        controllers_PeopleController_deletePerson9_invoker.call(PeopleController_1.deletePerson(sno))
      }
  
    // @LINE:23
    case controllers_Assets_versioned10_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned10_invoker.call(Assets_3.versioned(path, file))
      }
  }
}
