package io.github.addoncommunity.galactifun.scripting.dsl

import io.github.addoncommunity.galactifun.api.objects.properties.Orbit
import io.github.addoncommunity.galactifun.scripting.PlanetDsl
import io.github.addoncommunity.galactifun.util.RequiredProperty
import io.github.addoncommunity.galactifun.util.units.Distance
import kotlin.time.Duration

@PlanetDsl
class OrbitBuilder {

    var distance: Distance by RequiredProperty()
    var yearLength: Duration by RequiredProperty()

    fun build(): Orbit {
        return Orbit(distance, yearLength)
    }
}

inline fun orbit(block: OrbitBuilder.() -> Unit): Orbit {
    return OrbitBuilder().apply(block).build()
}

inline fun PlanetBuilder.orbit(block: OrbitBuilder.() -> Unit) {
    orbit = OrbitBuilder().apply(block).build()
}