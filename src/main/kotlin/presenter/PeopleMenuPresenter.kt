package presenter

import tornadofx.*
import util.Alerts
import view.PeopleMenuView
import view.PeopleNewView

class PeopleMenuPresenter(val view: PeopleMenuView): Controller() {

    fun addPersonPressed() {
        view.replaceWith(PeopleNewView())
    }

    fun findPersonPressed() {
        Alerts.instance.showWarning("Warning","You clicked Find Person","Not Implemented!")
    }

}