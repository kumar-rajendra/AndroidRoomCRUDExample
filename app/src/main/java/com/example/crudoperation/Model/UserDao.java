package com.example.crudoperation.Model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;


import java.util.List;

@Dao
public interface UserDao {
    @Insert
    Long insert(User u);

    @Insert
    Long insertLibrary(Library library);

    @Query("SELECT * FROM `User` ORDER BY `id` DESC")
    List<User> getAllUsers();

    @Query("SELECT * FROM `User` WHERE `id` =:id")
    User getUser(int id);

    @Update
    void update(User u);

    @Delete
    void delete(User u);

    @Transaction
    @Query("SELECT * FROM User")
    public List<UserAndLibrary> getUsersAndLibraries();

    @Query("SELECT * FROM `User` where `id` = :userid")
    public UserAndLibrary getUserAndLibrary(Long userid);
}