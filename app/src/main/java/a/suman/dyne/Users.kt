package a.suman.dyne

import com.google.firebase.firestore.PropertyName

class Users (
    @PropertyName("Name")
    var Name:String?=null,
    @PropertyName("Email")
    var Email:String?=null,
    @PropertyName("Mobile")
    var Mobile:String?=null,
    @PropertyName("Mobile2")
    var Mobile2:String?=null,
    @PropertyName("Weight")
    var Weight:String?=null,
    @PropertyName("Height")
    var Height:String?=null,
    @PropertyName("Gender")
    var Gender:String?=null
    )