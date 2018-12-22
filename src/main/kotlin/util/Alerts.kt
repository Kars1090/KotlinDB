package util

import javafx.scene.control.Alert

class Alerts private constructor() {
    private object Holder { val INSTANCE = Alerts() }

    companion object {
        val instance: Alerts by lazy { Holder.INSTANCE }
    }

    fun showInformation(title: String, header: String, message: String) {
        val alert = Alert(Alert.AlertType.INFORMATION)
        alert.title = title
        alert.headerText = header
        alert.contentText = message
        alert.show()
    }

    fun showWarning(title: String, header: String, message: String) {
        val alert = Alert(Alert.AlertType.WARNING)
        alert.title = title
        alert.headerText = header
        alert.contentText = message
        alert.show()
    }

    fun showError(title: String, header: String, message: String) {
        val alert = Alert(Alert.AlertType.ERROR)
        alert.title = title
        alert.headerText = header
        alert.contentText = message
        alert.show()
    }
}