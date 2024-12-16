@file:OptIn(ExperimentalFoundationApi::class)

package com.example.moviedbtestassignment

import android.os.Bundle
import android.os.Parcelable
import android.provider.ContactsContract.Profile
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Colors
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Rounded
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.RememberObserver
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import com.example.core.NetworkClient
import com.example.moviedbtestassignment.material.MaterialExample
import com.example.moviedbtestassignment.repository.ExampleUserService
import com.example.moviedbtestassignment.repository.User
import com.example.moviedbtestassignment.ui.MoviesDbViewModel
import com.example.moviedbtestassignment.ui.screen.ListScreen
import com.example.moviedbtestassignment.ui.screen.OldDetailScreen
import com.github.javafaker.Faker
import com.google.android.material.tabs.TabItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.parcelize.Parcelize
import kotlin.random.Random


//
//@kotlin.io.Serializable
//object Profile
//@Serializable
object FriendsLis

@Composable
fun LoginScr(navigation: NavHostController) {
    var name = remember { mutableStateOf("") }

    Column(

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()

            .background(Color.Red)

    ) {
        Log.i("RECOMP", "LoginScr: ")

        Column {
            Button(onClick = {
                navigation.navigate("detail/${name.value}/444")
            }) {
                Text("Press me")

            }
            Text("Profile ${name.value}", fontSize = 30.sp, color = Color.White)


            OutlinedTextField(
                value = name.value,
                onValueChange = { name.value = it },
                label = { Text("Name") }
            )
        }


    }

}

@Composable
fun DeatailScr(navigation: NavHostController, text: String, num: Int) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)

    ) { Text("Hello  $text ! Num is ${num}", fontSize = 30.sp, color = Color.White) }
}


@Composable
fun logRememberState() = remember {
    object : RememberObserver {
        override fun onAbandoned() {

        }

        override fun onForgotten() {
            TODO("Not yet implemented")
        }

        override fun onRemembered() {
            TODO("Not yet implemented")
        }
    }
}


private class LifecycleRememberObserver(val name: String) : RememberObserver {
    override fun onAbandoned() {
        Log.i("ObserverRemember", "$name onAbandoned: ")
    }

    override fun onForgotten() {
        Log.i("ObserverRemember", "$name onForgotten: ")
    }

    override fun onRemembered() {
        Log.i("ObserverRemember", "$name onRemembered: ")
    }
}

@AndroidEntryPoint
class MainActivity : FragmentActivity() {


    private val viewModel: MoviesDbViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        setContentView(R.layout.content_main)
        setContent {

//            val navigation: NavHostController = rememberNavController()
//            NavHost(navController = navigation, startDestination = "list") {
//
//                composable("login") { LoginScr(navigation) }
//                composable("list") { ListScreen(navigation) }
//                composable(
//                    route = "oldetail/{id}",
//                    arguments = listOf(navArgument("id") { type = NavType.IntType })
//
//                ) { backStack ->
//                    val num = backStack.arguments?.getInt("num") ?: 0
//                    OldDetailScreen(num, navigation)
//                }
//
//                composable(route = "detail/{id}/{num}",
//                    arguments = listOf(
//                        navArgument("id") { type = NavType.StringType },
//                        navArgument("num") { type = NavType.IntType }
//
//                    )
//                ) { backStack ->
//                    val id = backStack.arguments?.getString("id") ?: ""
//                    val num = backStack.arguments?.getInt("num") ?: 88
//                    DeatailScr(navigation, id, num)
//                }
//            }


//            AppScreen()


//                ListScreenExample()
            MaterialExample()
        }


//            ComposeView(this).apply {
//                consumeWindowInsets = false
//                setContent {
//
////                    AndroidViewBinding(ContentMainBinding::inflate)
//
//
//                }


//        setContent {
//            MovieDBTestAssignmentTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
//                    Column {
//
//
//                        MessageCard("Test title","test card")
//                        Button(onClick = {
//
//                            viewModel.onSearchClicked()
//                            Log.i("DEBUG8", "onCreate: ")}){
//                            Text("Press me")
//                        }
//
//
//
//                        SearchMoviesScreen(viewModel)                    }
//                }
//            }
//        }
//            })
    }


}

@Preview(showSystemUi = true, device = Devices.PIXEL_6)
@Composable
private fun AppScreen() {
    val context = LocalContext.current
    val (state, setState) = remember { mutableStateOf("Heelo") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),

    ) {

        Container("Buttons example"){
           ButtonExample()
        }

        Container("TextField example") {
            TextFieldExample()

        }
       Container("Checkboxes example") {

           CheckBoxesExample()
       }
    }
}

@Composable
fun ListScreenExample(){


        val users by ExampleUserService.instance().getUsers().collectAsStateWithLifecycle()


        val context = LocalContext.current



    LazyColumn {

        item { Text(text = "HEADER", fontSize = 20.sp, modifier = Modifier.fillMaxWidth().height(100.dp)) }
        items(users){
            UserCard(it)
        }
            listHeader("My header")
        items(10){
            Text(modifier = Modifier.padding(20.dp).fillMaxWidth(),text = "Label $it")
        }
    }
}


fun LazyListScope.listHeader(text:String){
    stickyHeader { Text(text, modifier = Modifier.background(Color.Gray).padding(20.dp).fillMaxWidth()) }

}


@Preview
@Composable
fun ShowUserCard(@PreviewParameter(UserProvider::class) user: User){
    UserCard(user)
}

class UserProvider() : PreviewParameterProvider<User>{
    override val values: Sequence<User>
        get() = sequenceOf(

            User(1,"testname", "urls"),
            User(2,"testnametestnametestnametestnametestnametestnametestnametestnametestnametestname", "urls"),

        )

}


