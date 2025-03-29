package io.github.theapache64.retrosheetsample

import java.util.*


suspend fun main() {
    val notesApi = buildNotesApi()
    println(notesApi.getNotes())

    // Adding sample order
    val newNote = notesApi.addNote(
        Note(
            createdAt = null,
            title = "Dynamic Note 1",
            description = "Dynámic Desc 1: ${Date()}"
        )
    )

    println(newNote)
}
