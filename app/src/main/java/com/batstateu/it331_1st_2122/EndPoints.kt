package com.batstateu.it331_1st_2122

object EndPoints {
    //private const val URL_ROOT = "https://aris-gail.com/WebApi/v1/?op="
    private const val groupNo: Int = 0 //your group number here
    private const val URL_ROOT =
        "https://developers.aris-gail.com/21221ST_CS3101_G$groupNo/WebApi/v1/?op="
    val URL_ADD_ARTIST = URL_ROOT + "addartist"
    val URL_GET_ARTIST = URL_ROOT + "getartists"
}