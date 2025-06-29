package com.example.lungcancerapp.ui.simulasi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.lungcancerapp.R
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class SimulasiFragment : Fragment() {

    private lateinit var interpreter: Interpreter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_simulasi, container, false)

        interpreter = Interpreter(loadModelFile("lungcancer.tflite"))

        val ageInput = view.findViewById<EditText>(R.id.inputAge)
        val genderGroup = view.findViewById<RadioGroup>(R.id.genderGroup)
        val energySeek = view.findViewById<SeekBar>(R.id.inputEnergySeek)
        val oxygenSeek = view.findViewById<SeekBar>(R.id.inputOxygenSeek)
        val energyValue = view.findViewById<TextView>(R.id.energyValue)
        val oxygenValue = view.findViewById<TextView>(R.id.oxygenValue)
        val resultText = view.findViewById<TextView>(R.id.textResult)

        val checkIds = listOf(
            R.id.checkSmoking,
            R.id.checkFinger,
            R.id.checkStress,
            R.id.checkPollution,
            R.id.checkIllness,
            R.id.checkImmune,
            R.id.checkBreathing,
            R.id.checkAlcohol,
            R.id.checkThroat,
            R.id.checkChest,
            R.id.checkFamily,
            R.id.checkFamilySmoke,
            R.id.checkStressImmune
        )

        energySeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                energyValue.text = progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        oxygenSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                oxygenValue.text = progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        val predictButton = view.findViewById<Button>(R.id.buttonPredict)

        predictButton.setOnClickListener {
            try {
                val inputList = mutableListOf<Float>()

                // 1. Age
                val age = ageInput.text.toString().toFloatOrNull() ?: 0f
                inputList.add(age)

                // 2. Gender
                val gender = when (genderGroup.checkedRadioButtonId) {
                    R.id.genderMale -> 1f
                    R.id.genderFemale -> 0f
                    else -> 0f
                }
                inputList.add(gender)

                // 3–7. 5 checkbox pertama
                for (i in 0..4) {
                    val check = view.findViewById<CheckBox>(checkIds[i])
                    inputList.add(if (check.isChecked) 1f else 0f)
                }

                // 8. Energy (dibagi 100)
                inputList.add(energySeek.progress.toFloat() / 100f)

                // 9–12. 4 checkbox lanjutan
                for (i in 5..8) {
                    val check = view.findViewById<CheckBox>(checkIds[i])
                    inputList.add(if (check.isChecked) 1f else 0f)
                }

                // 13. Oxygen saturation (dibagi 100)
                inputList.add(oxygenSeek.progress.toFloat() / 100f)

                // 14–17. 4 checkbox terakhir
                for (i in 9..12) {
                    val check = view.findViewById<CheckBox>(checkIds[i])
                    inputList.add(if (check.isChecked) 1f else 0f)
                }

                val inputArray = arrayOf(inputList.toFloatArray())
                val output = Array(1) { FloatArray(1) }

                interpreter.run(inputArray, output)

                val prediction = output[0][0]
                resultText.text = if (prediction >= 0.5f) {
                    "Hasil Prediksi: Risiko Tinggi (Kemungkinan Kanker Paru-paru)"
                } else {
                    "Hasil Prediksi: Risiko Rendah (Kemungkinan Aman)"
                }

            } catch (e: Exception) {
                resultText.text = "Terjadi kesalahan input: ${e.message}"
            }
        }

        return view
    }

    private fun loadModelFile(filename: String): MappedByteBuffer {
        val fileDescriptor = requireContext().assets.openFd(filename)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, fileDescriptor.startOffset, fileDescriptor.declaredLength)
    }
}
