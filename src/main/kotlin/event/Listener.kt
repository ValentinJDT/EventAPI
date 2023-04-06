package fr.valentin.api.event

open class Listener {

    fun execute(obj: Any, event: Event): Boolean {
        val functions = this::class.java.declaredMethods

        for(function in functions.filter { it.isAnnotationPresent(EventHandler::class.java) }) {

            for(parameter in function.parameters.filter { it.type === event::class.java}) {
                if(parameter.type === event::class.java) {
                    function.invoke(obj, event)
                    if(event is Cancellable && event.cancel)
                        return false
                }
            }
        }

        return true
    }

}