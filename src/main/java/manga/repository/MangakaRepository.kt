package manga.repository

import manga.model.Mangaka
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MangakaRepository: CrudRepository<Mangaka,Int>