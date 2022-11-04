package com.dk.newprojectsearchmovie.model

import com.dk.newprojectsearchmovie.R


data class Movie(
    var title: String?,
    var year: Int?,
    var rating: Double?,
    var poster: Int?
)


fun getMovieTop250ListFromLocalStorage() : List<Movie> {
    return listOf(
        Movie("Зеленая миля",1999,9.2, R.drawable.green_mile),
        Movie("Список Шиндлера",1993,9.1,R.drawable.shindlerslist),
        Movie("Побег из Шоушенка",1994,9.0,R.drawable.shawshenk_redemption),
        Movie("Форрест Гамп",1994,8.9,R.drawable.forrest_gamp),
        Movie("Властелин колец: Братство Кольца",2001,8.8,R.drawable.lotr_fellowship_ring),
        Movie("Властелин колец: Две крепости",2002,8.8,R.drawable.lotr_two_towers),
        Movie("Тайна Коко",2017,8.8,R.drawable.coco),
        Movie("Интерстеллар",2014,8.8,R.drawable.interstellar),
        Movie("Назад в будущее",1985,8.8,R.drawable.back_to_the_future),
        Movie("1+1",2011,8.8,R.drawable.intouchable),
    )
}

fun getMoviePopularListFromLocalStorage() : List<Movie> {
    return listOf(
        Movie("Зеленая миля",1999,9.2,1),
        Movie("Список Шиндлера",1993,9.1,1),
        Movie("Побег из Шоушенка",1994,9.0,1),
        Movie("Форрест Гамп",1994,8.9,1),
        Movie("Властелин колец: Братство Кольца",2001,8.8,1),
        Movie("Властелин колец: Две крепости",2002,8.8,1),
        Movie("Тайна Коко",2017,8.8,1),
        Movie("Интерстеллар",2014,8.8,1),
        Movie("Назад в будущее",1985,8.8,1),
        Movie("1+1",2011,8.8,1),
    )
}