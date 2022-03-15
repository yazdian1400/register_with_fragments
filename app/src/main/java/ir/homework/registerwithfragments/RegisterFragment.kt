package ir.homework.registerwithfragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import ir.homework.registerwithfragments.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {
    lateinit var binding: FragmentRegisterBinding
    lateinit var sharedPreferences: SharedPreferences
    var pass1 : String? = ""
    var pass2 : String?= ""
    var isFemale = true
    var isPassTrue = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments?.getBoolean("hasRegistered") != null)
            showInfoIfRegistered()
        setListeners()
    }

    private fun showInfoIfRegistered() {
        val name = sharedPreferences.getString("fullName", "")
        val username = sharedPreferences.getString("username", "")
        val email = sharedPreferences.getString("email", "")
        val password = sharedPreferences.getString("password", "")
        val isFemale = sharedPreferences.getBoolean("isFemale", false)

        if (name != "") {
            binding.etFullName.setText(name)
            binding.etUsername.setText(username)
            binding.etEmail.setText(email)
            binding.etPassword.setText(password)
            binding.etPassword2.setText(password)
            binding.radioGroup.check(when (isFemale) {
                true -> R.id.rb_female
                else -> R.id.rb_male
            })
        }
    }

    private fun setListeners() {
        binding.btnRegister.setOnClickListener {
            register()
        }
    }

    fun register(){
        val name = binding.etFullName.text.toString()
        val username = binding.etUsername.text.toString()
        val email = binding.etEmail.text.toString()
        pass1 = binding.etPassword.text.toString()
        pass2 = binding.etPassword2.text.toString()
        isPassTrue = pass1 != null && pass2 != null && pass1 == pass2
        isFemale = when (binding.radioGroup.checkedRadioButtonId){
            binding.rbFemale.id -> true
            binding.rbMale.id -> false
            else -> true
        }

        if (name.isNullOrBlank() || username.isNullOrBlank() || email.isNullOrBlank() || pass1.isNullOrBlank() || !isPassTrue){
            if (isPassTrue == false){
                Toast.makeText(activity, "The password repeat does not match!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(activity, "Please fill all fields.", Toast.LENGTH_LONG).show()
            }
        } else {
            val action = RegisterFragmentDirections.registerAction(name,username,email, pass1!!, isFemale)
            findNavController().navigate(action)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        sharedPreferences = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
    }
}