package com.subhajeet.medicaladmin.view.nav

import kotlinx.serialization.Serializable

sealed class Routes {

    @Serializable
    object AllUserScreen


    @Serializable
    data class UserDetailsScreen(
        val userId:String
    )
}