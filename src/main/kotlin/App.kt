import javafx.stage.Stage
import tornadofx.*
import view.MainMenuView

class Application : App() {
    override val primaryView = MainMenuView::class

    override fun start(stage: Stage) {
        stage.isResizable = true;
        super.start(stage)
    }
}