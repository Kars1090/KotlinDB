package view

import javafx.scene.control.Button
import javafx.scene.control.TextField
import presenter.PeopleNewPresenter
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import tornadofx.*

class PeopleNewView : View() {
    override val root = VBox()
    var presenter = PeopleNewPresenter(this)
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

    init {
        importStylesheet("/PeopleNewStyle.css")
        with (root) {
            id="root"
            label { id="Title"
                text="Create new person"
            }
            setupInfoBox()
            addressBox = setupAddressBox()
            addressBox.isVisible = false
            buttonAddAdress = button {id="buttonAddAdress"
                styleClass.add("Button")
                text="Add adress information"
                action { buttonAddAdressClicked() }
            }
            setupBottomBox()
        }
    }

    fun showAdressFields() {
        buttonAddAdress.isVisible = false
        addressBox.isVisible = true
    }

    private fun buttonAddAdressClicked() {
        presenter.addAddressClicked()
    }

    private fun buttonAddPersonClicked() {
        presenter.addPersonClicked(fieldFirstName.text, fieldLastName.text, fieldEmail.text,
                fieldStreet.text, fieldNumber.text, fieldAddition.text, fieldPostalCode.text, fieldCity.text)
    }

    private fun buttonBackClicked() {
        presenter.backClicked()
    }

    private fun setupInfoBox(): HBox {
        return hbox { id="InfoBox"
            vbox { id="FirstNameBox"
                label { text="First Name:*" }
                fieldFirstName =  textfield { id="fieldFirstName" }
            }
            vbox { id="LastNameBox"
                label { text="Last Name:*" }
                fieldLastName = textfield { id="fieldLastName" }
            }
            vbox { id="EmailBox"
                label { text="E-mail address:*" }
                fieldEmail = textfield { id="fieldEmail" }
            }
        }
    }

    private fun setupAddressBox(): VBox {
        return vbox {id="AdressBox"
            hbox { id="AddressFirstLineBox"
                vbox { id = "StreetBox"
                    label { text = "Street:*" }
                    fieldStreet = textfield { id = "fieldStreet" }
                }
                vbox { id = "NumberBox"
                    label { text = "Number:*" }
                    fieldNumber = textfield { id = "fieldNumber" }
                }
                vbox { id = "AdditionBox"
                    label { text = "Addition: (optional)" }
                    fieldAddition = textfield { id = "fieldAddition" }
                }
            }
            hbox { id="AddressSecondLineBox"
                vbox { id = "PostalCodeBox"
                    label { text = "Postal Code:*" }
                    fieldPostalCode = textfield { id = "fieldPostalCode" }
                }
                vbox { id = "CityBox"
                    label { text = "City:*" }
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
                    id = "buttonAddPerson"
                    styleClass.add("Button")
                    text = "Add new Person"
                    action { buttonAddPersonClicked() }
                }
            }
        }
    }

}
