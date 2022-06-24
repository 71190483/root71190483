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

class UpdateActivity : AppCompatActivity() {

    var firestore: FirebaseFirestore? = null
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()



        val getJudul = intent.getStringExtra("judul").toString()
        val getPenulis = intent.getStringExtra("penulis").toString()
        val getPenerbit = intent.getStringExtra("penerbit").toString()
        val getHalaman = intent.getStringExtra("halaman").toString()
        val getTahun = intent.getStringExtra("tahun").toString()

        val edtJudul = findViewById<EditText>(R.id.edtUpdateJudul)
        val edtPenulis = findViewById<EditText>(R.id.edtUpdatePenulis)
        val edtPenerbit = findViewById<EditText>(R.id.edtUpdatePenerbit)
        val edtHalaman= findViewById<EditText>(R.id.edtUpdateHalaman)
        val edtTahun = findViewById<EditText>(R.id.edtUpdateTahun)

        val btnSave = findViewById<Button>(R.id.btnSimpan)

        edtJudul.setText(getJudul)
        edtPenulis.setText(getPenulis)
        edtPenerbit.setText(getPenerbit)
        edtHalaman.setText(getHalaman)
        edtTahun.setText(getTahun)

        btnSave.setOnClickListener {
            val updateFilm = Buku(
                edtJudul.text.toString(),
                edtPenulis.text.toString(),
                edtPenerbit.text.toString(),
                edtHalaman.text.toString(),
                edtTahun.text.toString()
            )
            firestore?.collection("buku")?.whereEqualTo("judul", getJudul)?.get()!!
                .addOnSuccessListener {
                    for (update in it) {
                        firestore?.collection("buku")?.document(update.id)?.set(updateFilm)
                            ?.addOnCompleteListener {
                                if (it.isSuccessful) {
                                    Toast.makeText(this, "Update Berhasil", Toast.LENGTH_SHORT)
                                        .show()
                                    val i = Intent(this, MainActivity::class.java)
                                    startActivity(i)
                                }
                            }
                    }
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


        }
        return super.onOptionsItemSelected(item)
    }
}