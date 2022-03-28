package my.example.pertemuan7_71190483

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listContact = arrayListOf<Contact>()
        listContact.add(Contact("Aldodi", R.mipmap.orang1, "0814258790", "aldodi15@gmail.com"))
        listContact.add(Contact("Andy", R.mipmap.orang2, "3758430857", "andykick99@gmail.com"))
        listContact.add(Contact("Rose", R.mipmap.orang3, "1789364902", "rosemikey03@gmail.com"))
        listContact.add(Contact("Lucy", R.mipmap.orang4, "0812679064", "lucykent10@gmail.com"))
        listContact.add(Contact("Lydia", R.mipmap.orang5, "0813678906", "lydiaa28@gmail.com"))
        listContact.add(Contact("Shinta", R.mipmap.orang6, "08172589036", "shintacantik69@gmail.com"))
        listContact.add(Contact("Cantika", R.mipmap.orang7, "0828147958", "cantikadea12@gmail.com"))
        listContact.add(Contact("Dewi", R.mipmap.orang8, "0817888902", "dewisekarr@gmail.com"))
        listContact.add(Contact("Diana", R.mipmap.orang9, "08789090675", "dianareal606@gmail.com"))
        listContact.add(Contact("Ayu", R.mipmap.orang10, "0822226722", "ayubobba22@gmail.com"))


        val rvContact = findViewById<RecyclerView>(R.id.rvContact)
        rvContact.layoutManager = LinearLayoutManager(this)
        val contactAdapter = ContactAdapter(listContact)
        rvContact.adapter = contactAdapter
    }
}