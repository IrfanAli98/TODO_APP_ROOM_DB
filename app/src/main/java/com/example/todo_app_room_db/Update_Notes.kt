package com.example.todo_app_room_db

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.todo_app_room_db.databinding.ActivityUpdateNotesBinding
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

class Update_Notes : AppCompatActivity() {
    private lateinit var dataBinding: ActivityUpdateNotesBinding
    private lateinit var factory: Notes_Factory
    private lateinit var viewModel: Notes_ViewModel
    private lateinit var notes: Notes
    private lateinit var dateFormat: SimpleDateFormat
    private lateinit var date:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_update_notes)

        notes = Gson().fromJson(intent.getStringExtra("Notes"), Notes::class.java)

        dataBinding.etEdTitle.setText(notes.Title)
        dataBinding.etEdDescrip.setText(notes.Descryption)
        dataBinding.clkEdTime.setText(notes.Created_At)
        dataBinding.tvDate.setText(notes.Created_At)


        val notesDao = Notes_Database.getInstance(this).notesDao
        factory = Notes_Factory(Notes_Repository(notesDao))
        viewModel=ViewModelProvider(this,factory)[Notes_ViewModel::class.java]

        dateFormat = SimpleDateFormat("dd.MM.yyy")
        date = dateFormat.format(Date())
        dataBinding.tvCurrenttime.setText("Current Date: $date Time: ")

        dataBinding.btnEdUpdate.setOnClickListener {
            viewModel.updateNotes(Notes(notes.id, dataBinding.etEdTitle.text.toString(), dataBinding.etEdDescrip.text.toString(),
                "Modified_AT $date" + " "+dataBinding.clkEdTime.text.toString()))
            finish()
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.update_option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.ed_edit -> {
                dataBinding.etEdTitle.isFocusableInTouchMode = true
                dataBinding.etEdDescrip.isFocusableInTouchMode = true
                dataBinding.btnEdUpdate.visibility = View.VISIBLE

            }
            R.id.ed_delete -> {
                viewModel.deleteNotes(notes)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}