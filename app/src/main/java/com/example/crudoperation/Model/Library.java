package com.example.crudoperation.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Library {
        @PrimaryKey(autoGenerate = true)
        public long libraryId;
        public long userId;
        public String book_name;
        public String book_type;

        public void setLibraryId(long id)
        {
                this.libraryId = id;
        }

        public long getLibId() {
                return libraryId;
        }

        public void setUserId(long userId)
        {
                this.userId = userId;
        }

        public long getuserId() {return userId;}
        public  void  setBook_name(String book_name)
        {
                this.book_name = book_name;
        }
        public  String getBookName(){return book_name;}
        public  void  setBook_type(String book_type)
        {
                this.book_type = book_type;
        }
        public String getBook_type(){return book_type;}
}
