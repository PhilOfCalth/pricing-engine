package phil.poc.pricingEngine.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import phil.poc.pricingEngine.services.ProductService
import phil.poc.pricingEngine.services.dtos.CounterOffer

@RestController
@RequestMapping(value = ["/products"])
class ProductController {

    @Autowired
    private lateinit var productService: ProductService

    @RequestMapping("makeOffer")
    fun makeOffer(@RequestParam productId: Long, @RequestParam offerPrice: Int): CounterOffer {
        return productService.makeOffer(productId, offerPrice)
    }
}
