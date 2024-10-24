# The purpose of the practical work
Apply the principles of pure architecture when developing a real Android application.



# What needs to be done
Develop an application that, at the touch of a button, will suggest to the user what they can do.

- Take classes from www.boredapi.com . 
- The application should be divided into layers.
- Use Dagger or Hilt to link application components together.

![](https://github.com/user-attachments/assets/7e11b820-b12b-4e85-95d2-bebb4893079f)

1. Make up a screen similar to the one shown in the screenshot.
2. Create the **UsefulActivity** interface in the **entity** package with fields corresponding to the json from the API response.
3. Create a **UsefulActivityDto** in the **data** package that implements the **UsefulActivity** interface.
4. Create the **UsefulActivitiesRepository** class in the data package with the **getUsefulActivity** method, which returns **UsefulActivity**.
5. Implement in **UsefulActivityRepository** receiving data from the API, as you did in the network module. To deserialize the data, use **UsefulActivityDto**.
6. Create **GetUsefulActivityUseCase** in the **domain** package with the **execute** method returning **UsefulActivity**.
7. In the user case, in the constructor, throw **UsefulActivitiesRepository** and get data from it in the **execute** method.
8. In the **presentation** package, create **MainViewModel**, throw **GetUsefulActivityUseCase** into its constructor, add StateFlow to the View model to subscribe to them in Fragment/Activity, as well as the **reloadUsefulActivity** method, which should receive **UsefulActivity** from the user case and throw it into StateFlow.
9. Add the View model to Fragment/Activity (as standard via **by ViewModels**, if Hilt was selected, or insert it, as shown in this module, if Dagger was selected), subscribe to its changes to show the text in the UI from the **activity** field from **UsefulActivity**.
10. Add a call to the View Model method **reloadUsefulActivity** when the button is clicked.
11. Add DI support: mark the required classes with the annotation **@Inject**, then, if Dagger was selected, describe the component in the package **di**, as shown in this module. If Hilt was selected, mark all Fragment/Activity with the annotation **@AndroidEntryPoint**, View model with the annotation **@HiltViewModel**, and also create an **Application**class, mark it with the annotation **@HiltAndroidApp** and add it to the manifest.
