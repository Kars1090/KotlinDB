package view

import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import tornadofx.*
import presenter.PeopleMenuPresenter

class PeopleMenuView: View() {
    override val root = VBox()
    var presenter = PeopleMenuPresenter(this)

    init {
        importStylesheet("/MenuStyle.css")
        with (root) { id="root"
            setupTopBox()
            setupBottomBox()
        }
    }

    private fun buttonAddPersonPressed() {
        presenter.addPersonPressed()
    }

    private fun buttonFindPersonPressed() {
        presenter.findPersonPressed()
    }

    private fun setupTopBox() {
        hbox {
            id = "TopBox"
            vbox {
                id = "TopLeftBox"
                button {
                    id = "buttonAddPerson"
                    styleClass.add("MenuButton")
                    text = "Add new Person"
                    action { buttonAddPersonPressed() }
                }
            }
            vbox {
                id = "TopRightBox"
                button {
                    id = "buttonFindPerson"
                    styleClass.add("MenuButton")
                    text = "Find existing person"
                    action { buttonFindPersonPressed() }
                }
            }
        }
    }

    private fun setupBottomBox(): HBox {
        return hbox {
            id = "BottomBox"
            vbox {
                id = "BottomLeftBox"
            }
            vbox {
                id = "BottomRightBox"
                isVisible = false
            }
        }
    }
}