package com.hiray.mvvm.model.entity

import com.google.gson.annotations.SerializedName

data class TopStory(@SerializedName("image_source") var imageSource: String,
                    var title: String,
                    var url: String,
                    var image: String,
                    @SerializedName("share_url") var shareUrl: String,
                    @SerializedName("gap_prefix") var gapPrefix: String,
                    var id: String)
