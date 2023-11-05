package com.rohat.readwritesplittingpoc

import com.vaadin.flow.component.page.AppShellConfigurator
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReadWriteSplittingPocApplication : AppShellConfigurator

fun main(args: Array<String>) {
   runApplication<ReadWriteSplittingPocApplication>(*args)
}
