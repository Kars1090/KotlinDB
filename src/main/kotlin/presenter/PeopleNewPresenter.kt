package presenter

import tornadofx.*
import util.Alerts
import view.PeopleNewView
import view.PeopleMenuView

class PeopleNewPresenter(val view: PeopleNewView): Controller() {

    fun addAdressClicked() {
        view.showAdressFields()
    }

    fun addPersonClicked() {
        Alerts.instance.showWarning("Warning","You clicked Add Person","Not Implemented!")
    }

    fun backClicked() {
        view.replaceWith(PeopleMenuView())
    }

}