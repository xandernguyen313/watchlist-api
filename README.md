# X-Finance Backend
Project Description: This backend application allows users to store their watchlists and stocks.

## Entity Relationship Diagram
![ERD](https://github.com/xandernguyen313/watchlist-api/blob/main/erd.JPG)

## Endpoints:  

| Request Type | URL                       | Request Body               | Request Header              | Action                                                          | Access  |   
|--------------|---------------------------|----------------------------|-----------------------------|-----------------------------------------------------------------|---------|
| POST         | /auth/users/register      | emailAddress <br> password | Authorization: none         | registers a user                                                | PUBLIC  |
| POST         | /auth/users/login         | email <br> password        | Authorization: none         | allows a user to login                                          | PUBLIC  |
| GET          | /api/watchlists           |                            | Authorization: Bearer token | returns a list of all watchlists belongs to user                | PRIVATE |
| POST         | /api/watchlists           | name                       | Authorization: Bearer token | adds a watchlist to the database                                | PRIVATE |
| GET          | /api/watchlists/{list-id} |                            | Authorization: Bearer token | returns a single watchlist                                      | PRIVATE |
| PUT          | /api/watchlists/{list-id} | name                       | Authorization: Bearer token | updates an existing watchlist name                              | PRIVATE |
| DELETE       | /api/watchlists/{list-id} |                            | Authorization: Bearer token | removes a watchlist from the database                           | PRIVATE |
| POST  | /api/watchlists/{list-id}/symbols | ticker, companyName       | Authorization: Bearer token | adds a symbol to the watchlist                                  | PRIVATE |
| GET          | /api/watchlists/{list-id}/symbols/{symbol-id}|         | Authorization: Bearer token | returns a single symbol                                         | PRIVATE | 
| GET          | /api/watchlists/{list-id}/symbols|                     | Authorization: Bearer token | returns all symbols belongs to the watchlist with that id       | PRIVATE | 
| DELETE       | /api/watchlists/{list-id}/symbols/{symbol-id}|         | Authorization: Bearer token |  removes a symbol from the database                             | PRIVATE |
