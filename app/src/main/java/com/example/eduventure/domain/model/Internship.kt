package com.example.eduventure.domain.model

import com.google.gson.annotations.SerializedName

data class Internship(
    val id : Int,
    val title: String,
    @SerializedName("organization_logo")
    val organizationLogo: String?,
    val organization: String,
    val country: String,
    val location: String,
    val description: String,
    val duration: String?,
    val benefits: String?,
    val eligibility: String?,
    @SerializedName("required_documents")
    val requiredDocuments: String?,
    @SerializedName("how_to_apply")
    val howToApply: String?,
    val deadline: String?,
    val profession: Int?
)

/*{
    "id": 1,
    "title": "Software Engineer Intern",
    "organization_logo": null,
    "organization": "Some Company",
    "country": "Germany",
    "city": "Berlin",
    "description": "yes",
    "paid_internship": true,
    "salary": "100 000",
    "deadline": "05.04.2024",
    "link_to_apply": "some link",
    "profession": 1
}*/
