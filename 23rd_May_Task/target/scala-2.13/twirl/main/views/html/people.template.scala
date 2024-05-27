
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

object people extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[Seq[Person],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(persons: Seq[Person]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/main("All Persons")/*3.21*/ {_display_(Seq[Any](format.raw/*3.23*/("""
    """),format.raw/*4.5*/("""<ul>
    """),_display_(/*5.6*/for(person <- persons) yield /*5.28*/ {_display_(Seq[Any](format.raw/*5.30*/("""
        """),format.raw/*6.9*/("""<li>"""),_display_(/*6.14*/person/*6.20*/.name),format.raw/*6.25*/(""" """),format.raw/*6.26*/("""- """),_display_(/*6.29*/person/*6.35*/.city),format.raw/*6.40*/("""</li>
    """)))}),format.raw/*7.6*/("""
    """),format.raw/*8.5*/("""</ul>
""")))}),format.raw/*9.2*/("""
"""),format.raw/*10.1*/("""<h2>Add a new person</h2>
<form action="persons" method="post">
    <label for="name">Sno:</label>
    <input type="text" id="Sno" name="sno" required><br><br>
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br><br>

    <label for="city">City:</label>
    <input type="text" id="city" name="city" required><br><br>

    <input type="submit" value="Add Person">
</form>"""))
      }
    }
  }

  def render(persons:Seq[Person]): play.twirl.api.HtmlFormat.Appendable = apply(persons)

  def f:((Seq[Person]) => play.twirl.api.HtmlFormat.Appendable) = (persons) => apply(persons)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/people.scala.html
                  HASH: 840336a87354046eeabf264303172809e676ed23
                  MATRIX: 735->1|851->24|878->26|905->45|944->47|975->52|1010->62|1047->84|1086->86|1121->95|1152->100|1166->106|1191->111|1219->112|1248->115|1262->121|1287->126|1327->137|1358->142|1394->149|1422->150
                  LINES: 21->1|26->2|27->3|27->3|27->3|28->4|29->5|29->5|29->5|30->6|30->6|30->6|30->6|30->6|30->6|30->6|30->6|31->7|32->8|33->9|34->10
                  -- GENERATED --
              */
          