package io.github.theapache64.retrosheetsample

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import io.github.theapache64.retrosheet.annotations.Read
import io.github.theapache64.retrosheet.annotations.Write
import io.github.theapache64.retrosheet.core.RetrosheetConfig
import io.github.theapache64.retrosheet.core.RetrosheetConverter
import io.github.theapache64.retrosheet.core.createRetrosheetPlugin
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

const val SHEET_NAME = "notes"
const val ADD_NOTE_ENDPOINT = "add_note"

@Serializable
data class Note(
    @SerialName("Timestamp")
    val createdAt: String? = null,
    @SerialName("Title")
    val title: String,
    @SerialName("Description")
    val description: String?
)


interface NotesApi {
    @Read("SELECT *")
    @GET(SHEET_NAME) // sheet name
    suspend fun getNotes(): List<Note>

    @Write
    @POST(ADD_NOTE_ENDPOINT) // form name
    suspend fun addNote(@Body addNoteRequest: Note): Note
}

fun buildNotesApi(): NotesApi {
    val config = RetrosheetConfig.Builder()
        .setLogging(true)
        // To Read
        .addSheet(
            SHEET_NAME, // sheet name
            "created_at", "title", "description" // columns in same order
        )
        // To write
        .addForm(
            ADD_NOTE_ENDPOINT,
            "https://docs.google.com/forms/d/e/1FAIpQLSdmavg6P4eZTmIu-0M7xF_z-qDCHdpGebX8MGL43HSGAXcd3w/viewform?usp=sf_link" // form link
        )
        .build()

    val ktorClient = HttpClient {
        install(createRetrosheetPlugin(config)) {}
        install(ContentNegotiation) {
            json()
        }
    }

    val retrofit = Ktorfit.Builder()
        .baseUrl("https://docs.google.com/spreadsheets/d/1YTWKe7_mzuwl7AO1Es1aCtj5S9buh3vKauKCMjx1j_M/") // Sheet's public URL
        .httpClient(ktorClient)
        .converterFactories(RetrosheetConverter(config))
        .build()

    return retrofit.createNotesApi()
}