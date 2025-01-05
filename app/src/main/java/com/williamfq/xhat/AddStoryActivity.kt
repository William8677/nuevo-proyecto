package com.williamfq.xhat

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.williamfq.data.dao.StoryDao
import com.williamfq.data.entities.MediaType
import com.williamfq.data.entities.Story
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Actividad para agregar historias en Xhat.
 */
@AndroidEntryPoint
class AddStoryActivity : AppCompatActivity() {

    @Inject
    lateinit var storyDao: StoryDao

    private lateinit var etStoryTitle: EditText
    private lateinit var etStoryDescription: EditText
    private lateinit var btnUploadStory: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_story)

        // Inicialización de vistas
        etStoryTitle = findViewById(R.id.etStoryTitle)
        etStoryDescription = findViewById(R.id.etStoryDescription)
        btnUploadStory = findViewById(R.id.btnUploadStory)

        // Configurar listener para el botón
        btnUploadStory.setOnClickListener {
            val title = etStoryTitle.text.toString().trim()
            val description = etStoryDescription.text.toString().trim()
            uploadStory(title, description)
        }
    }

    /**
     * Sube una historia a la base de datos.
     *
     * @param title Título de la historia.
     * @param description Descripción de la historia.
     */
    private fun uploadStory(title: String, description: String) {
        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
        } else {
            lifecycleScope.launch(Dispatchers.IO) {
                try {
                    val currentUserId = getCurrentUserId()
                    val currentTimestamp = System.currentTimeMillis()

                    // Datos ficticios para mediaUrl y mediaType
                    val mediaUrl = "https://example.com/default.jpg" // Reemplazar con URL real
                    val mediaType = MediaType.IMAGE // Reemplazar con el tipo real

                    // Crea una instancia de Story
                    val newStory = Story(
                        userId = currentUserId,
                        title = title,
                        description = description,
                        mediaUrl = mediaUrl,
                        mediaType = mediaType,
                        timestamp = currentTimestamp,
                        isActive = true,
                        views = 0,
                        duration = 24,
                        tags = listOf("example", "story"), // Etiquetas ficticias
                        comments = emptyList(),
                        reactions = emptyList(),
                        poll = null
                    )

                    // Inserta la historia en la base de datos
                    storyDao.insertStory(newStory)

                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@AddStoryActivity,
                            "Historia subida exitosamente: $title",
                            Toast.LENGTH_SHORT
                        ).show()
                        etStoryTitle.text.clear()
                        etStoryDescription.text.clear()
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@AddStoryActivity,
                            "Error al subir la historia: ${e.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    /**
     * Obtiene el ID del usuario actual.
     *
     * @return ID del usuario como String.
     */
    private suspend fun getCurrentUserId(): String {
        // Implementar la lógica real para obtener el ID del usuario autenticado.
        // Ejemplo: Firebase Auth o almacenamiento local
        return "12345" // Placeholder para el ID del usuario
    }
}