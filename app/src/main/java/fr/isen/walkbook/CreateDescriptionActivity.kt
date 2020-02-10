package fr.isen.walkbook

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_create_description.*
import java.util.*


class CreateDescriptionActivity : AppCompatActivity() {

    companion object {
        val pictureRequestCode = 1
    }

    val db = FirebaseFirestore.getInstance ()
    val storage = FirebaseStorage.getInstance()

    // Create a storage reference from our app
    val storageRef = storage.reference

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
        val locale1 = Locale.FRANCE
        val tz1 = TimeZone.getTimeZone("GMT")
        val cal1 = Calendar.getInstance(tz1, locale1)

        val database = FirebaseFirestore.getInstance()
        // Create a new post with a description and a date
        val publication = hashMapOf(
            "description" to "test",
            "date" to cal1,
            "date" to "2020/02/10"
        )
        // Add a new document with a generated ID
        database.collection("post")
            .add(publication)
            .addOnSuccessListener { documentReference ->
                Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error adding document", e)
            }
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
