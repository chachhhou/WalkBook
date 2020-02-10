package fr.isen.walkbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import fr.isen.walkbook.models.UserModel
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance ()
    val storage = FirebaseStorage.getInstance()

    // Create a storage reference from our app
    val storageRef = storage.reference


    var user: UserModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        logOutButton.setOnClickListener {

            val intent = Intent(this, AuthActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            Toast.makeText(this,"Déconnexion",Toast.LENGTH_LONG).show()
            finish()
        }

        db.collection("user").document("W0yFOXFJhkzpb9RzrzlU")
                //TODO recuperer l'ID du prefshared
            .get()

            .addOnSuccessListener { result ->
                if(result != null) {
                  user = result.toObject(UserModel::class.java)
                    nameID.text = getString(R.string.nameID, user?.lastName)
                    firstNameID.text = getString(R.string.firstNameID) + ""+ user?.firstName
                    ageProfile.text = getString(R.string.ageProfile) + " " +user?.age

                    user?.userPicture?.let {
                        storageRef.child(it).downloadUrl.addOnSuccessListener {
                        Picasso.get().load(it).resize(200, 200).into(imageProfile)
                    }.addOnFailureListener {
                        // Handle any errors
                    } }

                }
                Log.d("bdd","data : ${result.data}")

            }
            .addOnFailureListener { exception ->
                Log.w("erreur", "Error getting documents.", exception)
            }


    }
}
