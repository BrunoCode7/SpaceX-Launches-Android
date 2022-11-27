package com.example.spacexlaunches.data.models.rockets

data class Rocket(
    val active: Boolean?,
    val boosters: Double?,
    val company: String?,
    val cost_per_launch: Double?,
    val country: String?,
    val description: String?,
    val diameter: Diameter?,
    val engines: Engines?,
    val first_flight: String?,
    val first_stage: FirstStage?,
    val flickr_images: List<String>?,
    val height: Height?,
    val id: String?,
    val landing_legs: LandingLegs?,
    val mass: Mass?,
    val name: String?,
    val payload_weights: List<PayloadWeight>?,
    val second_stage: SecondStage?,
    val stages: Double?,
    val success_rate_pct: Double?,
    val type: String?,
    val wikipedia: String?
)