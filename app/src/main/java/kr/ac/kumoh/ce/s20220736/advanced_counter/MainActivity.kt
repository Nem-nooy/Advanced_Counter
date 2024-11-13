package kr.ac.kumoh.ce.s20220736.advanced_counter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import kr.ac.kumoh.ce.s20220736.advanced_counter.ui.theme.Advanced_CounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val vm = ViewModelProvider(this)[CounterViewModel::class.java]
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Advanced_CounterTheme {
                MainScreen(vm)
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: CounterViewModel) {
    //val (count, setCount) = rememberSaveable { mutableIntStateOf(0) }
    val count by viewModel.count.observeAsState(0)
    val (expanded, setExpanded) = rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current  // 'LocalContext'를 써야 현재 context를 가져올 수 있다.

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//        Column(
//            modifier = Modifier
//                .padding(innerPadding)
//                .fillMaxSize(),
//            verticalArrangement = Arrangement.SpaceEvenly,
//        ) {
//            Counter()
//            Counter()
//        }
        Counter(
            Modifier.padding(innerPadding),
            count,
            {viewModel.onAdd()},
            {viewModel.onSub()},
            {viewModel.onReset()},
            expanded,
            setExpanded
        )
    }
}

@Composable
fun Counter(
    modifier: Modifier = Modifier,
    count: Int,
    onAdd: () -> Unit,
    onSub: () -> Unit,
    onReset: () -> Unit,
    expanded: Boolean,
    onChangeExpanded: (Boolean) -> Unit,
) {
    //var value = 0

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "$count",
            fontSize = 100.sp   // 강의 화면에서 크게 표시해야 하니까 쓴 것.
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = {
                //count++
                onAdd()
                onChangeExpanded(false)
            }
        ) {
            Text(
                "증가",
                fontSize = 30.sp,
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = {
                onChangeExpanded(!expanded)
            }
        ) {
            Text(
                "더보기",
                fontSize = 30.sp,
            )
        }
        AnimatedVisibility(expanded) {
            Row {
                Button(
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1F),
                    onClick = {
                        if (count > 0)
                            onSub()

                        onChangeExpanded(false)
                    }
                ) {
                    Text(
                        "감소",
                        fontSize = 30.sp,
                    )
                }
                Button(
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1F),
                    onClick = {
                        onReset()
                        onChangeExpanded(false)
                    }
                ) {
                    Text(
                        "초기화",
                        fontSize = 30.sp,
                    )
                }
            }
        }
    }
}

//@Composable
//fun Clicker(modifier: Modifier = Modifier) {
//    // 이렇게 하면 버튼 눌러도 안 바뀜
//    //var txtString = "안녕하세요"
//
//    // 이렇게 해야 버튼 눌렀을 때 바뀜.
//    //var txtString by remember { mutableStateOf("안녕하세요") }   // (첫 번째 방법)
//    val txtString = remember { mutableStateOf("안녕하세요") }    // (두 번째 방법)
//    //val (count, setTxtString) = remember { mutableStateOf("안녕하세요") }    // (세 번째 방법)
//
//    Column (
//        modifier = modifier.fillMaxSize(),
//        //verticalArrangement = Arrangement.SpaceAround,  // SpaceAround가 토스틱하고 보기 좋다.
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ){
//        Text(
//            //text = txtString,    // (첫 번째 방법 사용 시)
//            text = txtString.value, // (두 번째 방법 사용 시)
//            fontSize = 60.sp  // 강의용 모니터 이슈로 인한 글자 크기 키우기... 지만 글자가 너무 작으니 나도 쓰자
//        )
//        Button(
//            modifier = modifier.fillMaxWidth(),
//            onClick = {
//                //txtString = "눌렸습니다"  // (첫 번째 방법 사용 시)
//                txtString.value = "눌렸습니다"   // (두 번째 방법 사용 시)
//                //setTxtString("눌렀습니다")   // (세 번째 방법 사용 시)
//            },
//        ) {
//            Text(text = "클릭")
//        }
//    }
//}