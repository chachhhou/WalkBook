package fr.isen.walkbook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
