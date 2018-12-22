package view

import presenter.PeopleNewPresenter
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import tornadofx.*

class PeopleNewView : View() {
    override val root = VBox()
    var presenter = PeopleNewPresenter(this)

    init {
        importStylesheet("/PeopleNewStyle.css")
        with (root) {
            id="root"
            label { id="Title"
                text="Create new person"
            }
            setupInfoBox()
            setupAdressBox()
            setupBottomBox()
        }
    }

    fun showAdressFields() {

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
                label { text="First Name" }
                textfield { id="fieldFirstName" }
            }
            vbox { id="LastNameBox"
                label { text="Last Name" }
                textfield { id="fieldLastName" }
            }
            vbox { id="EmailBox"
                label { text="E-mail adress" }
                textfield { id="fieldEmail" }
            }
        }
    }

    private fun setupAdressBox(): HBox {
        return hbox {id="AdressBox"
            button {id="buttonAddAdress"
                styleClass.add("Button")
                text="Add adress information"
                action { buttonAddAdressClicked() }
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
                    id = "buttonAdd"
                    styleClass.add("Button")
                    text = "Add new Person"
                    action { buttonAddPersonClicked() }
                }
            }
        }
    }

}
