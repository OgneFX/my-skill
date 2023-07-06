package Presentation



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.first.injection.databinding.ActivityMainBinding
import di.DaggerAppComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    val Leadingscope = CoroutineScope(Job() + Dispatchers.Main)
    private val viewModel: MainViewModel by viewModels {
        DaggerAppComponent.create().mainViewModelFactory()
    }






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



            binding.button.setOnClickListener {
                Leadingscope.launch {
                viewModel.reloadUsefulActivity()
            }
        }

        lifecycleScope.launchWhenStarted {


            viewModel.state.collect() {
                binding.text.text = it.activity.toString()
            }
        }


    }


}

