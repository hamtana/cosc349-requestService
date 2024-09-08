# COSC349-assignment1

## Project Information
For this assignment a database and two other web services were designed. 
The two services work together to provide a property management application. 
The request service provides functionality for tenants to create an account and submit requests for work that may be required in a property, i.e A sink has broken.
Once the work request has been completed, the tenant can update the request, marking it as completed or deleting the request.


The management service provides functionality for managers to create an account, add a property and manage their properties.
Managers will be able to see the number requests from the tenant and make requests for that to work to be completed.

Both the Request & Management service containers are served by a database; a single container running a PostgreSQL.

### To run the project follow these steps:
1. clone the repository
2. ensure you have docker desktop running.
3. Run the command **docker compose up** within the assignment1 directory. 

