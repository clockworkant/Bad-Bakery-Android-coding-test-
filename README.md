# Overview
This is a test project to show a way of building an andriod app. 

## Project features
- model view presenter architecture (without library or abstract classes)
- local and remote datasource (local for running offline using an assets file)
- Koin for basic Inversion of control/Dependency injection
- retrofit with rxjava for networking
- mockito (kotlin) for unit testing
- Gson for json parsing
- Glide for image loading

# Must Haves
- on app started
    - fetch cakes from rest end point
        - https://gist.githubusercontent.com/t-reed/739df99e9d96700f17604a3971e701fa/raw/1d4dd9c5a0ec758ff5ae92b7b13fe4d57d34e1dc/waracle_cake-android-client
    - remove duplicate cakes
    - order cakes by name
    - show list of cakes
        - Image
        - Title
        - Divider
    - on click show description in pop up
- Pull to refresh
- Handle list loading error (no network etc)

# Tests
- When app is started and load succeeds, cakes are presented with duplicates removed and sorting applied
- When app is started and load fails, error is presented

# Extras
- Handle orientation changes without list reload
- Retry option on error
- Animate list items 

# Considerations
- Material design


