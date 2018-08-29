package manga.controller

import manga.exeption.MangaNotFoundException
import manga.model.Manga
import manga.service.MangaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/mangas")
class MangaController(private val mangaService: MangaService) {

    @GetMapping
    fun getAllMangas() : ResponseEntity<Iterable<Manga>> {
        return try {
            ResponseEntity.ok(mangaService.getAllMangas())
        }catch (e: MangaNotFoundException){
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }catch (e: Exception){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    @GetMapping("{id}")
    fun getMangaById(@PathVariable("id") id: Int) : ResponseEntity<Manga> {
        return try {
            ResponseEntity.ok(mangaService.getMangaById(id))
        }catch (e: MangaNotFoundException){
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }catch (e: Exception){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    @PutMapping("{id}")
    fun updateManga(@PathVariable("id") id: Int, @RequestBody manga: Manga) : ResponseEntity<Manga>{
        return try {
            ResponseEntity.ok(mangaService.updateManga(id,manga))
        }catch (e: MangaNotFoundException){
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }catch (e: Exception){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }
}