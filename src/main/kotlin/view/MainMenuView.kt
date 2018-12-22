package view

import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import presenter.MainMenuPresenter
import tornadofx.*

class MainMenuView : View("Main Menu") {
    override val root = VBox()
    var presenter: MainMenuPresenter = MainMenuPresenter(this)

    init {
        importStylesheet("/MenuStyle.css")
        with(root) { id = "root"
            setupTopBox()
            setupBottomBox()
        }
    }

    private fun buttonManagePeoplePressed() {
        presenter.managePeoplePressed()
    }

    private fun buttonManageOccupationsPressed() {
        presenter.manageOccupationsPressed()
    }

    private fun buttonManageBusinessesPressed() {
        presenter.manageBusinessesPressed()
    }

    private fun setupTopBox(): HBox {
        return hbox {
            id = "TopBox"
            vbox {
                id = "TopLeftBox"
                label { text = "People" }
                button {
                    id = "buttonManagePeople"
                    styleClass.add("MenuButton")
                    text = "Manage"
                    action { buttonManagePeoplePressed() }
                }
            }
            vbox {
                id = "TopRightBox"
                label { text = "Occupations" }
                button {
                    id = "buttonManageOccupations"
                    styleClass.add("MenuButton")
                    text = "Manage"
                    action { buttonManageOccupationsPressed() }
                }
            }
        }
    }

    private fun setupBottomBox(): HBox {
        return hbox {
            id = "BottomBox"
            vbox {
                id = "BottomLeftBox"
                label { text = "Businesses" }
                button {
                    id = "buttonManageBusinesses"
                    styleClass.add("MenuButton")
                    text = "Manage"
                    action { buttonManageBusinessesPressed() }
                }
            }
            vbox {
                id = "BottomRightBox"
                label { text = "Label4" }
                button { text = "Button4" }
                isVisible = false
            }
        }
    }
}