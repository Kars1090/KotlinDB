package view

import javafx.scene.control.Button
import presenter.PeopleNewPresenter
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import tornadofx.*

class PeopleNewView : View() {
    override val root = VBox()
    var presenter = PeopleNewPresenter(this)
    lateinit var adressBox: VBox
    lateinit var buttonAddAdress: Button

    init {
        importStylesheet("/PeopleNewStyle.css")
        with (root) {
            id="root"
            label { id="Title"
                text="Create new person"
            }
            setupInfoBox()
            adressBox = setupAdressBox()
            adressBox.isVisible = false
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
        adressBox.isVisible = true
    }

    private fun buttonAddAdressClicked() {
        presenter.addAdressClicked()
    }

    private fun buttonAddPersonClicked() {
        presenter.addPersonClicked()
    }

    private fun buttonBackClicked() {
        presenter.backClicked()
    }

    private fun setupInfoBox(): HBox {
        return hbox { id="InfoBox"
            vbox { id="FirstNameBox"
                label { text="First Name:" }
                textfield { id="fieldFirstName" }
            }
            vbox { id="LastNameBox"
                label { text="Last Name:" }
                textfield { id="fieldLastName" }
            }
            vbox { id="EmailBox"
                label { text="E-mail adress:" }
                textfield { id="fieldEmail" }
            }
        }
    }

    private fun setupAdressBox(): VBox {
        return vbox {id="AdressBox"
            hbox { id="AdressFirstLineBox"
                vbox { id = "StreetBox"
                    label { text = "Street:" }
                    textfield { id = "fieldStreet" }
                }
                vbox { id = "NumberBox"
                    label { text = "Number:" }
                    textfield { id = "fieldNumber" }
                }
                vbox { id = "AdditionBox"
                    label { text = "Addition: (optional)" }
                    textfield { id = "fieldAddition" }
                }
            }
            hbox { id="AdressSecondLineBox"
                vbox { id = "PostalCodeBox"
                    label { text = "Postal Code:" }
                    textfield { id = "fieldPostalCode" }
                }
                vbox { id = "CityBox"
                    label { text = "City:" }
                    textfield { id = "fieldCity" }
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
