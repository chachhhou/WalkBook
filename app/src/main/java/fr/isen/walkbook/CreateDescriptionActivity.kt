package fr.isen.walkbook

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import fr.isen.walkbook.models.ListPostModel
import fr.isen.walkbook.models.PostModel
import kotlinx.android.synthetic.main.activity_create_description.*
import java.util.*


class CreateDescriptionActivity : AppCompatActivity() {

    companion object {
        val pictureRequestCode = 1
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_description)

        pictureButton.setOnClickListener {
            onChangePhoto()
        }
        shareButton.setOnClickListener {
            sharePublication()
        }
    }

    fun onChangePhoto() {
        val galleryIntent = Intent(Intent.ACTION_PICK)
        galleryIntent.type = "image/*"

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        val intentChooser = Intent.createChooser(galleryIntent, "Choose your picture library")
        intentChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(cameraIntent))
        startActivityForResult(intentChooser, CreateDescriptionActivity.pictureRequestCode)
    }

    fun sharePublication(){
        var newPost: PostModel = PostModel("postPicture/image.jpeg","10/10/20","je suis une description","W0yFOXFJhkzpb9RzrzlU")

        writePostOnCloud(newPost)

    }
    fun writePostOnCloud(posts: PostModel) {
        var database: DatabaseReference
        database = FirebaseDatabase.getInstance().reference
        Log.d("creation","juste avant de enregistrer ")
        database.child("posts").setValue(posts)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CreateDescriptionActivity.pictureRequestCode &&
            resultCode == Activity.RESULT_OK) {
            if(data?.data != null) { // Gallery
                pictureButton.setImageURI(data?.data)
            } else { // Camera
                val bitmap = data?.extras?.get("data") as? Bitmap
                bitmap?.let {
                    pictureButton.setImageBitmap(it)
                }
            }
        }
    }
}
