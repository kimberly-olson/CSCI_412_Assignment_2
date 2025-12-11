package com.example.greetingcard

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import java.io.File

class ThirdActivity : AppCompatActivity() {

    private lateinit var openCamera: Button
    private lateinit var clickedImage: ImageView

    private lateinit var photoFile: File
    private lateinit var photoUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        openCamera = findViewById(R.id.camera_open)
        clickedImage = findViewById(R.id.click_image)

        openCamera.setOnClickListener {
            checkPermissionAndLaunchCamera()
        }
    }

    private val cameraPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) {
                launchCamera()
            } else {
                Toast.makeText(this, "Camera permission is required", Toast.LENGTH_SHORT).show()
            }
        }

    private val takePictureLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                Glide.with(this).load(photoUri).into(clickedImage)
            }
        }

    private fun checkPermissionAndLaunchCamera() {
        cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
    }

    private fun launchCamera() {
        photoFile = createImageFile()
        photoUri = FileProvider.getUriForFile(
            this,
            "com.example.greetingcard.fileprovider",
            photoFile
        )

        takePictureLauncher.launch(photoUri)
    }

    private fun createImageFile(): File {
        val imageDir = File(cacheDir, "images").apply { mkdirs() }
        return File.createTempFile("captured_", ".jpg", imageDir)
    }
}
