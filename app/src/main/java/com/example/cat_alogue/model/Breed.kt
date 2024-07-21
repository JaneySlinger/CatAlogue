package com.example.cat_alogue.model

import com.squareup.moshi.Json

data class Breed(
    @Json(name = "affection_level")
    val affectionLevel: Int? = null,
    @Json(name = "cat_friendly")
    val catFriendly: Int? = null,
    @Json(name = "cfa_url")
    val cfaUrl: String? = null,
    @Json(name = "child_friendly")
    val childFriendly: Int? = null,
    val description: String? = null,
    @Json(name = "dog_friendly")
    val dogFriendly: Int? = null,
    @Json(name = "energy_level")
    val energyLevel: Int? = null,
    val experimental: Int? = null,
    val grooming: Int? = null,
    val hairless: Int? = null,
    @Json(name = "health_issues")
    val healthIssues: Int? = null,
    val hypoallergenic: Int? = null,
    val id: String? = null,
    val intelligence: Int? = null,
    val lap: Int? = null,
    @Json(name = "life_span")
    val lifeSpan: String? = null,
    val name: String? = null,
    val natural: Int? = null,
    val origin: String? = null,
    val rare: Int? = null,
    @Json(name = "reference_image_id")
    val referenceImageId: String? = null,
    val rex: Int? = null,
    @Json(name = "shedding_level")
    val sheddingLevel: Int? = null,
    @Json(name = "social_needs")
    val socialNeeds: Int? = null,
    @Json(name = "stranger_friendly")
    val strangerFriendly: Int? = null,
    val temperament: String? = null,
    @Json(name = "vetstreet_url")
    val vetStreetUrl: String? = null,
    val vocalisation: Int? = null,
)