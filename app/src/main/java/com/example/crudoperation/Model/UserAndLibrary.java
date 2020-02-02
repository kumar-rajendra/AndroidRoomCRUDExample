package com.example.crudoperation.Model;

import java.util.List;
import androidx.room.Embedded;
import androidx.room.Relation;
public class UserAndLibrary {
    @Embedded public User user;
    @Relation(
            parentColumn = "id",
            entityColumn = "userId"
    )
   //public Library library;
   public List<Library> LibList ;
}
