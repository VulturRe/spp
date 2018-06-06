package com.ilshatgalimovf.spp

import com.ilshatgalimovf.spp.controller.MainController
import com.ilshatgalimovf.spp.view.MainView
import javafx.application.Application
import javafx.stage.Stage
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ConfigurableApplicationContext
import tornadofx.*
import kotlin.reflect.KClass

@SpringBootApplication
class SppApplication : App(MainView::class) {

    lateinit var applicationContext: ConfigurableApplicationContext
    private val mainController: MainController by inject()

    override fun init() {
        super.init()
        applicationContext = SpringApplication.run(SppApplication::class.java)
        FX.dicontainer = object : DIContainer {
            override fun <T : Any> getInstance(type: KClass<T>): T = applicationContext.getBean(type.java)
            override fun <T : Any> getInstance(type: KClass<T>, name: String): T = applicationContext.getBean(type.java, name)
        }
    }

    override fun start(stage: Stage) {
        super.start(stage)
        mainController.init()
    }
}

fun main(args: Array<String>) {
    Application.launch(SppApplication::class.java)
}
