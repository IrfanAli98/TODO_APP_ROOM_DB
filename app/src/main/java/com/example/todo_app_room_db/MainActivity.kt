package com.example.todo_app_room_db

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo_app_room_db.databinding.ActivityMainBinding
import com.example.todo_app_room_db.databinding.CreateNoteDialogueBinding
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var factory: Notes_Factory
    private lateinit var viewModel: Notes_ViewModel
    private lateinit var dateFormat: SimpleDateFormat
    private lateinit var date:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding= DataBindingUtil.setContentView(this, R.layout.activity_main)

        val notesDao = Notes_Database.getInstance(this).notesDao
        factory = Notes_Factory(Notes_Repository(notesDao))
        viewModel = ViewModelProvider(this, factory)[Notes_ViewModel::class.java]

        dateFormat = SimpleDateFormat("dd.MM.yyy")
        date = dateFormat.format(Date())



        dataBinding.recyclerView.layoutManager  = LinearLayoutManager(this)
        viewModel.notes.observe(this, Observer {
            val adapter= MyRecyclerViewAdapter(it, object : OnItemClickListener{
                override fun onItemClick(notes: Notes, position: Int) {
                    val intent = Intent(this@MainActivity, Update_Notes::class.java)
                    intent.putExtra("Notes", Gson().toJson(notes))
                    startActivity(intent)
                }

            })
            dataBinding.recyclerView.adapter = adapter
        })


        dataBinding.addContact.setOnClickListener {


            val dialogBinding : CreateNoteDialogueBinding = DataBindingUtil.inflate(LayoutInflater.from(this),R.layout.create_note_dialogue, null, false)
            val dialog = Dialog(this)
            dialog.setContentView(dialogBinding.root)

            val manager = WindowManager.LayoutParams()
            manager.width = WindowManager.LayoutParams.MATCH_PARENT
            manager.height = WindowManager.LayoutParams.WRAP_CONTENT
            dialog.window!!.attributes = manager

            dialog.show()


            dialogBinding.btnSave.setOnClickListener {

                val notes = Notes(0, dialogBinding.etTitle.text.toString(), dialogBinding.etDescrip.text.toString(),
                    "Created_AT $date" + " "+dialogBinding.clkTime.text.toString())
                viewModel.saveNotes(notes)
                dialog.dismiss()
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_option_delete, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.it_delete -> {
                viewModel.deleteAllNotes()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}