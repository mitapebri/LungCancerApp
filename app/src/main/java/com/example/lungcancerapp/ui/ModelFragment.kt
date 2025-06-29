package com.example.lungcancerapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lungcancerapp.R

class ModelFragment : Fragment() {

    data class SectionItem(val title: String, val description: String, val imageResId: Int)
    data class Section(val sectionTitle: String, val items: List<SectionItem>)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_model, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.modelRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val sectionList = listOf(
            Section(
                "1. Data Preparation", listOf(
                    SectionItem("Import Library", "Import library yang dibutuhkan untuk membaca dan memproses dataset.", R.drawable.data1),
                    SectionItem("Load Data", "Membaca dataset dari file CSV.", R.drawable.data2),
                    SectionItem("Explorasi Data", "Melihat jumlah baris dan kolom dari dataset.", R.drawable.data3),
                    SectionItem("Encoding Data", "Mengubah data kategori menjadi numerik.", R.drawable.data4),
                    SectionItem("Train Test Split", "Membagi data menjadi data latih dan data uji.", R.drawable.data5)
                )
            ),
            Section(
                "2. Modeling", listOf(
                    SectionItem("Import Model Library", "Mengimpor library Keras untuk membangun model.", R.drawable.model1),
                    SectionItem("Buat Model Sequential", "Menentukan arsitektur model (Dense layers, aktivasi ReLU dan sigmoid).", R.drawable.model2),
                    SectionItem("Compile Model", "Menentukan fungsi loss dan optimizer untuk pelatihan model.", R.drawable.model3),
                    SectionItem("Train Model", "Melatih model menggunakan data latih.", R.drawable.model4),
                    SectionItem("Evaluasi Model", "Visualisasi arsitektur model untuk melihat input dan output shape tiap layer.", R.drawable.model5)
                )
            ),
            Section(
                "3. Save Model", listOf(
                    SectionItem(
                        "Simpan dan Konversi Model",
                        "Model disimpan dalam format HDF5, lalu dikonversi ke format TensorFlow Lite (.tflite) agar bisa digunakan di aplikasi Android.",
                        R.drawable.save
                    )
                )
            )
        )

        recyclerView.adapter = ModelSectionAdapter(sectionList)
        return view
    }
}
