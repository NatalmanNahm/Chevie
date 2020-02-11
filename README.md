# Chevie Sport App
Bring you close to the sport news you want, and filter out the one you don’t want. From Previews to highlight of your favorite teams and sports, you get the latest news of your favorite sports and teams. Follow what is going on in the sport industry and save your favorite news. Connect to others by reading their comments about a specific article.

# Features
- See Nfl News, scores, games schedule, Teams details.
- Save favorite team and see its details.
- Login and and have control over your data.
- Save news for later review

# Installation
To instal and run you will have to create an account with at [Sport data](https://sportsdata.io/). You can then request a free Api key by going to Trial and select NFL then fill out the form to get your Api key. Add the Api key to your gradle.proprieties file. In the build.gradle add the key to the build.each like this `it.buildConfigField 'String', 'yourApiKey', yourApiKey`. Then go the Utilities folder then NetWorkUtils and change this line `private static String NFL_KEY = BuildConfig.nfl1Api;` with this `private static String NFL_KEY = BuildConfig.yourKey;`. Here is how to add Api Key to your gradle.propriaties file `yourApiKey="88625340bfbf4c51b41e25ef7ab9b771"`. And you use "yourApiKey" as key in the build.gradle.

# Libraries
- Firebase Database
- Firebase Analytics
- Firebase RealTime Data
- ButterKnife
- Picasso
# Why this project
This a project I am working on for my nanodegree Adroid developer 