@Composable
fun UserCard(user:User){
    Card(shape = RoundedCornerShape(size = 6.dp), elevation = 8.dp,

        modifier = Modifier.clickable() {

        }
    ){

        Row(modifier = Modifier.fillMaxWidth()){

            Text(modifier = Modifier.padding(20.dp).width(100.dp),text = user.name)
            IconButton(onClick = {},
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Rounded.Delete,
                    contentDescription = "delete button"
                )
            }
        }

    }
}

@Composable
fun UserImage(url:String){
    val placeHolder = rememberVectorPainter(image = Rounded.AccountCircle)


    AsyncImage(
        model = url,
        contentDescription = "User avatar",
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(64.dp).aspectRatio(1f/1f).clip(CircleShape),
        placeholder = placeHolder,

    )
}

@Composable
fun ButtonExample(){
    var state by rememberSaveable {
        mutableStateOf(ButtonState())
    }

    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(state.color)
        ),
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(2.dp, Color.Black),
        onClick = {
        val newColor = -Random.nextInt(0xffffff)
        state = state.copy(color = newColor, pressCount = state.pressCount + 1)



    }){
        Text(text = "Change color", color = Color(state.textColor))
    }

    Text("Count of clicks ${state.pressCount}")
}


@Composable
fun TextFieldExample(){
    var textValue by rememberSaveable {
        mutableStateOf("")
    }

    OutlinedTextField(value = textValue, onValueChange = {
        textValue = it
    },
    singleLine = true,
        modifier = Modifier.fillMaxWidth()

        )

    Spacer(modifier = Modifier.height(6.dp))
    Text(text = textValue.ifBlank { "[empty]" })
}


@Composable
fun CheckBoxesExample(){
    val state = rememberSaveable(saver =CheckBoxesState.Saver){
        CheckBoxesState(checkableItems =
        List(6) {index->
            val id = index + 1
            CheckableItem(
                title = "Item $id",
                isChecked = mutableStateOf(false)
            )

        })
    }

    state.checkableItems.forEach{
        checkedItem ->
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = {
                        val newCheckValue = checkedItem.isChecked.value.not()
                        checkedItem.isChecked.value = newCheckValue

                    },
                )





            ) {

            Checkbox(checked = checkedItem.isChecked.value,
                onCheckedChange = {
                    val newCheckValue = checkedItem.isChecked.value.not()
                    checkedItem.isChecked.value = newCheckValue

                })

            Text(checkedItem.title)

        }
    }

    Text("Selected items: ${state.selectedItemNames}")
}

@Composable
fun Container(name: String, content:@Composable ColumnScope.()-> Unit) {
    Card(
        border = BorderStroke(width = 1.dp, Color.Gray),


        shape = RoundedCornerShape(size = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {


            Text(
                name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(6.dp))
            content()
        }


    }
}


@Composable
fun test() {

    Column(
        verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "Hello oleh", modifier = Modifier
                .background(Color(75, 77, 255, 255), RoundedCornerShape(5.dp))
                .border(1.dp, Color.Black, RoundedCornerShape(5.dp))

                .padding(10.dp),
            color = Color.White
        )
    }
}


fun main() = runBlocking {


    val _tickFlow = MutableSharedFlow<String>(replay = 0)
    val tickFlow: SharedFlow<String> = _tickFlow


    for(i in  1..5){
        delay(500)
        _tickFlow.emit("Value $i")
    }



    tickFlow.collect{
        print("Collected$it")
    }

//    val chanel = Channel<Int>()
//    val producer = launch{
//        for (i in 1..5){
//            println("Sending $i")
//            chanel.send(i)
//            delay(1000)
//        }
//
//        chanel.close()
//    }
//
//
//    val consumer = launch {
//        for (item in chanel){
//            println("Producer ${item}")
//            delay(500)
//        }
//    }









//
//    launch (Dispatchers.IO){
//
//        println("I'm working from ${Thread.currentThread()}")
//    }


    Unit
}





data class CheckableItem(val title: String, val isChecked: MutableState<Boolean>)

data class CheckBoxesState(val checkableItems: List<CheckableItem>){


    val selectedItemNames: String get(){
        return checkableItems
            .asSequence()
            .filter { it.isChecked.value }
            .map{it.title}
            .joinToString()
            .takeIf { it.isNotEmpty() } ?:"[nothing]"
    }


    companion object{
        val Saver: Saver<CheckBoxesState,*> = Saver(
            save = { state: CheckBoxesState->
                return@Saver ParcealableCheckboxesState(
                    state.checkableItems.map { ParcealableCheckableItem(it.title,it.isChecked.value) }

                )

            },
            restore = {
                CheckBoxesState(it.checkedItems.map { CheckableItem(it.title, mutableStateOf(it.isChecked)) })
            }
        )
    }
}



@Parcelize
data class ParcealableCheckableItem(val title: String, val isChecked: Boolean):Parcelable

@Parcelize
data class ParcealableCheckboxesState(val checkedItems: List<ParcealableCheckableItem>):Parcelable


@Parcelize
data class  ButtonState(
    val color:Int = Color.Red.value.toInt(),
    val pressCount:Int = 0
):Parcelable{



    val textColor: Int by lazy ( LazyThreadSafetyMode.NONE ){
        if(android.graphics.Color.luminance(color) > 0.5f ){
            android.graphics.Color.BLACK
        }else{
           android.graphics.Color.WHITE
        }
    }
}
@Parcelize
data class MyState(val name: String) : Parcelable