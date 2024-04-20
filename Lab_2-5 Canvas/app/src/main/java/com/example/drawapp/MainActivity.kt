package com.example.drawapp

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.drawapp.ui.theme.DrawAppTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val pathData = remember {
        mutableStateOf(PathData())
      }
      val pathList = remember {
        mutableStateListOf(PathData())
      }
      DrawAppTheme {
        Column {

          var imageUri by remember { mutableStateOf<Uri?>(null) }
          val context = LocalContext.current
          val bitmap = remember { mutableStateOf<Bitmap?>(null) }

          val launcher =
            rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
              imageUri = uri
            }

          Box(
          ) {
            imageUri?.let {
              if (Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore.Images
                  .Media.getBitmap(context.contentResolver, it)
              } else {
                val source = ImageDecoder.createSource(context.contentResolver, it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
              }

              bitmap.value?.let { btm ->
                Image(
                  bitmap = btm.asImageBitmap(),
                  contentDescription = null,
                  modifier = Modifier
                    .size(400.dp)
                    .padding(20.dp)
                )
              }
            }

            DrawCanvas(pathData, pathList)
          }
          ButtonPanel(
            onClick = { color ->
              pathData.value = pathData.value.copy(
                color = color
              )
            },
            onLineWidthChange = { lineWidth ->
              pathData.value = pathData.value.copy(
                lineWidth = lineWidth
              )
            },
            onBackClick = {
              pathList.removeIf { pathD ->
                pathList[pathList.size - 1] == pathD
              }
            },
            onImportImage = { launcher ->

            }
          )
        }
      }
    }
  }
}

@Composable
fun DrawCanvas(pathData: MutableState<PathData>, pathList: SnapshotStateList<PathData>) {

  var tempPath = Path()

  Canvas(
    modifier = Modifier
      .fillMaxWidth()
      .fillMaxHeight(0.75f)
      .pointerInput(true) {
        detectDragGestures(
          onDragStart = {
            tempPath = Path()
          },
          onDragEnd = {
            pathList.add(
              pathData.value.copy(
                path = tempPath
              )
            )
          }
        ) { change, dragAmount ->
          tempPath.moveTo(
            change.position.x - dragAmount.x,
            change.position.y - dragAmount.y
          )
          tempPath.lineTo(
            change.position.x,
            change.position.y
          )

          if (pathList.size > 0) {
            pathList.removeAt(pathList.size - 1)
          }
          pathList.add(
            pathData.value.copy(
              path = tempPath
            )
          )
        }
      }
  ) {
    pathList.forEach { pathData ->
      drawPath(
        pathData.path,
        color = pathData.color,
        style = Stroke(pathData.lineWidth, cap = StrokeCap.Round)
      )
    }
  }
}