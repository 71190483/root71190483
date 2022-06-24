package my.example.a71190483_final

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CreateActivity: AppCompatActivity() {
    var firestore : FirebaseFirestore? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()



        val edtJudul = findViewById<EditText>(R.id.edtJudul)
        val edtPenulis = findViewById<EditText>(R.id.edtPenulis)
        val edtPenerbit = findViewById<EditText>(R.id.edtPenerbit)
        val edtHalaman = findViewById<EditText>(R.id.edtHalaman)
        val edtTahun = findViewById<EditText>(R.id.edtTahun)
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            val buku = Buku(
                edtJudul.text.toString(), edtPenulis.text.toString(),
                edtPenerbit.text.toString(), edtHalaman.text.toString(),
                edtTahun.text.toString()
            )

            edtJudul.setText("")
            edtPenulis.setText("")
            edtPenerbit.setText("")
            edtHalaman.setText("")
            edtTahun.setText("")
            firestore?.collection("buku")?.add(buku)?.addOnSuccessListener {
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
                Toast.makeText(this, "Tambah Data Berhasil", Toast.LENGTH_SHORT).show()
            }

        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.button_navigation, menu)
        return true

    }
    override fun onOptionsItemSelected(item: MenuItem) : Boolean {
        when(item.itemId){
            R.id.ic_home -> {
                val home = Intent(this, MainActivity::class.java)
                startActivity(home)
                this.finish()
            }
            R.id.ic_profile -> {
                val profile = Intent(this, ProfileActivity::class.java)
                startActivity(profile)
            }

            R.id.ic_exit -> {
                this.finish()
                true

            }
            else -> true

        }
        return super.onOptionsItemSelected(item)
    }
}