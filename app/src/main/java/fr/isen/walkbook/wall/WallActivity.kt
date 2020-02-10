package fr.isen.walkbook.wall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.*

import com.google.firebase.firestore.FirebaseFirestore
import fr.isen.walkbook.GlobalVar
import fr.isen.walkbook.GlobalVar.Companion.posts
import fr.isen.walkbook.R
import fr.isen.walkbook.models.ListPostModel

class WallActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wall)
        writePost()
    }


    fun writePost() {
        var database: DatabaseReference
        database = FirebaseDatabase.getInstance().reference

        database.child("posts").setValue(posts)
        // val database = FirebaseDatabase.getInstance()
        //  val myRef = database.getReference("posts")

        database.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val postsUpdate = dataSnapshot.getValue(ListPostModel::class.java)

                GlobalVar.posts = postsUpdate?.posts

            }

        })
    }
}