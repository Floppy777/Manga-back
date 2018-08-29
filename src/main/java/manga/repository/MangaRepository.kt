package manga.repository

import manga.model.Manga
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MangaRepository : CrudRepository<Manga,Int>