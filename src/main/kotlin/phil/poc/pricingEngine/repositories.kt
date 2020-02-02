package phil.poc.pricingEngine

import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import phil.poc.pricingEngine.models.Product

@RepositoryRestResource(excerptProjection = Product::class)
interface ProductRepository: CrudRepository<Product, Long> {

}
