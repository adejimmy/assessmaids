Library Management System Documentation
This document provides clear instructions on running the application, interacting with API endpoints. It also includes the required payloads and configurations.

Running the Application
1) Prerequisites
   Java Development Kit (JDK): Ensure JDK 17 is installed on your machine.
   Maven or Gradle: Install Maven build tool.
   Database: Set up a Mysql database.
   Steps to Run
2) Clone the Repository:
   git clone <repository-url>
   cd <project-directory>
   Configure Database:

3) Update application.properties (or application.yml) with your database configuration.
   properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/library
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true


4Build the Project:

Using Maven:
mvn clean install

5)Run the Application:

Execute the main method in the Application class (usually named LibraryManagementApplication).

mvn spring-boot:run

6 Interacting with API Endpoints
Base Url: http://localhost:8080/api
Books Endpoints
6a) GET /books: Retrieve all books.


6b) GET /books/{id}: Retrieve a specific book by ID.


6c) POST /books: Create a new book.

Request Payload:
{
"title": "To Kill a Mockingbird",
"author": "Harper Lee",
"publisher": "J. B. Lippincott & Co.",
"publicationYear": 1960,
"isbn": "9780061120084",
"genre": "Novel",
"pageCount": 281,
"format": "HARDCOPY",
"synopsis": "To Kill a Mockingbird is a novel by Harper Lee published in 1960. It was immediately successful, winning the Pulitzer Prize, and has become a classic of modern American literature.",
"averageRating": 4.4,
"available": true
}

6d) PUT /books/{id}: Update an existing book.

Request Payload:
{
"title": "Advanced Java Programming",
"author": "John Doe",
"publicationYear": 2023,
"isbn": "1234567890",
"publisher": "TechBooks",
"genre": "Technology",
"language": "English",
"edition": 2,
"pageCount": 400,
"format": "PHYSICAL",
"synopsis": "A comprehensive guide to advanced Java programming.",
"averageRating": 4.7,
"available": true
}
6e) DELETE /books/{id}: Delete a book by ID.


6f)Patron Endpoints

Create a Patron

URL: /patrons
Method: POST
Payload:

{
"name": "John Doe",
"dateOfBirth": "1980-01-01",
"gender": "Male",
"address": {
"street": "123 Main St",
"city": "Anytown",
"state": "Anystate",
"zipCode": "12345"
},
"email": "johndoe@example.com",
"phoneNumber": "123-456-7890",
"libraryCardNumber": "LC123456",
"membershipStatus": "ACTIVE"
}
6g) Get Patron by ID

URL: /patrons/{id}
Method: GET
6h) Update Patron

URL: /patrons/{id}
Method: PUT
Payload:
{
"name": "John Doe Updated",
"dateOfBirth": "1980-01-01",
"gender": "Male",
"address": {
"street": "123 Main St",
"city": "Anytown",
"state": "Anystate",
"zipCode": "12345"
},
"email": "john.doe.updated@example.com",
"phoneNumber": "123-456-7890",
"libraryCardNumber": "LC123456",
"membershipStatus": "ACTIVE"
}
6h) Delete Patron

URL: /patrons/{id}
Method: DELETE


6i) Borrowing Record Endpoints

URL: /borrow
Method: POST
Payload:
{
"bookId": 1,
"patronId": 1,
"borrowingDate": "2024-05-16",
"returnDate": "2024-06-16",
"actualReturnDate": null
}
6j) Get Borrowing Record by ID

URL: /borrow/{id}
Method: GET


6k) Update Borrowing Record

URL: /borrow/{id}
Method: PUT
Payload:
{
"bookId": 1,
"patronId": 1,
"borrowingDate": "2024-05-16",
"returnDate": "2024-06-16",
"actualReturnDate": "2024-06-10"
}
6l) Delete Borrowing Record

URL: /borrow/{id}