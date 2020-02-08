package phil.poc.pricingEngine.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController {

    @RequestMapping("/")
    fun index(): String {
        return "Greetings from Phil Larkin!"
    }
}
