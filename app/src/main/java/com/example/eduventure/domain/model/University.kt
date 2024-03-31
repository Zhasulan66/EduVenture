package com.example.eduventure.domain.model

import com.google.gson.annotations.SerializedName

data class University(
    val id: Int,
    val name: String,
    val logo: String,
    val country: String,
    val city: String,
    val tuition: String,
    @SerializedName("percentage_of_income")
    val percentageOfIncome: String,
    val max: String,
    @SerializedName("full_grant")
    val fullGrant: String,
    @SerializedName("deadline_1")
    val deadline1: String,
    @SerializedName("deadline_2")
    val deadline2: String,
    @SerializedName("TOEFL_IELTS")
    val toeflAndIelts: String,
    @SerializedName("SAT")
    val sat: String,
    val essay: String,
    @SerializedName("recommendation_letter")
    val recommendationLetter: String,
    @SerializedName("cost_of_request")
    val costOfRequest: String,
    @SerializedName("link_form")
    val linkForm: String,
    val website: String,
    @SerializedName("rating_by_country")
    val ratingByCountry: String,
    @SerializedName("world_ranking")
    val worldRanking: String,
    val professions: String,
    @SerializedName("additional_comments")
    val additionalComments: String,
)


/*{
    "id": 1,
    "name": "University of Hong Kong",
    "logo": null,
    "country": "Hong Kong",
    "city": "Kowloon",
    "tuition": "tuititon fee - $22,020 a year, accommodations - min $1,985 - $4415 a year, living expenses - about $6,450 a year",
    "percentage_of_income": "10%",
    "max": "Full-tuition is available  1.HKU Foundation Entrance Scholarships for Outstanding Students 2.HeForShe Impact Champion Scholarships",
    "full_grant": "TRUE",
    "deadline_1": "15 November",
    "deadline_2": "1 December",
    "TOEFL_IELTS": "0",
    "SAT": "1350",
    "essay": "Required Personal statement",
    "recommendation_letter": "Optional",
    "cost_of_request": "$40- HK$300",
    "link_form": "https://ug.hku.hk/hku-applicant/hku/index/login.xhtml",
    "website": "https://www.hku.hk",
    "rating_by_country": 1,
    "world_ranking": 22,
    "professions": "HKU's Dental Faculty is currently ranked #1 in the world. HKU's Faculty of Education is ranked #3 in the world. 11th for civil & structural engineering.",
    "additional_comments": "almost 100% of graduates of Hong Kong University were employed"
}*/
