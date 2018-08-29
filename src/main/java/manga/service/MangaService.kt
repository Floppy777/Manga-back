package manga.service

import manga.exeption.MangaNotFoundException
import manga.model.Manga
import manga.repository.MangaRepository
import org.springframework.stereotype.Service

@Service
class MangaService(private val mangaRepository: MangaRepository) {


    /**
     * Get specified Manga
     * @throws MangaNotFoundException
     */
    fun getMangaById(id: Int) = mangaRepository.findById(id).orElseThrow { MangaNotFoundException() }

    fun deleteManga(manga: Manga) = mangaRepository.delete(manga)

    fun persistManga(manga: Manga) = mangaRepository.save(manga)

    fun getAllMangas(): Iterable<Manga> {
        val mangaCollection = mangaRepository.findAll().toList()
        if(mangaCollection.isEmpty()){
            throw MangaNotFoundException()
        }
        return mangaCollection
    }

    fun updateManga(id: Int, manga: Manga) : Manga {
        val targetManga: Manga = this.getMangaById(id)
        targetManga.copy(manga.isbn,manga.title,manga.author,manga.readingDetails)
        return mangaRepository.save(targetManga)
    }






}