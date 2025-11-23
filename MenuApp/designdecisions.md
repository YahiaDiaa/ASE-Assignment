# 📐 Design Decisions Document

## Restaurant Ordering & Billing System
---

## 📋 Table of Contents

1. [Overview](#overview)
2. [Why Each Design Pattern](#why-each-design-pattern)
3. [Why SOLID Principles](#why-solid-principles)
4. [Key Design Choices](#key-design-choices)
---

## 1. Overview

This document explains **why** we made specific design decisions in building the Restaurant Ordering & Billing System.

### Main Goals:
-  Easy to add new features
-  Easy to understand and maintain
-  Each part does one thing well
-  Code can be reused

---

## 2. Why Each Design Pattern

### 🏭 Abstract Factory Pattern (MenuFactory)

**Problem:** Different menus need different sets of items (appetizer, main, dessert).

**Solution:** Abstract Factory creates complete menu families.

**Why this pattern:**
-  Keeps menu items consistent (Kids menu = Fries + Chicken + Ice Cream)
-  Easy to add new menu types
-  All menu creation logic in one place

---

### 🔨 Factory Method Pattern (MainDishFactory)

**Problem:** Need flexible way to create different main dishes (chicken, beef).

**Solution:** Let subclasses decide which dish to create.

**Why this pattern:**
- Easy to add new dish types
- Works well with Abstract Factory
- Separates creation logic

---

### 🎨 Decorator Pattern (AddOns)

**Problem:** Customers want to add extras (cheese, sauce) to items.

**Solution:** "Wrap" items with decorators that add features.

**Why this pattern:**
- Add features without changing original classes
- Can combine multiple add-ons
- Price automatically calculated

**Why NOT inheritance:**
- ❌ Would need: ChickenWithCheese, ChickenWithSauce, ChickenWithBoth... (too many classes!)

---

### 💡 Strategy Pattern - Payment

**Problem:** Different payment methods (cash, card, wallet) process differently.

**Solution:** Each payment method is a separate strategy.

**Why this pattern:**
- Easy to add new payment methods
- Change payment method at runtime
- No messy if-else chains


---

### 💡 Strategy Pattern - Discounts

**Problem:** Different discount types with different calculations.

**Solution:** Each discount is a separate strategy.

**Why this pattern:**
- Easy to add promotional discounts
- Discount logic separate from order logic
- Can change discount dynamically


### 👀 Observer Pattern (Notifications)

**Problem:** Kitchen and waiter need to know when orders are placed.

**Solution:** OrderSystem notifies all observers automatically.

**Why this pattern:**
-  OrderSystem doesn't know about Kitchen/Waiter directly
-  Easy to add more observers (Delivery, Inventory)
-  Loose coupling


---

### 🎭 Facade Pattern (MenuManager)

**Problem:** Using factories directly is complex.

**Solution:** MenuManager simplifies access to menu items.

**Why this pattern:**
- Hides complexity
- Provides simple methods
- Easier for clients to use



---

## 3. Why SOLID Principles

### S - Single Responsibility

**Rule:** Each class does ONE thing only.

**Examples:**
- `Order` → Stores items and order type
- `OrderCalculator` → Does math (subtotal, tax, total)
- `BillGenerator` → Formats and prints receipts

**Why:** If billing format changes, only BillGenerator changes. Order stays the same.

---

### O - Open/Closed

**Rule:** Open for extension, closed for modification.

**Examples:**
- Add new menu type? Create new class, don't modify MenuFactory
- Add new payment method? Create new class, don't modify existing code

**Why:** Adding features doesn't break existing code.

---

### L - Liskov Substitution

**Rule:** Can swap implementations without breaking things.

**Examples:**
```java
PaymentStrategy payment = new CashPayment();  // Works
payment = new CardPayment();                   // Still works
payment = new WalletPayment();                 // Still works
```

**Why:** Polymorphism works correctly.

---

### I - Interface Segregation

**Rule:** Keep interfaces small and focused.

**Examples:**
- `MenuItem` → Only 2 methods (getDescription, getCost)
- `Observer` → Only 1 method (update)
- `PaymentStrategy` → Only 1 method (pay)

**Why:** Classes only implement what they need.

---

### D - Dependency Inversion

**Rule:** Depend on interfaces, not concrete classes.

**Examples:**
- MenuManager depends on `MenuFactory` (interface), not `KidsMenu` (concrete)
- OrderSystem depends on `Observer` (interface), not `Kitchen` (concrete)

**Why:** Easy to swap implementations and test with mocks.

---

## 4. Key Design Choices

### Choice 1: Separate OrderCalculator from Order

**Decision:** Create separate OrderCalculator class.

**Why:**
- Order just stores data
- Calculator does math
- Each has one job (SRP)
- Easy to change tax calculation without touching Order

---

### Choice 2: Separate BillGenerator

**Decision:** Create separate BillGenerator for formatting.

**Why:**
- Formatting is different from calculation
- Can easily change receipt format
- Can support multiple formats (PDF, HTML, plain text)

---

### Choice 3: Keep Interfaces Simple

**Decision:** MenuItem only has 2 methods.

**Why:**
- Easy to implement
- Flexible for different item types
- Follows Interface Segregation Principle

---

### Choice 4: Package-Based Organization

**Decision:** Organize by pattern and functionality.

**Structure:**
```
menuFactory/      → Abstract Factory
decorator/        → Decorator Pattern
payment/          → Payment Strategy
Discount/         → Discount Strategy
observer/         → Observer Pattern
order/            → Business Logic
```

**Why:**
- Easy to find related code
- Patterns are clearly visible
- Good separation of concerns

---

### Choice 5: Main.java Handles UI Only

**Decision:** All business logic in separate classes.

**Why:**
- Can add GUI later without changing business logic
- Can add REST API without changing business logic
- Easy to test business logic separately

---

