package com.dk.newprojectsearchmovie.data.model.imdbMovie


import com.google.gson.annotations.SerializedName

data class ImdbMovieDetail(
    @SerializedName("actorList") val actorList: List<Actor> = listOf(),
    @SerializedName("awards") val awards: String = "",
    @SerializedName("companies") val companies: String = "",
    @SerializedName("contentRating") val contentRating: String = "",
    @SerializedName("countries") val countries: String = "",
    @SerializedName("directors") val directors: String = "",
    @SerializedName("errorMessage") val errorMessage: String? = "",
    @SerializedName("genres") val genres: String = "",
    @SerializedName("id") val id: String = "",
    @SerializedName("imDbRating") val imDbRating: String? = "",
    @SerializedName("imDbRatingVotes") val imDbRatingVotes: String = "",
    @SerializedName("image") val image: String? = "",
    @SerializedName("languages") val languages: String = "",
    @SerializedName("metacriticRating") val metacriticRating: String = "",
    @SerializedName("plotLocal") val plotLocal: String = "",
    @SerializedName("releaseDate") val releaseDate: String = "",
    @SerializedName("runtimeStr") val runtimeStr: String = "",
    @SerializedName("similars") val similars: List<Similar> = listOf(),
    @SerializedName("stars") val stars: String = "",
    @SerializedName("tagline") val tagline: String = "",
    @SerializedName("title") val title: String? = "",
    @SerializedName("type") val type: String = "",
    @SerializedName("writers") val writers: String = "",
    @SerializedName("year") val year: String? = ""
)

