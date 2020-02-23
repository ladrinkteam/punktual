package com.example.punktual.enums

enum class LocationType {
    CAMPUS_NUMERIQUE {
        override fun toString(): String {
            return "CAMPUS_NUMERIQUE"
        }
    },
    PAPETERIE {
        override fun toString(): String {
            return "PAPETERIE"
        }
    }
}