# Blockchain-Threads

Summary:

The project requires implementing a Java software application without a graphical user interface (GUI) that records products on a custom blockchain. The application is intended for a company to log its products and their prices. Each block of the blockchain should take at least 1 minute to create.

The software will also include a database, specifically for storing the blockchain data. The user will have several functions available, including:

View all products: Displays information about all recorded products, including serial registration number, product code, title, timestamp, price, product description, product category, and previous product record.

Add a product: Allows the user to add a new product to the blockchain, providing the necessary fields such as product details and price. The block for the new product will be generated by the system, and the "previous product record" field will contain the serial registration number of the previous record if available.

Add multiple products: Similar to adding a single product, but allows the user to specify the number of products they want to add and then enter the required fields for each product.

Product search: Enables searching for a product based on one or more criteria. The search results will include information about the product and indicate whether it is the first or last appearance in the blockchain.

View product statistics: Allows the user to select a product and retrieve its received values over time by searching the entire blockchain.

The application should handle time-consuming functions efficiently, and multithreaded programming is encouraged to optimize performance. However, the project does not support data modification operations like delete or update; only insert operations are allowed.

The assignment will consist of three versions, with the first version focusing on the implementation of the application's basic functionalities.
