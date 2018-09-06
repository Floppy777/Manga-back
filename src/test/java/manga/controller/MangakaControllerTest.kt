package manga.controller

import manga.Application
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.web.context.WebApplicationContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print


@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(classes = [Application::class])
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MangakaControllerTest {

    private var mockMvc: MockMvc? = null

    @Autowired
    private lateinit var wac: WebApplicationContext

    @Before
    fun setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }

    @Test
    fun verifyExistingMangakaById() {
        mockMvc?.perform(MockMvcRequestBuilders.get("/mangakas/1").accept(MediaType.APPLICATION_JSON))
                ?.andExpect(jsonPath("$.id").exists())
                ?.andExpect(jsonPath("$.lastname").exists())
                ?.andExpect(jsonPath("$.firstname").exists())
                ?.andExpect(jsonPath("$.id").value(1))
                ?.andExpect(jsonPath("$.lastname").value("Kishimoto"))
                ?.andExpect(jsonPath("$.firstname").value("Masashi"))
                ?.andDo(print())
    }

    @Test
    fun verifyUnexistingMangaka() {
        mockMvc?.perform(MockMvcRequestBuilders.get("/mangakas/45").accept(MediaType.APPLICATION_JSON))
                ?.andExpect(jsonPath("$.errorCode").value(404))
                ?.andDo(print())
    }





}