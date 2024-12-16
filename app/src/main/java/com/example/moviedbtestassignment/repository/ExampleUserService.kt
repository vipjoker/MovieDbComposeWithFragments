package com.example.moviedbtestassignment.repository

import com.github.javafaker.Faker
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

interface ExampleUserService {
    fun getUsers():StateFlow<List<User>>

    fun removeUser(user: User)
 companion object{
     fun instance( ):ExampleUserService{
         return ExampleUserServiceImpl
     }
 }
}

private object ExampleUserServiceImpl :ExampleUserService{


    private val users= MutableStateFlow(createUsers())
    override fun getUsers(): StateFlow<List<User>> {
        return users
    }

    override fun removeUser(user: User) {
        users.update { users -> users - user }
    }

    private fun createUsers():List<User>{
        val faker = Faker.instance()
        return buildList<User> {

            add(User(0, faker.name().nameWithMiddle(), "https://fastly.picsum.photos/id/57/2448/3264.jpg?hmac=ewraXYesC6HuSEAJsg3Q80bXd1GyJTxekI05Xt9YjfQ"))
            add(User(1, faker.name().nameWithMiddle(), "https://fastly.picsum.photos/id/64/4326/2884.jpg?hmac=9_SzX666YRpR_fOyYStXpfSiJ_edO3ghlSRnH2w09Kg"))
            add(User(1, faker.name().nameWithMiddle(), "https://avatar.iran.liara.run/public/25"))
            add(User(1, faker.name().nameWithMiddle(), "https://avatar.iran.liara.run/public/20"))
            add(User(1, faker.name().nameWithMiddle(), "https://avatar.iran.liara.run/public/21"))
            add(User(1, faker.name().nameWithMiddle(), "https://avatar.iran.liara.run/public/22"))
        }
    }
}

data class User(val id:Int, val name:String, val photo:String)