import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Contact {
     private String name;
     private String phone;
     private String email;

     public Contact(String name, String phone, String email) {
         this.name = name;
         this.phone = phone;
         this.email = email;
     }

     public String getName() {
         return name;
     }

     public String getPhone() {
         return phone;
     }

     public String getEmail() {
         return email;
     }

 }   @Override
 public String toString() {
     return name;
 }
}
}
