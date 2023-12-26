package com.example.matchmakingproxypattern

import java.lang.reflect.Proxy

fun main() {
    //initialize database
    drive()
}

fun drive() {
    val jack = PersonBeanImpl()
    val ownerProxy = getOwnerProxy(jack)
    println("Name is ${ownerProxy.name}")
    ownerProxy.interests = "bowling, skiing"
    println("Interests set from owner proxy")
    try {
        ownerProxy.hotOrNotRating = 10
    } catch (e: Exception) {
        println("can not set rating from owner proxy")
    }
    println("Rating is ${ownerProxy.hotOrNotRating}")

    val nonOwnerProxy = getNonOwnerProxy(jack)
    println("Name is ${nonOwnerProxy.name}")
    try {
        nonOwnerProxy.interests = "horse riding, games, gym"
    } catch (e: Exception) {
        println("Can not set interests from non owner proxy")
    }
    nonOwnerProxy.hotOrNotRating = 3
    println("Rating set from non owner proxy")
    println("Rating is ${nonOwnerProxy.hotOrNotRating}")
}

fun getOwnerProxy(person: PersonBean): PersonBean {
    return Proxy.newProxyInstance(
        person.javaClass.classLoader,
        person.javaClass.interfaces,
        OwnerInvocationHandler(person)
    ) as PersonBean
}

fun getNonOwnerProxy(person: PersonBean): PersonBean {
    return Proxy.newProxyInstance(
        person.javaClass.classLoader,
        person.javaClass.interfaces,
        NonOwnerInvocationHandler(person)
    ) as PersonBean
}