package manga.controller

import manga.exeption.MangakaNotFoundException
import manga.model.Mangaka
import manga.service.MangakaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/mangakas")
class MangakaController(val mangakaService: MangakaService) {

    /**
     * Get All mangakas in the database
     * @return 200 OK
     * @return 404 Mangakas not found / List empty
     * @return 500 Internal Error
     */
    @GetMapping
    fun getAllMangakas() : ResponseEntity<Iterable<Mangaka>>{
        return try {
            ResponseEntity.status(HttpStatus.OK).body(mangakaService.getAllMangakas())
        }catch(e: MangakaNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }catch(e: Exception){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }


    /**
     * Get All mangakas in the database
     * @return 200 OK
     * @return 404 Mangaka specified id not found
     * @return 500 Internal Error
     */
    @GetMapping("{id}")
    fun getMangakaById(@PathVariable("id") id: Int) : ResponseEntity<Mangaka>{
        return try {
            ResponseEntity.status(HttpStatus.OK).body(mangakaService.getMangakaById(id))
        }catch (e: MangakaNotFoundException){
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }catch (e: Exception){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    /**
     * Insert mangaka in database
     * @return 201 Mangaka Created
     * @return 500 Internal Error
     */
    @PostMapping
    fun createMangaka(@RequestBody mangaka: Mangaka): ResponseEntity<Mangaka> {
        return try {
            val persistedMangaka = this.mangakaService.persistMangaka(mangaka)
            ResponseEntity.status(HttpStatus.CREATED).body(persistedMangaka)
        }catch(e: Exception){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }
}