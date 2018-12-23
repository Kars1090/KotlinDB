package presenter

import dao.PersonDAO
import tornadofx.*
import view.PeopleSearchView

class PeopleSearchPresenter(val view: PeopleSearchView): Controller() {
    private var searchAddress = false
    private val personDAO = PersonDAO()

    fun searchAddressClicked() {
        searchAddress = true
        view.showAdressFields()
    }

    fun searchPersonClicked(firstName: String, lastName: String, email: String, street: String, number: String, addition: String, postalCode: String, city: String) {
        personDAO.findPeople(firstName, lastName, email).forEach { view.addPersonToList(it) }
    }

    fun backClicked() {

    }

}