package com.example.scrollinggallery.domain

class PicDetailed(
    val pic: Pic,
    val state: Status
){
    companion object {
        fun newSuccessPic(pic: Pic) = PicDetailed(pic, Status.SUCCESS)
        fun newErrorPic() = PicDetailed(Pic(0,"","",false), Status.ERROR)
    }
}