package com.joshua.wayaknacatalog.data

object PackageRepository {
    fun getAll(): List<ConsultingPackage> = listOf(
        ConsultingPackage(
            id = "via",
            category = "Odoo Wayakna",
            name = "Via",
            summary = "Una entrada rapida para empresas que necesitan ajustes puntuales y una configuracion ligera en Odoo.",
            price = "$8,200 MXN sin IVA",
            priceAmount = 8200.0,
            billingType = BillingType.ONE_TIME,
            idealFor = "Equipos que quieren empezar con control, soporte y configuracion sin entrar aun a personalizaciones profundas.",
            timeline = "Paquete de 10 horas",
            valueProposition = "24% mas economico que Odoo",
            details = listOf(
                "Servicio de soporte por telefono y correo electronico",
                "Capacitacion inicial",
                "Configuracion base",
                "Atencion enfocada a necesidades inmediatas"
            ),
            outcomes = listOf(
                "Arranque mas simple en Odoo",
                "Respuesta rapida a necesidades operativas",
                "Base para una siguiente etapa de adopcion"
            )
        ),
        ConsultingPackage(
            id = "rastro",
            category = "Odoo Wayakna",
            name = "Rastro",
            summary = "Pensado para organizaciones que requieren una configuracion relativamente rapida, pero con mas capacidad de acompanamiento.",
            price = "$18,375 MXN sin IVA",
            priceAmount = 18375.0,
            billingType = BillingType.ONE_TIME,
            idealFor = "Empresas que ya ven valor en Odoo y buscan mas horas para estabilizar y ordenar su operacion.",
            timeline = "Paquete de 25 horas",
            valueProposition = "20% mas economico que Odoo",
            details = listOf(
                "Soporte por telefono y correo electronico",
                "Capacitacion",
                "Configuracion",
                "Acompanamiento con mayor alcance operativo"
            ),
            outcomes = listOf(
                "Mas horas para consolidar procesos",
                "Configuracion con mayor seguimiento",
                "Mejor adopcion del sistema en el equipo"
            )
        ),
        ConsultingPackage(
            id = "sendero",
            category = "Odoo Wayakna",
            name = "Sendero",
            summary = "Un paquete de mayor profundidad para empresas que ya quieren una gestion mas seria de procesos y mejoras sobre Odoo.",
            price = "$36,500 MXN sin IVA",
            priceAmount = 36500.0,
            billingType = BillingType.ONE_TIME,
            idealFor = "Equipos que necesitan mas acompanamiento, configuracion, capacitacion y margen para personalizaciones funcionales.",
            timeline = "Paquete de 50 horas",
            valueProposition = "19% mas economico que Odoo",
            details = listOf(
                "Servicio de soporte por telefono y correo electronico",
                "Capacitacion",
                "Configuracion",
                "Asistencia con importacion de datos",
                "Personalizaciones funcionales"
            ),
            outcomes = listOf(
                "Mayor control sobre la operacion",
                "Mas espacio para mejoras reales en el sistema",
                "Una ruta clara para siguientes fases del proyecto"
            )
        ),
        ConsultingPackage(
            id = "correobox_5g",
            category = "CorreoBox",
            name = "CorreoBox Flex Plan 5G",
            summary = "Una solucion practica para empresas que necesitan correo corporativo estable, seguro y facil de administrar.",
            price = "$249.00 / Mes",
            priceAmount = 249.0,
            billingType = BillingType.MONTHLY,
            idealFor = "Negocios que requieren una base profesional de correo con buen equilibrio entre costo y capacidad.",
            timeline = "5 GB compartidos",
            valueProposition = "Hasta 500 correos salientes por dia",
            details = listOf(
                "Buzones ilimitados segun el espacio disponible",
                "Respaldo hasta 1 ano atras",
                "3 restauraciones por ano",
                "DNS incluido",
                "Conector Odoo incluido"
            ),
            outcomes = listOf(
                "Correo estable para la operacion diaria",
                "Respaldo y proteccion basica",
                "Infraestructura lista para crecer"
            )
        ),
        ConsultingPackage(
            id = "correobox_25g",
            category = "CorreoBox",
            name = "CorreoBox Flex Plan 25G",
            summary = "Un plan de mayor capacidad para organizaciones que dependen del correo como herramienta clave de comunicacion.",
            price = "$999.00 / Mes",
            priceAmount = 999.0,
            billingType = BillingType.MONTHLY,
            idealFor = "Empresas que necesitan mas almacenamiento, mas envios diarios y soporte tecnico mas completo.",
            timeline = "25 GB compartidos",
            valueProposition = "Hasta 1000 correos salientes al dia",
            details = listOf(
                "Servicio compartido administrado",
                "DNS incluido",
                "Servidor SMTP respaldado",
                "Conector Odoo incluido",
                "Soporte tecnico y dominio"
            ),
            outcomes = listOf(
                "Mejor capacidad para equipos en crecimiento",
                "Infraestructura mas robusta",
                "Dominio y correo mejor integrados"
            )
        ),
        ConsultingPackage(
            id = "correobox_100g",
            category = "CorreoBox",
            name = "CorreoBox Flex Plan 100G",
            summary = "Infraestructura de correo para empresas con alto flujo de comunicacion y necesidad de mayor capacidad operativa.",
            price = "$2,999.00 / Mes",
            priceAmount = 2999.0,
            billingType = BillingType.MONTHLY,
            idealFor = "Equipos con multiples areas operativas que necesitan estabilidad, control y espacio de almacenamiento amplio.",
            timeline = "100 GB compartidos",
            valueProposition = "Respaldo y capacidad para operacion intensiva",
            details = listOf(
                "Hasta 200 correos salientes al dia",
                "Buzones ilimitados segun el espacio disponible",
                "Respaldo de correos hasta 1 ano atras",
                "DNS incluido",
                "Servidor SMTP respaldado"
            ),
            outcomes = listOf(
                "Mayor capacidad para operacion continua",
                "Proteccion y respaldo extendidos",
                "Infraestructura estable para alto volumen"
            )
        )
    )

    fun findById(id: String): ConsultingPackage? =
        getAll().firstOrNull { it.id == id }
}
