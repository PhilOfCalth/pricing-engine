package phil.poc.pricingEngine.config

import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener
import org.springframework.validation.Validator
import java.util.Map

@Configuration
class ValidatorEventRegister: InitializingBean {

    @Autowired
    lateinit var validatingRepositoryEventListener: ValidatingRepositoryEventListener

    @Autowired
    private lateinit var validators: Map<String?, Validator>

    override fun afterPropertiesSet() {
        val events = listOf("beforeCreate", "afterCreate", "beforeSave", "afterSave", "beforeLinkSave",
                            "afterLinkSave", "beforeDelete", "afterDelete")

        for (entry in validators.entrySet()){
            events.stream()
                .filter({ p: String -> entry.key!!.startsWith(p) } )
                .findFirst()
                .ifPresent({ p: String -> validatingRepositoryEventListener.addValidator(p, entry.value) });
        }
    }
}