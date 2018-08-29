package manga.model

import javax.persistence.*


@Entity
@Table(name="reading_details")
data class ReadingDetails(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int? = null,

        val chapters: Int? = null,

        val pages: Int? = null,

        val isRead: Boolean? = null
)