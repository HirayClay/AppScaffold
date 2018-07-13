package com.hiray.mvvm.model.entity

data class Tip(var type:String, var tip:String)
{
    override fun toString(): String {
        return "type:$type, tip:$tip"
    }
}

