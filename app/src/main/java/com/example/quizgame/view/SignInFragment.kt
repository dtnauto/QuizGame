package com.example.quizgame.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.example.quizgame.R
import com.example.quizgame.viewModel.AuthViewModel2
import com.example.quizgame.viewmodel.AuthViewModel
import com.google.android.material.textfield.TextInputEditText

class SignInFragment : Fragment() {

    lateinit var viewModel: AuthViewModel

    private lateinit var authViewModel: AuthViewModel2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[AuthViewModel::class.java] // cac fragment con có thẻ chia se dc dư lieu ban viewmodel

        authViewModel = ViewModelProvider(requireActivity())[AuthViewModel2::class.java] // cac frag trong 1 acty thì co the chia se du lieu dc vs nahu
    }

    private fun observerUserLiveData(owner: LifecycleOwner) {

        authViewModel.observerUserLiveData().observe(owner) {

            navController?.navigate(R.id.action_signInFragment_to_loginFragment)

        }
    }

    private fun onClick_BtnSignIn() {

        signInBtn?.setOnClickListener {

            val email = editEmail?.text.toString().trim()
            val passWord = editPass?.text.toString().trim()

            if(email.isNotEmpty() && passWord.isNotEmpty())
                authViewModel.signIn(email, passWord)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    private var navController: NavController? = null
    private var editEmail: EditText? = null
    private var editPass:EditText? = null
    private var signUpText: TextView? = null
    private var signInBtn: Button? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController(view)

        editEmail = view.findViewById<TextInputEditText>(R.id.txt_email)
        editPass = view.findViewById<TextInputEditText>(R.id.txt_password)
        signUpText = view.findViewById<Button>(R.id.btn_sign_up)
        signInBtn = view.findViewById<Button>(R.id.btn_sign_in)

        observerUserLiveData(this)
        onClick_BtnSignIn()
//        signUpText?.setOnClickListener() {
//            navController?.navigate(R.id.action_signInFragment_to_signUpFragment)
//        }
//
//        signInBtn?.setOnClickListener() {
//            val email = editEmail.getText().toString()
//            val pass: String = editPass.getText().toString()
//            if (!email.isEmpty() && !pass.isEmpty()) {
//                viewModel.signIn(email, pass)
//                Toast.makeText(context, "Login Successfully", Toast.LENGTH_SHORT).show()
//                viewModel.apply {
//                    Log.e("mycodeisblocking","aaaaaaaaaaaaaaaaaa")
//                    firebaseUserMutableLiveData.apply {
//                        Log.e("mycodeisblocking","bbbbbbbbbbbbbbbbbbbbbbbbb")
//                        observe(viewLifecycleOwner) {
////                            Log.e("mycodeisblocking","observer")
//                            //                            fun onChanged(firebaseUser: FirebaseUser?) {
////                                if (it != null) {
//                                Log.e("mycodeisblocking", "eeeeeeeeeeeeeeeeeeeeeee")
//                                navController?.navigate(R.id.action_signInFragment_to_loginFragment)
////                                }
////                            }
//                        }
//                    }
//                }
//            } else {
//                Toast.makeText(context, "Please Enter Email and Pass", Toast.LENGTH_SHORT).show()
//            }
//        }
    }

}