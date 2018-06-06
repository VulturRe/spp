package com.ilshatgalimovf.spp.controller

import com.ilshatgalimovf.spp.view.BlankView
import com.ilshatgalimovf.spp.view.MainView
import tornadofx.*

class MainController : Controller() {

    private val blankView: BlankView by inject()
    private val mainView: MainView by inject()

    fun init() {
        showBlankView()
    }

    fun showBlankView() {
        mainView.replaceWith(blankView, sizeToScene = true, centerOnScreen = true)
    }

    fun showMainView() {
        blankView.replaceWith(mainView, sizeToScene = true, centerOnScreen = true)
    }
}
