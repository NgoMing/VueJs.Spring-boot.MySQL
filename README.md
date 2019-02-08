# Project Manager Application
## Project Manager user stories
### User Roles
* Visitor: Anonymous visitors that the system does not recognize
* Registered User: A user that has registered in the system
* Team Creator: A user who create the team
* Team Member: A user who is added in the team
* Board Member: A user who is added in the board

### Grouped Themes
#### Users
##### Register
* As a visitor, I want to register a user account with my email address, username and password
* Acceptance criteria:
    + The email address must not be null
    + The email address must be valid format
    + The email address must not be longer than 100 characters
    + The email address must not already exist in the system
    + The username must not be null
    + The username must be between 2 and 50
    + The username must not already exist in the system
    + The password must not be null
    + The password must be between 6 and 30

##### Login
* As a registered user, I can user my username or email address with the password to log into the application

#### Teams
##### Create a team
* As a registered user, I want to create teams so that I can organize tasks of 
##### Change team name
* As a team creator, I can change the name of the team that I created
##### Add team member
* As a team creator, I can add registered users to a team as team members so that they can access boards of the team
##### Remove team member
* As a team creator, I can remove team members from a team so that they can not access boards of the team.

#### Boards
##### Create a personal board
* As a registered user, I can create a personal board so that I can organize personal tasks
##### Create a team board
* As a team creator, I can add a board to my team so that I can organize the tasks of the team
##### Add board member
* As a team creator, I can add team members of my team to a board as board members so that they can access task cards in the board
##### View board activities
* As a board member, I can view all activities of that board so that I can track what happened

#### Task lists
##### Create a task list
* As a team creator, I can add task lists in a board so that I can arrange task cards in those lists
##### Rename task list
* As a team creator, I can change the name of that task list
##### Change task list position
* As a team creator, I can change the position of a task list in that board
##### View task list
* As a board member, I can view the list of tasks in that board

#### Task cards
##### Add task card
* As a team creator, I can add task cards to task lists in that board
##### View task card
* As a board member, I can view the details of a task card in that board
##### Edit task card title
* As a team creator, I can edit the title of a task card in that board
##### Edit task card description
* As a team creator, I can eidt the description of a task card in that board
##### Assign a member to a card
* As a team creator, I can add a board member to a task card
##### Change task card position
* As a team creator, I can change the position of task cards in that task list
##### Move task card
* As a team creator, I can move task cards between task lists in that board
##### Remove task card
* As a team creator, I can remove task cards in that task list
##### Update process of task card
* As a board member, I can update process of task cards added to me
##### Add task card attachment
* As a board member, I can attach attachments to a task card in that task list
##### Download task card attachment
* As a board member, I can download attachments of task card in that task list
##### Add task card comment
* As a board member, I can add comments to a task card in that task list
##### Edit task card comment
* As a board member, I can edit my own comments of task cards
##### Delete task card comment
* As a board member, I can delete my own comments of task cards
##### View task card activities
* As a board member, I can view all the activites of a card so I can track the history of that task card

## Database Design
* Using draw.io

## Application Scaffold
### Front-end
* Using vue-cli to generate the front-end scaffold
### Back-end
* Using (https://start.spring.io/) to generate the back-end scaffold
### Connect together
* Using maven to combine build process since front-end is inside back-end as a subfolder
  + Run `npm install`, `npm run test:unit`, `npm build` in prepare-package phase
  + Copy resource to the package
  + Start Spring boot
  + Run `npm run test:e2e` in integration test phase
  + Stop Spring boot
* Bridge the communication
  + Using proxy to bridge API requests to back-end
  ```
  '/api/*' => 'http://localhost:8081'
  ```
