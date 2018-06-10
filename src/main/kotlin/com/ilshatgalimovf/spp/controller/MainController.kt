package com.ilshatgalimovf.spp.controller

import com.ilshatgalimovf.spp.domain.Blank
import com.ilshatgalimovf.spp.domain.Project
import com.ilshatgalimovf.spp.domain.Sheet
import com.ilshatgalimovf.spp.service.BlankService
import com.ilshatgalimovf.spp.service.ProjectService
import com.ilshatgalimovf.spp.service.SheetService
import com.ilshatgalimovf.spp.view.BlankView
import com.ilshatgalimovf.spp.view.MainView
import org.springframework.transaction.annotation.Transactional
import tornadofx.*

internal class MainController : Controller() {

    private val blankView: BlankView by inject()
    private val mainView: MainView by inject()
    private val projectService: ProjectService by di()
    private val sheetService: SheetService by di()
    private val blankService: BlankService by di()
    var currentProject: Project = Project()

    fun init() {
        showBlankView()
    }

    fun showBlankView() {
        mainView.replaceWith(blankView, sizeToScene = true, centerOnScreen = true)
    }

    fun showMainView() {
        blankView.replaceWith(mainView, sizeToScene = true, centerOnScreen = true)
    }

    fun updateCurrentProject(project: Project) {
        currentProject = project
        mainView.update()
    }

    fun createProject(name: String) {
        val project = Project(0, name)
        currentProject = projectService.save(project)
        showMainView()
    }

    fun updateSheet(sheet: Sheet): Sheet {
        val savedSheet = sheetService.save(sheet)
        currentProject.sheet = sheet
        currentProject = projectService.update(currentProject)
        return savedSheet
    }

    fun updateBlank(blank: Blank): Blank {
        val blankId = blank.id
        val savedBlank = blankService.save(blank)
        if (blankId == null) {
            if (currentProject.blank == null) {
                currentProject.blank = arrayListOf(savedBlank)
            } else {
                currentProject.blank?.add(savedBlank)
            }
        } else {
            currentProject.blank?.removeIf { it.id == blank.id }
            currentProject.blank?.add(savedBlank)
        }

        currentProject = projectService.update(currentProject)
        return savedBlank
    }
}
