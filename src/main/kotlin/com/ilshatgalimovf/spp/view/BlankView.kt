package com.ilshatgalimovf.spp.view

import tornadofx.*

class BlankView : View("1DSPP") {

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
                item("Закрыть проект").isDisable = true
                separator()
                item("Выход").action {
                    close()
                }
            }
            menu("Проект") {
                item("Раскрой").isDisable = true
            }
        }
    }
}
