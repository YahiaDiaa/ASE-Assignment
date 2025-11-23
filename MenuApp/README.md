# 🍽️ Restaurant Ordering & Billing System

A modular and extensible restaurant management system built with Java, demonstrating clean architecture and advanced object-oriented design patterns.

---

## 🎯 Overview

This system simulates a complete restaurant ordering workflow, from menu browsing to payment processing. It adheres to **SOLID design principles** and implements **6 major design patterns** to ensure flexibility, maintainability, and extensibility.

### Key Capabilities:
- 🍔 Browse multiple menu types (Kids, Vegetarian, Non-Vegetarian)
- 🧀 Customize meals with add-ons (Extra Cheese, Sauce)
- 💳 Multiple payment methods (Cash, Card, Wallet)
- 🎟️ Dynamic discount strategies
- 📊 Automated billing with tax calculation
- 👨‍🍳 Real-time kitchen and waiter notifications

---

## ✨ Features

### 1. **Menu Management**
- Three menu types with distinct offerings
- Dynamic main dish selection
- Appetizers and desserts included

### 2. **Order Customization**
- Add extra toppings using the Decorator pattern
- Choose order type (Dine-in, Delivery, Takeaway)
- Interactive item selection

### 3. **Payment Processing**
- Multiple payment strategies
- Secure payment validation
- Real-time payment confirmation

### 4. **Billing System**
- Automatic subtotal calculation
- 14% tax application
- Discount integration
- Professional receipt generation

### 5. **Notifications**
- Kitchen receives order details
- Waiter gets notified
- Observer pattern ensures real-time updates

---

## 🏗️ Design Patterns

This project implements **6 design patterns**:

### 1. **Abstract Factory Pattern** 🏭
**Purpose:** Create families of related menu items without specifying concrete classes.

**Implementation:**
- `MenuFactory` (Abstract Factory)
- `KidsMenu`, `VegMenu`, `NonVeg` (Concrete Factories)

```java
MenuFactory kidsMenu = new KidsMenu(mainDishFactory);
MenuItem appetizer = kidsMenu.createAppetizer();
```

---

### 2. **Factory Method Pattern** 🔨
**Purpose:** Delegate object creation to subclasses.

**Implementation:**
- `MainDishFactory` (Creator)
- `simpleDish` (Concrete Creator)

```java
MainDishFactory factory = new simpleDish();
MenuItem chicken = factory.createDish("chicken");
```

---

### 3. **Decorator Pattern** 🎨
**Purpose:** Dynamically add features to objects without modifying their structure.

**Implementation:**
- `AddOnDecorator` (Base Decorator)
- `ExtraCheese`, `Sauce` (Concrete Decorators)

```java
MenuItem meal = new FriedChicken();
meal = new ExtraCheese(meal);
meal = new Sauce(meal);
// Result: "Fried Chicken + Extra Cheese + Sauce"
```

---

### 4. **Strategy Pattern** 💡
**Purpose:** Define interchangeable algorithms.

**Implementation A - Payment:**
- `PaymentStrategy` interface
- `CashPayment`, `CardPayment`, `WalletPayment`

```java
PaymentStrategy payment = new CardPayment();
payment.pay(25.50);
```

**Implementation B - Discounts:**
- `DiscountStrategy` interface
- `ChickenDiscount`, `PizzaDiscount`

```java
DiscountStrategy discount = new ChickenDiscount();
double newPrice = discount.applyDiscount(10.0);
```

---

### 5. **Observer Pattern** 👀
**Purpose:** Notify multiple objects about state changes.

**Implementation:**
- `OrderSystem` (Subject)
- `Observer` interface
- `Kitchen`, `Waiter` (Concrete Observers)

```java
OrderSystem orderSystem = new OrderSystem();
orderSystem.attach(new Kitchen());
orderSystem.attach(new Waiter());
orderSystem.placeOrder("Order details...");
```

---

### 6. **Facade Pattern** 🎭
**Purpose:** Provide simplified interface to complex subsystems.

**Implementation:**
- `MenuManager` (Facade)

```java
MenuManager manager = new MenuManager(menuFactory);
manager.displayFullMenu("chicken");
MenuItem appetizer = manager.getAppetizer();
```

---

## 🏛️ Architecture

### **Package Structure**

