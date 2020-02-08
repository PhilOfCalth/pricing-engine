package phil.poc.pricingEngine.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import phil.poc.pricingEngine.repositories.ProductRepository
import phil.poc.pricingEngine.services.dtos.CounterOffer

interface ProductService {
    fun makeOffer(productId: Long, offerPrice: Int): CounterOffer
}

@Service
class ProductServiceImpl: ProductService {

    @Autowired
    private lateinit var productRepository: ProductRepository

    override fun makeOffer(productId: Long, offerPrice: Int) : CounterOffer {
        val productUnderOffer = productRepository.findById(productId).get()
        //Deal with NoSuchElementException

        val counterOffer = CounterOffer(productId)

        if(offerPrice < productUnderOffer.minPrice!!){
            counterOffer.counterOfferPrice = productUnderOffer.wantedPrice
            return counterOffer
        }
        else if(offerPrice > productUnderOffer.wantedPrice!!){
            counterOffer.accepted = true
            counterOffer.counterOfferPrice = offerPrice
            return counterOffer
        }
        else {
            calculateCounterOffer(counterOffer, offerPrice, productUnderOffer.wantedPrice!!)
            return counterOffer
        }
    }

    private fun calculateCounterOffer(counterOffer: CounterOffer, offerPrice: Int, wantedPrice:Int){
        val priceDiff = wantedPrice - offerPrice
        val counterOfferPrice = offerPrice + (priceDiff/2)
        counterOffer.accepted = false
        counterOffer.counterOfferPrice = counterOfferPrice
    }
}