package view

import javafx.scene.control.Button
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import presenter.PeopleSearchPresenter
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import model.Address
import model.Person
import tornadofx.*
import util.Alerts

class PeopleSearchView : View() {
    override val root = VBox()
    var presenter = PeopleSearchPresenter(this)
    private lateinit var addressBox: VBox
    private lateinit var buttonAddAdress: Button
    private lateinit var fieldFirstName: TextField
    private lateinit var fieldLastName: TextField
    private lateinit var fieldEmail: TextField
    private lateinit var fieldStreet: TextField
    private lateinit var fieldNumber: TextField
    private lateinit var fieldAddition: TextField
    private lateinit var fieldPostalCode: TextField
    private lateinit var fieldCity: TextField
    private lateinit var tableResults: TableView<Person>
    private val people = mutableListOf<Person>().observable()

    init {
        importStylesheet("/PeopleStyle.css")
        with (root) {
            id="root"
            label { id="Title"
                text="Find person"
            }
            setupInfoBox()
            addressBox = setupAddressBox()
            addressBox.isVisible = false
            buttonAddAdress = button {id="buttonFindAdress"
                styleClass.add("Button")
                text="Search for adress information"
                action { buttonSearchAdressClicked() }
            }
            tableResults = setupResultBox()
            setupBottomBox()
        }
    }

    fun showAdressFields() {
        buttonAddAdress.isVisible = false
        addressBox.isVisible = true
    }

    fun addPersonToList(person: Person) {
        people.add(person)
        SmartResize.POLICY.requestResize(tableResults)
    }

    private fun buttonSearchAdressClicked() {
        presenter.searchAddressClicked()
    }

    private fun buttonSearchPersonClicked() {
        clearList()
        presenter.searchPersonClicked(fieldFirstName.text, fieldLastName.text, fieldEmail.text,
                fieldStreet.text, fieldNumber.text, fieldAddition.text, fieldPostalCode.text, fieldCity.text)
    }

    private fun clearList() {
        people.clear()
    }

    private fun buttonBackClicked() {
        presenter.backClicked()
    }

    private fun setupInfoBox(): HBox {
        return hbox { id="InfoBox"
            vbox { id="FirstNameBox"
                label { text="First Name:" }
                fieldFirstName =  textfield { id="fieldFirstName" }
            }
            vbox { id="LastNameBox"
                label { text="Last Name:" }
                fieldLastName = textfield { id="fieldLastName" }
            }
            vbox { id="EmailBox"
                label { text="E-mail address:" }
                fieldEmail = textfield { id="fieldEmail" }
            }
        }
    }

    private fun setupAddressBox(): VBox {
        return vbox {id="AddressBox"
            hbox { id="AddressFirstLineBox"
                vbox { id = "StreetBox"
                    label { text = "Street:" }
                    fieldStreet = textfield { id = "fieldStreet" }
                }
                vbox { id = "NumberBox"
                    label { text = "Number:" }
                    fieldNumber = textfield { id = "fieldNumber" }
                }
                vbox { id = "AdditionBox"
                    label { text = "Addition:" }
                    fieldAddition = textfield { id = "fieldAddition" }
                }
            }
            hbox { id="AddressSecondLineBox"
                vbox { id = "PostalCodeBox"
                    label { text = "Postal Code:" }
                    fieldPostalCode = textfield { id = "fieldPostalCode" }
                }
                vbox { id = "CityBox"
                    label { text = "City:" }
                    fieldCity = textfield { id = "fieldCity" }
                }
            }
        }
    }

    private fun setupBottomBox(): HBox {
        return hbox {id="BottomBox"
            vbox { id = "BottomLeftBox"
                button {
                    id = "buttonBack"
                    styleClass.add("Button")
                    text = "Back"
                    action { buttonBackClicked() }
                }
            }
            vbox { id = "BottomRightBox"
                button {
                    id = "buttonSearchPerson"
                    styleClass.add("Button")
                    text = "Search!"
                    action { buttonSearchPersonClicked() }
                }
            }
        }
    }

    private fun setupResultBox(): TableView<Person> {
        return tableview(people) { id="ResultTable"
            column<Person, String>("First Name") { it.value.firstName }
            column<Person, String>("Last Name") { it.value.lastName }
            column<Person, String>("Email Name") { it.value.email }
            readonlyColumn("Street", Person::address).cellFormat { text = it?.street?.value }
            readonlyColumn("No.", Person::address).cellFormat { text = it?.number?.value.toString() }
            readonlyColumn("Addition", Person::address).cellFormat { text = it?.addition?.value }
            readonlyColumn("Postal Code", Person::address).cellFormat { text = it?.postalCode?.value }
            readonlyColumn("City", Person::address).cellFormat { text = it?.city?.value }
            columnResizePolicy = SmartResize.POLICY
            isEditable = true
        }
    }

}
