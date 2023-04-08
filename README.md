# MovieBDD
Top Rated Movie List with BDD Specs

## **Movie Feature Specs**
### **Story: Customer requests to see Top Rated Movies List**

### **Narrative #1**
As an online customer__
I want the app to show Top Rated Movies List__
So i can always see which Movies currently have the best Rating__

**Scenarios (Acceptance criteria)**
Given the customer has connectivity__
When the customer open the app and requests to see Top Rated Movies list__
Then the app should display list of Top Rated Movies from remote API (TMDB)__

### **Narrative #2**
As an Offline customer__
I want the app to show error after 2 minutes if there is no connection or if there is any error__

**Scenarios (Acceptance criteria)**
Given the customer doesn't have connectivity__
When the customer requests to see Top Rated Movies list__
The app should display the error message on screen__
