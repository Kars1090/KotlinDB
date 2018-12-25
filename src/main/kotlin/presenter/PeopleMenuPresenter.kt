package presenter

import tornadofx.*
import view.PeopleMenuView
import view.PeopleNewView
import view.PeopleSearchView

class PeopleMenuPresenter(val view: PeopleMenuView): Controller() {

    fun addPersonPressed() {
        view.replaceWith(PeopleNewView())
    }

    fun findPersonPressed() {
        view.replaceWith(PeopleSearchView())
    }

}