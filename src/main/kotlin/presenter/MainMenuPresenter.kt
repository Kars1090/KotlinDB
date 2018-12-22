package presenter

import tornadofx.*
import view.MainMenuView
import view.PeopleMenuView

class MainMenuPresenter(val view: MainMenuView): Controller() {

    fun managePeoplePressed() {
        view.replaceWith(PeopleMenuView())
    }

    fun manageOccupationsPressed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun manageBusinessesPressed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}