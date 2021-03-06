package com.example.smkcodingchallenge1

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    private var image :Uri?= null
    private var name : String = ""
    private var age : String = ""
    private var gender : String = ""
    private var email : String = ""
    private var telp : String = ""
    private var address : String = ""

    companion object {
        val REQUEST_CODE = 100
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        getProfileData()
        btnEditProfile.setOnClickListener{goToEditProfile()}
        btnAbout.setOnClickListener { goToAbout() }
        btnCall.setOnClickListener { dialPhoneNumber(txtTelephone.text.toString()) }
    }

    private fun getProfileData(){
        val bundle = intent.extras
        if(intent.extras!=null){
            image = Uri.parse( bundle?.getString("image"))
            name = bundle?.getString("name").toString()
            gender = bundle?.getString("gender").toString()
            age = bundle?.getString("age").toString()
            email = bundle?.getString("email").toString()
            telp = bundle?.getString("telp").toString()
            address = bundle?.getString("address").toString()
            iv_image.setImageURI(image)
            txtName.text = name
            txtGender.text = gender
            txtAge.text = age
            txtEmail.text = email
            txtTelephone.text = telp
            txtAddress.text = address
        }
    }

    private fun goToEditProfile(){
        val intent = Intent(this, EditProfileActivity::class.java)
        intent.putExtra("image", image.toString())
        intent.putExtra("name", name)
        intent.putExtra("gender", gender)
        intent.putExtra("age", age)
        intent.putExtra("email", email)
        intent.putExtra("telp", telp)
        intent.putExtra("address", address)
        startActivityForResult(intent, REQUEST_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data:
    Intent?) {
        if (requestCode == REQUEST_CODE){
            if (resultCode == Activity.RESULT_OK) {
                image = Uri.parse( data?.getStringExtra("image"))
                name = data?.getStringExtra("name").toString()
                gender = data?.getStringExtra("gender").toString()
                age = data?.getStringExtra("age").toString()
                email = data?.getStringExtra("email").toString()
                telp = data?.getStringExtra("telp").toString()
                address = data?.getStringExtra("address").toString()

                iv_image.setImageURI(image)

                txtName.text = name
                txtGender.text = gender
                txtAge.text = age
                txtEmail.text = email
                txtTelephone.text = telp
                txtAddress.text = address
            }else{
                Toast.makeText(this, "Edit failed",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun goToAbout(){
    val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }

    private fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }


    }
}
