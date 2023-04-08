# MovieBDD
Top Rated Movie List with BDD Specs

## **Movie Feature Specs**
### **Story: Customer requests to see Top Rated Movies List**

### **Narrative #1**
As an online customer<br />
I want the app to show Top Rated Movies List<br />
So i can always see which Movies currently have the best Rating<br />

**Scenarios (Acceptance criteria)**<br />
Given the customer has connectivity<br />
When the customer open the app and requests to see Top Rated Movies list<br />
Then the app should display list of Top Rated Movies from remote API (TMDB)<br />

### **Narrative #2**
As an Offline customer<br />
I want the app to show error after 2 minutes if there is no connection or if there is any error<br />

**Scenarios (Acceptance criteria)**<br />
Given the customer doesn't have connectivity<br />
When the customer requests to see Top Rated Movies list<br />
The app should display the error message on screen<br />

### **Flowchart**
<img src="https://user-images.githubusercontent.com/95727832/230703826-d3288388-821e-49e1-b0b3-6f2d13fafbd6.png" width="500" height="700">
