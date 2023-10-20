# Java-Hotel-Managment-System-Java
--this java project is a hotel managment system created in a main class and the other classes are created as an object respectively in the appropreat jbuttons this code mainly uses the java swing and the java frame to design and add functionalities to each buttons 
--it is connected to a database that the data base is mainly used to store and gather informations for the customer and hotel
--it have 10 jframe classes and 1 main class
--it has a login PAGE AT THE MAIN CLASS that checks whether the user or manager or the employee have access to the database and new users are created in the database



--Database Connection: First, establish a connection to the database using JDBC. You can use the java.sql package to connect to the database, execute queries, and retrieve data. In your Java Swing application, create a DatabaseConnection class that handles the database connection. This class can include methods such as connect() to establish the connection and disconnect() to close the connection.

--Hotel Management Form: Create a HotelManagementForm class that extends JFrame. This form will serve as the main interface for managing hotel information. You can design it with appropriate Swing components like labels, text fields, buttons, and tables to display and manipulate hotel data.

--Hotel Data Access Object (DAO): Create a HotelDAO class that acts as a bridge between the Java code and the database. This class should have methods to perform CRUD operations on the Hotel table. For example, you can have methods like createHotel(), readHotel(), updateHotel(), and deleteHotel() that execute SQL queries to interact with the database.

--Event Listeners: Implement event listeners for the buttons or components in the HotelManagementForm class. For example, when the user clicks the "Add Hotel" button, an event listener can trigger the corresponding method in the HotelDAO class to insert a new hotel record into the database. Similarly, you can handle events for updating or deleting hotel records.

--Data Display: To display hotel data, you can use a table component from Swing, such as JTable. Retrieve the hotel records using methods from the HotelDAO class and populate the table with the data fetched from the database.

--Database Connection Integration: In the HotelDAO class, establish a connection to the database using the DatabaseConnection class. Utilize the connection instance to execute SQL queries and retrieve data from the Hotel table.

--Error Handling: Implement proper error handling and exception management to handle database connectivity issues, query execution errors, and other exceptional scenarios.

--------the database query of the java project-----------

-- Create the Hotel table
CREATE TABLE Hotel (
  hotel_id INT PRIMARY KEY,
  hotel_name VARCHAR(100),
  place VARCHAR(100),
  phone_number VARCHAR(20)
);

-- Create the Room table
CREATE TABLE Room (
  room_id INT PRIMARY KEY,
  hotel_id INT,
  room_type VARCHAR(50),
  price_per_night DECIMAL(10, 2),
  available VARCHAR(20),
  FOREIGN KEY (hotel_id) REFERENCES Hotel(hotel_id)
);

-- Create the Reservable_table table
CREATE TABLE Reservable_table (
  reservable_table_id INT PRIMARY KEY,
  table_number INT,
  floor_number INT
);

-- Create the Guest table
CREATE TABLE Guest (
  guest_id INT PRIMARY KEY,
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  sex VARCHAR(10),
  address VARCHAR(100),
  phone_number VARCHAR(20) UNIQUE
);

-- Create the Reservation table
CREATE TABLE Reservation (
  reservation_id INT PRIMARY KEY,
  guest_id INT,
  room_id INT,
  reservable_table_id INT,
  checkin_date DATE,
  checkout_date DATE,
  _status VARCHAR(100) NOT NULL DEFAULT 'Confirmed',
  special_requirements VARCHAR(100),
  FOREIGN KEY (guest_id) REFERENCES Guest(guest_id),
  FOREIGN KEY (room_id) REFERENCES Room(room_id),
  FOREIGN KEY (reservable_table_id) REFERENCES Reservable_table(reservable_table_id)
);

-- Create the CheckIn table
CREATE TABLE CheckIn (
  checkin_id INT IDENTITY(1,1) PRIMARY KEY,
  reservation_id INT,
  checkin_date DATETIME,
  room_key VARCHAR(50),
  FOREIGN KEY (reservation_id) REFERENCES Reservation(reservation_id)
);

-- Create the BestFoodDrink table
CREATE TABLE BestFoodDrink (
  item_id INT PRIMARY KEY,
  item_name VARCHAR(100),
  price DECIMAL(10, 2)
);

-- Create the Order table
CREATE TABLE O_rder (
  order_id INT PRIMARY KEY,
  guest_id INT,
  item_id INT,
  quantity INT,
  FOREIGN KEY (guest_id) REFERENCES Guest(guest_id),
  FOREIGN KEY (item_id) REFERENCES BestFoodDrink(item_id)
);

-- Create the CheckOut table
CREATE TABLE CheckOut (
  checkout_id INT PRIMARY KEY,
  reservation_id INT,
  order_id INT,
  checkout_date DATETIME,
  total_cost DECIMAL(10, 2),
  payment_status VARCHAR(20),
  checkout_status VARCHAR(20) NOT NULL DEFAULT 'pending',
  FOREIGN KEY (reservation_id) REFERENCES Reservation(reservation_id),
  FOREIGN KEY (order_id) REFERENCES O_rder(order_id)
);
