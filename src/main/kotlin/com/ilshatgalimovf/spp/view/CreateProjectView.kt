package com.ilshatgalimovf.spp.view

import com.ilshatgalimovf.spp.controller.MainController
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import tornadofx.*

class CreateProjectView : View("Create Project - 1DSPP") {

    private val mainController: MainController by inject()

    private val model = object : ViewModel() {
        val name = bind { SimpleStringProperty() }
    }

    override val root = anchorpane {
        prefWidth = 400.0
        prefHeight = 150.0

        form {
            hbox(20) {
                fieldset("Новый проект", labelPosition = Orientation.VERTICAL) {
                    vbox {
                        field ("Имя проекта:", Orientation.VERTICAL) {
                            textfield(model.name) {
                                prefWidth = 375.0
                                required()
                                whenDocked { requestFocus() }
                            }
                        }
                    }
                }
            }

            buttonbar {
                button("Готово") {
                    isDefaultButton = true

                    action {
                        mainController.showMainView()
                        close()
                    }
                }
            }
        }
    }

    override fun onDock() {
        model.validate(decorateErrors = false)
    }
}
