package phil.poc.pricingEngine.projections

import org.springframework.beans.factory.annotation.Value
import org.springframework.data.rest.core.config.Projection
import phil.poc.pricingEngine.models.Product

@Projection(name = "productLite", types = [Product::class])
interface CustomerProduct {
    @get:Value("#{target.id}")
    val id: Long

    val name: String
    val description: String
    val rrp: Int

}