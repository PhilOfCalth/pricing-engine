package phil.poc.pricingEngine.services.dtos

data class CounterOffer(val productId: Long){
    var accepted = false
    var counterOfferPrice: Int? = null
}
