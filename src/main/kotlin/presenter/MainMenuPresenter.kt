package presenter

import tornadofx.*
import util.Alerts
import view.MainMenuView
import view.PeopleMenuView

class MainMenuPresenter(val view: MainMenuView): Controller() {

    fun managePeoplePressed() {
        view.replaceWith(PeopleMenuView())
    }

    fun manageOccupationsPressed() {
        Alerts.instance.showWarning("Warning","You clicked Occupations","Not Implemented!")
    }

    fun manageBusinessesPressed() {
        Alerts.instance.showWarning("Warning","You clicked Businesses","Not Implemented!")
    }

}