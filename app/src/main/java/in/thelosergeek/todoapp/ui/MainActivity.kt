package `in`.thelosergeek.todoapp.ui


import `in`.thelosergeek.todoapp.R
import `in`.thelosergeek.todoapp.adapter.NoteItem
import `in`.thelosergeek.todoapp.data.model.Note
import `in`.thelosergeek.todoapp.utility.SwipeTouchCallback
import `in`.thelosergeek.todoapp.viewmodel.NoteViewModel
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var noteViewModel: NoteViewModel
    val groupAdapter = GroupAdapter<ViewHolder>()
    var notes_list : List<Note> ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)


        fab.setOnClickListener {
            startActivityForResult(
                Intent(this, NewNoteActivity::class.java),
                900
            )
        }

        recycler_view.apply {
            adapter = groupAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)

        }
        ItemTouchHelper(touchCallback).attachToRecyclerView(recycler_view)

        noteViewModel.getAllNotes().observe(this,
            Observer<List<Note>> {
                    t ->
                Toast.makeText(this,  "Data Updated!", Toast.LENGTH_SHORT).show()
                notes_list = t
                groupAdapter.clear()
                groupAdapter.addAll(t.toNoteItem()!!)
            })
    }


    private fun List<Note>.toNoteItem() : List<NoteItem> {
        return this.map {
            NoteItem(it)
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 900 && resultCode == Activity.RESULT_OK) {
            val newNote = data!!.getStringExtra("_TITLE")?.let {
                data.getStringExtra("_DESCRIPTION")?.let { it1 ->
                    Note(
                        it,
                        it1
                    )
                }
            }
            if (newNote != null) {
                noteViewModel.insert(newNote)
            }

            Toast.makeText(this, "Note Added Successfully!", Toast.LENGTH_SHORT).show()
        } else {
        }


    }

    private val touchCallback: SwipeTouchCallback by lazy {
        object : SwipeTouchCallback() {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                noteViewModel.deleteNotes(notes_list!!.get(viewHolder.adapterPosition))

            }
        }
    }
}