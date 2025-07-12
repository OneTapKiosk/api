package com.liveforpresent.cookiosk.shared.core.domain.vo

class ImageUrl private constructor(val value: String) {
    companion object {
        fun create(value: String): ImageUrl {
            val imageUrl = ImageUrl(value)
            imageUrl.validate()

            return imageUrl
        }
    }

    fun validate() {
        require(value.isNotEmpty()) { "[ImageUrl] 이미지 경로는 필수입니다." }
    }
}
