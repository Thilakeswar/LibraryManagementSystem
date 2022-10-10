--/*List all databases*/
SHOW DATABASES;

--/*Create a Database*/
CREATE DATABASE Library;

--/*Switch to the Database*/
USE Library;

--/*Create Books table*/
CREATE TABLE Books (
    BookId INT AUTO_INCREMENT,
    BookTitle VARCHAR(1000) NOT NULL,
    AuthorName VARCHAR(1000) NOT NULL,
    Genre VARCHAR(100) NOT NULL,
    CurrentStock INT NOT NULL,
    PublishedYear YEAR(4) NOT NULL,
    ISBN VARCHAR(200) UNIQUE NOT NULL,
    PRIMARY KEY (BookId)
);

--/*Create Student table*/
CREATE TABLE Students (
    Id int AUTO_INCREMENT,
    Status INT,
    Name VARCHAR(200) NOT NULL,
    DOB DATE,
    BloodGroup VARCHAR(10),
    Department VARCHAR(10) NOT NULL,
    CourseStartYear YEAR(4) NOT NULL,
    CourseEndYear YEAR(4) NOT NULL,
    PRIMARY KEY (Id)
);

--/*Create BorrowLedger Table*/
CREATE TABLE BorrowLedger (
    BorrowId INT AUTO_INCREMENT,
    Status INT,
    BookId INT NOT NULL,
    StudentId INT NOT NULL,
    BorrowedDate DATE NOT NULL,
    ExpectedReturnDate DATE NOT NULL,
    ReturnedDate DATE,
    LateByDays INT,
    LateFees DECIMAL,
    PRIMARY KEY (BorrowId),
    FOREIGN KEY (BookId) REFERENCES Books(BookId) ON DELETE RESTRICT,
    FOREIGN KEY (StudentId) REFERENCES Students(Id) ON DELETE RESTRICT
);