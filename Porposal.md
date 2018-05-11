# Initial proposal for an in-memory java database (jmdb)

jmdb will be an in-memory database written in Java. The purpose of this database is to understand how databases work and to try and implement simple concepts from scratch.

## Table Structure

Tables can be setup in 2 fashions: Row or Column-based. There are pros and cons for each type and jmdb needs to decide on which type it will support.

### Row:
* Data is stored on a per-row basis
* Searching a specific column needs to search to that rows index and then perform its query
* When a match is found on a query, getting the rest of the row is trivial
* Data compression will be difficult

### Column (*this is my personal vote*):
* Data is stored on each column
* Searching a specific column is trivial
* When a match is found on a query, getting the rest of the row is a performance hit
* Data compression is done on each column

## Data Types

jmdb will support just a few data types as a proof-of-concept.

### Null:
* Null value is a substitute for any other value
* Indicates that a value is not provided
### Boolean:
* Values of `true` or `false`
* Can be stored internally as 1 for `true` and 0 for `false`
### Long:
* Values of java.lang.Long are supported
### FixedLengthString
* Stored internally as `UTF-8`
* Created with the fields length defined
* A max length is enforced by the engine (maybe 1000 characters to start)

## Operations

jmdb will support basic SQL operations as a proof-of-concept.

### Create Table:
* Ability to create a table and define its column names and types
```
create table <table name> with columns (
	<column 1> boolean,
	<column 2> long,
	<column 3> fixedLengthString(60)
)
```
### Delete Table:
* Ability to delete the specified table
```
delete table <table name>
```
### Insert Row:
* Ability to insert a row into the specified table
```
insert into <table name> value (
	<column 1 value>,
	<column 2 value>,
	<column 3 value>
)
```
### “*” Any Clause:
* Indicates that all columns should be returned
### Select Clause:
* Ability to select data from a specific table
```
select * from <table name>
select <column 1>, <column 2> from <table name>
```
### Where Clause:
* Ability to search for a certain value within a specific column
```
select <column 1> from <table name> where <column 2> = <specified value>
```
* boolean columns will not be quoted
* long columns will not be quoted
* fixedLengthString columns will be quoted
### Delete Clause:
* Ability to delete a row
```
delete from <table name> where <column 1> = <specified value>
```

## Project Setup

To create a database such as this, there needs to be several modules separated by functionality.

### core:
* Contains most basic functionality of the database
* Handles all defined Data Types
* Interfaces and implementations of Tables and Columns
* Performs actual lookup of data
* Stores data in memory
### parser:
* Parses the input SQL into a series of functions to get the data
* Handles all defined Operations
* Uses something like ANTLR to parse SQL
### transport:
* Layer to provide network/internet transport protocols
* Takes SQL `byte[]` as input and provides results as `byte[]`
* Handles all server connections
