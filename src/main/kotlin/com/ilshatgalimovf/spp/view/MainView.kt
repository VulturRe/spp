package com.ilshatgalimovf.spp.view

import com.ilshatgalimovf.spp.controller.MainController
import tornadofx.*

class MainView : View("1DSPP") {

    private val mainController: MainController by inject()

    override val root = borderpane {
        prefWidth = 600.0
        prefHeight = 400.0

        top = menubar {
            menu("Файл") {
                item("Создать") {
                    action {
                        find<CreateProjectView>().openModal()
                    }
                }
                item("Открыть") {
                    action {
                        find<OpenProjectView>().openModal()
                    }
                }
                item("Закрыть проект") {
                    action {
                        mainController.showBlankView()
                    }
                }
                separator()
                item("Сохранить")
                separator()
                item("Выход").action {
                    close()
                }
            }
        }
    }
}
