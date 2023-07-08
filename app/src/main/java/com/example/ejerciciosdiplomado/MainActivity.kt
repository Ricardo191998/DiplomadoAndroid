package com.example.ejerciciosdiplomado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.ejerciciosdiplomado.ejercicio_two.TwoActivity
import com.example.ejerciciosdiplomado.ejercicio_uno.CicloVida
import com.example.ejerciciosdiplomado.ejercicio_uno.FirstActivity
import com.example.ejerciciosdiplomado.ejercicio_uno.IntentImplicitoActivity
import com.example.ejerciciosdiplomado.login.LoginActivity
import com.example.ejerciciosdiplomado.registro.RegistroActivity
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val toolbar: Toolbar = findViewById<Toolbar>(R.id.toolbar)
        // setSupportActionBar(toolbar)

        setupView()
    }

    private fun setupView() {
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.navigation_view)

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener { menuItem ->
            // Manejar el clic en los elementos del menú aquí
            when (menuItem.itemId) {
                R.id.nav_item0 -> {
                    // Acción para el elemento 1
                    val intent = Intent(this, CicloVida::class.java)
                    startActivity(intent)
                }
                R.id.nav_item1 -> {
                    // Acción para el elemento 1
                    val intent = Intent(this, IntentImplicitoActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_item2 -> {
                    // Acción para el elemento 2
                    val intent = Intent(this, FirstActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_item3 -> {
                    // Acción para el elemento 2
                    val intent = Intent(this, TwoActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_item4 -> {
                    // Acción para el elemento 2
                    val intent = Intent(this, RegistroActivity::class.java)
                    startActivity(intent)
                }

                R.id.nav_item5 -> {
                    // Acción para el elemento 2
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
            }
            drawerLayout.closeDrawers()
            true
        }
    }

}