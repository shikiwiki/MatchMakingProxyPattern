package com.example.matchmakingproxypattern

class PersonBeanImpl : PersonBean {

    private var rating: Int = 0
    private var ratingCount = 0
    override lateinit var name: String
    override lateinit var gender: String
    override lateinit var interests: String

    override var hotOrNotRating: Int
        get() = if (ratingCount == 0) 0 else (rating/ratingCount)
        set(value) {
            this.rating += value
            ratingCount++
        }
}