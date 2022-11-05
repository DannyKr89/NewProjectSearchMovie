package com.dk.newprojectsearchmovie.model

import android.os.Parcelable
import com.dk.newprojectsearchmovie.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var title: String?,
    var year: Int?,
    var rating: Double?,
    var poster: Int?
) : Parcelable


fun getMovieTop250ListFromLocalStorage() : List<Movie> {
    return listOf(
        Movie("Зеленая миля", 1999, 9.2, R.drawable.green_mile),
        Movie("Список Шиндлера", 1993, 9.1, R.drawable.shindlerslist),
        Movie("Побег из Шоушенка", 1994, 9.0, R.drawable.shawshenk_redemption),
        Movie("Форрест Гамп", 1994, 8.9, R.drawable.forrest_gamp),
        Movie("Властелин колец: Возвращение Короля", 2011, 8.9, R.drawable.lotr_returnking),
        Movie("Властелин колец: Братство Кольца", 2001, 8.8, R.drawable.lotr_fellowship_ring),
        Movie("Властелин колец: Две крепости", 2002, 8.8, R.drawable.lotr_two_towers),
        Movie("Тайна Коко", 2017, 8.8, R.drawable.coco),
        Movie("Интерстеллар", 2014, 8.8, R.drawable.interstellar),
        Movie("Назад в будущее", 1985, 8.8, R.drawable.back_to_the_future),
    )
}

fun getMoviePopularListFromLocalStorage() : List<Movie> {
    return listOf(
        Movie("Сердце пармы",2022,7.0,R.drawable.serdce_parmi),
        Movie("Петр I: Последний царь и первый император",2022,7.2,R.drawable.petr1),
        Movie("На Западном фронте без перемен ",2022,7.8,R.drawable.im_westen),
        Movie("Чёрный Адам",2022,6.5,R.drawable.black_adam),
        Movie("Вышка",2022,6.8,R.drawable.fall),
        Movie("Быстрее пули ",2022,7.7,R.drawable.bullet_train),
        Movie("Любовники",2022,6.4,R.drawable.lubovniki),
        Movie("Либерея: Охотники за сокровищами",2022,6.6,R.drawable.liberia),
        Movie("Одиннадцать молчаливых мужчин",2021,6.2,R.drawable.odinnadcat),
        Movie("Три тысячи лет желаний",2022,6.9,R.drawable.three_thousand),
    )
}