package com.seekho.model

import com.google.gson.annotations.SerializedName


/**
 * Created by Ritik on: 10/05/25
 */

data class AnimeSeriesData(
     @SerializedName("pagination" ) var pagination : Pagination?     = Pagination(),
     @SerializedName("data"       ) var data       : ArrayList<AnimeSeriesList> = arrayListOf()
)

data class Pagination (
    @SerializedName("last_visible_page" ) var lastVisiblePage : Int?     = null,
    @SerializedName("has_next_page"     ) var hasNextPage     : Boolean? = null,
    @SerializedName("current_page"      ) var currentPage     : Int?     = null,
    @SerializedName("items"             ) var items           : Items?   = Items()
)

data class Items (
    @SerializedName("count"    ) var count   : Int? = null,
    @SerializedName("total"    ) var total   : Int? = null,
    @SerializedName("per_page" ) var perPage : Int? = null
)

data class AnimeSeriesList (
    @SerializedName("mal_id"          ) var malId          : Int?                    = null,
    @SerializedName("url"             ) var url            : String?                 = null,
    @SerializedName("images"          ) var images         : Images?                 = Images(),
    @SerializedName("trailer"         ) var trailer        : Trailer?                = Trailer(),
    @SerializedName("approved"        ) var approved       : Boolean?                = null,
    @SerializedName("titles"          ) var titles         : ArrayList<Titles>       = arrayListOf(),
    @SerializedName("title"           ) var title          : String?                 = null,
    @SerializedName("title_english"   ) var titleEnglish   : String?                 = null,
    @SerializedName("title_japanese"  ) var titleJapanese  : String?                 = null,
    @SerializedName("title_synonyms"  ) var titleSynonyms  : ArrayList<String>       = arrayListOf(),
    @SerializedName("type"            ) var type           : String?                 = null,
    @SerializedName("source"          ) var source         : String?                 = null,
    @SerializedName("episodes"        ) var episodes       : Int?                    = null,
    @SerializedName("status"          ) var status         : String?                 = null,
    @SerializedName("airing"          ) var airing         : Boolean?                = null,
    @SerializedName("aired"           ) var aired          : Aired?                  = Aired(),
    @SerializedName("duration"        ) var duration       : String?                 = null,
    @SerializedName("rating"          ) var rating         : String?                 = null,
    @SerializedName("score"           ) var score          : Double?                 = null,
    @SerializedName("scored_by"       ) var scoredBy       : Int?                    = null,
    @SerializedName("rank"            ) var rank           : Int?                    = null,
    @SerializedName("popularity"      ) var popularity     : Int?                    = null,
    @SerializedName("members"         ) var members        : Int?                    = null,
    @SerializedName("favorites"       ) var favorites      : Int?                    = null,
    @SerializedName("synopsis"        ) var synopsis       : String?                 = null,
    @SerializedName("background"      ) var background     : String?                 = null,
    @SerializedName("season"          ) var season         : String?                 = null,
    @SerializedName("year"            ) var year           : Int?                    = null,
    @SerializedName("broadcast"       ) var broadcast      : Broadcast?              = Broadcast(),
    @SerializedName("producers"       ) var producers      : ArrayList<Producers>    = arrayListOf(),
    @SerializedName("licensors"       ) var licensors      : ArrayList<Licensors>    = arrayListOf(),
    @SerializedName("studios"         ) var studios        : ArrayList<Studios>      = arrayListOf(),
    @SerializedName("genres"          ) var genres         : ArrayList<Genres>       = arrayListOf(),
    @SerializedName("explicit_genres" ) var explicitGenres : ArrayList<String>       = arrayListOf(),
    @SerializedName("demographics"    ) var demographics   : ArrayList<Demographics> = arrayListOf()
)

data class Demographics (
    @SerializedName("mal_id" ) var malId : Int?    = null,
    @SerializedName("type"   ) var type  : String? = null,
    @SerializedName("name"   ) var name  : String? = null,
    @SerializedName("url"    ) var url   : String? = null
)

data class Genres (
    @SerializedName("mal_id" ) var malId : Int?    = null,
    @SerializedName("type"   ) var type  : String? = null,
    @SerializedName("name"   ) var name  : String? = null,
    @SerializedName("url"    ) var url   : String? = null
)

data class Studios (
    @SerializedName("mal_id" ) var malId : Int?    = null,
    @SerializedName("type"   ) var type  : String? = null,
    @SerializedName("name"   ) var name  : String? = null,
    @SerializedName("url"    ) var url   : String? = null
)

data class Licensors (
    @SerializedName("mal_id" ) var malId : Int?    = null,
    @SerializedName("type"   ) var type  : String? = null,
    @SerializedName("name"   ) var name  : String? = null,
    @SerializedName("url"    ) var url   : String? = null
)

data class Producers (
    @SerializedName("mal_id" ) var malId : Int?    = null,
    @SerializedName("type"   ) var type  : String? = null,
    @SerializedName("name"   ) var name  : String? = null,
    @SerializedName("url"    ) var url   : String? = null
)

data class Broadcast (
    @SerializedName("day"      ) var day      : String? = null,
    @SerializedName("time"     ) var time     : String? = null,
    @SerializedName("timezone" ) var timezone : String? = null,
    @SerializedName("string"   ) var string   : String? = null
)

data class Aired (
    @SerializedName("from"   ) var from   : String? = null,
    @SerializedName("to"     ) var to     : String? = null,
    @SerializedName("prop"   ) var prop   : Prop?   = Prop(),
    @SerializedName("string" ) var string : String? = null
)

data class Prop (
    @SerializedName("from" ) var from : From? = From(),
    @SerializedName("to"   ) var to   : To?   = To()
)

data class To (
    @SerializedName("day"   ) var day   : Int? = null,
    @SerializedName("month" ) var month : Int? = null,
    @SerializedName("year"  ) var year  : Int? = null
)

data class From (
    @SerializedName("day"   ) var day   : Int? = null,
    @SerializedName("month" ) var month : Int? = null,
    @SerializedName("year"  ) var year  : Int? = null
)

data class Titles (
    @SerializedName("type"  ) var type  : String? = null,
    @SerializedName("title" ) var title : String? = null
)

data class Trailer (
    @SerializedName("youtube_id" ) var youtubeId : String? = null,
    @SerializedName("url"        ) var url       : String? = null,
    @SerializedName("embed_url"  ) var embedUrl  : String? = null,
    @SerializedName("images"     ) var images    : TrailerImages? = TrailerImages()
)

data class TrailerImages (
    @SerializedName("image_url"         ) var imageUrl        : String? = null,
    @SerializedName("small_image_url"   ) var smallImageUrl   : String? = null,
    @SerializedName("medium_image_url"  ) var mediumImageUrl  : String? = null,
    @SerializedName("large_image_url"   ) var largeImageUrl   : String? = null,
    @SerializedName("maximum_image_url" ) var maximumImageUrl : String? = null
)

data class Images (
    @SerializedName("jpg"  ) var jpg  : Jpg?  = Jpg(),
    @SerializedName("webp" ) var webp : Webp? = Webp()
)

data class Webp (
    @SerializedName("image_url"       ) var imageUrl      : String? = null,
    @SerializedName("small_image_url" ) var smallImageUrl : String? = null,
    @SerializedName("large_image_url" ) var largeImageUrl : String? = null
)

data class Jpg (
    @SerializedName("image_url"       ) var imageUrl      : String? = null,
    @SerializedName("small_image_url" ) var smallImageUrl : String? = null,
    @SerializedName("large_image_url" ) var largeImageUrl : String? = null

)