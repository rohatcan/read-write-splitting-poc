package com.rohat.readwritesplittingpoc

import com.vaadin.flow.component.page.AppShellConfigurator
import com.vaadin.flow.theme.Theme
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@Theme(variant = "dark")
@SpringBootApplication
class ReadWriteSplittingPocApplication : AppShellConfigurator

fun main(args: Array<String>) {
   runApplication<ReadWriteSplittingPocApplication>(*args)
}
