package com.example.lungcancerapp.ui.fitur

import android.graphics.Typeface
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.lungcancerapp.R

class FiturFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fitur, container, false)
        val fiturContainer = view.findViewById<LinearLayout>(R.id.fiturContainer)

        val fiturList = listOf(
            "AGE" to "Usia pasien saat data dikumpulkan. Usia bisa menjadi indikator penting karena risiko kanker paru-paru meningkat seiring bertambahnya usia.",
            "GENDER" to "Jenis kelamin pasien. Dikodekan sebagai 0 untuk perempuan dan 1 untuk laki-laki.",
            "SMOKING" to "Apakah pasien memiliki kebiasaan merokok. Nilai 1 menunjukkan ya, dan 0 menunjukkan tidak. Merokok adalah faktor risiko utama kanker paru-paru.",
            "FINGER_DISCOLORATION" to "Perubahan warna pada jari pasien. Kondisi ini bisa menandakan masalah sirkulasi atau oksigenasi, yang berhubungan dengan penyakit paru.",
            "MENTAL_STRESS" to "Tingkat stres psikologis yang dirasakan pasien. Tingkat stres tinggi dapat berdampak negatif terhadap sistem kekebalan tubuh.",
            "POLLUTION" to "Paparan jangka panjang terhadap polusi udara. Polusi adalah faktor lingkungan yang sangat memengaruhi kesehatan paru-paru.",
            "ILLNESS" to "Riwayat penyakit jangka panjang lainnya seperti diabetes atau penyakit kronis yang bisa memengaruhi risiko kanker.",
            "ENERGY" to "Tingkat energi atau vitalitas pasien. Nilai 0 menunjukkan energi rendah dan 1 untuk energi tinggi.",
            "IMMUNE" to "Kondisi sistem kekebalan tubuh pasien. Nilai 1 menunjukkan sistem imun lemah, sedangkan 0 berarti normal.",
            "BREATHING" to "Apakah pasien mengalami kesulitan bernapas atau gangguan pernapasan seperti sesak atau napas pendek.",
            "THROAT" to "Ketidaknyamanan atau nyeri pada tenggorokan yang mungkin merupakan gejala awal gangguan saluran pernapasan.",
            "OXYGEN" to "Tingkat saturasi oksigen dalam darah pasien. Kadar oksigen rendah bisa mengindikasikan masalah paru-paru.",
            "CHEST" to "Keluhan sesak atau tekanan di area dada yang bisa menjadi gejala gangguan paru-paru atau jantung.",
            "FAMILY HISTORY" to "Riwayat keluarga dengan penyakit kanker paru-paru. Faktor genetik bisa meningkatkan risiko penyakit ini.",
            "FAMILY SMOKE" to "Apakah anggota keluarga pasien memiliki kebiasaan merokok. Paparan asap rokok pasif juga berisiko.",
            "STRESS IMMUNE" to "Interaksi antara stres dan sistem kekebalan tubuh. Kombinasi ini dapat memperparah kondisi kesehatan.",
            "RESULT" to "Hasil akhir diagnosis. Biasanya 0 = Tidak mengidap kanker paru-paru, 1 = Positif mengidap kanker paru-paru."
        )


        for ((judul, deskripsi) in fiturList) {
            val card = CardView(requireContext()).apply {
                radius = 16f
                setContentPadding(24, 16, 24, 16)
                cardElevation = 4f
                useCompatPadding = true
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 0, 0, 16)
                }
            }

            val innerLayout = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.VERTICAL
            }

            val judulView = TextView(requireContext()).apply {
                text = judul
                setTypeface(null, Typeface.BOLD)
                textSize = 16f
                setTextColor(ContextCompat.getColor(requireContext(), R.color.skyBlueDarkText))
            }

            val deskripsiView = TextView(requireContext()).apply {
                text = deskripsi
                textSize = 14f
                setTextColor(ContextCompat.getColor(requireContext(), android.R.color.black))
                setPadding(0, 4, 0, 0)
            }

            innerLayout.addView(judulView)
            innerLayout.addView(deskripsiView)
            card.addView(innerLayout)
            fiturContainer.addView(card)
        }

        return view
    }
}
