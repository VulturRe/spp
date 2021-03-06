package com.ilshatgalimovf.spp.view

import com.ilshatgalimovf.spp.controller.MainController
import com.ilshatgalimovf.spp.domain.Blank
import com.ilshatgalimovf.spp.domain.ListNode
import com.ilshatgalimovf.spp.domain.ListNodeType
import com.ilshatgalimovf.spp.domain.Sheet
import com.ilshatgalimovf.spp.util.Combinations
import javafx.beans.property.SimpleIntegerProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.geometry.Orientation
import tornadofx.*

class MainView : View("1DSPP") {

    private val mainController: MainController by inject()
    private var selectedBlankId: Long? = null
    private var listViewObservable: ObservableList<ListNode> = FXCollections.observableArrayList()

    private val model = object : ViewModel() {
        val sheetLength = bind { SimpleIntegerProperty() }
        val sheetWidth = bind { SimpleIntegerProperty() }
        val blankLength = bind { SimpleIntegerProperty() }
        val blankCount = bind { SimpleIntegerProperty() }
    }

    override fun onDock() {
        title = mainController.currentProject.name + " - 1DSPP"
        update()
    }

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
                item("Выход").action {
                    close()
                }
            }
            menu("Проект") {
                item("Раскрой").action {
                    Combinations(3, 5)
                }
            }
        }

        left = listview(listViewObservable) {
            prefWidth = 200.0
            prefHeight = 400.0
            cellFormat {
                val t = it.count ?: it.width
                text = it.name + ": " + it.length + ", " + t
            }
            setOnMouseClicked {
                updateViewBySelectedItem(selectedItem)
            }
        }

        center = anchorpane {
            prefWidth = 400.0
            prefHeight = 400.0

            form {
                fieldset("Лист", labelPosition = Orientation.VERTICAL) {
                    vbox {
                        field("Длина листа", Orientation.VERTICAL) {
                            textfield(model.sheetLength) {
                                prefWidth = 375.0
                            }
                        }
                        field("Ширина листа", Orientation.VERTICAL) {
                            textfield(model.sheetWidth) {
                                prefWidth = 375.0
                            }
                        }
                        buttonbar {
                            button("Применить").action {
                                saveSheet()
                            }
                        }
                    }
                }
                separator()
                fieldset("Деталь", labelPosition = Orientation.VERTICAL) {
                    vbox {
                        field("Длина заготовки", Orientation.VERTICAL) {
                            textfield(model.blankLength) {
                                prefWidth = 375.0
                            }
                        }
                        field("Количество", Orientation.VERTICAL) {
                            textfield(model.blankCount) {
                                prefWidth = 375.0
                            }
                        }
                        buttonbar {
                            button("Применить").action {
                                addBlank()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun sheetToNode(sheet: Sheet): ListNode {
        return ListNode(
                sheet.id!!,
                "Sheet",
                sheet.length,
                sheet.width,
                null,
                ListNodeType.SHEET
        )
    }

    private fun blankToNode(blank: Blank): ListNode {
        return ListNode(
                blank.id!!,
                "Blank",
                blank.length,
                null,
                blank.count,
                ListNodeType.BLANK
        )
    }

    private fun blankListToNodeList(bl: List<Blank>): List<ListNode> {
        val nodes = arrayListOf<ListNode>()
        for (item in bl) {
            nodes.add(blankToNode(item))
        }
        return nodes
    }

    fun update() {
        clear()
        if (mainController.currentProject.sheet != null) {
            val sheet = mainController.currentProject.sheet!!
            val node = sheetToNode(sheet)
            updateSheetModel(sheet)
            listViewObservableUpdate(node)
        }
        if (mainController.currentProject.blank != null && mainController.currentProject.blank?.size!! > 0) {
            val nodes = blankListToNodeList(mainController.currentProject.blank!!)
            listViewObservableAddAll(nodes)
        }
    }

    private fun updateViewBySelectedItem(selectedItem: ListNode?) {
        if (selectedItem == null)
            return

        selectedBlankId = if (selectedItem.type == ListNodeType.BLANK) {
            selectedItem.id
        } else {
            null
        }

        if (selectedItem.type == ListNodeType.BLANK) {
            model.blankCount.value = selectedItem.count
            model.blankLength.value = selectedItem.length
        } else {
            model.sheetLength.value = selectedItem.length
            model.sheetWidth.value = selectedItem.width
        }
    }

    private fun updateSheetModel(sheet: Sheet) {
        model.sheetLength.value = sheet.length
        model.sheetWidth.value = sheet.width
    }

    private fun saveSheet() {
        val sheet = Sheet(
                mainController.currentProject.sheet?.id,
                model.sheetLength.value.toInt(),
                model.sheetWidth.value.toInt()
        )
        val savedSheet = mainController.updateSheet(sheet)
        listViewObservableUpdate(sheetToNode(savedSheet))
    }

    private fun addBlank() {
        val blank = Blank(
                selectedBlankId,
                model.blankLength.value.toInt(),
                model.blankCount.value.toInt(),
                mainController.currentProject.id!!
        )
        val savedBlank = mainController.updateBlank(blank)
        listViewObservableUpdate(blankToNode(savedBlank))
    }

    private fun listViewObservableUpdate(node: ListNode) {
        if (node.type == ListNodeType.SHEET && listViewObservable.size > 0) {
            listViewObservable[0] = node
        } else {
            listViewObservable.removeIf { it.type == node.type && it.id == node.id }
            listViewObservable.add(node)
        }
    }

    private fun listViewObservableAddAll(nodes: List<ListNode>) {
        listViewObservable.addAll(nodes)
    }

    fun listViewObservableClear() {
        listViewObservable.removeAll()
    }

    private fun clear() {
        listViewObservable.clear()
        model.blankCount.value = 0
        model.blankLength.value = 0
        model.sheetLength.value = 0
        model.sheetWidth.value = 0
    }
}
