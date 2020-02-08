package phil.poc.pricingEngine.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Product(
    @Id
    var id: Long,
    @Column(nullable = false)
    var name: String,
    var description: String?,
    var rrp: Int?,
    @Column(name="wanted_price", nullable = false)
    var wantedPrice: Int?,
    @Column(name="min_price", nullable = false)
    var minPrice: Int?
){
}
