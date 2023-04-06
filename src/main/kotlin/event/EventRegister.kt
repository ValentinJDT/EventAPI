package fr.valentin.api.event

class EventRegister private constructor() {

    private val listeners = mutableListOf<Listener>();

    companion object {
        private var instance: EventRegister? = null;

        fun getInstance(): EventRegister {
            synchronized(EventRegister::class.java) {
                if(instance == null) {
                    instance = EventRegister()
                }
            }

            return instance!!
        }
    }

    fun runEvent(event: Event): Boolean = if(listeners.size == 0) true else listeners.all { it.execute(it, event) }

    fun registerListener(listener: Listener) {
        listeners += listener
    }

    fun removeListener(listener: Listener) {
        listeners -= listener
    }

}