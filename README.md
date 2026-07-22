# Spring Boot Data JPA vs Spring Data JDBC POC

### About this project

This project is meant to show the differences between these two major paradigms while demonstrating modern application
architecture concepts. Specifically those related to DDD and Ports & Adapters architecture. This project was separated
into several maven modules, which is NOT a necessity for applying these architectural patterns and ways of working. The
choice to separate them was to be able to use Maven to switch the domains.

### DDD and Ports & Adapters concepts

You'll notice the Application, Presentation, Infrastructure and multiple Domain modules.

- The Presentation layer is similar to the Infrastructure layer, but it is meant for incoming traffic. Both the
  Presentation and the Infrastructure layers contain Adapters for Ports defined in the Domain(-API).
- The Application layer is used in the Presentation layer for incoming traffic related to use-cases.
- The Infrastructure layer is used in the Application layer for outgoing traffic and Adapters to infrastructural
  concerns (also the framework).

The domain consists of three separate Aggregate Roots:

- Customer
- OrderOverview
- Product

These do have references to each other, but no foreign key constraints. Reasoning: 
- A Customer can be deleted, but their order history must remain for the finance department.
- A Product must not be deleted when an OrderOverview is deleted.

note: the domain-without-any-nonsense module only exists as reference material, or to start an experiment to use some
other persistence layer technology.

### How to run

```bash
./run.sh spring-boot-data-jpa|spring-data-jdbc #choose one
```

