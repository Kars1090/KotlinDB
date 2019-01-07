package presenter

import org.junit.Ignore
import org.junit.Test
import view.MainMenuView

import org.mockito.Mockito

class TestMainMenuPresenter {
    private val view = Mockito.mock(MainMenuView::class.java)
    private val presenter = MainMenuPresenter(view)

    @Ignore
    @Test
    fun testManagePeoplePressed() {
        presenter.managePeoplePressed()
    }

}