package com.dk.newprojectsearchmovie.model.imdb


import android.net.Uri
import android.os.Parcelable
import com.dk.newprojectsearchmovie.R
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImdbMovieList(
    @SerializedName("items")
    val movies: List<Movie>?
) : Parcelable

@Parcelize
data class Movie(
    @SerializedName("id")
    val id: String?,
    @SerializedName("imDbRating")
    val imDbRating: String?,
    @SerializedName("imDbRatingCount")
    val imDbRatingCount: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("rank")
    val rank: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("year")
    val year: String?
) : Parcelable


fun getMovieTop250ListFromLocalStorage(): ImdbMovieList {
    return ImdbMovieList(
        listOf(
            Movie(
                title = "Зеленая миля", year = "1999", imDbRating = "9.2", image = Uri.parse(
                    "android.resource://com.dk.newprojectsearchmovie/" + R.drawable.green_mile
                ).toString(), id = "", imDbRatingCount = "", rank = ""
            ),
            Movie(
                title = "Список Шиндлера", year = "1993", imDbRating = "9.1", image = Uri.parse(
                    "android.resource://com.dk.newprojectsearchmovie/" + R.drawable.shindlerslist
                ).toString(), id = "", imDbRatingCount = "", rank = ""
            ),
            Movie(
                title = "Побег из Шоушенка", year = "1994", imDbRating = "9.0", image = Uri.parse(
                    "android.resource://com.dk.newprojectsearchmovie/" + R.drawable.shawshenk_redemption
                ).toString(), id = "", imDbRatingCount = "", rank = ""
            ),
            Movie(
                title = "Форрест Гамп", year = "1994", imDbRating = "8.9", image = Uri.parse(
                    "android.resource://com.dk.newprojectsearchmovie/" + R.drawable.forrest_gamp
                ).toString(), id = "", imDbRatingCount = "", rank = ""
            ),
            Movie(
                title = "Властелин колец: Возвращение Короля",
                year = "2011",
                imDbRating = "8.9",
                image = Uri.parse(
                    "android.resource://com.dk.newprojectsearchmovie/" + R.drawable.lotr_returnking
                ).toString(),
                id = "",
                imDbRatingCount = "",
                rank = ""
            ),
            Movie(
                title =
                "Властелин колец: Братство Кольца",
                year = "2001",
                imDbRating = "8.8",
                image = Uri.parse(
                    "android.resource://com.dk.newprojectsearchmovie/" + R.drawable.lotr_fellowship_ring
                ).toString(),
                id = "",
                imDbRatingCount = "",
                rank = ""
            ),
            Movie(
                title = "Властелин колец: Две крепости",
                year = "2002",
                imDbRating = "8.8",
                image = Uri.parse(
                    "android.resource://com.dk.newprojectsearchmovie/" + R.drawable.lotr_two_towers
                ).toString(),
                id = "",
                imDbRatingCount = "",
                rank = ""
            ),
            Movie(
                title = "Тайна Коко", year = "2017", imDbRating = "8.8", image = Uri.parse(
                    "android.resource://com.dk.newprojectsearchmovie/" + R.drawable.coco
                ).toString(), id = "", imDbRatingCount = "", rank = ""
            ),
            Movie(
                title = "Интерстеллар", year = "2014", imDbRating = "8.8", image = Uri.parse(
                    "android.resource://com.dk.newprojectsearchmovie/" + R.drawable.interstellar
                ).toString(), id = "", imDbRatingCount = "", rank = ""
            ),
            Movie(
                title = "Назад в будущее", year = "1985", imDbRating = "8.8", image = Uri.parse(
                    "android.resource://com.dk.newprojectsearchmovie/" + R.drawable.back_to_the_future
                ).toString(), id = "", imDbRatingCount = "", rank = ""
            ),
        )
    )
}

fun getMoviePopularListFromLocalStorage(): ImdbMovieList {
    return ImdbMovieList(
        listOf(
            Movie(
                title = "Сердце пармы", year = "2022", imDbRating = "7.0", image = Uri.parse(
                    "android.resource://com.dk.newprojectsearchmovie/" + R.drawable.serdce_parmi
                ).toString(), id = "", imDbRatingCount = "", rank = ""
            ),
            Movie(
                title = "Петр I: Последний царь и первый император",
                year = "2022",
                imDbRating = "7.2",
                image = Uri.parse(
                    "android.resource://com.dk.newprojectsearchmovie/" + R.drawable.petr1
                ).toString(),
                id = "",
                imDbRatingCount = "",
                rank = ""
            ),
            Movie(
                title = "На Западном фронте без перемен ",
                year = "2022",
                imDbRating = "7.8",
                image = Uri.parse(
                    "android.resource://com.dk.newprojectsearchmovie/" + R.drawable.im_westen
                ).toString(),
                id = "",
                imDbRatingCount = "",
                rank = ""
            ),
            Movie(
                title = "Чёрный Адам", year = "2022", imDbRating = "6.5", image = Uri.parse(
                    "android.resource://com.dk.newprojectsearchmovie/" + R.drawable.black_adam
                ).toString(), id = "", imDbRatingCount = "", rank = ""
            ),
            Movie(
                title = "Вышка", year = "2022", imDbRating = "6.8", image = Uri.parse(
                    "android.resource://com.dk.newprojectsearchmovie/" + R.drawable.fall
                ).toString(), id = "", imDbRatingCount = "", rank = ""
            ),
            Movie(
                title = "Быстрее пули ", year = "2022", imDbRating = "7.7", image = Uri.parse(
                    "android.resource://com.dk.newprojectsearchmovie/" + R.drawable.bullet_train
                ).toString(), id = "", imDbRatingCount = "", rank = ""
            ),
            Movie(
                title = "Любовники", year = "2022", imDbRating = "6.4", image = Uri.parse(
                    "android.resource://com.dk.newprojectsearchmovie/" + R.drawable.lubovniki
                ).toString(), id = "", imDbRatingCount = "", rank = ""
            ),
            Movie(
                title = "Либерея: Охотники за сокровищами",
                year = "2022",
                imDbRating = "6.6",
                image = Uri.parse(
                    "android.resource://com.dk.newprojectsearchmovie/" + R.drawable.liberia
                ).toString(),
                id = "",
                imDbRatingCount = "",
                rank = ""
            ),
            Movie(
                title = "Одиннадцать молчаливых мужчин",
                year = "2021",
                imDbRating = "6.2",
                image = Uri.parse(
                    "android.resource://com.dk.newprojectsearchmovie/" + R.drawable.odinnadcat
                ).toString(),
                id = "",
                imDbRatingCount = "",
                rank = ""
            ),
            Movie(
                title = "Три тысячи лет желаний",
                year = "2022",
                imDbRating = "6.9",
                image = Uri.parse(
                    "android.resource://com.dk.newprojectsearchmovie/" + R.drawable.three_thousand
                ).toString(),
                id = "",
                imDbRatingCount = "",
                rank = ""
            ),
        )
    )
}