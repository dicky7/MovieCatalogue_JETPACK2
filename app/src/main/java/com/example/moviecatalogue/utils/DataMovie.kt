package com.example.moviecatalogue.utils

import com.example.moviecatalogue.MovieModel
import com.example.moviecatalogue.R
import com.example.moviecatalogue.model.data.entity.DetailEntity
import com.example.moviecatalogue.model.data.entity.MovieEntity
import com.example.moviecatalogue.model.data.entity.TvEntity
import com.example.moviecatalogue.model.data.remote.response.movie.MovieRemote
import com.example.moviecatalogue.model.data.remote.response.tv.TvRemote


object DataMovie {
    fun generateDataMovie():List<MovieEntity> {
        return listOf(
            MovieEntity(
                460465,
                "Mortal Combat",
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "2021-04-07"
            ),
            MovieEntity(
                399566,
                "Godzilla vs. Kong",
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "Mar 24, 2021"
            ),
            MovieEntity(
                804435,
                "Vanquish",
                "/b5Ack16C0lDm0AfVkFIGYaZjI83.jpg",
                "2021-04-16"
            ),
            MovieEntity(
                615457,
                "Nobody",
                "/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
                "2021-03-26",
            ),
            MovieEntity(
                635302,
                "Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train",
                "/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
                "2020-10-16"
            )
        )
    }

    fun generateDataTv():List<TvEntity> {
        return listOf(
            TvEntity(
                95557,
                "Invincible",
                "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                "2021-03-26"
            ),
            TvEntity(
                88396,
                "The Falcon and the Winter Soldier",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "2021-03-19"
            ),
            TvEntity(
                71712,
                "The Good Doctor",
                "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                "2017-09-25"
            ),
            TvEntity(
                60735,
                "The Flash",
                "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg",
                "2014-10-07"
            ),
            TvEntity(
                62286,
                "The Walking Dead",
                "/58PON1OrnBiX6CqEHgeWKVwrCn6.jpg",
                "2015-08-23"
            )
        )
    }

    fun getMovieRemote(): List<MovieRemote>{
        return listOf(
            MovieRemote(
                460465,
                "Mortal Combat",
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "2021-04-07"
            ),
            MovieRemote(
                399566,
                "Godzilla vs. Kong",
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "Mar 24, 2021"
            ),
            MovieRemote(
                804435,
                "Vanquish",
                "/b5Ack16C0lDm0AfVkFIGYaZjI83.jpg",
                "2021-04-16"
            ),
            MovieRemote(
                615457,
                "Nobody",
                "/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
                "2021-03-26",
            ),
            MovieRemote(
                635302,
                "Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train",
                "/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
                "2020-10-16"
            )
        )
    }

    fun getTvRemote(): List<TvRemote>{
        return listOf(
            TvRemote(
                95557,
                "Invincible",
                "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                "2021-03-26"
            ),
            TvRemote(
                88396,
                "The Falcon and the Winter Soldier",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "2021-03-19"
            ),
            TvRemote(
                71712,
                "The Good Doctor",
                "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                "2017-09-25"
            ),
            TvRemote(
                60735,
                "The Flash",
                "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg",
                "2014-10-07"
            ),
            TvRemote(
                62286,
                "The Walking Dead",
                "/58PON1OrnBiX6CqEHgeWKVwrCn6.jpg",
                "2015-08-23"
            )
        )
    }
}