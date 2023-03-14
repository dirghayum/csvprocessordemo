The main ideas behind Java’s Object-Oriented Programming, OOP concepts include abstraction, encapsulation, inheritance and polymorphism. 

Object 
- Every real-world entity is an object. 
- An object has Behaviour (things it does or performs) and Attributes (things that describe it).
- For eg: A Chair object can have behaviour like Movement, Height Adjustment & Attributes like Color, Make & Model, and Price.
	
Class 
- The collection of all related objects is called a class. 
- Consider class as a general category or a blueprint which contains all the related objects inside it. 
- For eg: Objects like Wheelchair, Office Chair and Wooden Chair can be a part of the "Chair" class.

Encapsulation
- It means wrapping data into a single unit & securing it.
- Limiting code visibility and protecting code from random access by using access modifiers.
- Reduce complexity + increase resusability
-  For eg: Drug Capsule wraps different medicines into a single unit and protects them from the outside environment. Bank Locker wraps your valuables into a single unit(locker) and protects it via passcode.

Polymorphism
- It means many forms. With the same name, it provides different forms. 
- Polymorphism is the ability of a single object or function to take on multiple forms. Can be achived from method overloading(compile-time polymorphism) and method overriding(run-time polymorphism.
- Refactors ugly switch case statements.
- For eg: In Chess, we've 6 pieces - king, rook, bishop, queen, knight, and pawn. All of them "move" differently i.e. Bishop moves diagonally, Rooks move horizontally and vertically, etc.

Abstraction
- Hiding complexity from the user and showing only the relative stuff. 
- Reduce complexity + isolate impact of changes
- For Eg: In Car, all the complexity like the engine, machinery, etc is hidden from you; only relevant parts are shown, like the brakes, accelerator, and gearbox.

Inheritance
- The way we inherited a few qualities from our parents similarly, a class can also inherit the qualities from a parent class. 
- Inheritance is the ability of a class to inherit characteristics and behavior from a parent class.
- It eliminates redundant code
- For eg: A Phone Class can have two Child Classes: 1) TelePhone and 2) MobilePhone. Both can inherit the "calling" behaviour.

Pass by reference and pass by value.
-   _call by value_ means that you pass **values** as function arguments
-   _call by reference_ means that you pass **variables** as function arguments

The Scope of Access Modifiers in a class
![[Screen Shot 2022-10-01 at 10.17.19 AM.png]]

Best Practices of OOP
1. DRY (Dont Repeat Yourself)
	- DRY (don't repeat yourself) means don't write duplicate code, instead use Abstraction to abstract common things in one place.

2. Encapsulate what changes
	- If you expect your Java code to change in the future, encapsulate it by making all variables and methods private at the outset. As the code changes, increase access to **“protected”** as needed, but not too **public.**
	
3. Open Closed Design
	- Classes, methods, or functions should be Open for extension (new functionality) and Closed for modification.

4. Single Responsibility Principle
	- A class should always have only one functionality that way, the class can be called and/or extended on its own when new uses arise for it, without causing **coupling** between different functionalities.

5. Dependency Injection or Inversion principle
	- Inversion of control (IoC) is a design principle in which a software system controls the flow of program execution rather than the programmer and dependency injection is one way to implement inversion of control. DI is a software design pattern that allows a programmer to remove hard-coded dependencies and make it possible to change them, whether at run-time or compile-time.


