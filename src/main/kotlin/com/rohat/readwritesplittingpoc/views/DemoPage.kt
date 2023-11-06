package com.rohat.readwritesplittingpoc.views

import com.github.mvysny.karibudsl.v10.*
import com.rohat.readwritesplittingpoc.orders.OrderController
import com.rohat.readwritesplittingpoc.products.ProductController
import com.rohat.readwritesplittingpoc.users.UserController
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.router.Route
import com.vaadin.flow.spring.annotation.SpringComponent

@Route("demo")
@SpringComponent
class DemoPage(
    private val orderController: OrderController,
    private val productController: ProductController,
    private val userController: UserController
) : KComposite() {


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


