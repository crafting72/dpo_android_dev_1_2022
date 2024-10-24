# The goals of practical work
Practice working with lists.

# What needs to be done
Develop a small application to display a list of photos from Mars. To do this, use the open Nasa API.

# Stages of work:

1. Create a new project and connect RecyclerView to it.
2. Use the Mars Rover Photos API to upload information.
3. Connect the necessary libraries to download data from the network and navigation (if necessary)
4. Implement data loading from the Nasa API using the data loading library, create the necessary classes and describe the data model in accordance with the API documentation. 
  You can open any of the queries in the browser. The result will be a json string that contains the uploaded data. For better visualization, copy the resulting json into some json parser (for example, http://json.parser.online .fr) and create a class that will describe the received data.
  Use the MVVM template to load and display data.
5. Analyze the resulting class and create a markup for the list item. For example, this:

![](https://github.com/user-attachments/assets/6f187f04-88c6-4bce-8c42-cec3edb0f7e6)

6. Create an activity or a fragment to display the list. Add RecyclerView to the markup and install the required LayoutManager.
7. Create an Adapter and a ViewHolder to display the uploaded data on the UI. Implement the necessary functions for the adapter and install the adapter in RecyclerView. To load images asynchronously, use the Glide library. 
8. As a result, you should have a scrolling list that displays photos and information about them. Experiment with different LayoutManagers.
9. Make it so that you can open the photo in full screen. For this: 
  - set the clickListener for the list item,
  - create a separate screen to view the image,
  - pass the URL of the photo to this screen,
  - re-display the photo using Glide (Glide caches images, so there will be no re-loading of the image).

# The work of the program



https://github.com/user-attachments/assets/aaaac654-78a8-483f-9b73-3b9aced8b24a

