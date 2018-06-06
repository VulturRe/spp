package com.ilshatgalimovf.spp.controller

import com.ilshatgalimovf.spp.domain.Project
import com.ilshatgalimovf.spp.service.ProjectService
import com.ilshatgalimovf.spp.view.BlankView
import com.ilshatgalimovf.spp.view.MainView
import tornadofx.*

internal class MainController : Controller() {

    private val blankView: BlankView by inject()
    private val mainView: MainView by inject()
    private val projectService: ProjectService by di()
    var currentProject: Project? = null

    fun init() {
        showBlankView()
    }

    fun showBlankView() {
        mainView.replaceWith(blankView, sizeToScene = true, centerOnScreen = true)
    }

    fun showMainView() {
        blankView.replaceWith(mainView, sizeToScene = true, centerOnScreen = true)
    }

    fun createProject(name: String) {
        val project = Project(null, name)
        currentProject = projectService.create(project)
    }
}
