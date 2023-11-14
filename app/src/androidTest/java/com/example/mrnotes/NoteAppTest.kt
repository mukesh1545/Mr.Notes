package com.example.mrnotes

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mrnotes.RoomData.NoteApp
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat

@RunWith(AndroidJUnit4::class)
class NoteAppTest {
    private lateinit var Values :NoteApp

    @Before
    fun init()
    {
         Values=NoteApp(1,"Id1","Test1","Content1")
    }

    @Test
    fun ExpectedOutPut()
    {
        var output=NoteApp(1,"Id1","Test1","Content1")
        assertThat(output).isEqualTo(Values)
    }
    @Test
    fun NotExpectedOutPut()
    {
        var output=NoteApp(1,"Id2","Test1","Content1")
        assertThat(output).isNotEqualTo(Values)
    }

}