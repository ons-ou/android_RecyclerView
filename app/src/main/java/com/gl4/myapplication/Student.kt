package com.gl4.myapplication

class Student {
    constructor(name: String, lastName: String, gender: Char,p:Boolean) {
        this.name = name
        this.lastName = lastName
        this.gender = gender
        this.present=p
    }

    var name: String = ""
    var lastName: String = ""
    var gender: Char = 'X'
    var present: Boolean=false

}