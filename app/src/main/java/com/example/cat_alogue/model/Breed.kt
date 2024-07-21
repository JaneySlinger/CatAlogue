package com.example.cat_alogue.model

import com.squareup.moshi.Json

data class Breed(
    @Json(name = "cat_friendly") val catFriendly: Int? = null,
    @Json(name = "child_friendly") val childFriendly: Int? = null,
    val description: String? = null,
    @Json(name = "dog_friendly") val dogFriendly: Int? = null,
    val id: String? = null,
    @Json(name = "life_span") val lifeSpan: String? = null,
    val name: String? = null,
    val origin: String? = null,
    @Json(name = "reference_image_id") val referenceImageId: String? = null,
    @Json(name = "stranger_friendly") val strangerFriendly: Int? = null,
    val temperament: String? = null,
)