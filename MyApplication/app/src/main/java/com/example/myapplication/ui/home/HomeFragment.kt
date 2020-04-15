package com.example.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    //AGREGAMOS VIEWMODEL
    private val viewModel by lazy {
        ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
    }

    //DATABINDING
    lateinit var binding: FragmentHomeBinding


    //OBSERVADOR 
    private val observerText = Observer<String> {
        if (it != null) {
            setTextMessage()
        }
    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        //DATABINDING
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //INICIALIZAMOS METODOS EN EL ONVIEWCREATED LIFECYCLE FRAGMENT
        init()
        retrieveResponseServices()
    }


    //INICIALIZAMOS VISTAS CON ESTE METODO Y EN ESTE CASO TAMBIEN AGREGAMOS EL LISTENER DEL SWITCH
    //PARA SUS CAMBIOS DE ESTADO

    private fun init(){
        btn_switch.setOnCheckedChangeListener{ buttonView, isChecked ->
            if (isChecked) {
                Toast.makeText(activity, R.string.txt_activado, Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(activity, R.string.txt_desactivado, Toast.LENGTH_SHORT).show()
            }
        }
    }


    //ACCESO A DATOS DESDE VIEWMODEL CON LIVEDATA EN UN METODO DE CONEXION DE RESPUESTA DE DATOS
    // (PODRIA SER UN SERVICIO O PERSISTENCIA EN EL VIEWMODEL)
    private fun retrieveResponseServices() {
        viewModel.text.observe(viewLifecycleOwner, observerText)
    }

    //ASIGNAMOS DENTRO DE ESTE METODO EL VALOR DEL VIEWMODEL AL TEXTVIEW
    private fun setTextMessage() {
        text_home.text = viewModel.text.value
    }

}



