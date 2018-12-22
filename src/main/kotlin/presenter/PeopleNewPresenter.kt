package presenter

import tornadofx.*
import view.PeopleNewView

class PeopleNewPresenter(val view: PeopleNewView): Controller() {

    fun addAdressClicked() {
        view.showAdressFields()
    }

    fun addPersonClicked() {
    }

    fun backClicked() {
    }

}