```
MenuApp/src/
│
├── Main.java                          # Entry point with interactive workflow
│
├── menuItems/                         # Menu item components
│   ├── MenuItem.java                  # Interface
│   ├── AbstractMenuItem.java         # Base implementation
│   ├── FriedChicken.java
│   ├── BeefSteak.java
│   ├── Fries.java
│   ├── IceCream.java
│   └── FruitSalad.java
│
├── menuFactory/                       # Abstract Factory Pattern
│   ├── MenuFactory.java              # Abstract Factory
│   ├── KidsMenu.java
│   ├── VegMenu.java
│   └── NonVeg.java
│
├── mainDishFactory/                   # Factory Method Pattern
│   ├── MainDishFactory.java          # Creator
│   └── simpleDish.java               # Concrete Creator
│
├── decorator/                         # Decorator Pattern
│   ├── addOnDecorator.java           # Base Decorator
│   ├── ExtraCheese.java
│   └── Sauce.java
│
├── payment/                           # Strategy Pattern (Payment)
│   ├── PaymentStrategy.java
│   ├── CashPayment.java
│   ├── CardPayment.java
│   └── WalletPayment.java
│
├── Discount/                          # Strategy Pattern (Discounts)
│   ├── DiscountStrategy.java
│   ├── ChickenDiscount.java
│   └── PizzaDiscount.java
│
├── observer/                          # Observer Pattern
│   ├── Observer.java
│   ├── OrderSystem.java
│   ├── Kitchen.java
│   └── Waiter.java
│
├── order/                             # Order Management
│   ├── Order.java
│   ├── OrderCalculator.java
│   └── BillGenerator.java
│
└── manager/                           # Facade Pattern
    └── MenuManager.java
```

---

### Prerequisites
- Java JDK 11 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code)

### Steps

1. **Clone the repository**
```bash
git clone https://github.com/YahiaDiaa/ASE-Assignment.git
cd ASE-Assignment
```

2. **Navigate to source directory**
```bash
cd MenuApp/src
```

3. **Compile the project**
```bash
javac Main.java
```

4. **Run the application**
```bash
java Main
```

---

## 🚀 Usage

### **Running the Application**

When you run the program, you'll see an interactive menu:

```
========================================
       WELCOME TO OUR RESTAURANT       
========================================

=== SELECT ORDER TYPE ===
1. Dine-in
2. Delivery
3. Takeaway
Enter your choice (1-3): 
```

### **User Workflow**

1. **Choose Order Type**
   - Select dine-in, delivery, or takeaway

2. **Select Menu Type**
   - Kids Menu
   - Vegetarian Menu
   - Non-Vegetarian Menu

3. **Build Your Order**
   - Add appetizer (optional)
   - Select main dish (required)
   - Add dessert (optional)

4. **Customize Items**
   - Add extra cheese (+$0.50)
   - Add sauce (+$0.30)

5. **Apply Discounts**
   - Chicken Discount (15% off)
   - Pizza Discount (10% off)

6. **View Bill**
   - See itemized bill with tax and discounts

7. **Pay**
   - Choose payment method (Cash/Card/Wallet)

8. **Order Confirmation**
   - Kitchen and waiter get notified


---

The system uses the following key relationships:
- **Inheritance** (Factory hierarchies)
- **Composition** (Order contains MenuItems)
- **Aggregation** (OrderSystem has Observers)
- **Dependency** (MenuManager uses Order)

---

## 🛠️ Technologies

- **Language:** Java 11+
- **Paradigm:** Object-Oriented Programming
- **Patterns:** 6 Design Patterns
- **Principles:** SOLID
- **Architecture:** Layered Architecture

---

## 🎓 SOLID Principles

###  **S** - Single Responsibility Principle
Each class has one clear responsibility:
- `Order` - manages order items
- `OrderCalculator` - handles calculations
- `BillGenerator` - formats receipts

###  **O** - Open/Closed Principle
System is open for extension, closed for modification:
- Add new menu types without changing `MenuFactory`
- Add new payment methods without modifying existing code
- Add new decorators without touching base classes

###  **L** - Liskov Substitution Principle
All implementations can replace their interfaces:
- Any `PaymentStrategy` implementation works interchangeably
- All `MenuItem` implementations are substitutable
- All `Observer` implementations can replace each other

###  **I** - Interface Segregation Principle
Focused, minimal interfaces:
- `MenuItem` - only 2 methods (getDescription, getCost)
- `PaymentStrategy` - single method (pay)
- `Observer` - single method (update)
- `DiscountStrategy` - single method (applyDiscount)

###  **D** - Dependency Inversion Principle
Depend on abstractions, not concretions:
- `MenuFactory` depends on `MenuItem` interface
- `OrderSystem` depends on `Observer` interface
- `MenuManager` depends on `MenuFactory` abstraction


---

## 📈 Project Statistics

- **Total Classes:** 30+
- **Design Patterns:** 6
- **Interfaces:** 5
- **Abstract Classes:** 3


---


## 👥 Contributors

- **Yahia Diaa** - [@YahiaDiaa](https://github.com/YahiaDiaa)
- **Maurice** - [@Y-mauriceokay](https://github.com/Y-mauriceokay)

**Course:** Advanced Software Engineering 2025  
**Institution:** Cairo University - Faculty of Computers and Artificial Intelligence

---