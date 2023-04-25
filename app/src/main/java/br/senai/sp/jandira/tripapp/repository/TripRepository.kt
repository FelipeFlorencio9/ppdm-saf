package br.senai.sp.jandira.tripapp.repository

import br.senai.sp.jandira.tripapp.model.Trip
import java.time.LocalDate

class TripRepository {

    companion object{
        fun getTrips() : List<Trip>{
            return listOf(
                Trip(
                    id = 1,
                    location = "Jandira",
                    description = "Cidade muito bonita com muitas opcoes. Visitei o ponto turístico Escadao.",
                    startDate = LocalDate.of(2023,4,21),
                    endDate = LocalDate.of(2023,4,23)
                ),
                Trip(
                    id = 2,
                    location = "Sao Roque",
                    description = "Cidade com muitas opcoes de passeios. Fui na venícula Goes",
                    startDate = LocalDate.of(2023,4,21),
                    endDate = LocalDate.of(2023,4,23)
                ),
                Trip(
                    id = 3,
                    location = "Rio de Janeiro",
                    description = "Cidade muito bonita. Da janela do hotel, vista de copacabana.",
                    startDate = LocalDate.of(2023,4,21),
                    endDate = LocalDate.of(2023,4,23)
                )
            )
        }
    }
}