package com.example.lungcancerapp.ui.dataset

import android.graphics.Typeface
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.lungcancerapp.R

class DatasetFragment : Fragment() {

    private val datasetList = mutableListOf<DatasetItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dataset, container, false)

        val columnContainer = view.findViewById<LinearLayout>(R.id.dataColumnContainer)
        val textDeskripsi = view.findViewById<TextView>(R.id.textDescription)
        val textJudulTabel = view.findViewById<TextView>(R.id.textTableTitle)
        val textSource = view.findViewById<TextView>(R.id.textSourceLink)
        val expandTitle = view.findViewById<TextView>(R.id.expandDeskripsiTitle)

        // Enable hyperlink
        textSource.movementMethod = LinkMovementMethod.getInstance()

        // Deskripsi toggle show/hide
        expandTitle.setOnClickListener {
            if (textDeskripsi.visibility == View.GONE) {
                textDeskripsi.visibility = View.VISIBLE
                expandTitle.text = "Deskripsi Dataset"
            } else {
                textDeskripsi.visibility = View.GONE
                expandTitle.text = "Deskripsi Dataset"
            }
        }

        textDeskripsi.text = """
Dataset ini terdiri dari 16 kolom dan 10 baris pertama yang ditampilkan di bawah ini. 
Kami hanya menampilkan sebagian kecil dari data untuk menjaga performa dan keterbacaan tampilan.

Dataset ini mencakup berbagai fitur yang berkaitan dengan faktor risiko kanker paru-paru, 
termasuk usia, riwayat keluarga, kebiasaan merokok, tingkat stres, dan lain-lain.

Setiap kolom merepresentasikan variabel yang penting dalam model prediksi berbasis machine learning 
yang digunakan untuk menganalisis kemungkinan terjadinya kanker paru-paru.
""".trimIndent()

        textJudulTabel.text = "📊 Contoh Data (10 Baris Pertama):"

        loadCSVFromAssets()

        val columns = listOf(
            "Age" to { d: DatasetItem -> d.age },
            "Gender" to { d -> d.gender },
            "Smoking" to { d -> d.smoking },
            "Finger Discolor" to { d -> d.fingerDiscolor },
            "Stress" to { d -> d.mentalStress },
            "Pollution" to { d -> d.pollution },
            "Illness" to { d -> d.illness },
            "Energy" to { d -> d.energy },
            "Immune" to { d -> d.immune },
            "Breathing" to { d -> d.breathing },
            "Throat" to { d -> d.throat },
            "Oxygen" to { d -> d.oxygen },
            "Chest" to { d -> d.chest },
            "Family History" to { d -> d.familyHistory },
            "Family Smoke" to { d -> d.familySmoke },
            "Stress Immune" to { d -> d.stressImmune },
            "Result" to { d -> d.result }
        )

        val limitedData = datasetList.take(10)
        val columnWidth = resources.getDimensionPixelSize(R.dimen.table_column_width)

        for ((label, accessor) in columns) {
            val columnLayout = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.VERTICAL
                layoutParams = LinearLayout.LayoutParams(columnWidth, LinearLayout.LayoutParams.WRAP_CONTENT)
            }

            val headerView = TextView(requireContext()).apply {
                text = label
                setTypeface(null, Typeface.BOLD)
                gravity = Gravity.CENTER
                textAlignment = View.TEXT_ALIGNMENT_CENTER
                setTextColor(ContextCompat.getColor(requireContext(), android.R.color.black))
                setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.skyBlue))
                setPadding(8, 6, 8, 6)
                width = columnWidth
            }

            columnLayout.addView(headerView)

            for (item in limitedData) {
                val cellView = TextView(requireContext()).apply {
                    text = accessor(item)
                    gravity = Gravity.CENTER
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    setTextColor(ContextCompat.getColor(requireContext(), android.R.color.black))
                    setBackgroundResource(android.R.drawable.editbox_background)
                    setPadding(8, 6, 8, 6)
                    width = columnWidth
                }
                columnLayout.addView(cellView)
            }

            columnContainer.addView(columnLayout)
        }

        return view
    }

    private fun loadCSVFromAssets() {
        try {
            val inputStream = requireContext().assets.open("lung_cancer_data.csv")
            val lines = inputStream.bufferedReader().readLines()

            for (i in 1 until lines.size) {
                val tokens = lines[i].split(",")
                if (tokens.size >= 17) {
                    datasetList.add(
                        DatasetItem(
                            tokens[0], tokens[1], tokens[2], tokens[3],
                            tokens[4], tokens[5], tokens[6], tokens[7],
                            tokens[8], tokens[9], tokens[10], tokens[11],
                            tokens[12], tokens[13], tokens[14], tokens[15], tokens[16]
                        )
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
