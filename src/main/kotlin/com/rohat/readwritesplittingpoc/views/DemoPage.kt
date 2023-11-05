package com.rohat.readwritesplittingpoc.views

import com.github.mvysny.karibudsl.v10.KComposite
import com.github.mvysny.karibudsl.v10.button
import com.github.mvysny.karibudsl.v10.horizontalLayout
import com.github.mvysny.karibudsl.v10.onLeftClick
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.router.Route

@Route("")
class DemoPage : KComposite() {

    init {
        ui {
            // create the component UI here; maybe even attach very simple listeners here
            horizontalLayout {
                button("ok") {
                    onLeftClick { Notification.show("OKAY") }
                }
                button("cancel") {
                    onLeftClick { Notification.show("CAncelled") }
                }
            }
        }

        // perform any further initialization here
    }


}


