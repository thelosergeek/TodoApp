package `in`.thelosergeek.todoapp.adapter

import `in`.thelosergeek.todoapp.R
import `in`.thelosergeek.todoapp.data.model.Note
import androidx.recyclerview.widget.ItemTouchHelper
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.note_item.view.*

class NoteItem(val note: Note): Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.text_view_title.text = note.title
        viewHolder.itemView.text_view_description.text = note.description

    }

    override fun getLayout(): Int {
        return R.layout.note_item
    }


    override fun getSwipeDirs(): Int {
        return ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    }
}