package my.example.a71190483_final

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth


    var firestore: FirebaseFirestore? = null
    var listBuku = arrayListOf<Buku>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firestore = FirebaseFirestore.getInstance()

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        //variable untuk pencarian
        val btnCari = findViewById<ImageButton>(R.id.btnCari)
        val edtCari = findViewById<EditText>(R.id.edtSearch)
        val rvBuku = findViewById<RecyclerView>(R.id.rvBuku)
        val btnAdd = findViewById<FloatingActionButton>(R.id.fab_add)


        btnAdd.setOnClickListener {
            val i = Intent(this, CreateActivity::class.java)
            startActivity(i)
        }


        firestore?.collection("buku")?.get()?.addOnSuccessListener { docs ->
            var hasil = ""
            for (doc in docs) {
                hasil += "${doc["judul"]}"
                val bukuAdd = Buku(
                    "${doc["judul"]}",
                    "${doc["penulis"]}",
                    "${doc["penerbit"]}",
                    "${doc["halaman"]}",
                    "${doc["tahun"]}"
                )
                listBuku.add(bukuAdd)
            }

        }
        Handler().postDelayed({
            rvBuku.layoutManager = LinearLayoutManager(this)
            val adapter = BukuAdapter(listBuku, this)
            rvBuku.adapter = adapter
        }, 1000)

        btnCari.setOnClickListener {
            var pencarian = edtCari.text.toString()
            if (pencarian.isEmpty()) {
                Toast.makeText(this, "Pencarian Kosong", Toast.LENGTH_SHORT).show()
                Handler().postDelayed({
                    rvBuku.layoutManager = LinearLayoutManager(this)
                    val adapter = BukuAdapter(listBuku, this)
                    rvBuku.adapter = adapter
                }, 1000)
            } else if (!pencarian.isEmpty()) {
                listBuku.clear()
                firestore?.collection("buku")?.get()?.addOnSuccessListener { docs ->
                    for (cari in docs) {
                        var bool = true
                        val bukuCari = Buku(
                            "${cari["judul"]}",
                            "${cari["penulis"]}",
                            "${cari["penerbit"]}",
                            "${cari["halaman"]}",
                            "${cari["tahun"]}"
                        )
                        if (pencarian.equals("${cari["judul"]}") && bool) {
                            Toast.makeText(this, "Pencarian Ditemukan", Toast.LENGTH_SHORT).show()
                            bool = false
                            listBuku.add(bukuCari)
                        }
                        if (pencarian.equals("${cari["penulis"]}") && bool) {
                            Toast.makeText(this, "Pencarian Ditemukan", Toast.LENGTH_SHORT).show()
                            bool = false
                            listBuku.add(bukuCari)
                        }
                        if (pencarian.equals("${cari["penerbit"]}") && bool) {
                            Toast.makeText(this, "Pencarian Ditemukan", Toast.LENGTH_SHORT).show()
                            bool = false
                            listBuku.add(bukuCari)
                        }
                        if (pencarian.equals("${cari["halaman"]}") && bool) {
                            Toast.makeText(this, "Pencarian Ditemukan", Toast.LENGTH_SHORT).show()
                            bool = false
                            listBuku.add(bukuCari)
                        }
                        if (pencarian.equals("${cari["tahun"]}") && bool) {
                            Toast.makeText(this, "Pencarian Ditemukan", Toast.LENGTH_SHORT).show()
                            bool = false
                            listBuku.add(bukuCari)
                        }
                    }
                }

                Handler().postDelayed({
                    rvBuku.layoutManager = LinearLayoutManager(this)
                    val adapter = BukuAdapter(listBuku, this)
                    rvBuku.adapter = adapter
                },1000)
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