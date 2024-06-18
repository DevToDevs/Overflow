package youtube.dev2devs.overflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ContextualFlowRow
import androidx.compose.foundation.layout.ContextualFlowRowOverflow
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import youtube.dev2devs.overflow.ui.theme.OverflowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OverflowTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    OverFlow(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OverFlow(
    modifier: Modifier
) {
    val items = (0..10).toList()

    val maxLines = remember {
        mutableStateOf(1)
    }

    val overFlow = ContextualFlowRowOverflow.expandIndicator {
        val hiddenCount = items.size - shownItemCount
        Button(
            modifier = Modifier.size(80.dp),
            colors = ButtonDefaults.buttonColors(Color.Blue),
            onClick = { maxLines.value = Int.MAX_VALUE },
            shape = CircleShape
        ) {
            Text(text = "+$hiddenCount")
        }
    }


    ContextualFlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        itemCount = items.size,
        maxLines = maxLines.value,
        overflow = overFlow
    ) { index ->
        Button(
            modifier = Modifier.size(80.dp),
            colors = ButtonDefaults.buttonColors(Color.Green),
            onClick = { },
            shape = CircleShape
        ) {
            Text(text = index.toString())
        }
    }
}
