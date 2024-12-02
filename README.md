# Travel Advisors

## Team Members

- Teddy Yang (teddyyang8)
- Yasmina Mimassi (yasmatopia)
- Shirley Zhang (shirleyyzhang)
- Ray Fang (rayf5372)
- Sean Woo (seanwoo12)

Travel Advisors is a personalized travel planner designed to enhance your travel experience. Users can create profiles, specify their interests, and receive curated recommendations for places to visit based on their preferences. This app helps you plan your adventures with features like mapping locations, rating visited places, and scheduling activities on a calendar. This project was created as part of our CSC207 course, highlighting the usage of Clean Architecture in software design. 

# Table of Contents
1. [Features](#features)
2. [Installation Instructions](#installation-instructions)
3. [Usage](#usage)
4. [Feedback and Contributions](#feedback-and-contributions)

## Features

- Personalized Recommendations:
Users create profiles and select interests to receive a tailored list of places, allowing them to save set schedules to their respective profiles. 
- Interactive Mapping:
Visualize recommended locations on a map and understand the distances between them.
- Place Ratings:
Like or dislike visited places and add personalized ratings.
- Activity Scheduling:
Create schedules on a calendar for planned activities.
- Dynamic Filters:
Filter your list of places by removing disliked locations or those already saved.

## Installation Instructions
To set up and run Travel Advisors locally, you must have Java and a JVM installed to compile and run the program. Installing an IDE such as IntelliJ IDEA is recommended to run the program. 

## Usage

When you’ve downloaded Travel Advisors, 
1. Add the API Key from Google Cloud to .env.
2. Then run mainWithDB.
3. Once launched, in the top right of the IDE, open edit configurations
  <img width="720" alt="image" src="https://github.com/user-attachments/assets/927098c8-83f5-49d5-b931-a5a1f39377c1">

4. Add the .env file to the environmental variables.
<img width="720" alt="image" src="https://github.com/user-attachments/assets/f1712b69-fa33-4054-a62b-ca23b25b6a7a">

5. Now you are ready to use the program!

Sign up by creating a username and password. List up to five fundamental interests and a city of choice and receive a curated list of suggested locations. Before searching, you can filter the list to remove disliked and previously saved locations. Select a few suggestions that interest you, and click the associated button to open it in Google Maps. You can then place your activities in a calendar to create your ideal timetable. Once you have visited a location, you can leave a personalized note and rating for it. The selected locations will be saved to your profile for future reference. 

## Feedback and Contributions

We welcome contributions to improve Travel Advisors! To contribute:
1. Fork the repository.
2. Create a feature branch:
git checkout -b feature/your-feature-name
3. Commit your changes:
git commit -m "Add your feature description here"
4. Push to the branch:
git push origin feature/your-feature-name
5. Submit a pull request.
  - Code changes must be correctly implemented to achieve their intended functionality, pass all preexisting tests, and directly solve a problem.
  - The title must be clear and concise, and the code should contain inline comments to document the changes made to explain non-obvious logic or decisions.
  - All necessary changes and files must be included.
  - It must reference related issues or tasks and potentially additional documentation to explain functionality.
6. Reviewing Contributions
  - Ensure the code behaves as intended and passes existing tests.
  - Make precise comments that are unambiguous and concise.
  - Confirm all related files are included in the documentation.
  - Confirm the merge request has proper instructions for testing and validation.
  - Ensure the branch has been rebased with the latest changes from the main branch.
