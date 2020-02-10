package fr.isen.walkbook

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import fr.isen.walkbook.wall.WallActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        logOutButton.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        addButton.setOnClickListener {
            val intent = Intent(this, CreateDescriptionActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        profilButton.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        wallButton.setOnClickListener {
            val intent = Intent(this, WallActivity::class.java)
            startActivity(intent)
        }
        startSignInActivity()
    }

    //FOR DATA
    // 1 - Identifier for Sign-In Activity
    private val RC_SIGN_IN = 123



    // --------------------
    // ACTIONS
    // --------------------

    fun onClickLoginButton() {
        // 3 - Launch Sign-In Activity when user clicked on Login Button
        startSignInActivity()
    }


    // --------------------
    // NAVIGATION
    // --------------------

    // 2 - Launch Sign-In Activity
    private fun startSignInActivity() {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setTheme(R.style.LoginTheme)
                .setAvailableProviders(
                    Arrays.asList(AuthUI.IdpConfig.EmailBuilder().build())
                )
                .setIsSmartLockEnabled(false, true)
                //.setLogo(R.drawable.ic_logo_auth)
                .build(),
            RC_SIGN_IN
        )
    }
}
