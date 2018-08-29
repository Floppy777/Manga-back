package manga.model

import javax.persistence.*

@Entity
@Table(name="manga")
data class Manga(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val isbn: Int? = null,

        val title: String? = null,

        val nbChapters: Int? = null,

        val nbPages: Int? = null,

        @ManyToOne
        @JoinColumn(name = "author_id")
        val author: Mangaka? = null,

        @OneToOne
        @JoinColumn(name="reading_details_id")
        val readingDetails: ReadingDetails? = null

)