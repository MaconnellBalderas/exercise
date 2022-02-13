# tma-exercise
# There is nothing extra needed to run this application, here are all the APIs that were created:
# Player: 
#   http://localhost:8080/players?teamId={teamId} // lists all players
#   http://localhost:8080/player?teamId={teamId} // add a player 
      requestBody: {
        "age": 28,
        "weight": 188,
        "firstName": "John",
        "lastName": "Doe",
        "height": "5'7",
        "position": "batter",
        "salary": 1200000
      }
# Team:
#   http://localhost:8080/teams?city={City}&mascot={Mascot}&division={division} // get all teams - city/mascot/division are OPTIONAL 
#   http://localhost:8080/team?teamId={teamId} // get a single team
#   http://localhost:8080/team?teamId={teamId} // update an existing team
      requestBody: {
        "city": "Mississippi"
      }
#   http://localhost:8080/team // add a team
      requestBody: {
        "city": "Dallas", 
        "division": "Central",
        "mascot": "Eagle"
      }

#After the application was created, it seemed fairly straight forward. Started with the controller class to have a foundation then worked through the service and model class. While building the service classes for Player and Team, the JPA (Team/Player) repository class were built along with the corresponding service. When thinking of the requirement "A player must always be associated with a team", the first thing that came to mind was a parent/child DB relationship or more specifically a Many-to-One (Player -> Team) relationship. I tried to make it as simple as possible using annotions from lombok/creating multi-purpose controllers, but when it came to getting all teams with the optional query parameters (city/mascot/division) I had to create 8 seperate methods in the JPA Repo for all combinations. I know we can do query statements to better simplifiy these methods (from 8 methods to 1 in JPA Repo) but I knew I would be able to complete the soluton with the 8 methods. 

#Really the only issue I came across was a infinite response when trying to save a player to a certain team. When I set the Many-to-One/One-to-Many relationship, the JSON was causing a recursive infinite loop because of the team field in the plaer object so I added @Jsonignore to the field. 

#There were no issues I was not able to overcome.

#One thing I would improve is setting up the query statements for retrieving all teams based on a query of either "city" &|| "mascot" $|| "division" || "no query"
Another thing is I would probably have this All/if not mostly tested, just didnt have time to go 80% or more on test coverage.
