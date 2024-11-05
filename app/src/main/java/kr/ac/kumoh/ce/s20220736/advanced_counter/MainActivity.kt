package kr.ac.kumoh.ce.s20220736.advanced_counter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kr.ac.kumoh.ce.s20220736.advanced_counter.ui.theme.Advanced_CounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Advanced_CounterTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current  // 'LocalContext'를 써야 현재 context를 가져올 수 있다.

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Button(
            modifier = Modifier.padding(innerPadding).fillMaxWidth(),
            onClick = {
                Toast.makeText(
                    context,
                    "눌렸습니다",
                    Toast.LENGTH_SHORT
                ).show()
            },
        ) {
            Text(text = "클릭")
        }
    }
}