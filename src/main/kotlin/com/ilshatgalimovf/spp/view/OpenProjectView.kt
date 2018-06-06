package com.ilshatgalimovf.spp.view

import com.ilshatgalimovf.spp.controller.MainController
import com.ilshatgalimovf.spp.domain.Project
import com.ilshatgalimovf.spp.service.ProjectService
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.geometry.Orientation
import tornadofx.*

class OpenProjectView : View("Open Project - 1DSPP") {

    private val mainController: MainController by inject()
    private val projectService: ProjectService by di()
    private var projectList: ArrayList<Project> = ArrayList()
    private var projectListAsObservable: ObservableList<Project> = FXCollections.observableArrayList()

    private val model = object : ViewModel() {
        val name = bind { SimpleObjectProperty<Project>() }
    }

    override fun onDock() {
        super.onDock()
        projectList = projectService.findAll() as ArrayList<Project>
        projectListAsObservable.addAll(projectList)
    }

    override val root = anchorpane {
        prefWidth = 400.0
        prefHeight = 150.0

        form {
            hbox(20) {
                fieldset("Новый проект", labelPosition = Orientation.VERTICAL) {
                    vbox {
                        field("Имя проекта:", Orientation.VERTICAL) {
                            combobox(model.name, projectListAsObservable) {
                                prefWidth = 375.0
                                cellFormat { text = it.name }
                                valueProperty().onChange {
                                    mainController.currentProject = it!!
                                }
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
}
