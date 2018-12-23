package presenter

import model.Person
import model.factory.AddressFactory
import model.factory.PersonFactory
import tornadofx.*
import view.PeopleNewView
import view.PeopleMenuView
import util.Alerts
import java.lang.NumberFormatException

class PeopleNewPresenter(val view: PeopleNewView) : Controller() {
    private var addedAddress = false

    fun addAddressClicked() {
        view.showAdressFields()
        addedAddress = true
    }

    fun addPersonClicked(firstName: String, lastName: String, email: String,
                         street: String, number: String, addition: String, postalCode: String, city: String) {
        if (firstName == "" || lastName == "" || email == "") {
            Alerts.instance.showError("Error", "You did something wrong", "Please fill in all mandatory fields!")
            return
        }
        val personFactory = PersonFactory()
        var person: Person?
        if (!addedAddress) {
            person = personFactory.createPerson(firstName, lastName, email)
        } else {
            if (street == "" || number == "" || postalCode == "" || city == "") {
                Alerts.instance.showError("Error", "You did something wrong", "Please fill in all mandatory fields!")
                return
            }
            var addressNumber: Int?
            try {
                addressNumber = number.toInt()
            } catch (e: NumberFormatException) {
                Alerts.instance.showError("Error", "You did something wrong", "Number field is numbers only")
                return
            }
            val addressFactory = AddressFactory()
            var addressAddition: Char? = null
            if (addition != "") addressAddition = addition[0]
            val address = addressFactory.createAdress(street, addressNumber, postalCode, city, addressAddition)
            person = personFactory.createPerson(firstName, lastName, email, null, address)
        }
        println(person.getInfoString())
    }

    fun backClicked() {
        view.replaceWith(PeopleMenuView())
    }

}