package com.efhems.socialvue.ui.response

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.efhems.socialvue.R
import com.efhems.socialvue.databinding.FragmentOtherQuestionBinding
import java.util.*


class OtherQuestionFragment : Fragment(), DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {

    private val TAG = OtherQuestionFragment::class.java.name

    private var year: Int? = null
    private var month: Int? = null
    private var day: Int? = null

    private var hour: Int? = null
    private var minute: Int? = null

    private lateinit var binding: FragmentOtherQuestionBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_other_question, container, false)

        val c = Calendar.getInstance()
        year = c.get(Calendar.YEAR)
        month = c.get(Calendar.MONTH)
        day = c.get(Calendar.DAY_OF_MONTH)

        hour = c.get(Calendar.HOUR)
        minute = c.get(Calendar.MINUTE)

        binding.startTime.setOnClickListener {
            Log.i(TAG, "start pressed")

            val datePickerDialog = DatePickerDialog(this.requireContext(),
                    this, year!!, month!!, day!!)
            datePickerDialog.show()

        }

        binding.endTime.setOnClickListener {

            Log.i(TAG, "end pressed")
            val datePickerDialog = DatePickerDialog(this.requireContext(),
                    this, year!!, month!!, day!!)
            datePickerDialog.show()
        }

        binding.btnSubmit.setOnClickListener{
            val alertDialog = AlertDialog.Builder(this.requireContext())
                    .setTitle("Succeeful")
                    .setMessage("Your message has been sent to admin")
                    .setCancelable(true)
                    .setView(R.layout.ok_successful)
                    .setPositiveButton("Ok"){ _: DialogInterface, i: Int ->

                    }
                    alertDialog.show()
        }

        return binding.root
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

        val timePickerDialog = TimePickerDialog(this.requireContext(),
                this, hour!!, minute!!, false)
        timePickerDialog.show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        binding.startTime.text = "2019/05/02 5:43 PM"
    }
}
