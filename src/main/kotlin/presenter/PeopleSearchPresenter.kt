package presenter

import dao.PersonDAO
import tornadofx.*
import util.Alerts
import view.PeopleSearchView
import view.PeopleMenuView
import java.lang.NumberFormatException

class PeopleSearchPresenter(val view: PeopleSearchView): Controller() {
    private var searchAddress = false
    private val personDAO = PersonDAO()

    fun searchAddressClicked() {
        searchAddress = true
        view.showAdressFields()
    }

    fun searchPersonClicked(firstName: String, lastName: String, email: String, street: String, number: String, addition: String, postalCode: String, city: String) {
        var numberInt: Int? = null
        var additionChar: Char? = null
        try {
            if (number != "") numberInt = number.toInt()
        } catch (e: NumberFormatException) {
            Alerts.instance.showWarning("Warning","Number was not an int","Number will be excluded from the search")
        }
        if (addition != "") {
            if (addition.length == 1) additionChar = addition[0]
            else Alerts.instance.showWarning("Warning","Addition contained more than 1 char","Addition will be excluded from the search")
        }
        personDAO.findPeople(firstName, lastName, email, street, numberInt, additionChar, postalCode, city).forEach { view.addPersonToList(it) }
    }

    fun backClicked() {
        view.replaceWith(find(PeopleMenuView::class))
    }

}