package view

import javafx.scene.control.Button
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import presenter.PeopleSearchPresenter
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import model.Person
import tornadofx.*

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
            setupResultBox()
            setupBottomBox()
        }
    }

    fun showAdressFields() {
        buttonAddAdress.isVisible = false
        addressBox.isVisible = true
    }

    fun addPersonToList(person: Person) {
        people.add(person)
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
            readonlyColumn("First Name",Person::firstName)
            readonlyColumn("Last Name",Person::lastName)
            readonlyColumn("Email Address",Person::email)
            isEditable = true
        }
    }

}
