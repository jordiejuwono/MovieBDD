# MovieBDD
Top Rated Movie List with BDD Specs

## **Movie Feature Specs**
### **Story: Customer requests to see Top Rated Movies List**

### **Narrative #1**
```
As an online customer
I want the app to show Top Rated Movies List
So i can always see which Movies currently have the best Rating
```
**Scenarios (Acceptance criteria)**<br />
```
Given the customer has connectivity
When the customer open the app and requests to see Top Rated Movies list
Then the app should display list of Top Rated Movies from remote API (TMDB)
```
### **Narrative #2**
```
As an Offline customer
I want the app to show error after 2 minutes if there is no connection or if there is any error
```
**Scenarios (Acceptance criteria)**<br />
```
Given the customer doesn't have connectivity
When the customer requests to see Top Rated Movies list
The app should display the error message on screen
```
### **Flowchart**
<img src="https://user-images.githubusercontent.com/95727832/230703826-d3288388-821e-49e1-b0b3-6f2d13fafbd6.png" width="400" height="550">

### **App Architecture**
<img src="https://user-images.githubusercontent.com/95727832/230704949-e14186d5-eedd-45ac-8e22-710d6a34eed4.png" width="550" height="550">


### **Payload contract**
```
GET movie/top_rated

200 Response

{
    "page": 1,
    "results": [
        {
            "adult": false,
            "backdrop_path": "/tmU7GeKVybMWFButWEGl2M4GeiP.jpg",
            "genre_ids": [
                18,
                80
            ],
            "id": 238,
            "original_language": "en",
            "original_title": "The Godfather",
            "overview": "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.",
            "popularity": 140.195,
            "poster_path": "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
            "release_date": "1972-03-14",
            "title": "The Godfather",
            "video": false,
            "vote_average": 8.7,
            "vote_count": 17704
        }
    ],
    "total_pages": 549,
    "total_results": 10962
}
```