fun getTestMovie(): ImdbMovieDetail {
    return ImdbMovieDetail(
        id = "tt1375666",
        title = "Inception",
        type = "Movie",
        year = "2010",
        image = "https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_Ratio0.6762_AL_.jpg",
        releaseDate = "2010-07-16",
        runtimeStr = "2h 28min",
        plotLocal = "Дом Кобб — талантливый вор, лучший из лучших в опасном искусстве извлечения:  он крадёт ценные секреты из глубин подсознания во время сна, когда человеческий разум наиболее уязвим. Редкие способности Кобба сделали его ценным игроком в привычном к предательству мире промышленного шпионажа, но они же превратили его в извечного беглеца и лишили всего, что он когда-либо любил. И вот у Кобба появляется шанс исправить ошибки. Его последнее дело может вернуть всё назад, но для этого ему нужно совершить невозможное — инициацию. Вместо идеальной кражи Кобб и его команда спецов должны будут провернуть обратное. Теперь их задача — не украсть идею, а внедрить её. Если у них получится, это и станет идеальным преступлением. Но никакое планирование или мастерство не могут подготовить команду к встрече с опасным противником, который, кажется, предугадывает каждый их ход. Врагом, увидеть которого мог бы лишь Кобб.",
        awards = "Top rated movie #13 | Won 4 Oscars, 158 wins & 220 nominations total",
        directors = "Christopher Nolan",
        writers = "Christopher Nolan",
        stars = "Leonardo DiCaprio, Joseph Gordon-Levitt, Elliot Page",
        actorList = listOf(
            Actor(
                "Cobb",
                "nm0000138",
                "https://m.media-amazon.com/images/M/MV5BMjI0MTg3MzI0M15BMl5BanBnXkFtZTcwMzQyODU2Mw@@._V1_Ratio1.0000_AL_.jpg",
                "Leonardo DiCaprio"
            ), Actor(
                "Arthur",
                "nm0330687",
                "https://m.media-amazon.com/images/M/MV5BMTY3NTk0NDI3Ml5BMl5BanBnXkFtZTgwNDA3NjY0MjE@._V1_Ratio1.0000_AL_.jpg",
                "Joseph Gordon-Levitt"
            ), Actor(
                "Ariadne",
                "nm0680983",
                "https://m.media-amazon.com/images/M/MV5BNmNhZmFjM2ItNTlkNi00ZTQ4LTk3NzYtYTgwNTJiMTg4OWQzXkEyXkFqcGdeQXVyMTM1MjAxMDc3._V1_Ratio1.0000_AL_.jpg",
                "Elliot Page"
            ), Actor(
                "Saito",
                "nm0913822",
                "https://m.media-amazon.com/images/M/MV5BMTQzMTUzNjc4Nl5BMl5BanBnXkFtZTcwMTUyODU2Mw@@._V1_Ratio1.0000_AL_.jpg",
                "Ken Watanabe"
            ), Actor(
                "Eames",
                "nm0362766",
                "https: //m.media-amazon.com/images/M/MV5BMTQ3ODEyNjA4Nl5BMl5BanBnXkFtZTgwMTE4ODMyMjE@._V1_Ratio1.0000_AL_.jpg",
                "Tom Hardy"
            ), Actor(
                "Yusuf",
                "nm2438307",
                "https://m.media-amazon.com/images/M/MV5BMTI5Nzc2NTc2MF5BMl5BanBnXkFtZTcwMDM2MTc1Mg@@._V1_Ratio1.5000_AL_.jpg",
                "Dileep Rao"
            ), Actor(
                "Robert Fischer",
                "nm2438307",
                "https://m.media-amazon.com/images/M/MV5BMTUzMjg1NzIyOV5BMl5BanBnXkFtZTYwMzg2Mjgy._V1_Ratio1.0000_AL_.jpg",
                "Cillian Murphy"
            ), Actor(
                "Browning",
                "nm2438307",
                "https://m.media-amazon.com/images/M/MV5BMTk1OTQ5MzUzM15BMl5BanBnXkFtZTgwMDUxMTY1NDE@._V1_Ratio1.0000_AL_.jpg",
                "Tom Berenger"
            ), Actor(
                "Mal",
                "nm2438307",
                "https://m.media-amazon.com/images/M/MV5BMTQxNTEzNTkwNF5BMl5BanBnXkFtZTcwNzQ2NDIwOQ@@._V1_Ratio1.0000_AL_.jpg",
                "Marion Cotillard"
            ), Actor(
                "Maurice Fischer",
                "nm2438307",
                "https://m.media-amazon.com/images/M/MV5BMTMyMjIxMTQ2NV5BMl5BanBnXkFtZTYwNDQ0NTE1._V1_Ratio1.0000_AL_.jpg",
                "Pete Postlethwaite"
            )
        ),
        genres = "Действие, Приключение, Научная фантастика",
        companies = "Warner Bros., Legendary Entertainment, Syncopy",
        countries = "США, Великобритания",
        languages = "Английский, Японский, Французский",
        contentRating = "PG-13",
        imDbRating = "8.8",
        imDbRatingVotes = "2327099",
        metacriticRating = "74",
        similars = listOf(
            Similar(
                "tt0816692",
                "https://m.media-amazon.com/images/M/MV5BZjdkOTU3MDktN2IxOS00OGEyLWFmMjktY2FiMmZkNWIyODZiXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_Ratio0.6763_AL_.jpg",
                "Interstellar",
                "8.6"
            ),
            Similar(
                "tt0816692",
                "https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_Ratio0.6763_AL_.jpg",
                "The Dark Knight",
                "9.0"
            ),
            Similar(
                "tt0816692",
                "https://m.media-amazon.com/images/M/MV5BNWIwODRlZTUtY2U3ZS00Yzg1LWJhNzYtMmZiYmEyNmU1NjMzXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_Ratio0.6957_AL_.jpg",
                "Forrest Gump",
                "8.8"
            ),
            Similar(
                "tt0816692",
                "https://m.media-amazon.com/images/M/MV5BNDIzNDU0YzEtYzE5Ni00ZjlkLTk5ZjgtNjM3NWE4YzA3Nzk3XkEyXkFqcGdeQXVyMjUzOTY1NTc@._V1_Ratio0.6763_AL_.jpg",
                "Fight Club",
                "8.8"
            ),
            Similar(
                "tt0816692",
                "https://m.media-amazon.com/images/M/MV5BNGNhMDIzZTUtNTBlZi00MTRlLWFjM2ItYzViMjE3YzI5MjljXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_Ratio0.6860_AL_.jpg",
                "Pulp Fiction",
                "8.9"
            ),
            Similar(
                "tt0816692",
                "https://m.media-amazon.com/images/M/MV5BOTUwODM5MTctZjczMi00OTk4LTg3NWUtNmVhMTAzNTNjYjcyXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_Ratio0.6763_AL_.jpg",
                "Se7en",
                "8.6"
            ),
            Similar(
                "tt0816692",
                "https: //m.media-amazon.com/images/M/MV5BMjIxMjgxNTk0MF5BMl5BanBnXkFtZTgwNjIyOTg2MDE@._V1_Ratio0.6763_AL_.jpg",
                "The Wolf of Wall Street",
                "8.2"
            ),
            Similar(
                "tt0816692",
                "https://m.media-amazon.com/images/M/MV5BNGVjNWI4ZGUtNzE0MS00YTJmLWE0ZDctN2ZiYTk2YmI3NTYyXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_Ratio0.6763_AL_.jpg",
                "Joker",
                "8.4"
            ),
        )
    )

}