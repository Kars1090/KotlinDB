package view

import javafx.scene.Scene
import javafx.stage.Stage
import org.junit.Test
import org.mockito.Mockito
import org.testfx.api.FxToolkit
import org.testfx.framework.junit.ApplicationTest
import presenter.PeopleMenuPresenter

class TestPeopleMenuView : ApplicationTest() {
    private val presenter: PeopleMenuPresenter = Mockito.mock(PeopleMenuPresenter::class.java)

    override fun start(stage: Stage?) {
        val view = PeopleMenuView()
        view.presenter = presenter
        stage?.scene = Scene(view.root, 800.0, 600.0)
        stage?.show()
    }

    @Throws(Exception::class)
    override fun stop() {
        FxToolkit.hideStage()
    }

    @Test
    fun testButtonAddPerson() {
        clickOn("#buttonAddPerson")
        Mockito.verify(presenter).addPersonPressed()
    }

    @Test
    fun testButtonFindPerson() {
        clickOn("#buttonFindPerson")
        Mockito.verify(presenter).findPersonPressed()
    }
}