package manga.service

import manga.exception.MangakaNotFoundException
import manga.repository.MangakaRepository
import manga.model.Mangaka
import org.springframework.stereotype.Service

@Service
class MangakaService(val mangakaRepository: MangakaRepository){

    fun getAllMangakas() : Iterable<Mangaka>{
        val mangaCollection = mangakaRepository.findAll().toList()
        if(mangaCollection.isEmpty()) {
            throw MangakaNotFoundException()
        }
        return mangaCollection
    }

    fun getMangakaById(id: Int) = mangakaRepository.findById(id).orElseThrow { MangakaNotFoundException() }

    fun persistMangaka(mangaka: Mangaka): Mangaka {
        return this.mangakaRepository.save(mangaka)
    }

}