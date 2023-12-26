package com.example.testing

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore

class ChatRoom : AppCompatActivity() {

    private lateinit var firebaseFirestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)

        firebaseFirestore = FirebaseFirestore.getInstance()

        findViewById<ImageView>(R.id.kirim).setOnClickListener {
            // Dapatkan pesan dari edit text
            val message = findViewById<EditText>(R.id.chatEditText).text.toString()

            // Kirim pesan ke Firebase
            val chatMessage = ChatMessage(message)
            firebaseFirestore.collection("chats").add(chatMessage)

            // Clear edit text
            findViewById<EditText>(R.id.chatEditText).setText("")
        }

        val messagesRef = firebaseFirestore.collection("chats")
        messagesRef.addSnapshotListener { snapshot, error ->
            if (error != null) {
                Log.e("ChatActivity", "Error fetching messages: ", error)
                return@addSnapshotListener
            }

            // Clear list view
            findViewById<ListView>(R.id.listViewMessages).adapter = null

            // Tampilkan pesan
            for (document in snapshot!!.documents) {
                val chatMessage = document.toObject(ChatMessage::class.java)
                val adapter = chatMessage?.let { ChatAdapter(this, it) }
                findViewById<ListView>(R.id.listViewMessages).adapter = adapter
            }
        }
    }


    class ChatMessage(
        val message: String
    )

    class ChatAdapter(
        context: Context,
        private val chatMessage: ChatMessage
    ) : BaseAdapter() {

        private val inflater: LayoutInflater = LayoutInflater.from(context)

        override fun getCount(): Int {
            return 1
        }

        override fun getItem(position: Int): Any {
            return chatMessage
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view: View
            if (convertView == null) {
                view = inflater.inflate(R.layout.item_chat, parent, false)
            } else {
                view = convertView
            }

            // Set message
            val textViewMessage = view.findViewById<TextView>(R.id.text_view_message)
            textViewMessage.text = chatMessage.message

            return view
        }
    }
}