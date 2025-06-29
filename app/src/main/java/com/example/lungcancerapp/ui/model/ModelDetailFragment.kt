package com.example.lungcancerapp.ui.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lungcancerapp.databinding.FragmentModelDetailBinding
import com.example.lungcancerapp.R
import androidx.navigation.fragment.navArgs

class ModelDetailFragment : Fragment() {

    private var _binding: FragmentModelDetailBinding? = null
    private val binding get() = _binding!!

    private val args: ModelDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentModelDetailBinding.inflate(inflater, container, false)

        val tipe = args.tipe
        val sectionList = getContentForType(tipe)

        binding.recyclerViewDetail.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewDetail.adapter = ModelDetailAdapter(sectionList)

        binding.textDetailTitle.text = when (tipe) {
            "arsitektur" -> "Arsitektur Model"
            "data" -> "Data Preparation"
            "modeling" -> "Modeling"
            "save" -> "Save Model"
            else -> "Detail Materi"
        }
        return binding.root
    }

    private fun getContentForType(tipe: String): List<ModelItem> {
        return when (tipe) {
            "arsitektur" -> listOf(
                ModelItem(
                    "Arsitektur Model",
                    "Model ini menggunakan arsitektur Artificial Neural Network (ANN) dengan 4 lapisan:\n" +
                            "- Dense(128) + ReLU\n" +
                            "- Dense(64) + ReLU\n" +
                            "- Dense(32) + ReLU\n" +
                            "- Dense(1) + Sigmoid\n\n" +
                            "Model ini dirancang untuk memprediksi risiko kanker paru-paru berdasarkan 17 fitur kesehatan dan gaya hidup pasien. " +
                            "Output model berupa probabilitas antara 0 hingga 1 yang mencerminkan tingkat risiko rendah hingga tinggi.",
                    null
                )
            )
            "data" -> listOf(
                ModelItem("Encoding variabel target", "Mengubah label target 'PULMONARY_DISEASE' dari bentuk kategori ('NO', 'YES') menjadi numerik (0 untuk 'NO', 1 untuk 'YES') agar bisa digunakan oleh model Machine Learning.", R.drawable.data1),
                ModelItem("Memisahkan fitur dan target", "x berisi fitur (semua kolom selain 'PULMONARY_DISEASE')\n" +
                        "\n" +
                        "y berisi target (kolom 'PULMONARY_DISEASE')", R.drawable.data2),
                ModelItem("Normalisasi fitur", "Menggunakan StandardScaler untuk menstandarkan fitur (rata-rata = 0, standar deviasi = 1).\n" +
                        "\n" +
                        "x_scaled adalah hasil fitur yang sudah dinormalisasi.", R.drawable.data3),
                ModelItem("Membagi data menjadi training dan testing\n" +
                        "python\n" +
                        "Copy\n" +
                        "Edit\n", "Membagi data menjadi:\n" +
                        "\n" +
                        "80% data training (X_train, y_train)\n" +
                        "\n" +
                        "20% data testing (X_test, y_test)\n" +
                        "\n" +
                        "stratify=y memastikan distribusi kelas tetap seimbang antara training dan testing.", R.drawable.data4),
                ModelItem("Menghitung class weights (jika data tidak seimbang)", "Menghitung bobot tiap kelas untuk menangani data tidak seimbang.\n" +
                        "\n" +
                        "Diperlukan agar model tidak bias terhadap kelas mayoritas.", R.drawable.data5)
            )
            "modeling" -> listOf(
                ModelItem("Membuat arsitektur model", "Model ANN (Artificial Neural Network) dengan 4 layer:\n" +
                        "\n" +
                        "3 hidden layer (ReLU activation)\n" +
                        "\n" +
                        "1 output layer (Sigmoid untuk klasifikasi biner)", R.drawable.model1),
                ModelItem("Menyusun model (kompilasi)", "Optimizer: Adam dengan learning rate 0.001\n" +
                        "\n" +
                        "Loss function: Binary Crossentropy (karena klasifikasi biner)\n" +
                        "\n" +
                        "Metrik evaluasi: Accuracy", R.drawable.model2),
                ModelItem("Callback untuk early stopping", "Menghentikan training lebih awal jika val_loss tidak membaik selama 10 epoch.\n" +
                        "\n" +
                        "restore_best_weights=True akan mengembalikan model ke kondisi terbaiknya (saat val_loss terendah).\n" +
                        "\n", R.drawable.model3),
                ModelItem("Visualisasi arsitektur model (opsional)\n" +
                        "python\n" +
                        "Copy\n" +
                        "Edit\n", "Menampilkan diagram struktur model, bentuk input/output per layer.\n" +
                        "\n" +
                        "Tidak wajib, hanya untuk membantu pemahaman.\n" +
                        "\n", R.drawable.model4),
                ModelItem("Melatih model", "Melatih model menggunakan data training (X_train, y_train)\n" +
                        "\n" +
                        "20% dari data training digunakan sebagai validation set\n" +
                        "\n" +
                        "Epoch: 50 kali iterasi\n" +
                        "\n" +
                        "Batch size: 16\n" +
                        "\n" +
                        "Menggunakan callback early_stop", R.drawable.model5)
            )
            "save" -> listOf(
                ModelItem(
                    "Simpan & Konversi Model",
                    "Model disimpan dalam format .h5 lalu dikonversi ke .tflite agar bisa digunakan di Android.",
                    R.drawable.save
                )
            )
            else -> emptyList()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
