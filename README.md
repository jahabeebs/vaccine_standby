![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)

# Vaccine Standby
 A proof of concept for an online vaccine scheduler that can prioritize who will be contacted first to receive a vaccine when there are vaccines that will expire if not administered the same day. The project is written in Java and uses Thymeleaf, Spring Boot, H2 database engine and Jakarta Persistence 

## Getting Started

Use Maven to install needed dependencies and easily build and run

## Build Snapshot
Homepage: <br><br>
<img width="864" alt="Standby List Homepage" src="https://user-images.githubusercontent.com/47253537/134792899-dc8b1921-63fc-4bba-ab80-d9b954d3f660.png">
<br><br>
A priority score is calculated for each patient and a user can select to see the highest priority patient to call next: <br><br>
<img width="817" alt="Highest Priority Patient" src="https://user-images.githubusercontent.com/47253537/134792903-c6a54fa0-a82b-421e-a287-5281e00ccdf2.png">
<br><br>
A list of patients who can plan on coming to the pharmacy at the requested day or time: <br><br>
<img width="801" alt="Patients Coming" src="https://user-images.githubusercontent.com/47253537/134792905-41917cd0-92a2-49a5-bbd3-0f792b9fd583.png">
<br>

## Built With
    
[IntelliJ IDEA](https://www.jetbrains.com/idea/) - IDE

[Maven](https://maven.apache.org/) - Dependency Management

[Thymeleaf](https://www.thymeleaf.org/) - Template Engine

[H2 Database Engine](https://www.h2database.com/html/main.html) - DB

[Jakarta Persistence](https://jakarta.ee/specifications/persistence/3.0/) - Persistence to DB

## Authors

  Jacob Habib

## Acknowledgments

Robert Sedgewick and Kevin Wayne for the Location.java class (modified by me from original).
Code used is [here](https://introcs.cs.princeton.edu/java/44st/Location.java.html)

Dariawan:
[Tutorial Link](https://www.dariawan.com/tutorials/spring/spring-boot-thymeleaf-crud-example/)

