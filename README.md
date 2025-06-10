# JWT based auth Springboot  
>Spring Boot project implementing JWT authentication with 1-minute token expiry. Uses MySQL for user data persistence.

## JWT Workflow  
- User registers and logs in.  
- Server returns JWT token on login.  
- Client sends token in Authorization header for protected routes.  
- Server validates token for access.

## Architecture Diagrams  
<img src="arch/arch1.png" width="600" />  
<img src="arch/arch2.png" width="600" />

## How to Run  
```bash
git clone <repo-url>  
cd <project-folder>  
mvn clean install  
mvn spring-boot:run
```

Server runs at [http://localhost:8080](http://localhost:8080)

## How to Test Using Postman

### 1. Register

**POST** `http://localhost:8080/register`  
**Body:**
```bash
{
  "username": "user",
  "password": "pass"
}
```

### 2. Login

**POST** `http://localhost:8080/login`  
**Body:**
```bash
{
  "username": "user",
  "password": "pass"
}
```
Save token from response.

### 3. Public Endpoint

**GET** `http://localhost:8080/`  
_No token needed, expect 200._

### 4. Protected Endpoint

**GET** `http://localhost:8080/api/employee`  
or  
**GET** `http://localhost:8080/api/employee/{id}`  

**Header:**
Authorization: Bearer <token>
_Expect 200 if token valid._

### 5. No Token Access

**GET** `/api/employee` _without header_  
_Expect 401 Unauthorized._

### 6. Expired Token Access

Wait 1 minute (token expiry)  
**GET** `/api/employee` _with expired token_  
_Expect 401 Unauthorized (token expired)._
