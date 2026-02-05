

# 🚗 Parking Lot – Low Level Design (LLD)
    This repository is designed to help students understand how Low Level Design (LLD) interviews work, using one of the most frequently asked system design problems:
    Design a Parking Lot System.
    The focus is on object-oriented design, SOLID principles, and clear separation of responsibilities, exactly what interviewers expect in SDE-1 / SDE-2 LLD rounds.

## 📌 Requirement Gathering
    System Constraints
    Command-line application
    SOLID-compliant design
    In-memory storage only
    Future-ready design (can be extended to a SaaS product)
    Single-threaded system
    No concurrency or thread-safety handling
    Pure object-oriented design 
    
## TECH STACK USED : 

    1. Java 17 

## ✅ Functional Requirements
    The system manages a single Parking Lot
    A Parking Lot contains multiple Parking Floors
    Each Parking Floor contains multiple Parking Spots
    Each floor can support multiple vehicle types
    Each floor has multiple Entry and Exit Gates
    Parking Ticket is generated on vehicle entry
    Payment Slip is generated on vehicle exit
    Parking charges are calculated using pluggable pricing strategies
    The design must be extensible without modifying existing code

## 👤 User Journey
    A user enters the parking lot through an Entry Gate
    A Parking Ticket is generated
    The user parks the vehicle in an available Parking Spot
    On exit:
    The ticket is validated
    Parking fee is calculated
    User pays the amount
    A Payment Slip is generated

## 🧩 Core Entities Identified
    Parking Infrastructure
    ParkingLot
    ParkingFloor
    ParkingSpot
    Gate
    EntryGate
    ExitGate
    Vehicle & Parking
    Vehicle
    VehicleType
    ParkingStatus
    Ticketing & Payment
    Ticket
    PaymentSlip
    Pricing & Billing
    FareCalculationStrategy
    FareCalculator
    FareRepository


##  CLASS DIAGRAM : 

### PARKING_LOT : SINGLETON CLASS

    1. List<Parking_Floor>
    2. List<Gates> 
    3. List<Tickets>
    4. List<VehiclesType>
    5. ParkingStatus

### PARKING_FLOOR : CLASS

    1. List<Parking_Spot>
    2. List<Gates>
    3. List<VehicleType>
    4. ParkingStatus 

### PARKING_SPOT : CLASS 

    1. ParkingStatus 
    2. VehicleType



### Vehicle : Class
    
    1. VehicleNumber 
    2. ModelName 
    3. VehicleType 

### Ticket : Class 

    1. TicketId
    2. Vehicle
    3. EntryTime 
    4. ParkingFloor 
    5. FareCalculator

### PaymentSlip : Class 

    1. Ticket
    2. ExitTime
    3. TotalAmount 
    4. PaymentMethod 

### Gate : Interface 

    1. IssueToken 

    Implemented by 
    1. EntryGate
    2. ExitGate 

### VehicleType : Enum 

    1. Car
    2. Bike
    3. Bus 

### ParkingStatus : Enum 

    1. Available 
    2. Unavailable
    3. Occupied 

### FareCalculationStrategy : Interface 

    Behaviour : CalculateFare

    Implemented by classes 

    1. DayFareCalculationStrategy 
    2. WeeklyFareCalculationStrategy 
    
### FareSurcharge :  Interface

    Behaviour : apply(amount, ticket)

    Implemenred by classes 
    1. WeekendFareSurcharge : Singleton + Lazy Initialization
    2. FestivalFareSurcharge : Singleton + Lazy Initialization
    3. ShoppingDiscount : Singleton + Lazy Initialization
### FareCalculator : class

    FareCalculationStrategy 
    List<FareSurcharge>
     
## FareRepository 

    Map<VehicleType, float> vehcleFares

## Design Patterns Used  :

    Strategy	FareCalculationStrategy	Pricing logic
    Observer	SurchargeObservers	Dynamic surcharges
    Factory Method	EntryGate, ExitGate	Object creation
    Template Method	Gate	Gate hierarchy
    Repository	FareRepository	Data abstraction
    Facade	ParkingService	Simplified API

## TOTAL TIME CONSUMED TO DEVELOP : 3hrs although in inteviews, indepth implementation is not expected. 


## 🚀 Future Development (SaaS-Ready Architecture) For Reading

    As the system evolves from a single parking lot to a multi-tenant SaaS platform, the architecture will be extended to support multiple parking lots, high availability, and strong transactional guarantees.
    1️⃣ MongoDB for Parking Lot Configuration (Schema-Flexible Data)

    MongoDB will be used to store:

        ParkingLot metadata
        ParkingFloors
        ParkingSpots
        Gate configurations

    Why MongoDB?

    Parking lot layouts vary significantly:
    Different number of floors
    Different spot types per floor
    Different gate configurations
    These structures are deeply nested and heterogeneous
    MongoDB’s schema flexibility allows each parking lot to have a custom structure without expensive migrations
    Document Modeling Approach
    One document per ParkingLot
    Embedded ParkingFloors and ParkingSpots
    Read-optimized for fast configuration lookups

    📌 Benefit:
    Flexible modeling + faster onboarding of new parking lots.

    2️⃣ SQL Database for Payments & Spot Allocation (ACID Transactions)

        Relational DB (PostgreSQL / MySQL) will be used for:
        Ticket
        Spot allocation
        Payment
        PaymentSlip
        Transaction records
        Why SQL here?
        Payment flow requires:
        Atomicity → payment & spot release must happen together
        Consistency → one spot must belong to only one vehicle
        Isolation → concurrent exits must not double-free a spot
        Durability → completed payments must never be lost
        These guarantees are natively supported by SQL databases via ACID transactions
    📌 Example Transaction

        BEGIN TRANSACTION
        1. Validate ticket
        2. Lock parking spot row
        3. Process payment
        4. Mark spot as AVAILABLE
        5. Generate payment slip
        COMMIT

    3️⃣ Sharding Strategy for Scalability
        Sharding key: ParkingLotId
        Each shard handles:
        Tickets
        Payments
        Spot states
        This ensures:
        Horizontal scalability
        Reduced cross-shard transactions
        Parking lots remain operationally isolated
    📌 Result:
        High throughput with predictable scaling behavior.

Credits to ChatGPT for correct wording and vocabulary in the readMe file. 
