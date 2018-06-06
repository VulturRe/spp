package com.ilshatgalimovf.spp.view

import tornadofx.*

class MainView : View("1DSPP") {

    override val root = borderpane {
        prefWidth = 500.0
        prefHeight = 500.0

        top = menubar {
            menu("Файл") {
                item("Создать")
            }
        }
    }
}
