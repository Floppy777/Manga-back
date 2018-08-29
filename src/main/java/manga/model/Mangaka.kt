package manga.model

import javax.persistence.*

@Entity
@Table(name="mangaka")
data class Mangaka(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int? = null,
        val lastname: String? = null,
        val firstname: String? = null
)