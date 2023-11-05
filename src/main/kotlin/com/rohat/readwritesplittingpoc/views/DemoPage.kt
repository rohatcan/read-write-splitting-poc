package com.rohat.readwritesplittingpoc.views

import com.github.mvysny.karibudsl.v10.*
import com.rohat.readwritesplittingpoc.orders.OrderController
import com.rohat.readwritesplittingpoc.products.ProductController
import com.rohat.readwritesplittingpoc.users.UserController
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.router.Route
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Route("demo")
@Component
class DemoPage : KComposite() {

    @Autowired

    private lateinit var orderController: OrderController

    @Autowired
    private lateinit var productController: ProductController

    @Autowired
    private lateinit var userController: UserController

    init {
        ui {
            // create the component UI here; maybe even attach very simple listeners here
            horizontalLayout {
                verticalLayout {
                    h1("ORDER CONTROLLER")
                    button("Increment basket size") {
                        onLeftClick { orderController.incrementBasketSize() }
                    }
                    button("Get order") {
                        onLeftClick { Notification.show(orderController.getOrder(1L).toString()) }
                    }
                }

                verticalLayout {
                    h1("PRODUCT CONTROLLER")
                    button("Increment price") {
                        onLeftClick { productController.incrementPrice() }
                    }
                    button("Get product") {
                        onLeftClick { Notification.show(productController.getProduct(1L).toString()) }
                    }
                }


                verticalLayout {
                    h1("USER CONTROLLER")
                    button("Increment login count") {
                        onLeftClick { userController.incrementLoginCount() }
                    }
                    button("Get user") {
                        onLeftClick { Notification.show(userController.getUser(1L).toString()) }
                    }
                }


            }
        }
    }

}


