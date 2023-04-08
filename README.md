# MovieBDD
Top Rated Movie List with BDD Specs

## **Movie Feature Specs**
### **Story: Customer requests to see Top Rated Movies List**

### **Narrative #1**
As an online customer
I want the app to show Top Rated Movies List
So i can always see which Movies currently have the best Rating

**Scenarios (Acceptance criteria)**
Given the customer has connectivity
When the customer open the app and requests to see Top Rated Movies list
Then the app should display list of Top Rated Movies from remote API (TMDB)

### **Narrative #2**
As an Offline customer
I want the app to show error after 2 minutes if there is no connection or if there is any error

**Scenarios (Acceptance criteria)**
Given the customer doesn't have connectivity
When the customer requests to see Top Rated Movies list
The app should display the error message on screen
