package com.shreya.quizapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shreya.quizapp.R


class LeaderboardActivity : AppCompatActivity() {
    private val leaderboardEntries = listOf(
        LeaderboardEntry(1, "User1", 100),
        LeaderboardEntry(2, "User2", 90),
        LeaderboardEntry(3, "User3", 80),
        LeaderboardEntry(4, "User4", 70),
        LeaderboardEntry(5, "User5", 60)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        val leaderboardRecyclerView = findViewById<RecyclerView>(R.id.leaderboardRecyclerView)
        leaderboardRecyclerView.layoutManager = LinearLayoutManager(this)
        leaderboardRecyclerView.adapter = LeaderboardAdapter(this, leaderboardEntries)
    }
}
