# The goals of practical work
Apply knowledge:

- how to save data in the device memory;
- on creating and applying the "Repository" pattern.

# What needs to be done

Let's create a single-screen application that allows you to enter data and write it to SharedPreference, as well as display the entered data even after reopening the application.

1. Create a custom layout screen, similar to the one you developed earlier with the speaker, containing:
  - text input field (EditText);
  - a button with the text "Save" (Button);
  - a button with the text "Clear" (Button);
  - a text field (TextField).
2. Create a Repository class that will contain several methods:
  
  **getDataFromSharedPreference(): String?** — will get the value from SharedPreference;
  
  **getDataFromLocalVariable(): String?** — will get the value, return the value of the local variable;
  
  **saveText(text: String)** — will write values to both SharedPreference and local variable.
  
  **clearText()** — will clear the value in both SharedPreference and local variable.
  
  **getText(): String** — will get the value from sources: first it will try to take the value of a local variable; if it is null, then we will try to take the value from SharedPreference.
3. Create an instance of the repository class in your fragment/activity.
4. After clicking the "Save" button, take the text value from the EditText and pass it to the **saveText(text: String)** method.
5. When you click the "Clear" button, clear the value in the repository using the **clearText()** method.
6. When opening the application or changing the value, the text in the TextView should change.

# The work of the program


https://github.com/user-attachments/assets/aed956d4-5a49-41af-bbfc-d8aed8eacfd0

