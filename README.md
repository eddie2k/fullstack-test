Instantors Fullstack exercise
=========================

Implementation notes:
----
1. Assuming docker compose is installed in your system, simply run from the root directory
```
docker-compose up
```
and wait for the application to load. You can find the application at [localhost:3000](http://localhost:3000).

2. There are available test to run against the actual integration. It can be used to verify that the service is online and the API is still compatible with the 
implementation. They can be executed from the *spring-boot-backend* directory by running
```
gradle systemIntegrationTest
```
Original notes:
=========================
Welcome to Instantors fullstack exercise! 
This exercise is done to give you an opportunity to 
show case your coding style and how you think about problem solving. 
This exercise is usually paired with an interview were you will have the opportunity
to go through your solution to show and discuss your design choices.

Task
----
The task is to make this repository into an webapp/website that
presents information about a specific (or random) Star Wars character. 

Rules
-----
1. You should not need to change any front-end code to change the character information (if it contains errors).
2. The front-end may not communicate with any third-party resources. 
3. Have fun, and do not feel pressured to spend more then a couple of hours on this task.
4. Work on a personal fork and when you are done create a PR to [github.com/instantor/fullstack-test](https://github.com/instantor/fullstack-test).

Character Information
--------
The character information can either be static or
for extra credits it can be retrieved from [swapi.co](https://swapi.co) in the back-end.


Best of luck!

//Developers @ Instantor
