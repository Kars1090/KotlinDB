package view

import javafx.scene.Scene
import javafx.stage.Stage
import org.junit.Test
import org.mockito.Mockito
import org.testfx.api.FxToolkit
import org.testfx.framework.junit.ApplicationTest
import presenter.PeopleNewPresenter

class TestPeopleNewView : ApplicationTest() {
    private val presenter: PeopleNewPresenter = Mockito.mock(PeopleNewPresenter::class.java)

    override fun start(stage: Stage?) {
        val view = PeopleNewView()
        view.presenter = presenter
        stage?.scene = Scene(view.root, 800.0, 600.0)
        stage?.show()
    }

    @Throws(Exception::class)
    override fun stop() {
        FxToolkit.hideStage()
    }

    @Test
    fun testButtonAddAdress() {
        clickOn("#buttonAddAdress")
        Mockito.verify(presenter).addAddressClicked()
    }

    @Test
    fun testButtonBack() {
        clickOn("#buttonBack")
        Mockito.verify(presenter).backClicked()
    }

    @Test
    fun testButtonAddPerson() {
        clickOn("#buttonAddPerson")
        Mockito.verify(presenter).addPersonClicked("","","","","","","","")
    }
}