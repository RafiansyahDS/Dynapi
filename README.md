# Pure Java Dynamic API Switch Handler

This project demonstrates the use of a dynamic API switch handler in a pure Java application without any frameworks. The application routes API requests to different logic methods based on the HTTP method and path, allowing for flexible and scalable API management.

## Use Case

The main use case of this project is to handle various API requests dynamically using a switch statement. This approach simplifies the routing of requests and makes it easy to add, update, or delete API endpoints without modifying the core logic.

## API Endpoints

### User Management

#### Authenticate User

- **Method**: `GET`
- **Path**: `/api/login`
- **Description**: Authenticates a user.
- **Request Parameters**:
  - `userId` (String): The ID of the user.
  - `passwd` (String): The password of the user.
- **Response**: JSON object with authentication status and user details.

#### Get All Users

- **Method**: `GET`
- **Path**: `/api/mt/users`
- **Description**: Retrieves a list of all users.
- **Response**: JSON array of user objects.

#### Delete User

- **Method**: `DELETE`
- **Path**: `/api/mt/delete-user`
- **Description**: Deletes a user.
- **Request Parameters**:
  - `userId` (String): The ID of the user to be deleted.
- **Response**: JSON object with deletion status.

#### Update User

- **Method**: `PATCH`
- **Path**: `/api/mt/update-user`
- **Description**: Updates a user's details.
- **Request Body** (JSON):
  ```json
  {
    "id": "user_id",
    "userId": "user123",
    "nama": "User Name",
    "isActive": "Y",
    "passwd": "password"
  }
  ```
- **Response**: JSON object with update status.

#### Create User

- **Method**: `POST`
- **Path**: `/api/mt/create-user`
- **Description**: Creates a new user.
- **Request Body** (JSON):
  ```json
  {
    "id": "user_id",
    "userId": "user123",
    "nama": "User Name",
    "isActive": "Y",
    "passwd": "password"
  }
  ```
- **Response**: JSON object with creation status.

### Dynamic API Management

#### Create Dynamic API

- **Method**: `POST`
- **Path**: `/api/mt/create-dynapi`
- **Description**: Creates a new dynamic API.
- **Request Body** (JSON):
  ```json
  {
    "id": "api_id",
    "nama": "API Name",
    "httpMethod": "GET",
    "httpURI": "/api/custom-path",
    "isActive": "Y"
  }
  ```
- **Response**: JSON object with creation status.

#### Get All Dynamic APIs

- **Method**: `GET`
- **Path**: `/api/mt/dynapi`
- **Description**: Retrieves a list of all dynamic APIs.
- **Response**: JSON array of dynamic API objects.

#### Update Dynamic API

- **Method**: `PATCH`
- **Path**: `/api/mt/update-dynapi`
- **Description**: Updates a dynamic API.
- **Request Body** (JSON):
  ```json
  {
    "id": "api_id",
    "nama": "API Name",
    "httpMethod": "GET",
    "httpURI": "/api/custom-path",
    "isActive": "Y"
  }
  ```
- **Response**: JSON object with update status.

#### Delete Dynamic API

- **Method**: `DELETE`
- **Path**: `/api/mt/delete-dynapi`
- **Description**: Deletes a dynamic API.
- **Request Parameters**:
  - `id` (String): The ID of the dynamic API to be deleted.
- **Response**: JSON object with deletion status.

### Default Dynamic API Handler

- **Method**: Any
- **Path**: Any
- **Description**: Handles dynamic API requests that do not match any of the predefined endpoints.
- **Response**: JSON object with the result of the dynamic API request.

## Getting Started

### Prerequisites

- Java 11 or higher

### Installation

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/pure-java-dynamic-api-switch-handler.git
   cd pure-java-dynamic-api-switch-handler
   ```

2. Compile the project:
   ```
   javac -cp "path/to/gson.jar:path/to/servlet-api.jar" -d bin src/*.java
   ```

3. Run the application:
   ```
   java -cp "bin:path/to/gson.jar:path/to/servlet-api.jar" YourMainClass
   ```

## Contributing

Contributions are welcome! Please submit a pull request or open an issue to discuss any changes.

## License

This project is licensed under the MIT License.
