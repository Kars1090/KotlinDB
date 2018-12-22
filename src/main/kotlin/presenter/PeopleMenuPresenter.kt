package presenter

import model.Person
import tornadofx.*
import view.PeopleMenuView

class PeopleMenuPresenter(val view: PeopleMenuView): Controller() {

    fun addPersonPressed() {
        val p1 = Person("p","1","email@email.com")
        val p2 = Person("p","2","email@email.com")
        println(p1.interactWith(p2))
    }

    fun findPersonPressed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}