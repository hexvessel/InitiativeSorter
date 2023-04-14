package com.example.initiativesorter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Collections
import java.util.Random

class MainActivity : AppCompatActivity() {
    private lateinit var data: ArrayList<Character>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val roll20d = findViewById<Button>(R.id.main_roll)
        val addnRoll = findViewById<Button>(R.id.main_addnRollCharButton)
        val deleteButton = findViewById<Button>(R.id.main_deleteButton)
        val sortButton = findViewById<Button>(R.id.main_sortButton)
        val recyclerView = findViewById<RecyclerView>(R.id.main_recyclerView)
        val nextButton = findViewById<Button>(R.id.main_next)
        val addCharButton = findViewById<Button>(R.id.main_addCharButton)
        val charName = findViewById<EditText>(R.id.main_charName)
        val dexMod = findViewById<EditText>(R.id.main_init)
        val hp = findViewById<EditText>(R.id.main_hp)
        val delchar = findViewById<EditText>(R.id.main_deleteCharEdit)

        var dataIndex: Int = 0
        data = ArrayList<Character>()

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = CharAdapter(data)
        recyclerView.adapter = adapter


        addCharButton.setOnClickListener {
            val name = charName.text.toString()
            var check: Boolean = true
            for (i in data){
                if (i.name == name){
                    check = false
                    Toast.makeText(this, "$name is already in the list", Toast.LENGTH_SHORT).show()
                    break;
                }
            }
            if(check) {
                val initiative = dexMod.text.toString().toInt()
                val health = hp.text.toString().toInt()
                val newChar = Character(name, initiative, health)
                data.add(dataIndex, newChar)
                val adapter = CharAdapter(data)
                recyclerView.adapter = adapter
            }
        }

        sortButton.setOnClickListener {
            data.sortByDescending { it.lastRollSum }
            val adapter = CharAdapter(data)
            recyclerView.adapter = adapter
        }

        nextButton.setOnClickListener{
            Collections.rotate(data,-1)
            val adapter = CharAdapter(data)
            recyclerView.adapter = adapter
        }
        deleteButton.setOnClickListener {
            val charToDelete = delchar.text.toString()
            var newData: ArrayList<Character> = ArrayList<Character>()
            for (i in data){
                if (i.name != charToDelete){
                    newData.add(i)
                }
            }
            if(newData.size < data.size){
                val adapter = CharAdapter(newData)
                recyclerView.adapter = adapter
                data = newData
            }else{
                Toast.makeText(this, "$charToDelete is not in the list", Toast.LENGTH_SHORT).show()
            }
        }
        addnRoll.setOnClickListener {
            val name = charName.text.toString()
            var check: Boolean = true
            for (i in data){
                if (i.name == name){
                    check = false
                    Toast.makeText(this, "$name is already in the list", Toast.LENGTH_SHORT).show()
                    break;
                }
            }
            if(check) {
                val randomRoll = (1..20).shuffled().first()
                val initiative = dexMod.text.toString().toInt()
                val health = hp.text.toString().toInt()
                val newChar = Character(name, initiative, health,randomRoll)
                data.add(dataIndex, newChar)
                val adapter = CharAdapter(data)
                recyclerView.adapter = adapter
            }
        }
        roll20d.setOnClickListener {
            val randomRoll = (1..20).shuffled().first()
            Toast.makeText(this, "$randomRoll", Toast.LENGTH_SHORT).show()
        }
    }

}