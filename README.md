# moj - API Endpoints ...

### GET /api/user/{id}
This retrieves a user data with user id :id.

### GET /api/user
This retrieves all user in the System

### POST /api/user
This creates new user record, the data should be in json eg.

```
{
    "firstName": "James",
    "secondName": "Harry",
    "accountNumber": 66449
}

```
### PUT /api/user
This updates user record, the data should be in json eg.

```
{
    "id":3,
    "firstName": "James",
    "secondName": "Harry",
    "accountNumber": 66449
}

```
### DELETE /api/user/{id}
This removes a user with id :id from record.





