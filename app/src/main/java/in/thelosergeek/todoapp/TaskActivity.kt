package `in`.thelosergeek.todoapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_task.*
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.min

const val DB_NAME = "todo.db"
class TaskActivity : AppCompatActivity(), View.OnClickListener {


    lateinit var myCalender: Calendar
    lateinit var dateSetListener : DatePickerDialog.OnDateSetListener
    lateinit var timeSetListener: TimePickerDialog.OnTimeSetListener

    val db by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            DB_NAME
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        dateEdt.setOnClickListener(this)
        timeEdt.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.dateEdt-> {
                setlistener()
            }
            R.id.timeEdt -> {
                setTimeListener()
            }
        }
    }

    private fun setTimeListener() {
        myCalender = Calendar.getInstance()


        timeSetListener =
            TimePickerDialog.OnTimeSetListener{_: TimePicker, hourOfDay:Int, minute:Int ->
                myCalender.set(Calendar.HOUR_OF_DAY,hourOfDay)
                myCalender.set(Calendar.MINUTE, minute)
                upDateTime()
            }

        val timePickerDialog = TimePickerDialog(
            this,timeSetListener, myCalender.get(Calendar.HOUR_OF_DAY),
            myCalender.get(Calendar.MINUTE), false
        )
        timePickerDialog.show()
    }

    private fun upDateTime() {
        val myformat = "h:mm a"
        val sdf = SimpleDateFormat(myformat)
        timeEdt.setText(sdf.format(myCalender.time))


    }

    private fun setlistener() {
        myCalender = Calendar.getInstance()


        dateSetListener =
            DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                myCalender.set(Calendar.YEAR,year)
                myCalender.set(Calendar.MONTH,month)
                myCalender.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                upDateDate()
            }

        val datePickerDialog = DatePickerDialog(
            this,
            dateSetListener,myCalender.get(Calendar.YEAR),
            myCalender.get(Calendar.MONTH),myCalender.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.show()

    }

    private fun upDateDate() {
        val format = "EEE, d MMM yyyy"
        val sdf = SimpleDateFormat(format)
        dateEdt.setText(sdf.format(myCalender.time))

        timeInptLay.visibility = View.VISIBLE
    }
}