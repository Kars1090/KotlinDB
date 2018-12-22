package view

import javafx.scene.Scene
import javafx.stage.Stage
import org.junit.Test
import org.mockito.Mockito
import org.testfx.api.FxToolkit
import org.testfx.framework.junit.ApplicationTest
import presenter.MainMenuPresenter

class TestMainMenuView : ApplicationTest() {
    private val presenter: MainMenuPresenter = Mockito.mock(MainMenuPresenter::class.java)

    override fun start(stage: Stage?) {
        val view = MainMenuView()
        view.presenter = presenter
        stage?.scene = Scene(view.root, 800.0, 600.0)
        stage?.show()
    }

    @Throws(Exception::class)
    override fun stop() {
        FxToolkit.hideStage()
    }

    @Test
    fun testButtonManagePeople() {
        clickOn("#buttonManagePeople")
        Mockito.verify(presenter).managePeoplePressed()
    }

    @Test
    fun testButtonManageOccupations() {
        clickOn("#buttonManageOccupations")
        Mockito.verify(presenter).manageOccupationsPressed()
    }

    @Test
    fun testButtonManageBusinesses() {
        clickOn("#buttonManageBusinesses")
        Mockito.verify(presenter).manageBusinessesPressed()
    }
}