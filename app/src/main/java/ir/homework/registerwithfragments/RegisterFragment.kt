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

        setListeners()
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
            val bundle = Bundle()
            bundle.putString("name", name)
            bundle.putString("username", username)
            bundle.putString("email", email)
            bundle.putString("password", pass1 as String)
            bundle.putBoolean("isFemale", isFemale)

//            editor.putString("name", name);
//            editor.putString("username", username)
//            editor.putString("email", email)
//            editor.putString("password",pass1 as String)
//            editor.putBoolean("isFemale",isFemale)
//            editor.apply()
            findNavController().navigate(R.id.action_registerFragment_to_infoFragment, bundle)
        }
    }


}