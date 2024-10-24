# The objectives of the work
- Apply knowledge about working with fragments and the AndroidX Navigation library.
- Practice designing the navigation of the application.
- Consolidate the skills of working with markup and styles.

# What needs to be done
Copy the achievements of the QUIZ application from the previous modules to the new project and add several screens to it.

To organize navigation through the application, be sure to use the AndroidX Navigation library. 

The application should consist of three fragments: the welcome screen, the survey screen and the results screen.

The procedure of actions:

1. Copy the entire project from the folder of the previous module “Project Resources” to the folder corresponding to this module.
2. Connect the AndroidX Navigation library to the project and add the necessary components to the Activity markup according to the instructions given earlier.
3. Add three screens to the project, create markup for them and fill them with data. Use the styles, themes, and resources that came from the results of previous modules.
4. **The welcome screen** should contain minimal information (actually, the greeting), as well as the "Start" button, which should take the user to the next "Survey" screen.
5. **The survey screen** should consist of a questionnaire of three questions, the data for which should be taken from the object QuizStorage. If you have copied the entire project from the Project Resources module to the folder of the current module, then this object should be stored in the following path:

![image](https://github.com/user-attachments/assets/1cc81eca-ea82-4a56-8336-1e358adc87f4)

Implement the markup for this screen using the ScrollView component, which allows you to scroll through the screen. 

Add the necessary Views to the ScrollView. Get a list of questions and answers and set this data to the appropriate View.

If necessary, install the listeners in the View to be able to process the answers to the questions.

There should also be two buttons on this screen:

- "Back", which returns the application to the start screen.
- "Send", which is clicked to process the results of the questionnaire using the QuizStorage.answer() function. The result should be sent to the third Results screen as an argument.

6. **Results screen** on which there should be:
    - The result of completing the questionnaire that you submitted in the previous paragraph.
    - The "Start over" button, which opens a new blank screen with a questionnaire.
7. Test your application. Make sure that the survey results are calculated and transmitted to the results screen.
8. Work with the fragments backstack and set up project navigation as follows: 
    - Welcome screen, "Start" button: leads to the survey screen.
    - Welcome screen, Back button of your phone: closes the application.
    - Survey screen, Back button: returns to the welcome screen.
    - The survey screen, the Back button of your phone: also returns to the welcome screen.
    - The survey screen, the "Send" button: leads to the results screen, the corresponding results are transmitted.
    - Results screen, "Start over" button: leads to a new survey screen, while if you press the Back button of your phone on the screen that opens, the application should not return to the results screen. Instead, the welcome screen should open.
    - Screen 3, your phone's Back button: returns to the welcome screen.

# The work of the program


https://github.com/user-attachments/assets/9e295c18-630f-4946-b669-2f7329aa7dfa

