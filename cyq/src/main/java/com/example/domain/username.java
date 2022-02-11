package com.example.domain;

public class username {
         private int id;
      private String username;
      private  String talk;
      private  String name;
      private   String date;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = Integer.parseInt(String.valueOf(id));
   }

   public String getDate() {
      return date;
   }

   public void setDate(String date) {
      this.date = date;
   }

   public String getUsername() {
      return username;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getTalk() {
      return talk;
   }

   public void setTalk(String talk) {
      this.talk = talk;
   }

   @Override
   public String toString() {
      return "username{" +
              "id=" + id +
              ", username='" + username + '\'' +
              ", talk='" + talk + '\'' +
              ", name='" + name + '\'' +
              ", date='" + date + '\'' +
              '}';
   }
}
