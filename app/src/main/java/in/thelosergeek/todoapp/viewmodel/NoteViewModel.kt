package `in`.thelosergeek.todoapp.viewmodel

import `in`.thelosergeek.todoapp.data.model.Note
import `in`.thelosergeek.todoapp.data.repository.NoteRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: NoteRepository = NoteRepository(application)
    private var allNotes: LiveData<List<Note>> = repository.getAllNotes()

    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }

    fun insert(note: Note) {
        repository.insert(note)
    }

    fun deleteAllNotes() {
        repository.deleteAllNotes()
    }

    fun deleteNotes(note: Note) {
        repository.delete(note)
    }


}