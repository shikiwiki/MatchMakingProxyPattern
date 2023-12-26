package com.example.matchmakingproxypattern

import java.lang.reflect.InvocationHandler
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

class NonOwnerInvocationHandler(private var person: PersonBean) : InvocationHandler {

    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
        try {
            if (method?.name!!.startsWith("get")) {
                return method.invoke(person, args)
            } else if (method.name.equals("setHotOrNotRating")) {
                return method.invoke(person, args)
            } else if (method.name.startsWith("set")) {
                throw IllegalAccessException()
            }
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
        return null
    }

}