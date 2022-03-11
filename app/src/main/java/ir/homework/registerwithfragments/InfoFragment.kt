package ir.homework.registerwithfragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ir.homework.registerwithfragments.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {
    lateinit var binding: FragmentInfoBinding
    lateinit var sharedPreferences: SharedPreferences
    var name = ""
    var username = ""
    var email = ""
    var password = ""
    var isFemale: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name = arguments?.getString("name").toString()
        username = arguments?.getString("username").toString()
        email = arguments?.getString("email").toString()
        password = arguments?.getString("password").toString()
        isFemale = arguments?.getBoolean("isFemale")!!

        binding.tvFullName.text = "full name: " + name
        binding.tvUsername.text = "username: " + username
        binding.tvEmail.text = "email: " + email
        binding.tvPassword.text = "password: " + password
        binding.tvGender.text = "gender: " + when (isFemale) {
            true -> "Female"
            else -> "Male"
        }
        binding.btnSave.setOnClickListener{
            onSaveClick()
        }
    }

    private fun onSaveClick() {
        val editor = sharedPreferences.edit()
        editor.putString("fullName", name)
        editor.putString("username", username)
        editor.putString("email", email)
        editor.putString("password", password)
        editor.putBoolean("isFemale", isFemale)
        editor.apply()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        sharedPreferences = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
    }

}