package com.example.cat_alogue.model

import com.squareup.moshi.Json

data class Breed(
    val adaptability: Int,
    @Json(name = "affection_level")
    val affectionLevel: Int,
    @Json(name = "cat_friendly")
    val catFriendly: Int,
    @Json(name = "cfa_url")
    val cfaUrl: String? = null,
    @Json(name = "child_friendly")
    val childFriendly: Int,
    val description: String,
    @Json(name = "dog_friendly")
    val dogFriendly: Int,
    @Json(name = "energy_level")
    val energyLevel: Int,
    val experimental: Int,
    val grooming: Int,
    val hairless: Int,
    @Json(name = "health_issues")
    val healthIssues: Int,
    val hypoallergenic: Int,
    val id: String,
    val indoor: Int,
    val intelligence: Int,
    val lap: Int,
    @Json(name = "life_span")
    val lifeSpan: String,
    val name: String,
    val natural: Int,
    val origin: String,
    val rare: Int,
    @Json(name = "reference_image_id")
    val referenceImageId: String,
    val rex: Int,
    @Json(name = "shedding_level")
    val sheddingLevel: Int,
    @Json(name = "social_needs")
    val socialNeeds: Int,
    @Json(name = "stranger_friendly")
    val strangerFriendly: Int,
    val temperament: String,
    @Json(name = "vetstreet_url")
    val vetStreetUrl: String? = null,
    val vocalisation: Int,
    val weight: Weight,
)

data class Weight(
    val imperial: String,
    val metric: String
)