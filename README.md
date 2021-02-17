<h1>Library Management System (LMS)</h1>

<h2>Design Decisions / Assumptions</h2>

* In absence of any database, I have implemented the DAO to load data from CSV file

* The Save to CSV file is not implemented so everytime the code will start in same state

* If you borrow a book, you will notice that you cannot borrow as the state is maintained within the run

* No user validation is happening  
  
* No unit tests are added as there is no code that is testable - most of the important code is in CSVDao layer that will be discarded on database availability

* The code is available in githib at https://github.com/gaurav2010/lms

<h2>Run The solution</h2>

* Do a Maven build and the code can be executed using one of below steps

** Execute Main class LmsApplicationTests, the end point are 

*** http://localhost:8080/book/find?keyword=Roald

*** http://localhost:8080/book/findById?id=1

*** http://localhost:8080/borrow?bookId=4&userId=10

*** http://localhost:8080/return?bookId=4

** Run End Point Integration Test LmsEndPointIntegrationTest


