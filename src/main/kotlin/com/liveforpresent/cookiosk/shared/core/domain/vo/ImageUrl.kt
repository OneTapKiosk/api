package com.liveforpresent.cookiosk.shared.core.domain.vo

import com.liveforpresent.cookiosk.api.product.command.domain.vo.Barcode

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

    override fun equals(other: Any?): Boolean = other is ImageUrl && this.value == other.value
    override fun hashCode(): Int = value.hashCode()
}
